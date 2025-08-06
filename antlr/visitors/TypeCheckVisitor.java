/*
 *
 *  * Alunos:
 *  * - Nome: Lucas Silva Santana      Matrícula: 202165092C
 *  * - Nome: Ricardo Ervilha Silva       Matrícula: 202165561C
 *  *
 *  * Disciplina: DCC045 - Teoria de Compiladores
 *
 */

package visitors;

import ast.*;
import util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TypeCheckVisitor extends Visitor {

    private final STyInt tyint = STyInt.newSTyInt();
    private final STyFloat tyfloat = STyFloat.newSTyFloat();
    private final STyBool tybool = STyBool.newSTyBool();
    private final SType tynull = STyNull.newSTyNull();
    private final SType tychar = STyChar.newSTyChar();
    private final SType tyvoid = STyVoid.newSTyVoid();
    private final STyErr tyerr = STyErr.newSTyErr();

    private ArrayList<String> logError;

    // tabela hash que mapeia nomes para environments local de tipos: nome de uma função e o tipo dela
    // e a definição de tipos local
    private TyEnv<LocalEnv<SType>> env;

    private LocalEnv<SType> temp;// usado para construir o ambiente local de cada função

    private Stack<SType> stk;// usado para calcular os tipos de cada expressão
    private boolean retChk;// variavel para poder verificar se houve retorno de função

    public TypeCheckVisitor() {
        stk = new Stack<SType>();
        env = new TyEnv<LocalEnv<SType>>();
        logError = new ArrayList<String>();
    }

    public int getNumErrors() {
        return logError.size();
    }

    public void printErrors() {
        for (String s : logError) {
            System.out.println(s);
        }
    }

    @Override
    public void visit(Program p) {
        for (Def def : p.getDefinitions()) {
            if (def instanceof Fun f) {
                STyFun paramRetFunc;

                List<Param> params = (f.getParams() != null)
                        ? f.getParams().getParamList()
                        : Collections.emptyList();
                SType[] paramTypes = new SType[params.size()];
                for (int i = 0; i < params.size(); i++) {
                    params.get(i).getType().accept(this);
                    paramTypes[i] = stk.pop();// vai colocando os parametros da função dentro desse vetor
                }

                List<Type> returns = (f.getReturnTypes() != null)
                        ? f.getReturnTypes()
                        : Collections.emptyList();
                SType[] returnTypes = new SType[returns.size()];
                for (int i = 0; i < f.getReturnTypes().size(); i++) {
                    returns.get(i).accept(this);
                    returnTypes[i] = stk.pop();
                }

                paramRetFunc = new STyFun(paramTypes, returnTypes);
                env.set(f.getID(), new LocalEnv<SType>(f.getID(), paramRetFunc));
            } else {
                def.accept(this);//TODO: criar espaço de tipos para tipos criados pelo usuario
            }
        }


        for (Def def : p.getDefinitions()) {
            if (def instanceof Fun f) {
                f.accept(this);
            }
        }

        System.out.println("Imprimindo tabela de tipos...");
        env.printTable();
    }

    @Override
    public void visit(Fun f) {
        retChk = false;//
        temp = env.get(f.getID());// a função já foi criada na minha tabela env

        // add na tabela temp cada um dos parametros da função, com o nome e o tipo do parametro
        List<Param> params = (f.getParams() != null) ? f.getParams().getParamList() : Collections.emptyList();
        for (Param p : params) {
            p.getType().accept(this);
            // p.getId é o nome do parametro, stk.pop é o tipo, eg Int
            // coloca no typeEnv
            temp.set(p.getId(), stk.pop());
        }

        f.getCmd().accept(this);
        //se após tipar o corpo da função a flag continua falsa, tem algum erro, deveria ter um retorno

        if (f.getID() != "main" && !f.getReturnTypes().isEmpty() && !retChk) {
            logError.add(f.getLine() + ", " + f.getCol() + ": Função " + f.getID() + " deve retornar algum valor.");
        }
    }

    /*
        cmd --> ID ‘(’ [exps] ‘)’ [‘<’ lvalue {‘,’ lvalue} ‘>’ ] ‘;’
    */
    @Override
    public void visit(CmdFuncCall e) {
        LocalEnv<SType> sTypeLocalEnv = env.get(e.getId());

        if (sTypeLocalEnv == null) {
            logError.add(e.getLine() + ", " + e.getCol() + ": Chamada à função não declarada: " + e.getId());
            stk.push(tyerr);
            return;
        }

        STyFun tf = (STyFun) sTypeLocalEnv.getFuncType();
        SType[] paramTypes = tf.getParamTypes();
        SType[] returnTypes = tf.getReturnTypes();

        List<Expr> args = (e.getExps() != null) ? e.getExps().getExpressions() : new ArrayList<>();

        if (args.size() != paramTypes.length) {
            logError.add(e.getLine() + ", " + e.getCol() + ": Chamada à função " + e.getId() +
                    " incompatível com número de argumentos.");
            stk.push(tyerr);
            return;
        }

        boolean allArgsOk = true;// para verificar todos os argumentos e não parar no primeiro
        for (int i = 0; i < args.size(); i++) {
            Expr arg = args.get(i);
            arg.accept(this);
            SType argType = stk.pop();

            if (!paramTypes[i].match(argType)) {
                logError.add(arg.getLine() + ", " + arg.getCol() + ": " + (i + 1) +
                        "º argumento incompatível com o parâmetro correspondente da função " + e.getId() +
                        " (esperado: " + paramTypes[i] + ", encontrado: " + argType + ")");
                allArgsOk = false;
            }
        }

        if (allArgsOk) {
            // Se todos os argumentos estiverem corretos, empilha o tipo de retorno
            if (returnTypes.length > 0) {
                stk.push(returnTypes[0]); // TODO: empilhar múltiplos retornos se suportado
            } else {
                stk.push(tyvoid); // ou tyunit, dependendo da linguagem
            }
        } else {
            stk.push(tyerr);
        }
    }

    /*
        exp --> ID ‘(’ [exps] ‘)’ ‘[’ exp ‘]’
    */
    @Override
    public void visit(CallFunctionAccess e) {
        LocalEnv<SType> sTypeLocalEnv = env.get(e.getFunctionName());//pega o ambiente local da função
        if (sTypeLocalEnv != null) {
            STyFun tf = (STyFun) sTypeLocalEnv.getFuncType();

            // verificar se tem a mesma quantidade de parametros da função ao chamar
            // observe que pego apenas os parametros

            if (e.getExps() != null && e.getExps().getExpressions().size() == tf.getParamTypes().length) {
                int k = 0;
                boolean r = true;

                // verificar se o tipo de cada argumento ao chamar uma função casa com o tipo do parametro da função
                for (Expr x : e.getExps().getExpressions()) {
                    x.accept(this);
                    if (!tf.getParamTypes()[k].match(stk.pop())) {
                        logError.add(x.getLine() + ", " + x.getCol() + ": " + (k + 1) +
                                "º argumento incompatível com o respectivo parâmetro de " + e.getFunctionName());
                    }
                    k++;
                }

                // TODO: tipar o acesso ao vetor --> fn()[2]

                // se a chamada de função da certo, o tipo resultante é tipo que ela declara que ela retorna
                // por isso jogo esse tipo para o topo da pilha
                // stk.push(tf.getTypes()[tf.getTypes().length - 1]);//pq?

                if (tf.getReturnTypes().length > 0) {
                    stk.push(tf.getReturnTypes()[0]);//TODO: tratar retorno de varios valores
                }

            } else {
                logError.add(e.getLine() + ", " + e.getCol() +
                        ": Chamada a função " + e.getFunctionName() + " incompatível com argumentos. ");
                stk.push(tyerr);
            }
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Chamada a função não declarada: " + e.getFunctionName());
            stk.push(tyerr);
        }
    }

    @Override
    public void visit(DataDecl p) {

    }

    @Override
    public void visit(AbstractDataDecl p) {

    }

    @Override
    public void visit(Decl p) {

    }

    @Override
    public void visit(FunAbstractData p) {

    }

    @Override
    public void visit(Params e) {

    }

    @Override
    public void visit(ArrayExpr e) {

    }

    @Override
    public void visit(Block b) {
        for (Cmd c : b.getCommands()) {
            c.accept(this);
        }
    }

    private void typeArithmeticBinOp(Node n, String opName) {
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if ((tyr.match(tyint) && tyl.match(tyint)) || (tyr.match(tyfloat) && tyl.match(tyfloat))) {
            stk.push(tyl);
        } else {
            logError.add(n.getLine() + ", " + n.getCol() + ": Operador " + opName + " não se aplica aos tipos " + tyl + " e " + tyr);
            stk.push(tyerr);
        }
    }

    @Override
    public void visit(Add e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e, "+");
    }

    @Override
    public void visit(Sub e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e, "-");
    }

    @Override
    public void visit(Mul e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e, "*");
    }

    @Override
    public void visit(Div e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e, "/");
    }

    /**
     * [Bool,Bool] → Bool
     * @param n
     * @param opName
     */
    private void typeRelationalBinOp(Node n, String opName) {
        SType tyr = stk.pop();
        SType tyl = stk.pop();

        if (tyl.match(tyr) && (tyl.match(tyint) || tyl.match(tyfloat) || tyl.match(tychar))) {
            stk.push(tybool);
        } else {
            logError.add(n.getLine() + ", " + n.getCol() + ": Operador " + opName +
                    " não se aplica aos tipos " + tyl + " e " + tyr);
            stk.push(tyerr);
        }
    }

    /**
     * [a, a] → Bool, em que a ∈ {Int, Float, Char}
     * Os dois operandos devem ter o mesmo tipo
     * @param e
     */
    @Override
    public void visit(Eq e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeRelationalBinOp(e, "==");
    }

    /**
     * [a, a] → Bool, em que a ∈ {Int, Float, Char}
     * Os dois operandos devem ter o mesmo tipo
     * @param e
     */
    @Override
    public void visit(Diff e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeRelationalBinOp(e, "!=");
    }

    /**
     * [a, a] → Bool, em que a ∈ {Int, Float, Char}
     * Os dois operandos devem ter o mesmo tipo
     * @param e
     */
    @Override
    public void visit(Lt e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeRelationalBinOp(e, "<");
    }

    @Override
    public void visit(Mod e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if (tyr.match(tyint) && tyl.match(tyint)) {
            stk.push(tyint);
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Operador % não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
            stk.push(tyerr);
        }
    }

    /**
     * [Bool, Bool] → Bool
     * @param e
     */
    @Override
    public void visit(And e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if (tyr.match(tybool) && tyl.match(tybool)) {
            stk.push(tybool);
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Operador & não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
            stk.push(tyerr);
        }
    }

    /**
     * Bool → Bool
     * @param e
     */
    @Override
    public void visit(NotExpr e) {
        e.getExpression().accept(this);
        SType tyr = stk.pop();
        if (tyr.match(tybool)) {
            stk.push(tybool);
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Operador ! não se aplica ao tipo " + tyr.toString());
            stk.push(tyerr);
        }
    }

    /**
     * a → a, em que a ∈ {Int, Float}
     * @param e
     */
    @Override
    public void visit(MinusExpr e) {
        e.getExpr().accept(this);
        SType tyr = stk.pop();
        if (tyr.match(tybool)) {
            stk.push(tybool);
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Operador ! não se aplica ao tipo " + tyr.toString());
            stk.push(tyerr);
        }
    }

    @Override
    public void visit(LValueExp e) {

    }

    @Override
    public void visit(VarExpr e) {
//        SType t = temp.get(e.getName());
//        if (t != null) {
//            for (Expr x : e.getIdx()) {
//                if (t instanceof STyArr) {
//                    t = ((STyArr) t).getArg();
//                } else {
//                    t = tyerr;
//                }
//            }
//            if (t == tyerr) {
//                logError.add(e.getLine() + ", " + e.getCol() + ": Atribuição de tipos incompatíveis " + e.getName());
//            }
//            stk.push(t);
//        } else {
//            logError.add(e.getLine() + ", " + e.getCol() + ": Variável não declarada " + e.getName());
//            stk.push(tyerr);
//        }
    }

    @Override
    public void visit(CmdReturn r) {
        List<SType> actuals = new ArrayList<>();

        //avalia cada expressão e adiciona no topo da pilha stk
        for (Expr e : r.getExpressions()) {
            e.accept(this);
            actuals.add(stk.pop());
        }

        if (temp.getFuncType() instanceof STyFun) {
            SType[] tiposRetorno = ((STyFun) temp.getFuncType()).getReturnTypes();//pega os retornos
            if (tiposRetorno == null) {
                logError.add("Return fora de função.");
                return;
            }

            if (tiposRetorno.length != actuals.size()) {
                logError.add(String.format("Número de valores retornados (%d) diferente do esperado (%d).",
                        actuals.size(), tiposRetorno.length));
                return;
            }

            for (int i = 0; i < actuals.size(); i++) {
                if (!actuals.get(i).match(tiposRetorno[i])) {
                    logError.add(String.format("Tipo de retorno na posição %d é %s, mas esperava %s.",
                            i, actuals.get(i), tiposRetorno[i]));
                }
            }
        } else {
            stk.pop().match(temp.getFuncType());//faz sentido?
        }
        retChk = true;
    }

    /*
        cmd --> lvalue ‘=’ exp ‘;’
        lvalue --> ID | lvalue ‘[’ exp ‘]’ | lvalue ‘.’ ID
        ou seja, posso fazer atribuição em variavel, vetor ou atributo de um registro
    */
    @Override
    public void visit(CmdAssign p) {
        if (p.getLvalue() instanceof ID) {
            String nameVar = ((ID) p.getLvalue()).getName();
            //variavel ainda não foi declarada
            if (temp.get(nameVar) == null) {
                p.getExpression().accept(this); // esse accept vai empilhar o tipo no stk
                temp.set(nameVar, stk.pop());
            } else {//já esta declarada
                p.getLvalue().accept(this);
                p.getExpression().accept(this);

                SType tyExpression = stk.pop();
                SType tyLvalue = stk.pop();

                if (!tyLvalue.match(tyExpression)) {
                    logError.add(p.getLine() + ", " + p.getCol() +
                            ": Atribuição ilegal para a variável " + nameVar +
                            ". Esperava um " + tyLvalue.toString() + " mas encontrou " + tyExpression.toString() + ".");
                }
            }
        }
    }

    @Override
    public void visit(CmdIf e) {
        boolean rt, re;
        re = true;
        e.getCondition().accept(this);
        if (stk.pop().match(tybool)) {
            retChk = false;
            e.getThenCmd().accept(this);
            rt = retChk;
            if (e.getElseCmd() != null) {
                retChk = false;
                e.getElseCmd().accept(this);
                re = retChk;
            }
            retChk = rt && re;
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Expressão de teste do IF deve ter tipo Bool");
        }
    }

    /*
        cmd --> iterate ‘(’ itcond ‘)’ cmd
     */
    @Override
    public void visit(CmdIterate e) {

        if (e.getCondition() instanceof ExpItCond expItCond) { // exp
            expItCond.getExpression().accept(this);
            SType tyExpression = stk.pop();
            if (tyExpression.match(tyint)) {//TODO: pode ser int ou tipo arranjo (vetor)
                e.getBody().accept(this);
            } else {
                logError.add(e.getLine() + ", " + e.getCol() + ": Expressão de teste do Iterate deve ter tipo Int");
            }

        } else if (e.getCondition() instanceof IdItCond idItCond) {// ID ‘:’ exp iterate(v:e){
            /*
                Na segunda forma, quando e tem tipo Int, verificamos que v não está no contexto ou está com tipo Int.
                Caso e seja um vetor, a regra é similar, porém v deve ter o mesmo tipo dos elementos do vetor.
             */

            idItCond.getExpression().accept(this);
            SType tyExpression = stk.pop();// é o exp do iterate
            if (tyExpression.match(tyint)) {
                String v = idItCond.getId();
                SType typeV = temp.get(v);
                if (typeV == null) {// ou seja, o v ainda não esta no contexto
                    temp.set(v, tyint);// seto o v com o tipo int
                } else if (!typeV.match(tyint)) {
                    logError.add(e.getLine() + ", " + e.getCol() + ": O tipo de " + v + " deveria ser Int mas foi encontrado " + typeV);
                }
                e.getBody().accept(this);
            }


        }
    }

    @Override
    public void visit(CmdPrint e) {
        e.getExpression().accept(this);
        SType t = stk.pop();

        if (!(t.match(tyint) || t.match(tyfloat) || t.match(tybool) || t.match(tychar))) {
            logError.add(e.getLine() + ", " + e.getCol() +
                    ": Tipo " + t + " não pode ser impresso com 'print'.");
            stk.push(tyerr);
        }
    }

    @Override
    public void visit(CmdRead p) {

    }

    @Override
    public void visit(TypeInt t) {
        stk.push(tyint);
    }

    @Override
    public void visit(TypeFloat t) {
        stk.push(tyfloat);
    }

    @Override
    public void visit(TypeBool t) {
        stk.push(tybool);
    }

    @Override
    public void visit(TypeChar e) {

    }

    @Override
    public void visit(ArrayType t) {
//        t.getTyArg().accept(this);
//        stk.push(new STyArr(stk.pop()));
    }

    /*
        itcond --> ID ‘:’ exp | exp
    */
    @Override
    public void visit(ExpItCond e) {

    }

    @Override
    public void visit(Exps e) {

    }

    @Override
    public void visit(ArrayLValue e) {

    }

    @Override
    public void visit(ID e) {
        SType t = temp.get(e.getName());
        if (t != null) {
            stk.push(t);
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Variável não declarada " + e.getName());
            stk.push(tyerr);
        }
    }

    @Override
    public void visit(TYID e) {

    }

    @Override
    public void visit(FieldLValue e) {

    }

    @Override
    public void visit(IdItCond e) {

    }

    @Override
    public void visit(IdLValue e) {
        System.out.println("chegou no IdLValue");

    }

    @Override
    public void visit(TrueValue e) {
        stk.push(tybool);
    }

    @Override
    public void visit(FalseValue e) {
        stk.push(tybool);
    }

    @Override
    public void visit(IntValue e) {
        stk.push(tyint);
    }

    @Override
    public void visit(CharValue e) {
        stk.push(tychar);
    }

    @Override
    public void visit(NullValue e) {
        stk.push(tynull);
    }

    @Override
    public void visit(FloatValue e) {
        stk.push(tyfloat);
    }
}
