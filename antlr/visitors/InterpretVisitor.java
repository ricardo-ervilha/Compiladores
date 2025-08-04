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
import util.InterpretException;

import java.util.*;
import util.Debug;

public class InterpretVisitor extends Visitor {

    // para instanciar variaveis.
    enum AccessMode {
        READ, WRITE
    }
    AccessMode currentAccessMode = null;

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
    // É apenas um template que será usado para criar as variaveis do tipo Registro/Abstrato
    private HashMap<String, HashMap<String, Object>> dataTypesEnv = new HashMap<String, HashMap<String, Object>>();
    private Stack<String> namesStack = new Stack<>(); // Stack para ajudar a percorrer Decl E fun

    private boolean retMode, debug;

    public InterpretVisitor() {
        env = new Stack<HashMap<String, Object>>();
        env.push(new HashMap<String, Object>());
        funcs = new HashMap<String, Fun>();
        operands = new Stack<Object>();
        retMode = false;
        debug = false;
    }

    public InterpretVisitor(boolean debug) {
        this.debug = debug;
    }

    public void visit(Program p) {
        // CHECK
        Debug.log("Visit Program");
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
        Debug.log("Visit DataDecl");

        String name = p.getTypeId();// nome do tipo Registro para ser superchave

        dataTypesEnv.put(name, new HashMap<>());
        namesStack.push(name);// guardo o nome na pilha para saber depois de qual Registro são essas declarações
        
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
        Debug.log("Visit Decl");
        String nameData = namesStack.peek();
        String nameVar = p.getId();// nome do atributo do Registro/Tipo Abstrato
        dataTypesEnv.get(nameData).put(nameVar, null);//coloco null pq ainda não sei o valor...
        namesStack.push(nameVar);// guardo no namesStack pois ao visitar os Type vou precisar saber o nome do atributo
        p.getType().accept(this);
    }

    @Override
    public void visit(TypeInt e) {
        // check
        Debug.log("Visit TypeInt");
        String nameVar = namesStack.pop();
        String nameData = namesStack.peek();
        dataTypesEnv.get(nameData).put(nameVar, e);// guardando o Tipo do atributo
    }

    @Override
    public void visit(TypeFloat e) {
        // check
        Debug.log("Visit TypeFloat");
       String nameVar = namesStack.pop();
       String nameData = namesStack.peek();
       dataTypesEnv.get(nameData).put(nameVar, e);// guardando o Tipo do atributo
    }

