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

    private Stack<SType> stk;// pilha de tipos, usado para calcular os tipos de cada expressão
    private boolean retChk;// variavel para poder verificar se houve retorno de função


    /**
     * Chave (String): nome do Registro --> <STyData>
     * Valor (STyData): tem nome do tipo, atributos e funcoes do tipo abstrato
     */
    private HashMap<String, STyData> typeStructs = new HashMap<>();

    private Set<String> abstractTypes = new HashSet<>();//útil para fazer verificação de acesso apenas se for em um tipo ABS

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
                if(env.containsKey(f.getID())){
                    logError.add(f.getLine() + ", " + f.getCol() +
                            ": Função " + f.getID() + " duplicada.");
                    return;
                }
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

                env.set(f.getID(), new LocalEnv<SType>(f.getID(), paramRetFunc));


            } else if (def instanceof AbstractDataDecl abstractDataDecl) {
                String typeNameAbs = abstractDataDecl.getTypeId(); // nome do tipo, e.g "Racional"

                LinkedHashMap<String, SType> atributosAbs = new LinkedHashMap<>();// atributos do tipo Abstrato
                LinkedHashMap<String, STyFun> funcsAbs = new LinkedHashMap<>(); // Funções do tipo abstrato, juntamente com argumentos/retorno

                for (Node declFun : abstractDataDecl.getDeclFuns()) {// pega declarações ( idade::Int ) e funções
                    if (declFun instanceof Decl decl) {
                        decl.getType().accept(this);
                        atributosAbs.put(decl.getId(), stk.pop());

                    } else if (declFun instanceof FunAbstractData f) {
                        if(env.containsKey(f.getID())){
                            logError.add(f.getLine() + ", " + f.getCol() +
                                    ": Função " + f.getID() + " duplicada.");
                            return;
                        }
                        // pego os parametros da função
                        List<Param> params = (f.getParams() != null)
                                ? f.getParams().getParamList()
                                : Collections.emptyList();

                        // crio uma lista do mesmo tamanho do numero de parametros
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
                        // salvo no env, o nome da função e os tipos dos parametros/retornos
                        env.set(f.getID(), new LocalEnv<SType>(f.getID(), paramRetFunc));
                        funcsAbs.put(f.getID(), paramRetFunc);
                    }
                }

                if (typeStructs.containsKey(typeNameAbs)) {
                    logError.add(abstractDataDecl.getLine() + ", " + abstractDataDecl.getCol() + ": Tipo " + typeNameAbs + " já foi declarado.");
                } else {
                    STyData sTyData = new STyData(typeNameAbs, atributosAbs, funcsAbs);
                    typeStructs.put(typeNameAbs, sTyData);// coloco o nome do tipo e os campos dele na tabela de Registros

                    abstractTypes.add(typeNameAbs); // salvo para saber os tipos que são ABS
                }
            } else if (def instanceof DataDecl dataDecl) {
                dataDecl.accept(this);
            }
        }

        // Nesse ponto tenho todas as funções e tipos do usuario do programa


        if(!env.containsKey("main")){
            logError.add(program.getLine() + ", " + program.getCol() + ": Função main não foi declarada.");
            return;
        }

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
        temp = env.get(f.getID());// a função já foi criada na minha tabela env previamente

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
        if (!f.getReturnTypes().isEmpty() && !retChk) {
            logError.add(f.getLine() + ", " + f.getCol() + ": Comando de retorno nao encontrado na função " + f.getID() + " para todos os caminhos.");
        }
    }

    /*
        cmd --> ID ‘(’ [exps] ‘)’ [‘<’ lvalue {‘,’ lvalue} ‘>’ ] ‘;’
        divmod (5 ,2) <q , r >;
    */
    @Override
    public void visit(CmdFuncCall e) {
        // Verifica se a função existe no ambiente global
        LocalEnv<SType> sTypeLocalEnv = env.get(e.getId());
        if (sTypeLocalEnv == null) {
            logError.add(e.getLine() + ", " + e.getCol() + ": Chamada à função não declarada: " + e.getId());
            return;
        }

        // Se a função existe, pego a assinatura dela (args, rets)
        if (!(sTypeLocalEnv.getFuncType() instanceof STyFun tf)) {
            logError.add(e.getLine() + ", " + e.getCol() + ": " + e.getId() + " não é uma função.");
            return;
        }

        SType[] paramTypes = tf.getParamTypes();
        SType[] returnTypes = tf.getReturnTypes();

        // Validação dos args
        List<Expr> args = (e.getExps() != null) ? e.getExps().getExpressions() : new ArrayList<>();

        if (args.size() != paramTypes.length) {
            logError.add(e.getLine() + ", " + e.getCol() + ": Chamada à função " + e.getId() +
                    " incompatível com número de argumentos (esperado: " + paramTypes.length + ", encontrado: " + args.size() + ").");
        } else {
            for (int i = 0; i < args.size(); i++) {
                Expr arg = args.get(i);
                arg.accept(this);
                SType argType = stk.pop();

                if (!paramTypes[i].match(argType)) {
                    logError.add(arg.getLine() + ", " + arg.getCol() + ": " + (i + 1) +
                            "º argumento incompatível para função " + e.getId() +
                            " (esperado: " + paramTypes[i] + ", encontrado: " + argType + ")");
                }
            }
        }

        // Validação dos retornos
        List<LValue> lValues = (e.getLvalues() != null) ? e.getLvalues() : new ArrayList<>();

        if (lValues.size() > returnTypes.length) {
            logError.add(e.getLine() + ", " + e.getCol() + ": Chamada à função " + e.getId() +
                    " incompatível com número de retornos " +
                    "(definição da função tem : " + returnTypes.length + ", chamada tem: " + lValues.size() + ").");
        } else {
            for (int i = 0; i < lValues.size(); i++) {
                LValue lv = lValues.get(i);

                // variavel simples
                if (lv instanceof ID id) {
                    String nameVar = id.getName();
                    SType retType = returnTypes[i];

                    if (temp.get(nameVar) == null) {
                        // Variável não declarada então coloco no ambiente com tipo do retorno da def da função
                        temp.set(nameVar, retType);
                    } else {
                        // Variável já declarada, valido o tipo
                        SType sTypeVar = temp.get(nameVar);
                        if (!sTypeVar.match(retType)) {
                            logError.add(e.getLine() + ", " + e.getCol() +
                                    ": Atribuição ilegal ao retorno " + nameVar +
                                    " (esperado: " + sTypeVar + ", encontrado: " + retType + ")");
                        }
                    }
                } else {//REgistros ou array

                    lv.accept(this);
                    SType lvType = stk.pop();
                    SType retType = returnTypes[i];
                    if (!lvType.match(retType)) {
                        logError.add(e.getLine() + ", " + e.getCol() +
                                ": Tipo incompatível no retorno da função " + e.getId() +
                                " (tipo retorno: " + retType + " e tipo encontrado: " + lvType + ")");
                    }
                }
            }
        }
    }

    private Integer tryEvaluateIntExpr(Expr expr) {
        if (expr instanceof IntValue iv) {
            return Integer.parseInt(iv.getValue());
        } else if (expr instanceof Add add) {
            Integer l = tryEvaluateIntExpr(add.getLeft());
            Integer r = tryEvaluateIntExpr(add.getRight());
            return (l != null && r != null) ? l + r : null;
        } else if (expr instanceof Sub sub) {
            Integer l = tryEvaluateIntExpr(sub.getLeft());
            Integer r = tryEvaluateIntExpr(sub.getRight());
            return (l != null && r != null) ? l - r : null;
        } else if (expr instanceof Mul mul) {
            Integer l = tryEvaluateIntExpr(mul.getLeft());
            Integer r = tryEvaluateIntExpr(mul.getRight());
            return (l != null && r != null) ? l * r : null;
        } else if (expr instanceof Div div) {
            Integer l = tryEvaluateIntExpr(div.getLeft());
            Integer r = tryEvaluateIntExpr(div.getRight());
            return (l != null && r != null && r != 0) ? l / r : null;
        } else if (expr instanceof Mod mod) {
            Integer l = tryEvaluateIntExpr(mod.getLeft());
            Integer r = tryEvaluateIntExpr(mod.getRight());
            return (l != null && r != null && r != 0) ? l % r : null;
        }
        return null;
    }

    /*
        exp --> ID ‘(’ [exps] ‘)’ ‘[’ exp ‘]’
        v = soma(2,4)[(1+1)*((3-3)+4)];
        Sempre vai tentar acessar retorno
    */
    @Override
    public void visit(CallFunctionAccess e) {
        LocalEnv<SType> sTypeLocalEnv = env.get(e.getFunctionName());//pega o ambiente local da função

        if (sTypeLocalEnv == null) {
            logError.add(e.getLine() + ", " + e.getCol() + ": Chamada a função não declarada: " + e.getFunctionName());
            stk.push(tyerr);
            return;
        }

        // pega o tipo da função que esta armazenado (args, rets)
        STyFun tf = (STyFun) sTypeLocalEnv.getFuncType();

        // verificar se tem a mesma quantidade de parametros da função ao chamar
        // observe que pego apenas os parametros
        if (
            // chamei a função passando parametros e a função tem tamanho diferente
                (e.getExps() != null && e.getExps().getExpressions().size() != tf.getParamTypes().length)
                        // não chamei passando parametros e função tem parametros
                        || (e.getExps() == null && tf.getParamTypes().length > 0)
        ) {
            logError.add(e.getLine() + ", " + e.getCol() +
                    ": Chamada a função " + e.getFunctionName() + " incompatível com argumentos. ");
            stk.push(tyerr);
            return;
        }

        int k = 0;
        // somente se tem argumentos, verificar se o tipo de cada argumento ao chamar uma função casa com o tipo do parametro da função
        if (e.getExps() != null) {
            for (Expr expr : e.getExps().getExpressions()) {
                expr.accept(this);
                if (!tf.getParamTypes()[k].match(stk.pop())) {
                    logError.add(expr.getLine() + ", " + expr.getCol() + ": " + (k + 1) +
                            "º argumento incompatível com o respectivo parâmetro de " + e.getFunctionName());
                }
                k++;
            }
        }

        // Verificar que os indicadores de acesso a valores de retorno estão dentro do limite do número  de valores de retorno, p
        e.getExp().accept(this);
        SType sType = stk.pop();//TODO: como pegar o valor calculado da expressao???

        if (!(sType.match(tyint))) {
            logError.add(e.getLine() + ", " + e.getCol() +
                    " o acesso ao indice de retorno da função deve ser do tipo Int.");
            stk.push(tyerr);
            return;
        }
        // calcular o valor do índice
        Integer idxValue = tryEvaluateIntExpr(e.getExp());
        int sizeRet = tf.getReturnTypes().length;

        if (idxValue != null) {
            if (sizeRet == 0) {
                logError.add(e.getLine() + ", " + e.getCol() +
                        " a função não tem retorno e está tentando acessar a posição " + idxValue);
                stk.push(tyerr);
                return;
            }
            if (idxValue < 0 || idxValue >= sizeRet) {
                logError.add(e.getLine() + ", " + e.getCol() +
                        ": index fora dos limites de retorno. Função retorna " + sizeRet +
                        " valores e está acessando posição " + idxValue);
                stk.push(tyerr);
                return;
            }

            // se a chamada de função da certo, o tipo resultante é tipo que ela declara que ela retorna
            // por isso jogo esse tipo para o topo da pilha
            // stk.push(tf.getTypes()[tf.getTypes().length - 1]);//pq?
            //expressão de chamada de função sempre retorna alguma coisa considerando que é obrigatorio ter o []
            stk.push(tf.getReturnTypes()[idxValue]);
        } else {
            if (sizeRet == 0) {
                logError.add(e.getLine() + ", " + e.getCol() +
                        " a função não tem retorno e está tentando acessar um índice.");
                stk.push(tyerr);
                return;
            }

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
            STyData sTyData = new STyData(typeName, fields);
            typeStructs.put(typeName, sTyData);
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

    /**
     * exp --> 'new' type ('[' e = exp ']')
     * type --> type '[' ']' | btype
     * btype --> Int | Char | Bool | Float | TYID
     * TODO: não esta chegando aqui com os array vazio, ja chega com o 3
     *
     * @param e
     */
    @Override
    public void visit(ArrayExpr e) {
        // Verifica se o tamanho e do tipo Int
        e.getExp().accept(this);
        SType sizeType = stk.pop();

        if (!sizeType.match(tyint)) {
            logError.add(e.getLine() + ", " + e.getCol() + ": Tamanho do array deve ser do tipo Int.");
            stk.push(tyerr);
            return;
        }

        // chama os visit nos array interno de forma recursiva
        e.getType().accept(this);
        SType typeDeclared = stk.pop();

        // pega o tipo base
        SType base = typeDeclared;
        while (base instanceof STyArr arr) {
            base = arr.getElemType();
        }

//        // valida se o tipo base é Int
//        if (!base.match(tyint)) {
//            logError.add(e.getLine() + ", " + e.getCol() +
//                    ": Só é permitido criar arrays de Int mas foi encontrado: " + base.toString());
//            stk.push(tyerr);
//            return;
//        }

        // empilha o novo tipo array
        stk.push(new STyArr(typeDeclared));
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
     * Acesso a Arrays
     * lvalue --> lvalue ‘[’ exp ‘]’
     *
     * @param e
     */
    @Override
    public void visit(LValueExp e) {
        e.getLvalue().accept(this); // avalia a expressão à esquerda (espera-se um STyArr)
        SType arrType = stk.pop();

        e.getIndex().accept(this); // avalia índice
        SType idxType = stk.pop();

        if (!(arrType instanceof STyArr)) {
            logError.add(e.getLine() + ", " + e.getCol() +
                    ": Acesso com índice em tipo que não é array.");
            stk.push(tyerr);
            return;
        }

        if (!idxType.match(tyint)) {
            logError.add(e.getLine() + ", " + e.getCol() +
                    ": Index de acesso ao array deve ser Int.");
            stk.push(tyerr);
            return;
        }

        // O tipo do acesso a arr[i] é o tipo do elemento do array
        stk.push(((STyArr) arrType).getElemType());
    }

    /**
     * Não entra array aqui, array entra no ArrayExpr
     * exp --> new btype
     * btype --> Int | Char | Bool | Float | TYID
     *
     * @param e
     */
    @Override
    public void visit(VarExpr e) {
        e.getType().accept(this);

        SType type = stk.pop();
        if (type instanceof STyData) {// Tratamento de tipos criados pelo usuario
            TYID tyid = (TYID) e.getType();
            if (!typeStructs.containsKey(tyid.getName())) {
                logError.add(e.getLine() + ", " + e.getCol() + ": Tipo " + tyid.getName() + " não declarado.");
                stk.push(tyerr);
            } else {
                //TODO: talvez possa trocar para typeStructs.get(e.getName()), já que os tipos já estao na tabela typeStructs
                stk.push(new STyData(tyid.getName())); //vai ser usado no CmdAssign
//                stk.push(typeStructs.get(tyid.getName())); //vai ser usado no CmdAssign
            }
        }
    }

    /**
     * cmd --> return exp {‘,’ exp} ‘;’
     * verificar se o comando de retorno bate com a assinatura da função
     * @param r
     */
    @Override
    public void visit(CmdReturn r) {
        List<SType> typesReturnCmd = new ArrayList<>();

        //avalia cada expressão e adiciona no tipo da pilha stk
        for (Expr e : r.getExpressions()) {
            e.accept(this);
            typesReturnCmd.add(stk.pop());
        }

        if (!(temp.getFuncType() instanceof STyFun)) {
            logError.add(r.getLine() + ", " + r.getCol() + ": Comando return fora de função.");
            retChk = false;
            return;
        }

        SType[] tiposRetorno = ((STyFun) temp.getFuncType()).getReturnTypes();

        // verificar quantidade bate
        if (tiposRetorno.length != typesReturnCmd.size()) {
            logError.add(r.getLine() + ", " + r.getCol() +
                    ": Número de valores retornados (" + typesReturnCmd.size() +
                    ") diferente do esperado (" + tiposRetorno.length + ").");
            retChk = true; // entrou no return então tem return
            return;
        }

        // valida tipos de cada retorno
        for (int i = 0; i < tiposRetorno.length; i++) {
            SType esperado = tiposRetorno[i];// tipo da assinatura da função
            SType encontrado = typesReturnCmd.get(i); // tipo do comando de retorno

            // null só é aceito para Data ou Array
            if (encontrado.match(tynull) && !(esperado instanceof STyData || esperado instanceof STyArr)) {
                logError.add(r.getLine() + ", " + r.getCol() +
                        ": Retorno na posição " + (i+1) + " é null, mas o tipo esperado é " + esperado);
                continue; //já encontrou um erro vai pro proximo
            }

            if (!esperado.match(encontrado)) {
                logError.add(r.getLine() + ", " + r.getCol() +
                        ": Retorno na posição " + (i+1) +
                        " incompatível. Esperado: " + esperado + ", encontrado: " + encontrado);
            }
        }

        retChk = true;
    }

    /*
        cmd --> lvalue ‘=’ exp ‘;’
        lvalue --> ID | lvalue '[' exp ']' | lvalue '.' ID | TYID ID;
        ou seja, posso fazer atribuição em variavel ou atributo de um registro
    */
    @Override
    public void visit(CmdAssign p) {
        if (p.getExpression() instanceof VarExpr && !(((VarExpr) p.getExpression()).getType() instanceof TYID)) {
            logError.add(p.getLine() + ", " + p.getCol() + " Não é permitido instanciar tipos primitivos.");
            stk.push(tyerr);
            return;
        }

        if (p.getLvalue() instanceof ID) {//variavel simples
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
        } else if (p.getLvalue() instanceof IdLValue) {//acesso a atributo de registro
            p.getLvalue().accept(this);
            p.getExpression().accept(this);

            SType tyExpression = stk.pop();
            SType tyLvalue = stk.pop();

            if (!tyLvalue.match(tyExpression)) {
                // se a expr for null, só pode ser aplicada a registros/array
                if(tyExpression.match(tynull) && !(tyLvalue instanceof STyData || tyLvalue instanceof STyArr)){
                    logError.add(p.getLine() + ", " + p.getCol() +
                            ": Atribuição ilegal para o atributo " + ((IdLValue) p.getLvalue()).getId() +
                            ". Esperava um " + tyLvalue.toString() + " mas encontrou " + tyExpression.toString() + ".");
                }

            }
        } else if (p.getLvalue() instanceof LValueExp lValueExp) {//LValueExp é acesso a array
            lValueExp.getLvalue().accept(this); // empilha o tipo do arranjo
            lValueExp.getIndex().accept(this);  // empilha o tipo do índice

            SType tyIndex = stk.pop();
            SType tyArray = stk.pop();

            // Verifica se está acessando um array
            if (!(tyArray instanceof STyArr arr)) {
                logError.add(p.getLine() + ", " + p.getCol() +
                        ": Tentando acessar como array algor que não é array.");
                return;
            }

            // Verifica se o índice é inteiro
            if (!tyIndex.match(tyint)) {
                logError.add(p.getLine() + ", " + p.getCol() +
                        ": Índice de acesso ao array deve ser do tipo Int.");
                return;
            }

            // Agora verifica o tipo da expressão que será atribuída
            p.getExpression().accept(this);
            SType tyExpression = stk.pop();
            SType tyElem = arr.getElemType();

            if (!tyElem.match(tyExpression)) {
                logError.add(p.getLine() + ", " + p.getCol() +
                        ": Não é possivel fazer esse acesso ao array. Esperava " +
                        tyElem.toString() + " mas encontrou " + tyExpression.toString() + ".");
            }
        }
    }

    @Override
    public void visit(CmdIf e) {
        boolean returnThen;

        e.getCondition().accept(this);
        if (stk.pop().match(tybool)) {
            retChk = false;
            e.getThenCmd().accept(this);
            returnThen = retChk;
            retChk = false;

            if (e.getElseCmd() != null) {
                e.getElseCmd().accept(this);
            }
            retChk = retChk && returnThen;
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
            //tem que ser int ou array
            // quando for array, o corpo vai ser executado a quantidade de vezes do tamanho do array
            if (tyExpression.match(tyint) || tyExpression instanceof STyArr) {
                e.getBody().accept(this);
            } else {
                logError.add(e.getLine() + ", " + e.getCol() + ": Expressão de teste do Iterate deve ter tipo Int/Array");
            }

        } else if (e.getCondition() instanceof IdItCond idItCond) {// ID ‘:’ exp iterate(v:e){

            idItCond.getExpression().accept(this);
            SType tyExpression = stk.pop();// é o exp do iterate
            // iterate ( ID : expr ) cmd
            if (tyExpression.match(tyint)) {//TODO: pode ser int ou tipo arranjo (vetor)
                // iterate ( v : 10 ) cmd
                // Na segunda forma, quando e tem tipo Int, verificamos que v não está no contexto ou está com tipo Int.
                String v = idItCond.getId();
                SType typeV = temp.get(v);
                if (typeV == null) {// ou seja, o v ainda não esta no contexto
                    temp.set(v, tyint);// seto o v com o tipo int
                } else if (!typeV.match(tyint)) {
                    logError.add(e.getLine() + ", " + e.getCol() + ": O tipo de " + v + " deveria ser Int mas foi encontrado " + typeV);
                }
                e.getBody().accept(this);
            } else if (tyExpression instanceof STyArr) {
                // iterate ( v : array ) cmd
                // Caso e seja um vetor, a regra é similar, porém v deve ter o mesmo tipo dos elementos do vetor.

                String v = idItCond.getId();
                SType typeV = temp.get(v);
                SType sTypeElementsArr = ((STyArr) tyExpression).getElemType();
                if (typeV == null) {// ou seja, o v ainda não esta no contexto
                    temp.set(v, tyint);// seto o v com o tipo int
                } else if (!typeV.match(sTypeElementsArr)) {
                    logError.add(e.getLine() + ", " + e.getCol() + ": O tipo de " + v + " deveria ser " + sTypeElementsArr + " mas foi encontrado " + typeV);
                }
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
        p.getLvalue().accept(this);
        SType t = stk.pop();

        if (!(t.match(tyint) || t.match(tyfloat) || t.match(tybool) || t.match(tychar))) {
            logError.add(p.getLine() + ", " + p.getCol() +
                    ": Tipo " + t + " inválido para o comando read. Comando read aceita apenas: Int, Char, Bool, Float.");
            stk.push(tyerr);
        }
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
        stk.push(tychar);//TODO: descomentar e testar
    }

    @Override
    public void visit(ArrayType t) {
        t.getType().accept(this); // empilha o tipo interno, vai percorrendo recursivamente ate chegar no tipo base
        SType innerType = stk.pop();
        stk.push(new STyArr(innerType));
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
        String typeName = userType.getTypeName();

        // Verifico que o ambiente de tipos Data contem o Data com o nome dataName
        if (!typeStructs.containsKey(typeName)) {
            logError.add(e.getLine() + ", " + e.getCol() + ": Tipo de registro não definido: " + typeName);
            stk.push(tyerr);
            return;
        }

        // Verifico se é um tipo abstrato
        if (abstractTypes.contains(typeName)) {
            // Só permite acesso direto se a função atual for uma das definidas no mesmo tipo

            String nomeFuncAtual = temp.getFuncID();
            LinkedHashMap<String, STyFun> funcsData = typeStructs.get(typeName).getFuncsData();
            // verifico se estou na função que pode acessar o atributo do tipo abstrato
            if (!funcsData.containsKey(nomeFuncAtual)) {
                logError.add(e.getLine() + ", " + e.getCol() +
                        ": Acesso ilegal ao campo " + atributo + " do tipo abstrato " + typeName +
                        " fora do escopo do tipo.");
                stk.push(tyerr);
                return;
            }
        }

        // Se tem o dataName, pego o mapeamento de nomes e tipos
        STyData sTyData = typeStructs.get(typeName); // pego os atributos do tipo
        Map<String, SType> atributos = sTyData.getAttrsData();

        // E verifico se tem o atributo que estou acessando
        if (!atributos.containsKey(atributo)) {
            logError.add(e.getLine() + ", " + e.getCol() + ": Campo " + atributo + " não existe no tipo " + typeName);
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
