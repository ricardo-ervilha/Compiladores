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

import java.util.*;

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
    // só é adicionado funçãoes dentro do visit do Program, depois só busca dentro do env
    private TyEnv<LocalEnv<SType>> env;

    private LocalEnv<SType> temp;// usado para construir o ambiente local de cada função

    private Stack<SType> stk;// usado para calcular os tipos de cada expressão
    private boolean retChk;// variavel para poder verificar se houve retorno de função


    /**
     * TODO: mudar aqui para fazer um mapeamento nome do Registro --> StyData
     *  StyData vai ter os campos com os tipos e as funções também com os argumentos --> tipo e retorno --> tipo
     * Chave (String): nome do Registro --> <nome campo, tipo do campo>
     * Valor (SType): nome do campo --> tipo do campo
     */
    private HashMap<String, HashMap<String, SType>> typeStructs = new HashMap<>();

    private Set<String> abstractTypes = new HashSet<>();

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
    public void visit(Program program) {
        /*
            getDefinitions() pode ser Data, AbstractData ou Fun
            Fun: adiciono no env com os tipos de retorno e argumentos
            Data: visito o DataDecl, considerando que esse visitor só é chamado na declaração
            AbstractData: adiciono no env as funções do tipo abstrato e os tipo do registro no typeStructs
         */
        for (Def def : program.getDefinitions()) {
            if (def instanceof Fun f) {
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

                STyFun paramRetFunc = new STyFun(paramTypes, returnTypes);
                //TODO: verificar se a função com o mesmo nome já não foi adicionada
                env.set(f.getID(), new LocalEnv<SType>(f.getID(), paramRetFunc));
            } else if (def instanceof AbstractDataDecl abstractDataDecl) {
//                abstractDataDecl.accept(this); caso for fazer isso no visit do ABS

                String typeNameAbs = abstractDataDecl.getTypeId(); // nome do tipo, e.g "Racional"
                LinkedHashMap<String, SType> atributosAbs = new LinkedHashMap<>();
                for (Node declFun: abstractDataDecl.getDeclFuns()){// pega declarações ( idade::Int ) e funções
                    if (declFun instanceof Decl decl){
                        decl.getType().accept(this);
                        atributosAbs.put(decl.getId(), stk.pop());

                    }else if(declFun instanceof FunAbstractData funAbstractData){
                        // pego os parametros da função
                        List<Param> params = (funAbstractData.getParams() != null)
                                ? funAbstractData.getParams().getParamList()
                                : Collections.emptyList();

                        // crio uma lista do mesmo tamanho do numero de parametros
                        SType[] paramTypes = new SType[params.size()];
                        for (int i = 0; i < params.size(); i++) {
                            params.get(i).getType().accept(this);
                            paramTypes[i] = stk.pop();// vai colocando os parametros da função dentro desse vetor
                        }

                        List<Type> returns = (funAbstractData.getReturnTypes() != null)
                                ? funAbstractData.getReturnTypes()
                                : Collections.emptyList();
                        SType[] returnTypes = new SType[returns.size()];
                        for (int i = 0; i < funAbstractData.getReturnTypes().size(); i++) {
                            returns.get(i).accept(this);
                            returnTypes[i] = stk.pop();
                        }

                        STyFun paramRetFunc = new STyFun(paramTypes, returnTypes);
                        // salvo no env, o nome da função e os tipos dos parametros/retornos
                        //TODO: acho que seria indicado salvar as funções relacionados a um tipo em alguma estrutura
                        env.set(funAbstractData.getID(), new LocalEnv<SType>(funAbstractData.getID(), paramRetFunc));
                    }
                }

                if (typeStructs.containsKey(typeNameAbs)) {
                    logError.add(abstractDataDecl.getLine() + ", " + abstractDataDecl.getCol() + ": Tipo " + typeNameAbs + " já foi declarado.");
                } else {
                    typeStructs.put(typeNameAbs, atributosAbs);// coloco o nome do tipo e os campos dele na tabela de Registros
                    abstractTypes.add(typeNameAbs); // salvo para saber os tipos que são ABS
                }
            } else if (def instanceof DataDecl dataDecl){
                dataDecl.accept(this);
            }
        }

        // Nesse ponto tenho todas as funções e tipos do usuario do programa


        for (Def def : program.getDefinitions()) {
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

    /**
     * data TYID ‘{’ {decl} ‘}’
     *
     * @param p
     */
    @Override
    public void visit(DataDecl p) {
        String typeName = p.getTypeId(); // nome do tipo, e.g "Racional"
        LinkedHashMap<String, SType> fields = new LinkedHashMap<>();
        for (Node d : p.getDeclarations()) {
            Decl decl = (Decl) d;
            decl.getType().accept(this); // empilha tipo
            SType fieldType = stk.pop();
            fields.put(decl.getId(), fieldType);
        }

        if (typeStructs.containsKey(typeName)) {
            logError.add(p.getLine() + ", " + p.getCol() + ": Tipo " + typeName + " já foi declarado.");
        } else {
            typeStructs.put(typeName, fields);
        }
    }

    @Override
    public void visit(AbstractDataDecl p) {
        System.out.println("(AbstractDataDecl) Não deveria entrar aqui...");
    }

    @Override
    public void visit(FunAbstractData p) {
        System.out.println(" (FunAbstractData) Não deveria entrar aqui...");
    }

    @Override
    public void visit(Decl p) {

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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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

    /**
     * lvalue --> lvalue ‘[’ exp ‘]’
     *
     * @param e
     */
    @Override
    public void visit(LValueExp e) {

    }

    /**
     * Não entra array aqui, array entra no ArrayExpr
     * exp --> new type
     * type --> Int | Char | Bool | Float | TYID
     *
     * @param e
     */
    @Override
    public void visit(VarExpr e) {
        e.getType().accept(this);

        SType type = stk.pop();
        if (type instanceof STyData) {//Tratamento de tipos criados pelo usuario
            TYID tyid = (TYID) e.getType();
            if (!typeStructs.containsKey(tyid.getName())) {
                logError.add(e.getLine() + ", " + e.getCol() + ": Tipo " + tyid.getName() + " não declarado.");
                stk.push(tyerr);
            } else {
                stk.push(new STyData(tyid.getName()));
            }
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

    /*
        cmd --> lvalue ‘=’ exp ‘;’
        lvalue --> ID  | lvalue ‘.’ ID
        ou seja, posso fazer atribuição em variavel ou atributo de um registro
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
        } else if (p.getLvalue() instanceof IdLValue) {
            p.getLvalue().accept(this);
            p.getExpression().accept(this);

            SType tyExpression = stk.pop();
            SType tyLvalue = stk.pop();

            if (!tyLvalue.match(tyExpression)) {
                logError.add(p.getLine() + ", " + p.getCol() +
                        ": Atribuição ilegal para o atributo " + ((IdLValue) p.getLvalue()).getId() +
                        ". Esperava um " + tyLvalue.toString() + " mas encontrou " + tyExpression.toString() + ".");
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
                    ": Tipo " + t + " não pode ser impresso com 'print'. Comando print aceita apenas: Int, Char, Bool, Float.");
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

    /**
     * Nome do tipo definido pelo usuario
     * btype →  TYID
     *
     * @param e
     */
    @Override
    public void visit(TYID e) {
        stk.push(new STyData(e.getName()));
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
    public void visit(IdItCond e) {

    }

    /**
     * lvalue --> lvalue ‘.’ ID
     *
     * @param e
     */
    @Override
    public void visit(IdLValue e) {
        e.getLvalue().accept(this);
        SType baseType = stk.pop();

        // Primeiro certifico que é um tipo Data
        if (!(baseType instanceof STyData userType)) {
            logError.add(e.getLine() + ", " + e.getCol() + ": Tentativa de acesso a campo em tipo que não é Data: ");
            stk.push(tyerr);
            return;
        }

        String atributo = e.getId();
        String dataName = userType.getName();

        // Verifico que o ambiente de tipos Data contem o Data com o nome dataName
        if (!typeStructs.containsKey(dataName)) {
            logError.add(e.getLine() + ", " + e.getCol() + ": Tipo de registro não definido: " + dataName);
            stk.push(tyerr);
            return;
        }

        // Verifico se é um tipo abstrato
        if (abstractTypes.contains(dataName)) {
            // Só permite acesso direto se a função atual for uma das definidas no mesmo tipo
            // TODO: verificar se o Tipo tem a função atual, para isso no StyData poderio ter o nome
            //  dos campos e as funções dele e com isso verifico se a função atual está nas funções do tipo
            //
            if (!dataName.equals(temp.getFuncID())) {
                logError.add(e.getLine() + ", " + e.getCol() +
                        ": Acesso ilegal ao campo " + atributo + " do tipo abstrato " + dataName +
                        " fora do escopo do tipo.");
                stk.push(tyerr);
                return;
            }
        }

        // Se tem o dataName, pego o mapeamento de nomes e tipos
        Map<String, SType> atributos = typeStructs.get(dataName);

        // E verifico se tem o atributo que estou acessando
        if (!atributos.containsKey(atributo)) {
            logError.add(e.getLine() + ", " + e.getCol() + ": Campo " + atributo + " não existe no tipo " + dataName);
            stk.push(tyerr);
            return;
        }

        // Coloco na pilha o tipo do campo acessado
        stk.push(atributos.get(atributo));
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