    @Override
    public void visit(TYID e) {
        Debug.log("Visit TYID");
        String nameVar = namesStack.pop();
        String nameData = namesStack.peek();
        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(TypeBool e) {
        Debug.log("Visit TypeBool");
    // check
       String nameVar = namesStack.pop();
       String nameData = namesStack.peek();
       dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(TypeChar e) {
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
        Debug.log("Visit CmdAssign");
        currentAccessMode = AccessMode.READ;
        p.getExpression().accept(this);
        currentAccessMode = AccessMode.WRITE; // quando estou em um assign, significa que tenho intenção de escrever valor na variável.
        p.getLvalue().accept(this);
    }

    public void visit(Add e) {
        //check
        Debug.log("Visit Add");
        try {
            // Visita os operandos da esquerda e da direita
            currentAccessMode = AccessMode.READ;
            e.getLeft().accept(this);
            currentAccessMode = AccessMode.READ;
            e.getRight().accept(this);

            // Pega os valores avaliados da pilha
            Object dir = operands.pop();
            Object esq = operands.pop();

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
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Sub e) {
        Debug.log("Visit Sub");
        try {
            currentAccessMode = AccessMode.READ;
            e.getLeft().accept(this);
            currentAccessMode = AccessMode.READ;
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
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Mul e) {
        Debug.log("Visit Mul");
        try {
            // Visita os operandos da esquerda e da direita
            currentAccessMode = AccessMode.READ;
            e.getLeft().accept(this);
            currentAccessMode = AccessMode.READ;
            e.getRight().accept(this);

            // Pega os valores avaliados da pilha
            Object dir = operands.pop();
            Object esq = operands.pop();

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
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Div e) {
        Debug.log("Visit Div");
        try {
            // Visita os operandos da esquerda e da direita
            currentAccessMode = AccessMode.READ;
            e.getLeft().accept(this);
            currentAccessMode = AccessMode.READ;
            e.getRight().accept(this);

            // Pega os valores avaliados da pilha
            Object dir = operands.pop();
            Object esq = operands.pop();

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
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Mod e) {
        Debug.log("Visit Mod");
        try {
            currentAccessMode = AccessMode.READ;
            e.getLeft().accept(this);
            currentAccessMode = AccessMode.READ;
            e.getRight().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            operands.push(esq.intValue() % dir.intValue());
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(And e) {
        Debug.log("Visit And");
        try {
            currentAccessMode = AccessMode.READ;
            e.getLeft().accept(this);
            currentAccessMode = AccessMode.READ;
            e.getRight().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push((Boolean) esq && (Boolean) dir);
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Lt e) {
        Debug.log("Visit Less Than");
        try {
            currentAccessMode = AccessMode.READ;
            e.getLeft().accept(this);
            currentAccessMode = AccessMode.READ;
            e.getRight().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            //Converto os dois para Float, pois no final serão convertidos para Boolean. Isso trata o caso de tipos diferentes.
            operands.push((boolean) (esq.floatValue() < dir.floatValue()));
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Eq e) {
        Debug.log("Visit Equals");
        try {
            currentAccessMode = AccessMode.READ;
            e.getLeft().accept(this);
            currentAccessMode = AccessMode.READ;
            e.getRight().accept(this);
            Object val1 = operands.pop();
            Object val2 = operands.pop();
            operands.push(val1.equals(val2));
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Diff e) {
        Debug.log("Visit Different");
        try {
            currentAccessMode = AccessMode.READ;
            e.getLeft().accept(this);
            currentAccessMode = AccessMode.READ;
            e.getRight().accept(this);
            operands.push(!operands.pop().equals(operands.pop()));
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(MinusExpr e) {
        // NOT check | Falta completar o código desse.
    }

    public void visit(NotExpr e) {
        Debug.log("Visit Not");
        try {
            currentAccessMode = AccessMode.READ;
            e.getExpression().accept(this);
            operands.push(!(Boolean) (operands.pop()));
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Exps e) {

    }

    @Override
    public void visit(ArrayLValue e) {
        // Não está sendo usada.
    }

    @Override
    public void visit(ArrayType e) {

    }

    @Override
    public void visit(ArrayExpr e){
        Debug.log("Visit ArrayExpr");
        /* INSTANCIAR */
        e.getExp().accept(this); // descobre o tamanho 
        int val = (int) operands.pop(); // NÃO USO POR ENQUANTO
        HashMap<Integer, Object> array = new HashMap<>();
        operands.push(array);
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
                throw new InterpretException(
                        " (" + e.getLine() + ", " + e.getCol() + ") Função não definida " + e.getFunctionName());
            }

        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }

    }

    @Override
    public void visit(VarExpr e) {
        Debug.log("Visit VarExpr");
        
        Object type = e.getType();

        if(type instanceof TypeInt || type instanceof TypeFloat || type instanceof TypeBool || type instanceof TypeChar){
            // para tipos simples, basta adicionar nulo.
            operands.push(null);
        }
        else if(type instanceof TYID){
            // alocar a memória do tipo que foi criado.
            /*
                data Ponto {
                    x1 :: Float;
                    x2 :: Float;
                }

                data Reta {
                    p1 :: Ponto;
                    p2 :: Ponto;
                }
                ------------------------
             */

            // gerar uma copia do hasmap
            String nomeTipo = ((TYID) type).getName();
            HashMap<String, Object> internalTyid = dataTypesEnv.get(nomeTipo);
            HashMap<String, Object> memoriaTipo = recursiveAssembly(nomeTipo, internalTyid);
            operands.push(memoriaTipo); // quando chama pra fazer o new ele joga no operands como se fosse expressão...
        }
    }

    /* FUNÇÃO AUXILIAR PARA REALIZAR RECURSÃO DURANTE A MONTAGEM DO DATA. */
    public HashMap<String, Object> recursiveAssembly(String nomeTipo, HashMap<String,Object> variables){

        HashMap<String, Object> assembled = new HashMap<>();
        
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
           
            if (value instanceof TypeInt || value instanceof TypeFloat ||
                value instanceof TypeBool || value instanceof TypeChar) {
                assembled.put(key, null); // CASO BASE
            }else if(value instanceof TYID){
                HashMap<String, Object> nested = dataTypesEnv.get(((TYID)value).getName());
                assembled.put(key, recursiveAssembly(key, nested)); // chamada recursiva
            }
        }

        return assembled;
    }

    
    @Override
    public void visit(CharValue e) {
        // !! NOT check | Verificar as coisas do char.
        Debug.log("Visit CharValue");
        String value = e.getValue().replaceAll("'", "");
        Object charRet = null;
        if(value.equals("\\n")){
            charRet = value.replace("\\n", "\n").charAt(0);
        }else if(value.equals("\\t")){
            charRet = value.replace("\\t", "\t").charAt(0);
        } else if(value.equals("\\b")){
            charRet = value.replace("\\b", "\b").charAt(0);
        }  else if(value.equals("\\r")){
            charRet = value.replace("\\r", "\r").charAt(0);
        }else if (value.matches("\\\\[0-7]{3}")) {
            int asciiCode = Integer.parseInt(value.substring(1), 10); 
            charRet = (char) asciiCode;
        } 
        else{
            charRet = value.charAt(0);
        }
        try {
            operands.push(charRet);
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }
    
    /*
    cmd --> ID '(' exps? ')' ('<' lvalue (',' lvalue)* '>')? ';'
    */
    public void visit(CmdFuncCall e) {
        Debug.log("Visit CmdFuncCall");
        try {
            Fun f = funcs.get(e.getId());
            if (f != null) {
                for (Expr exp : e.getExps().getExpressions()) {
                    currentAccessMode = AccessMode.READ;
                    exp.accept(this);
                }
                f.accept(this);
                
                if(retMode){
                    List<LValue> lvalues = e.getLvalues();

                    @SuppressWarnings("unchecked")
                    List<Object> retObjs = (List<Object> )operands.pop();

                    for (int i = 0; i < retObjs.size(); i++) {
                        currentAccessMode = AccessMode.WRITE; // escreverá valor na variavel
                        operands.push(retObjs.get(i));
                        lvalues.get(i).accept(this);
                    }
                }

                retMode = false;

            } else {
                throw new InterpretException(
                    " (" + e.getLine() + ", " + e.getCol() + ") Função não definida " + e.getId());
                }
                
            } catch (Exception x) {
                throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
            }
    }
        
    @Override
    public void visit(ID e) {
        /* ATRIBUIÇÃO SIMPLES */
        Debug.log("Visit ID");
        String varName = e.getName();

        switch (currentAccessMode) {
            case READ:
                Object val = env.peek().get(varName);
                operands.push(val);
                break;
        
            case WRITE:
                env.peek().put(varName, operands.pop());
                break;
        }
    }

    /* Resolve toda a cascata de valores que pode ter dentro do LvalueID parcialmente */
    public Object resolveLvalue(LValue n){
        System.out.println("Recursão LValue");
        if(n instanceof IdLValue){
            @SuppressWarnings("unchecked")
            HashMap<String, Object> result = (HashMap<String, Object>) resolveLvalue(((IdLValue) n).getLvalue()); // quem chamou ganha o dicionario
            return result.get(((IdLValue) n).getId()); // acesso nessa chamada mais um nivel do dicionario e retorno pra quem chamou
        }else if(n instanceof ID){
            currentAccessMode = AccessMode.READ;
            ((ID) n).accept(this); // coloca o dicionario da variavel no operand
            return operands.pop(); // retorno ele pra quem chamou ("CASO BASE")
        }
        throw new RuntimeException("Tipo de Lvalue desconhecido: " + n.getClass().getName());
    }
    
    @Override
    public void visit(IdLValue e) {
        Debug.log("Visit IDLvalue");
        
        switch (currentAccessMode) {
            case READ:
                currentAccessMode = AccessMode.READ;
                e.getLvalue().accept(this);
                @SuppressWarnings("unchecked")
                HashMap<String, Object> dict = (HashMap<String, Object>) operands.pop();
                operands.push(dict.get(e.getId()));
                break;
            case WRITE:
                currentAccessMode = AccessMode.READ;
                Object val = operands.pop(); // tira da pilha o valor q vai ser inserido
                e.getLvalue().accept(this);
                @SuppressWarnings("unchecked")
                HashMap<String, Object> dict2 = (HashMap<String, Object>) operands.pop();
                dict2.put(e.getId(), val);
                break;
        }
        
    }

    @Override
    public void visit(LValueExp e) {
        Debug.log("Visit LValueExp");

        switch (currentAccessMode) {
            case READ:
                currentAccessMode = AccessMode.READ;
                e.getLvalue().accept(this);
                
                @SuppressWarnings("unchecked")
                HashMap<Integer, Object> dict = (HashMap<Integer, Object>) operands.pop();
                e.getIndex().accept(this); // encontra o indice de acesso do vetor.
                
                Object index = operands.pop();
                
                operands.push(dict.get((Integer) index));
                break;
            case WRITE:
                currentAccessMode = AccessMode.READ;
                Object val = operands.pop(); // tira da pilha o valor q vai ser inserido
                e.getLvalue().accept(this);

                @SuppressWarnings("unchecked")
                HashMap<Integer, Object> dict2 = (HashMap<Integer, Object>) operands.pop();
                e.getIndex().accept(this); // encontra o indice de acesso do vetor.
                
                Object index2 = operands.pop();

                dict2.put((Integer) index2, val);
                break;
        }
    }
    
    public void visit(TrueValue e) {
        Debug.log("Visit TrueValue");
        try {
            operands.push(true);
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(NullValue e) {
        Debug.log("Visit NullValue");
        try {
            operands.push(null);
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(FalseValue e) {
        Debug.log("Visit FalseValue");
        try {
            operands.push(false);
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(FieldLValue e) {
        // Not Check | Falta implementar
    }

    public void visit(IntValue e) {
        // check
        Debug.log("Visit IntValue");
        try {
            operands.push(Integer.valueOf(e.getValue()));
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(FloatValue e) {
        // check
        Debug.log("Visit FloatValue");
        try {
            operands.push(Float.valueOf(e.getValue()));
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(CmdIf e) {
        // check
        Debug.log("Visit CmdIF");
        try {
            e.getCondition().accept(this);
            if ((Boolean) operands.pop()) {
                e.getThenCmd().accept(this);
            } else if (e.getElseCmd() != null) {
                e.getElseCmd().accept(this);
            }
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(CmdIterate e) {
        Debug.log("Visit CmdIterate");
        try {
            if(e.getCondition() instanceof ExpItCond){

                currentAccessMode = AccessMode.READ;
                ((ExpItCond) e.getCondition()).getExpression().accept(this);
                
                int i = (Integer) operands.pop();
                while (i > 0) {
                    e.getBody().accept(this);
                    i--;
                }
            }else{
                currentAccessMode = AccessMode.READ; 
                ((IdItCond) e.getCondition()).getExpression().accept(this);
                String nameVar = ((IdItCond) e.getCondition()).getId();
                                
                Object var = operands.pop();
                
                if(var instanceof Integer){
                    int counter = (int) var;
                    Object oldValue = env.peek().get(nameVar);
                    env.peek().put(nameVar, var);
                    while(counter > 0){
                        e.getBody().accept(this);
                        counter--;
                        env.peek().put(nameVar, counter); // atualiza na memória o valor.
                    }
                    if(oldValue == null)
                        env.peek().remove(nameVar);
                    else
                        env.peek().put(nameVar, oldValue);
                }
                else if(var instanceof HashMap){
                    Object oldValue = env.peek().get(nameVar);
                    HashMap<Integer, Object> aux = (HashMap<Integer,Object>) var;  
                    for (Map.Entry<Integer, Object> entry : aux.entrySet()) {
                        env.peek().put(nameVar, entry.getValue());
                        e.getBody().accept(this);
                    }
                    if(oldValue == null)
                        env.peek().remove(nameVar);
                    else
                        env.peek().put(nameVar, oldValue);
                }
                
            }
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(IdItCond e) {
        // String name = e.getId();
        // e.getExpression().accept(this);
        // env.peek().put(name, operands.pop());
    }

    @Override
    public void visit(ExpItCond e) {
        // e.getExpression().accept(this);
    }

    public void visit(CmdPrint e) {
        // NOT check completed
        Debug.log("Visit CmdPrint");
        try {
            // Quando visita um print, a inteção é ler o valor se houver uma variavel como expressão.
            currentAccessMode = AccessMode.READ;
            e.getExpression().accept(this);
            Object v = operands.pop(); // pega o resultado da expressão.

            if (v instanceof AbstractMap.SimpleEntry) {
                Integer index = (Integer) ((AbstractMap.SimpleEntry<?, ?>) v).getValue();
                String var = (String)  ((AbstractMap.SimpleEntry<?, ?>) v).getKey();
                System.out.print(((int[])env.peek().get(var))[index]);
            }
            else{
                System.out.print(v.toString());
            }
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    /*Função auxiliar pro Read */
    public static Object detectType(String input) {
        // Tenta int
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ignored) {}

        // Tenta float
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException ignored) {}

        // Tenta char
        if (input.length() == 1) {
            return input.charAt(0);
        }

        // Default: retorna como String
        return input;
    }

    @Override
    public void visit(CmdRead p) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String valorLido = scanner.nextLine();
        Object val = detectType(valorLido);
        operands.push(val);
        currentAccessMode = AccessMode.WRITE;
        p.getLvalue().accept(this);
    }

    public void visit(Fun f) {
        Debug.log("Visit Fun");
        // check

        // cria um HashMap para conter o escopo local da função.
        HashMap<String, Object> localEnv = new HashMap<String, Object>();
        
        // se possui parâmetros, adiciono-os com chave e valor no escopo local.
        if(f.getParams() != null) {
            for (int i = f.getParams().getParamList().size() - 1; i >= 0; i--) {
                localEnv.put(f.getParams().getParamList().get(i).getId(), operands.pop());
            }
        }
        
        env.push(localEnv); // adiciono o localEnv no env.
        f.getCmd().accept(this); // chama para aceitar o bloco dentro da função.

        /*Aqui q printa a memória*/
        System.out.println("\n---------------------------CONTEXTO-------------------------------------");
        Object[] keys = env.peek().keySet().toArray();
        for (Object keyObj : keys) {
            String key = (String) keyObj;
            Object value = env.peek().get(key);

            String valueStr;
            if (value != null && value.getClass().isArray()) {
                if (value instanceof int[]) {
                    valueStr = Arrays.toString((int[]) value);
                } else if (value instanceof float[]) {
                    valueStr = Arrays.toString((float[]) value);
                } else if (value instanceof double[]) {
                    valueStr = Arrays.toString((double[]) value);
                } else if (value instanceof char[]) {
                    valueStr = Arrays.toString((char[]) value);
                } else if (value instanceof boolean[]) {
                    valueStr = Arrays.toString((boolean[]) value);
                } else if (value instanceof Object[]) {
                    valueStr = Arrays.deepToString((Object[]) value);
                } else {
                    valueStr = "Unsupported array type";
                }
            } else {
                valueStr = String.valueOf(value);
            }

            System.out.println(key + " : " + valueStr);
        }
        System.out.println("---------------------------CONTEXTO-------------------------------------");

        env.pop();
        retMode = false;
    }


    public void visit(CmdReturn e) {
        Debug.log("Visit CmdReturn");
        List<Object> retornos = new ArrayList<>();
        for(Expr r: e.getExpressions()){
            currentAccessMode = AccessMode.READ;
            r.accept(this);//aqui eu adiciono no operands
            retornos.add(operands.pop()); //aqui eu removo e adiciono na lista
        }
        operands.push(retornos);
        retMode = true;
    }

    /**
     * @param name nome da variavel no hashMap que está no topo do env
     * @param line linha onde ocorreu o erro
     * @param col coluna onde ocorreu o erro
     * @return o valor armazenado no hashmap do topo do env
     */
    public Object getVarFromEnv(String name, int line, int col){
        if (env.isEmpty()) {
            throw new InterpretException("Ambiente de variáveis está vazio: linha " + line + ", coluna " + col);
        }

        Object value = env.peek().get(name); // pego o hashmap do topo e pego o valor associado  a chave com nome name

        if (value == null) {
            throw new InterpretException("Variável <" + name + "> não foi definida: linha "+line+", coluna "+col);
        }

        return value;
    }

    /**
     * @param dataHashMap hashMap que contem todos os atributos de um tipo Registro
     * @param nameAtributo nome do atributo dentro de um Registro
     * @param line linha onde ocorreu o erro
     * @param col coluna onde ocorreu o erro
     * @return retorna o valor associado a nameAtributo do registro
     */
    public Object getAttributeFromDataHashMap(Object dataHashMap, String nameAtributo, int line, int col){
        if (dataHashMap instanceof HashMap<?, ?>) {// certifico que é um hashmap
            @SuppressWarnings("unchecked")
            HashMap<String, Object> map = (HashMap<String, Object>) dataHashMap;
            if(!map.containsKey(nameAtributo)){
                throw new InterpretException("O atributo <"+nameAtributo+"> não existe no registro acessado: linha " + line + ", coluna " + col);
            }
            return map.get(nameAtributo);
        }else {
            throw new InterpretException("Variável <" + dataHashMap + "> não é um registro: linha " + line + ", coluna " + col);
        }
    }

    /**
     * @param dataHashMap hashMap que contem todos os atributos de um tipo Registro
     * @param nameAtributo nome do atributo dentro de um Registro
     * @param valorAtributo valor que sera colocado no atributo
     * @param line linha onde ocorreu o erro
     * @param col coluna onde ocorreu o erro
     */
    public void putAttributeOnDataHashMap(Object dataHashMap, String nameAtributo, Object valorAtributo, int line, int col){
        if (dataHashMap instanceof HashMap<?, ?>) {
            @SuppressWarnings("unchecked")
            HashMap<String, Object> map = (HashMap<String, Object>) dataHashMap;
            if(!map.containsKey(nameAtributo)){
                throw new InterpretException("O atributo <"+nameAtributo+"> não existe no registro acessado: linha " + line + ", coluna " + col);
            }
            map.put(nameAtributo, valorAtributo);
        }else {
            throw new InterpretException("Variável <" + dataHashMap + "> não é um registro: linha " + line + ", coluna " + col);
        }
    }
}
