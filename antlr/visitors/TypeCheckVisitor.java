/*
 *
 *  * Alunos:
 *  * - Nome: Lucas Silva Santana      Matrícula: 202165092C
 *  * - Nome: Ricardo Ervilha Silva       Matrícula: 202165561C
 *  *
 *  * Disciplina: DCC045 - Teoria de Compiladores
 *
 *
 *
 */

package visitors;

import ast.*;
import util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TypeCheckVisitor extends Visitor {

    private STyInt tyint = STyInt.newSTyInt();
    private STyFloat tyfloat = STyFloat.newSTyFloat();
    private STyBool tybool = STyBool.newSTyBool();
    private STyErr tyerr = STyErr.newSTyErr();


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

    public void visit(Program p) {
        for (Def def : p.getDefinitions()) {
            if (def instanceof Fun f) {
                STyFun paramRetFunc;

                SType[] paramTypes = new SType[f.getParams().getParamList().size()];
                for (int i = 0; i < f.getParams().getParamList().size(); i++) {
                    f.getParams().getParamList().get(i).getType().accept(this);
                    paramTypes[i] = stk.pop();// vai colocando os parametros da função dentro desse vetor
                }

                SType[] returnTypes = new SType[f.getReturnTypes().size()];
                for (int i = 0; i < f.getReturnTypes().size(); i++) {
                    f.getReturnTypes().get(i).accept(this);
                    returnTypes[i] = stk.pop();
                }

                paramRetFunc = new STyFun(paramTypes, returnTypes);
                env.set(f.getID(), new LocalEnv<SType>(f.getID(), paramRetFunc));
            }
        }

        for (Def def : p.getDefinitions()) {
            if (def instanceof Fun f) {
                f.accept(this);
            }
        }
        //env.printTable();
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

    }

    @Override
    public void visit(CmdAssign p) {

    }

    @Override
    public void visit(CmdFuncCall p) {

    }

    private void typeArithmeticBinOp(Node n, String opName) {
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if ((tyr.match(tyint))) {
            if (tyl.match(tyint) || tyl.match(tyfloat)) {
                stk.push(tyl);
            } else {
                logError.add(n.getLine() + ", " + n.getCol() + ": Operador" + opName + "não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
                stk.push(tyerr);
            }

        } else if (tyr.match(tyfloat)) {
            if (tyl.match(tyint) || tyl.match(tyfloat)) {
                stk.push(tyl);
            } else {
                logError.add(n.getLine() + ", " + n.getCol() + ": Operador " + opName + " não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
                stk.push(tyerr);
            }
        } else {
            logError.add(n.getLine() + ", " + n.getCol() + ": Operador " + opName + " não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
            stk.push(tyerr);
        }
    }

    public void visit(Add e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e, "+");

    }

    public void visit(Sub e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e, "-");
    }

    public void visit(Mul e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e, "*");
    }

    public void visit(Div e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e, "/");
    }

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

    public void visit(Lt e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if ((tyr.match(tyint) || tyr.match(tyfloat)) && (tyl.match(tyint) || tyr.match(tyfloat))) {
            stk.push(tybool);
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Operador < não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
            stk.push(tyerr);
        }
    }

    public void visit(Eq e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if ((tyr.match(tyint) || tyr.match(tyfloat)) && (tyl.match(tyint) || tyr.match(tyfloat))) {
            stk.push(tybool);
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Operador = não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
            stk.push(tyerr);
        }
    }

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

    public void visit(TrueValue e) {
        stk.push(tybool);
    }

    public void visit(FalseValue e) {
        stk.push(tybool);
    }

    public void visit(IntValue e) {
        stk.push(tyint);
    }

    public void visit(FloatValue e) {
        stk.push(tyfloat);
    }

    public void visit(VarExpr e) {
        SType t = temp.get(e.getName());
        if (t != null) {
            for (Expr x : e.getIdx()) {
                if (t instanceof STyArr) {
                    t = ((STyArr) t).getArg();
                } else {
                    t = tyerr;
                }
            }
            if (t == tyerr) {
                logError.add(e.getLine() + ", " + e.getCol() + ": Atribuição de tipos incompatíveis " + e.getName());
            }
            stk.push(t);
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Variável não declarada " + e.getName());
            stk.push(tyerr);
        }
    }

    //  ID '(' (exps)? ')' '[' exp ']'
    //  fn()[2];
    public void visit(CallFunctionAccess e) {
        LocalEnv<SType> le = env.get(e.getFunctionName());
        if (le != null) {
            STyFun tf = (STyFun) le.getFuncType();

            // verificar se tem a mesma quantidade de parametros da função ao chamar
            // observe que pego apenas os parametros
            if (e.getExps().getExpressions().size() == tf.getParamTypes().length) {
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

                stk.push(tf.getReturnTypes()[0]);//pq?
            } else {
                logError.add(e.getLine() + ", " + e.getCol() + ": Chamada de função a função " + e.getFunctionName() + " incompatível com argumentos. ");
                stk.push(tyerr);
            }
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Chamada a função não declarada: " + e.getFunctionName());
            stk.push(tyerr);
        }

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

    public void visit(CmdAssign p) {
        if(p.getLvalue() instanceof ID){
            String nameVar = ((ID) p.getLvalue()).getName();
            p.getExpression().accept(this);
            env.peek().put(nameVar, operands.pop());

        }
        if (temp.get(e.getID().getName()) == null && (e.getID().getIdx() == null || e.getID().getIdx().length == 0)) {
            e.getExp().accept(this);
            temp.set(e.getID().getName(), stk.pop());
        } else {
            e.getID().accept(this);
            e.getExp().accept(this);
            if (!stk.pop().match(stk.pop())) {
                logError.add(e.getLine() + ", " + e.getCol() + ": Atribuição ilegal para a variável " + e.getID());
            }
        }
    }

    public void visit(CmdIf e) {
        boolean rt, re;
        re = true;
        e.getTeste().accept(this);
        if (stk.pop().match(tybool)) {
            retChk = false;
            e.getThen().accept(this);
            rt = retChk;
            if (e.getElse() != null) {
                retChk = false;
                e.getElse().accept(this);
                re = retChk;
            }
            retChk = rt && re;
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Expressão de teste do IF deve ter tipo Bool");
        }
    }

    public void visit(CmdIterate e) {
        e.getTeste().accept(this);
        if (stk.pop().match(tybool)) {
            e.getBody().accept(this);
        } else {
            logError.add(e.getLine() + ", " + e.getCol() + ": Expressão de teste do IF deve ter tipo Bool");
        }
    }

    public void visit(CmdPrint e) {
        e.getExpr().accept(this);
        stk.pop();
    }


    public void visit(Fun f) {
        retChk = false;
        temp = env.get(f.getID());// a função já foi criada na minha tabela env

        // add na tabela temp cada um dos parametros da função, com o nome e o tipo do parametro
        for (Param p : f.getParams().getParamList()) {
            p.getType().accept(this);
            temp.set(p.getId(), stk.pop());
        }

        f.getCmd().accept(this);
        //se após tipar o corpo da função a flag continua falsa, tem algum erro, deveria ter um retorno
        if (!retChk) {
            logError.add(f.getLine() + ", " + f.getCol() + ": Função " + f.getID() + " deve retornar algum valor.");
        }
    }


    public void visit(Param e) {
    }

    public void visit(TypeInt t) {
        stk.push(tyint);
    }

    public void visit(TypeFloat t) {
        stk.push(tyfloat);
    }

    public void visit(TypeBool t) {
        stk.push(tybool);
    }

    public void visit(ArrayType t) {
        t.getTyArg().accept(this);
        stk.push(new STyArr(stk.pop()));
    }

    @Override
    public void visit(Diff e) {

    }

    @Override
    public void visit(ExpItCond e) {

    }

    @Override
    public void visit(MinusExpr e) {

    }

    @Override
    public void visit(Exps e) {

    }

    @Override
    public void visit(ArrayLValue e) {

    }



    @Override
    public void visit(ID e) {

    }

    @Override
    public void visit(TYID e) {

    }


    @Override
    public void visit(CharValue e) {

    }

    @Override
    public void visit(FieldLValue e) {

    }

    @Override
    public void visit(IdItCond e) {

    }

    @Override
    public void visit(IdLValue e) {

    }

    @Override
    public void visit(NullValue e) {

    }
}
