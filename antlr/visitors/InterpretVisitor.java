package visitors;

import ast.*;
import util.AbstractFunctionData;

import java.util.*;
import java.util.stream.Collectors;

public class InterpretVisitor extends Visitor {
    String[] args;

    // Hashmap para gerenciar o escopo de funções.
    private Stack<HashMap<String, Object>> env;
    /*
    * Exemplo: {'f1': {'a': 12, b: '4'}, 'f2': {'a': 13, c: 4.13}};
    */
    // Hashmap para guardar a associação entre nome de funções e o objeto. (Guarda as chaves do env).
    private HashMap<String, Fun> funcs;
    /*
    *  Com base no exemplo acima, seria: {'f1': ObjectF1, 'f2': ObjectF2}.
    * */

    // Hashmap com os valores temporários das operações.
    private Stack<Object> operands;

    // Hashmap para guardar quais tipos foram declarados (Não abstratos) e suas variáveis/funções disponíveis.
    private HashMap<String, HashMap<String, Object>> dataTypesEnv = new HashMap<String, HashMap<String, Object>>();
    private Stack<String> namesStack = new Stack<>(); // Stack para ajudar a percorrer Decl E fun

    private boolean retMode, debug;

    public InterpretVisitor(String[] args) {
        env = new Stack<HashMap<String, Object>>();
        env.push(new HashMap<String, Object>());
        funcs = new HashMap<String, Fun>();
        operands = new Stack<Object>();
        retMode = false;
        debug = false;
        this.args = args; /* ARGUMENTOS DO TERMINAL QUE VÃO PARA A MAIN */
    }

    public InterpretVisitor(boolean debug) {
        this.debug = debug;
    }

    public void visit(Program p) {
        Node main = null;
        for(Def f : p.getDefinitions()){
            if(f instanceof Fun){
                Fun funcao = (Fun) f;
                funcs.put(funcao.getID(), funcao);
                if(funcao.getID().equals("main")){
                    main = f;
                }
            }else {
                f.accept(this);
            }
        }

        if(main != null){
            main.accept(this);
        }
    }

    @Override
    public void visit(DataDecl p) {
        String name = p.getTypeId();

        dataTypesEnv.put(name, new HashMap<>());
        namesStack.push(name);
        for(Node d: p.getDeclarations()){
            Decl x = (Decl) d;
            x.accept(this);
        }
        namesStack.pop();
    }

    @Override
    public void visit(AbstractDataDecl p) {
        //
    }

    @Override
    public void visit(Decl p) {
        String nameData = namesStack.peek();
        String nameVar = p.getId();
        dataTypesEnv.get(nameData).put(nameVar, null);
        namesStack.push(nameVar);
        p.getType().accept(this);
    }

