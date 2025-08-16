/*
 *
 *  * Alunos:
 *  * - Nome: Lucas Silva Santana           Matrícula: 202165092C
 *  * - Nome: Ricardo Ervilha Silva         Matrícula: 202165561C
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

    /**
     * * Hashmap para gerenciar o escopo de funções.
     * Chave: nome da função;
     * Valor: Dicionário com as variáveis, onde a chave é o nome da variável e valor é o valor associado da variável em Lang.
    */
    private Stack<HashMap<String, Object>> env;

    /**
    * * Hashmap para guardar a associação entre nome de funções e o objeto. (Guarda as chaves do env).
    * Exemplo: {'f1': {'a': 12, b: '4'}, 'f2': {'a': 13, c: 4.13}};
    * Com base no exemplo acima, seria: {'f1': ObjectF1, 'f2': ObjectF2}.
    */
    private HashMap<String, Fun> funcs;

    /**
     * * Pilha com os objetos que vão sendo empilhados para salvar o valor, e quando precisa-se operar em algo são retirados do operands.
     */
    private Stack<Object> operands;

    /**
     * * Hashmap para guardar quais tipos foram declarados (Abstratos & Não abstratos) e suas variáveis disponíves somente.
     * Acaba sendo um "template" que será usado para criar as variaveis de dados criados.
     */
    private HashMap<String, HashMap<String, Object>> dataTypesEnv = new HashMap<String, HashMap<String, Object>>();
    private Stack<String> namesStack = new Stack<>(); // Stack para ajudar a percorrer DataDecl

    /*
     * NEW: Enumerate para ajudar a saber quando o acesso a um ID é para recuperar valor (read) ou para atribuir um valor (write).
     */
    enum AccessMode {
        READ, WRITE
    }

    /**
     * * NEW: Flag para ajudar a controlar qual a forma atual de acesso a variável. 
     */
    AccessMode currentAccessMode = null;

    /**
     * * NEW: Similar ao HashMap funcs, mas criei um separado para abstractFuncs que são do tipo abstrato.
     */
    private HashMap<String, FunAbstractData> abstractFuncs;

    /**
     * * NEW: Variável que ajudará a controlar se está atualmente tratando ou não retorno de valores.
     */
    private boolean retMode;

    public InterpretVisitor() {
        /* Inicialização das estruturas de dados e variáveis */
        env = new Stack<HashMap<String, Object>>();
        env.push(new HashMap<String, Object>());
        funcs = new HashMap<String, Fun>();
        abstractFuncs = new HashMap<String, FunAbstractData>();
        operands = new Stack<Object>();
        retMode = false;
    }

    public void visit(Program p) {
        Debug.log("Visit Program");
        
        Node main = null;
        for(Def f : p.getDefinitions()){
            if(f instanceof Fun){
                Fun funcao = (Fun) f;
                funcs.put(funcao.getID(), funcao);
                if(funcao.getID().equals("main")){
                    main = f; // salva a main
                }
            }else {
                f.accept(this); // caso não seja função, e.g: Dado ou Dado abstrato, chama para visitar.
            }
        }

        if(main != null){ // ! P/ caso tenha um programa principal. PS: Semântico já está tratando ?
            main.accept(this);
        }
    }

    @Override
    public void visit(DataDecl p) {
        /**
         * ? data TYID {
         * ?    var1 :: type1;
         * ?    ...
         * ? }
         */
        Debug.log("Visit DataDecl");

        String name = p.getTypeId(); // Captura nome do tipo para ser superchave

        dataTypesEnv.put(name, new HashMap<>()); // Adiciona isso para registrar no dataTypesEnv
        namesStack.push(name); // guardo o nome na pilha para saber depois de qual Registro são as declarações que vão ser visitas
        
        // Percorre as atribuições
        for(Node d: p.getDeclarations()){
            Decl x = (Decl) d;
            x.accept(this);
        }
        
        // irá sobrar na namesStack o nome do tipo, então retira ele para caso tenham outros tipos depois.
        namesStack.pop();
    }

    @Override
    public void visit(AbstractDataDecl p) {
        /**
         * ? abstract data TYID {
         * ?    var1 :: type1;
         * ?    func1(params..): returns {...}
         * ?    ...
         * ? }
         */
        Debug.log("Visit AbstractDataDecl");
        
        // Pego o nome do tipo abstrato, e adiciono um dicionário correspondendo a um template de definição do que é esse tipo para futuras instanciações.
        String name = p.getTypeId(); 
        dataTypesEnv.put(name, new HashMap<>()); // adiciono um hashmap vazio para ser preenchido nas próximas linhas.

        // namesStack é uma pilha para resolver nomes de tipos criados.
        namesStack.push(name);// guardo o nome na pilha para saber depois encontrar corretamente as informações do Registro.
        
        /* Resolução das declarações */
        List<Node> bodyComponents = p.getDeclFuns();
        for(Node n : bodyComponents){
            if(n instanceof Decl){
                // é uma declaração de atributo
                Decl x = (Decl) n;
                x.accept(this);
            }else if(n instanceof FunAbstractData){
                // é uma declaração de função
                FunAbstractData x = (FunAbstractData) n;
                abstractFuncs.put(x.getID(), x); // guardo para acesso futuro no abstractFuncs.
            }
        }

        namesStack.pop(); // remove o último que será o nome do tipo abstrato.
    }

    @Override
    public void visit(Decl p) {
        // Visitando declaração de tipo : serve tanto para data quanto p/ abstract data.
        Debug.log("Visit Decl");
        String nameData = namesStack.peek(); // pego o nome do registro
        String nameVar = p.getId();// pego o nome do atributo do Registro/Tipo Abstrato
        
        dataTypesEnv.get(nameData).put(nameVar, null);//coloco null pq ainda não sei o valor...
        namesStack.push(nameVar);// guardo no namesStack pois ao visitar os Type vou precisar saber o nome do atributo
        
        p.getType().accept(this);
    }

    @Override
    public void visit(TypeInt e) {
        // Int
        /**
         * Documentação também serve para os tipos restantes pois é parecido o código.
         */
        Debug.log("Visit TypeInt");
        String nameVar = namesStack.pop();  // tira o nome da variável q veio do Decl
        String nameData = namesStack.peek(); // pega o nome do Tipo
        dataTypesEnv.get(nameData).put(nameVar, e);// guardando no dataTypesEnv com o tipo nameData o atributo nameVar com tipo sendo o objeto
    }

    @Override
    public void visit(TYID e) {
        // TYID (e.g: Ponto, Pessoa, Reta,...). Documentação em TypeInt
        Debug.log("Visit TYID");
        String nameVar = namesStack.pop();
        String nameData = namesStack.peek();
        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(TypeFloat e) {
        // Float. Documentação em TypeInt
        Debug.log("Visit TypeFloat");
        String nameVar = namesStack.pop();
        String nameData = namesStack.peek();
        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(TypeBool e) {
        // Bool. Documentação em TypeInt
        Debug.log("Visit TypeBool");
        String nameVar = namesStack.pop();
        String nameData = namesStack.peek();
        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(TypeChar e) {
        // Char. Documentação em TypeInt
        Debug.log("Visit TypeChar");
        String nameVar = namesStack.pop();
        String nameData = namesStack.peek();
        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    
    @Override
    public void visit(ArrayType e) {
        // type '[' ']'
        Debug.log("Visit ArrayType");
        String nameVar = namesStack.pop();
        String nameData = namesStack.peek();
        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(Params e) {
        //pass
    }

    @Override
    public void visit(Param p){
        //pass
    }

    @Override
    public void visit(Block b) {
        Debug.log("Visit Block");
        for(Cmd c: b.getCommands()) { // visita cada comando do block.
            c.accept(this);
        }
    }

    @Override
    public void visit(CmdAssign p) {
        /*
         * Comando de atribuição: <var> = <expr>
         */
        Debug.log("Visit CmdAssign");
        
        // SERVE PARA O RESTANTE ONDE É USADO: retMode true significa que em algum momento já chegou em um retorno, então o restante das instruções deve ser ignorado. Por exemplo:
        /**
         * . teste(){
         * .    a = 12; -- retMode é false (EXECUTA)
         * .    b = 13; -- retMode é false (EXECUTA)
         * .    return a + b; -- retMode vira true (EXECUTA)
         * .    c = 13; -- retMode é true (IGNORA)
         * .    print c; -- retMode é true (IGNORA)
         * .}
         */
        if(!retMode){  
            // visita o lado direito do assign, então é pra recuperar valor
            currentAccessMode = AccessMode.READ;
            p.getExpression().accept(this);

            // visita o lado esquerdo, então é para escrever o resultado lá
            currentAccessMode = AccessMode.WRITE; 
            p.getLvalue().accept(this);
        }
    }

    public void visit(Add e) {
        Debug.log("Visit Add");
        try {
            // Visita os operandos da esquerda e da direita na intenção de recuperar valor. Mesmo esquema nas operações restantes...
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

            Object dir = operands.pop();
            Object esq = operands.pop();

            Number esqNum = (Number) esq;
            Number dirNum = (Number) dir;
            
            if(esq instanceof Float || dir instanceof Float){
                operands.push(esqNum.floatValue() - dirNum.floatValue());
            }else{
                operands.push(esqNum.intValue() - dirNum.intValue());
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

            // Verifica se o divisor é zero
            if (dirNum.intValue() == 0 || dirNum.floatValue() == 0) {
                throw new ArithmeticException("Erro aritmético: divisão por zero.");
            }

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

        }catch (Exception ex) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + ex.getMessage());
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
            // ! Converto os dois para Float, pois no final serão convertidos para Boolean. Isso trata o caso de tipos diferentes.
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

            operands.push(Objects.equals(val1, val2));
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
            Object val1 = operands.pop();
            Object val2 = operands.pop();
            operands.push(!Objects.equals(val1, val2));
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(MinusExpr e) {
        // * -<val>
        Debug.log("Visit MinusExpr");
        e.getExpr().accept(this);
        int val = (int) operands.pop();
        try {
            operands.push(-val);
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
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
        //pass
    }

    @Override
    public void visit(ArrayExpr e){
        /**
         * * 'new' type ('[' e=exp ']')
         */
        Debug.log("Visit ArrayExpr");
        
        HashMap<Integer, Object> array = new HashMap<>(); // . empilha o array como um dicionário
        operands.push(array);
    }

    
    @Override
    public void visit(VarExpr e) {
        // * Declaração de variável: 'new' btype
        Debug.log("Visit VarExpr");
        
        Object type = e.getType(); // resolve tipo

        if(type instanceof TypeInt || type instanceof TypeFloat || type instanceof TypeBool || type instanceof TypeChar){
            // para tipos simples, basta adicionar nulo.
            // TODO: aparentemnete por causa do semântico não pode new Int. Então isso ficou inútil...
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

            // pega nome do tipo
            String nomeTipo = ((TYID) type).getName();

            // pega o template do dataTypesEnv
            HashMap<String, Object> internalTyid = dataTypesEnv.get(nomeTipo);
            
            // dicionário para guardar as variáveis de quem será instanciado
            HashMap<String, Object> assembled = new HashMap<>();

            // percorre o dicionário internalTyid
            for (Map.Entry<String, Object> entry : internalTyid.entrySet()) {
                assembled.put(entry.getKey(), null); // coloca nome da variável e nulo.
            }
            
            operands.push(assembled); // quando chama pra fazer o new ele joga no operands como se fosse expressão...
        }
    }

    
    @Override
    public void visit(CharValue e) {
        Debug.log("Visit CharValue");
        String value = e.getValue().replaceAll("'", ""); // TIRA AS ASPAS SIMPLES QUEM VEM DO .lan
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
            // caso do número ascii
            int asciiCode = Integer.parseInt(value.substring(1), 10); 
            charRet = (char) asciiCode;
        } 
        else{
            charRet = value.charAt(0); // casos mais simples tipo 'a', '0', '#', '!'
        }
        try {
            operands.push(charRet);
        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(CallFunctionAccess e) {
        /**
         * * ID '(' (exps)? ')' '[' exp ']': chamada de função acessando posição com []
         */
        Debug.log("Visit CallFunctionAccess");
        try {
            // pegar a função pelo name no hashmap de funções considerando ela estar em funcs ou abstractFuncs
            Def f;
            if(funcs.containsKey(e.getFunctionName()))
                f = funcs.get(e.getFunctionName());
            else if(abstractFuncs.containsKey(e.getFunctionName()))
                f = abstractFuncs.get(e.getFunctionName());
            else
                throw new InterpretException("Não existe essa função!"); // ! semântico precisa pegar... Mais para garantir

            if (f != null) {
                if(e.getExps() != null){ // parametros vazios.
                    for (Expr exp : e.getExps().getExpressions()) {
                        currentAccessMode = AccessMode.READ;
                        exp.accept(this); // empilho cada valor avaliado pela expressão na pilha de operandos
                    }
                }
                f.accept(this);// visito o Fun

                ArrayList retornos = (ArrayList) operands.pop(); // pega a lista de retornos

                e.getExp().accept(this);// avaliar a expressão que pega o indice
                int idx = (int) operands.pop(); // pegar o indice do topo da pilha

                operands.push(retornos.get(idx));// pegar o elemento pelo indice avaliado

                retMode = false; // seta para false o retMode
            } else {
                throw new InterpretException(
                        " (" + e.getLine() + ", " + e.getCol() + ") Função não definida " + e.getFunctionName());
            }

        } catch (Exception x) {
            throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }

    }
    
    public void visit(CmdFuncCall e) {
        /*
         * * ID '(' exps? ')' <var1, var2, ...>: chamada passando variável 
         */
        Debug.log("Visit CmdFuncCall");
        
        try {
            Def f;
            // pegando função das ED's que guardam funções
            if(funcs.containsKey(e.getId()))
                f = funcs.get(e.getId());
            else if(abstractFuncs.containsKey(e.getId()))
                f = abstractFuncs.get(e.getId());
            else
                throw new InterpretException("Não existe essa função!");
            if (f != null) {
                if(e.getExps() != null){ // parametros vazios.
                    for (Expr exp : e.getExps().getExpressions()) {
                        currentAccessMode = AccessMode.READ;
                        exp.accept(this); // empilho cada valor avaliado pela expressão na pilha de operandos
                    }
                }
                f.accept(this);
                
                List<LValue> lvalues = e.getLvalues();
                if (!lvalues.isEmpty()) {
                    @SuppressWarnings("unchecked")
                    List<Object> retObjs = (List<Object>) operands.pop(); // pega o retorno
                    
                    for (int i = 0; i < retObjs.size(); i++) {
                        currentAccessMode = AccessMode.WRITE; // escreverá valor na variavel
                        operands.push(retObjs.get(i)); // joga já no operandos o valor q vai escrever
                        lvalues.get(i).accept(this); // chama para resolver lvalue
                    }
                }

                retMode = false; // seta para false o retMode

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
        /* 
        * ATRIBUIÇÃO SIMPLES: a = 12
        */
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
    
    @Override
    public void visit(IdLValue e) {
        /* 
        * ACESSO A ATRIBUTO: lvalue.ID
        */
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
        Debug.log("Visit CmdIF");
        if(!retMode){
            try {
                currentAccessMode = AccessMode.READ; // tratando o caso especial do teste12.lan... 
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
    }

    public void visit(CmdIterate e) {
        Debug.log("Visit CmdIterate");
        if(!retMode){
            try {
                if (e.getCondition() instanceof ExpItCond) { // tratando iterate(12) ou iterate(a)

                    currentAccessMode = AccessMode.READ;
                    ((ExpItCond) e.getCondition()).getExpression().accept(this);

                    Object objeto = operands.pop();

                    int i;
                    if(objeto instanceof Integer){
                        i = (Integer) objeto;

                    }else if (objeto instanceof HashMap){
                        i = ((HashMap<?, ?>) objeto).size();
                    }else {
                        throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") Erro no iterate, tentando iterar em um objeto desconhecido...");
                    }

                    int j = 0; // contador auxiliar
                    while (j < i) {
                        e.getBody().accept(this);
                        j++;
                    }
                } else { // tratando iterate(i: x + 1) ou iterate(i : v) onde v é array...

                    currentAccessMode = AccessMode.READ;
                    ((IdItCond) e.getCondition()).getExpression().accept(this);
                    String nameVar = ((IdItCond) e.getCondition()).getId();

                    Object var = operands.pop();

                    if (var instanceof Integer) {
                        int counter = (int) var;
                        // Object oldValue = env.peek().get(nameVar);
                        int j = 0;
                        while (j < counter) {
                            env.peek().put(nameVar,j);
                            e.getBody().accept(this);
                            j++;
                        }
                        env.peek().put(nameVar,j);
                        // if (oldValue == null)
                        //     env.peek().remove(nameVar);
                        // else
                        //     env.peek().put(nameVar, oldValue); // ! não precisa RECUPERAR VALOR POIS PARECE NAO CRIAR NOVO ESCOPO (EXEMPLO itervarDec.lan)
                    } else if (var instanceof HashMap) { // * iterando em um array...
                        Object oldValue = env.peek().get(nameVar);
                        HashMap<Integer, Object> aux = (HashMap<Integer, Object>) var;
                        for (Map.Entry<Integer, Object> entry : aux.entrySet()) {
                            env.peek().put(nameVar, entry.getValue());
                            e.getBody().accept(this);
                        }
                        if (oldValue == null)
                            env.peek().remove(nameVar);
                        else
                            env.peek().put(nameVar, oldValue);
                    }

                }
            } catch (Exception x) {
                throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
            }
        }
    }

    @Override
    public void visit(IdItCond e) {
        //pass
    }

    @Override
    public void visit(ExpItCond e) {
        //pass
    }

    public void visit(CmdPrint e) {
        Debug.log("Visit CmdPrint");
        if(!retMode){
            try {
                // Quando visita um print, a inteção é ler o valor se houver uma variavel como
                // expressão.
                currentAccessMode = AccessMode.READ;
                e.getExpression().accept(this);
                Object v = operands.pop(); // pega o resultado da expressão.
                if (v instanceof AbstractMap.SimpleEntry) {
                    Integer index = (Integer) ((AbstractMap.SimpleEntry<?, ?>) v).getValue();
                    String var = (String) ((AbstractMap.SimpleEntry<?, ?>) v).getKey();
                    System.out.print(((int[]) env.peek().get(var))[index]);
                } else {
                    if(v != null)
                        System.out.print(v.toString());
                    else
                        System.out.println("NULL");
                }
            } catch (Exception x) {
                throw new InterpretException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
            }
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
        Debug.log("Visit CmdRead");
        if(!retMode){
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            String valorLido = scanner.nextLine();
            Object val = detectType(valorLido);
            operands.push(val);
            currentAccessMode = AccessMode.WRITE;
            p.getLvalue().accept(this);
        }
    }

    @Override
    public void visit(FunAbstractData p) {
        Debug.log("Visit FunAbstractData");

        // hashmap com escopo local da função.
        HashMap<String, Object> localEnv = new HashMap<String, Object>();

        // se possui parâmetros, adiciono-os com chave e valor no escopo local.
        if(p.getParams() != null) {
            for (int i = p.getParams().getParamList().size() - 1; i >= 0; i--) {
                localEnv.put(p.getParams().getParamList().get(i).getId(), operands.pop());
            }
        }

        env.push(localEnv); // adiciono o localEnv no env.
        
        p.getCmd().accept(this); // chama para aceitar o bloco dentro da função.
        
        // . Aqui q printa a memória
        // System.out.println("\n-----------------------" + "CONTEXTO da " + p.getID() + "-----------------------");
        // Object[] keys = env.peek().keySet().toArray();
        // for (Object keyObj : keys) {
        //     String key = (String) keyObj;
        //     Object value = env.peek().get(key);

        //     String valueStr;
        //     if (value != null && value.getClass().isArray()) {
        //         if (value instanceof int[]) {
        //             valueStr = Arrays.toString((int[]) value);
        //         } else if (value instanceof float[]) {
        //             valueStr = Arrays.toString((float[]) value);
        //         } else if (value instanceof double[]) {
        //             valueStr = Arrays.toString((double[]) value);
        //         } else if (value instanceof char[]) {
        //             valueStr = Arrays.toString((char[]) value);
        //         } else if (value instanceof boolean[]) {
        //             valueStr = Arrays.toString((boolean[]) value);
        //         } else if (value instanceof Object[]) {
        //             valueStr = Arrays.deepToString((Object[]) value);
        //         } else {
        //             valueStr = "Unsupported array type";
        //         }
        //     } else {
        //         valueStr = String.valueOf(value);
        //     }

        //     System.out.println(key + " : " + valueStr);
        // }
        // System.out.println("---------------------------CONTEXTO-------------------------------------");

        env.pop();
        retMode = false;
    }

    public void visit(Fun f) {
        Debug.log("Visit Fun");

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

        // . Aqui q printa a memória
        // System.out.println("\n-----------------------" + "CONTEXTO da " + f.getID() + "-----------------------");
        // Object[] keys = env.peek().keySet().toArray();
        // for (Object keyObj : keys) {
        //     String key = (String) keyObj;
        //     Object value = env.peek().get(key);

        //     String valueStr;
        //     if (value != null && value.getClass().isArray()) {
        //         if (value instanceof int[]) {
        //             valueStr = Arrays.toString((int[]) value);
        //         } else if (value instanceof float[]) {
        //             valueStr = Arrays.toString((float[]) value);
        //         } else if (value instanceof double[]) {
        //             valueStr = Arrays.toString((double[]) value);
        //         } else if (value instanceof char[]) {
        //             valueStr = Arrays.toString((char[]) value);
        //         } else if (value instanceof boolean[]) {
        //             valueStr = Arrays.toString((boolean[]) value);
        //         } else if (value instanceof Object[]) {
        //             valueStr = Arrays.deepToString((Object[]) value);
        //         } else {
        //             valueStr = "Unsupported array type";
        //         }
        //     } else {
        //         valueStr = String.valueOf(value);
        //     }

        //     System.out.println(key + " : " + valueStr);
        // }
        // System.out.println("---------------------------CONTEXTO-------------------------------------");

        env.pop();
        retMode = false;
    }


    public void visit(CmdReturn e) {
        Debug.log("Visit CmdReturn");
        if(!retMode){
            List<Object> retornos = new ArrayList<>();
            for(Expr r: e.getExpressions()){
                currentAccessMode = AccessMode.READ;
                r.accept(this);//aqui eu adiciono no operands
                retornos.add(operands.pop()); //aqui eu removo e adiciono na lista
            }
            operands.push(retornos);
            retMode = true; // seta true para ignorar o resto
        }
    }
}