    @Override
    public void visit(TypeInt e) {
        String nameVar = namesStack.pop();
        String nameData = namesStack.peek();
        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(TYID e) {
//        String nameVar = namesStack.pop();
//        String nameData = namesStack.peek();
//        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(TypeBool e) {
//        String nameVar = namesStack.pop();
//        String nameData = namesStack.peek();
//        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(TypeChar e) {
//        String nameVar = namesStack.pop();
//        String nameData = namesStack.peek();
//        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(TypeFloat e) {
//        String nameVar = namesStack.pop();
//        String nameData = namesStack.peek();
//        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(FunAbstractData p) {
        //
    }

    @Override
    public void visit(Params e) {

    }

    @Override
    public void visit(Block b) {
        for(Cmd c: b.getCommands()) {
            c.accept(this);
        }
    }

    @Override
    public void visit(CmdAssign p) {
        if(p.getLvalue() instanceof ID){
            String nameVar = ((ID)p.getLvalue()).getName();


            p.getExpression().accept(this);


            env.peek().put(nameVar, operands.pop());

        }else if(p.getLvalue() instanceof IdLValue){
            IdLValue lvalue = ((IdLValue) p.getLvalue());
            String namevar = ((ID) lvalue.getLvalue()).getName();
            String nameAtributo = lvalue.getId();

            p.getExpression().accept(this);

            Object value = env.peek().get(namevar);
            if (value instanceof HashMap<?, ?>) {
                @SuppressWarnings("unchecked")
                HashMap<String, Object> map = (HashMap<String, Object>) value;
                map.put(nameAtributo, operands.pop());
            }
        }
    }

    public void visit(Add e) {
        try {
            // Visita os operandos da esquerda e da direita
            e.getLeft().accept(this);
            e.getRight().accept(this);

            // Pega os valores avaliados da pilha
            Object dir = operands.pop();
            Object esq = operands.pop();

            // Verifica se ambos são instâncias de Number
            if (!(esq instanceof Number) || !(dir instanceof Number)) {
                throw new RuntimeException("Operandos não numéricos");
            }

            Number esqNum = (Number) esq;
            Number dirNum = (Number) dir;

            // Se algum for Float ou Double, faz soma em ponto flutuante
            if (esqNum instanceof Float || dirNum instanceof Float ||
                    esqNum instanceof Double || dirNum instanceof Double) {
                float resultado = esqNum.floatValue() + dirNum.floatValue();
                operands.push(resultado);
            } else {
                // Caso contrário, faz soma inteira
                int resultado = esqNum.intValue() + dirNum.intValue();
                operands.push(resultado);
            }

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Sub e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            if(e.getLeft() instanceof FloatValue || e.getRight() instanceof FloatValue){
                // Caso em que ao menos um dos dois é Float => Retorno da operação é Float.
                Number esq, dir;
                dir = (Number) operands.pop();
                esq = (Number) operands.pop();
                operands.push(esq.floatValue() - dir.floatValue());
            }else{
                // Caso em que são os dois Integer.
                Number esq, dir;
                dir = (Number) operands.pop();
                esq = (Number) operands.pop();
                operands.push(esq.intValue() - dir.intValue());
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Mul e) {
        try {
            // Visita os operandos da esquerda e da direita
            e.getLeft().accept(this);
            e.getRight().accept(this);

            // Pega os valores avaliados da pilha
            Object dir = operands.pop();
            Object esq = operands.pop();

            // Verifica se ambos são instâncias de Number
            if (!(esq instanceof Number) || !(dir instanceof Number)) {
                throw new RuntimeException("Operandos não numéricos");
            }

            Number esqNum = (Number) esq;
            Number dirNum = (Number) dir;

            // Se algum for Float ou Double, faz soma em ponto flutuante
            if (esqNum instanceof Float || dirNum instanceof Float ||
                    esqNum instanceof Double || dirNum instanceof Double) {
                float resultado = esqNum.floatValue() * dirNum.floatValue();
                operands.push(resultado);
            } else {
                // Caso contrário, faz soma inteira
                int resultado = esqNum.intValue() * dirNum.intValue();
                operands.push(resultado);
            }

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Div e) {
        try {
            // Visita os operandos da esquerda e da direita
            e.getLeft().accept(this);
            e.getRight().accept(this);

            // Pega os valores avaliados da pilha
            Object dir = operands.pop();
            Object esq = operands.pop();

            // Verifica se ambos são instâncias de Number
            if (!(esq instanceof Number) || !(dir instanceof Number)) {
                throw new RuntimeException("Operandos não numéricos");
            }

            Number esqNum = (Number) esq;
            Number dirNum = (Number) dir;

            // Se algum for Float ou Double, faz soma em ponto flutuante
            if (esqNum instanceof Float || dirNum instanceof Float ||
                    esqNum instanceof Double || dirNum instanceof Double) {
                float resultado = esqNum.floatValue() / dirNum.floatValue();
                operands.push(resultado);
            } else {
                // Caso contrário, faz soma inteira
                int resultado = esqNum.intValue() / dirNum.intValue();
                operands.push(resultado);
            }

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Mod e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            operands.push(esq.intValue() % dir.intValue());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(And e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push((Boolean) esq && (Boolean) dir);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Lt e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            //Converto os dois para Float, pois no final serão convertidos para Boolean. Isso trata o caso de tipos diferentes.
            operands.push((boolean) (esq.floatValue() < dir.floatValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Eq e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            operands.push(operands.pop().equals(operands.pop()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Diff e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            operands.push(!operands.pop().equals(operands.pop()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }


    @Override
    public void visit(MinusExpr e) {

    }

    public void visit(NotExpr e) {
        try {
            e.getExpression().accept(this);
            operands.push(!(Boolean) (operands.pop()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Exps e) {

    }

    @Override
    public void visit(ArrayLValue e) {

    }

    @Override
    public void visit(ArrayType e) {

    }

    // ID '(' (exps)? ')' '[' exp ']' - ch
    public void visit(CallFunctionAccess e) {
        try {
            // pegar a função pelo name no hashmap de funções
            Fun f = funcs.get(e.getFunctionName());
            if (f != null) {
                for (Expr exp : e.getExps().getExpressions()) {
                    exp.accept(this); // empilho cada valor avaliado pela expressão na pilha de operandos
                }
                f.accept(this);// visito o Fun

                ArrayList retornos = (ArrayList) operands.pop();

                e.getExp().accept(this);// avaliar a expressão que pega o indice
                int idx = (int) operands.pop(); // pegar o indice do topo da pilha

                operands.push(retornos.get(idx));// pegar o elemento pelo indice avaliado

            } else {
                throw new RuntimeException(
                        " (" + e.getLine() + ", " + e.getCol() + ") Função não definida " + e.getFunctionName());
            }

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }

    }

    @Override
    public void visit(VarExpr e) {
        Object type = e.getType();

        if(type instanceof TypeInt || type instanceof TypeFloat || type instanceof TypeBool || type instanceof TypeChar){
            operands.push(null);
        }else if(type instanceof TYID){
            // alocar a memória do novo tipo.
            /*
                data Racional{
                    numerador::Int;
                    denominador::Int;
                }
                ------------------------
                'numerador': null,
                'denominador': null
             */
                // gerar uma copia do hasmap
            String nomeTipo = ((TYID) type).getName();
            HashMap<String, Object> original = dataTypesEnv.get(nomeTipo);
            HashMap<String, Object> copia = new HashMap<>();
            for(Map.Entry<String, Object> entry: original.entrySet()){
                String chave = entry.getKey();
                copia.put(chave, null);
            }
            operands.push(copia);
        }
    }

    @Override
    public void visit(ID e) {
        Object val = env.peek().get(e.getName());
        operands.push(val);
    }

    @Override
    public void visit(CharValue e) {
        try {
            operands.push(e.getValue());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    /*
        cmd --> ID '(' exps? ')' ('<' lvalue (',' lvalue)* '>')? ';'
     */
    public void visit(CmdFuncCall e) {
        try {
            Fun f = funcs.get(e.getId());
            if (f != null) {
                for (Expr exp : e.getExps().getExpressions()) {
                    exp.accept(this);
                }
                f.accept(this);

            } else {
                throw new RuntimeException(
                        " (" + e.getLine() + ", " + e.getCol() + ") Função não definida " + e.getId());
            }

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(TrueValue e) {
        try {
            operands.push(true);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(IdLValue e) {

        String namevar = ((ID) e.getLvalue()).getName(); // primeiro faço o cast do Lvalue para o tipo concreto ID e pego o nome do registro = nameVar
        String nameAtributo = e.getId(); // pego o nome do atributo que esta dentro = nameAtributo

        Object value = env.peek().get(namevar); //pego o hashmap que  está na pilha
        if (value instanceof HashMap<?, ?>) {// certifico que é um hashmap com nomes dos atributos e valores dele
            @SuppressWarnings("unchecked")
            HashMap<String, Object> map = (HashMap<String, Object>) value;
            operands.push(map.get(nameAtributo));
        }


    }

    @Override
    public void visit(NullValue e) {
        try {
            operands.push(null);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(FalseValue e) {
        try {
            operands.push(false);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(FieldLValue e) {

    }

    public void visit(IntValue e) {
        try {
            operands.push(Integer.valueOf(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(LValueExp e) {

    }

    public void visit(FloatValue e) {
        try {
            operands.push(Float.valueOf(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

//    public void visit(Var e) {
//        try {
//            Object r = env.peek().get(e.getName());
//            if (r != null) {
//                if (e.getIdx() != null) {
//                    for (Expr exp : e.getIdx()) {
//                        exp.accept(this);
//                        r = ((ArrayList) r).get((Integer) operands.pop());
//                    }
//                }
//                operands.push(r);
//            } else {
//                throw new RuntimeException(
//                        " (" + e.getLine() + ", " + e.getCol() + ") variável não declarada " + e.getName());
//            }
//        } catch (Exception x) {
//            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
//        }
//    }

//    public void visit(Attr e) {
//        try {
//            Var v = e.getID();
//            e.getExp().accept(this);
//            Object val = operands.pop();
//
//            if (v.getIdx() != null && v.getIdx().length > 0) {
//                ArrayList arr = (ArrayList) env.peek().get(v.getName());
//                for (int k = 0; k < v.getIdx().length - 1; k++) {
//                    v.getIdx()[k].accept(this);
//                    arr = (ArrayList) arr.get((Integer) operands.pop());
//                }
//                v.getIdx()[v.getIdx().length - 1].accept(this);
//                arr.set((Integer) operands.pop(), val);
//            } else {
//                env.peek().put(e.getID().getName(), val);
//            }
//
//        } catch (Exception x) {
//            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
//        }
//    }

    public void visit(CmdIf e) {
        try {
            e.getCondition().accept(this);
            if ((Boolean) operands.pop()) {
                e.getThenCmd().accept(this);
            } else if (e.getElseCmd() != null) {
                e.getElseCmd().accept(this);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(CmdIterate e) {
        try {
            e.getCondition().accept(this);
            if(e.getCondition() instanceof ExpItCond){
                int i = (Integer) operands.pop();
                while (i > 0) {
                    e.getBody().accept(this);
                    i--;
                }
            }else{
                IdItCond cond = (IdItCond) e.getCondition();
                String nameVar = cond.getId();
                int counter = (Integer) env.peek().get(nameVar);
                while(counter > 0){
                    e.getBody().accept(this);
                    counter--;
                    env.peek().put(nameVar, counter); // atualiza na memória o valor.
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(IdItCond e) {
        String name = e.getId();
        e.getExpression().accept(this);
        env.peek().put(name, operands.pop());
    }

    @Override
    public void visit(ExpItCond e) {
        e.getExpression().accept(this);
    }

    public void visit(CmdPrint e) {
        try {
            e.getExpression().accept(this);
            System.out.println(operands.pop().toString());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(CmdRead p) {
        Scanner scanner = new Scanner(System.in);
        String valorLido = scanner.nextLine();


        if (p.getLvalue() instanceof ID){
            String nameVar =  ((ID) p.getLvalue()).getName();

            this.env.peek().put(nameVar, valorLido);

            // precisa chamar o accept aqui, considerando que o visit do ID vai jogar o valor que está no env pro operands?
            // p.getLvalue().accept(this);

        } else if(p.getLvalue() instanceof IdLValue){
            // nameVar.nameAtributo
            IdLValue lvalue = ((IdLValue) p.getLvalue()); //pego a implementação concreta de IdLValue

            String namevar = ((ID) lvalue.getLvalue()).getName(); // pego o nome do registro = nameVar
            String nameAtributo = lvalue.getId(); // pego o nome do atributo que esta dentro = nameAtributo

            Object value = env.peek().get(namevar);
            if (value instanceof HashMap<?, ?>) {
                @SuppressWarnings("unchecked")
                HashMap<String, Object> map = (HashMap<String, Object>) value;
                map.put(nameAtributo, valorLido);
            }
        }

    }

//    public void visit(StmtList e) {
//        if (retMode) {
//            return;
//        }
//        try {
//            e.getCmd1().accept(this);
//            if (retMode) {
//                return;
//            }
//            e.getCmd2().accept(this);
//        } catch (Exception x) {
//            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
//        }
//    }

    public void visit(Fun f) {

        HashMap<String, Object> localEnv = new HashMap<String, Object>();
        if(f.getID().equals("main")){
            /*SE FOR A MAIN, OS PARÂMETROS TEM QUE VIR DO ARGS.*/
            if(f.getParams() != null) {
                for (int i = f.getParams().getParamList().size() - 1; i >= 0; i--) {
                    Param p = f.getParams().getParamList().get(i);
                    Object value = null;
                    if(p.getType() instanceof TypeInt)
                        value = Integer.valueOf( this.args[i+1] );
                    else if(p.getType() instanceof TypeFloat)
                        value = Float.valueOf( this.args[i+1] );
                    localEnv.put(p.getId(), value); // PEGA i+1 POR CAUSA DO PRIMEIRO PARÂMETRO Ser o caminho até o exemplo de teste.
                }
            }
        }else{
            if(f.getParams() != null) {
                for (int i = f.getParams().getParamList().size() - 1; i >= 0; i--) {
                    localEnv.put(f.getParams().getParamList().get(i).getId(), operands.pop());
                }
            }
        }

        env.push(localEnv);
        f.getCmd().accept(this);
        /*Aqui q printa a memória*/
//        Object[] x = env.peek().keySet().toArray();
//        for(int i =0; i < x.length; i++){
//            System.out.println(((String) x[i]) + " : " + env.peek().get(x[i]).toString());
//        }
        env.pop();
        retMode = false;
    }

//    public void visit(Inst e) {
//        try {
//            Var v = e.getID();
//            e.getSize().accept(this);
//            Integer size = (Integer) operands.pop();
//            ArrayList val = new ArrayList(size);
//
//            for (int i = 0; i < size; i++) {
//                val.add(null);
//            }
//
//            if (env.peek().get(v.getName()) == null) {
//                env.peek().put(v.getName(), val);
//            } else if (v.getIdx() != null && v.getIdx().length > 0) {
//                ArrayList arr = (ArrayList) env.peek().get(v.getName());
//                for (int k = 0; k < v.getIdx().length - 1; k++) {
//                    v.getIdx()[k].accept(this);
//                    arr = (ArrayList) arr.get((Integer) operands.pop());
//                }
//                v.getIdx()[v.getIdx().length - 1].accept(this);
//                arr.set((Integer) operands.pop(), val);
//            } else {
//                env.peek().put(e.getID().getName(), val);
//            }
//
//        } catch (Exception x) {
//            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
//        }
//    }

    public void visit(CmdReturn e) {
        List<Object> retornos = new ArrayList<>();
        for(Expr r: e.getExpressions()){
            r.accept(this);//aqui eu adiciono no operands
            retornos.add(operands.pop()); //aqui eu removo e adiciono na lista
        }
        operands.push(retornos);
        retMode = true;
    }
}
