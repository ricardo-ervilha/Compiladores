package visitors;

import ast.*;
import util.AbstractFunctionData;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class InterpretVisitor extends Visitor {

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

    public InterpretVisitor() {
        env = new Stack<HashMap<String, Object>>();
        env.push(new HashMap<String, Object>());
        funcs = new HashMap<String, Fun>();
        operands = new Stack<Object>();
        retMode = false;
        debug = false;
    }

    public InterpretVisitor(boolean debug) {
        this();
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
        if(main == null){
            throw new RuntimeException( "Não há uma função chamada main... Abortando ! ");
        }
        main.accept(this);
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
        String name = p.getTypeId();
        dataTypesEnv.put(name, new HashMap<>());
        namesStack.push(name);
        for(Node d: p.getDeclFuns()){
            if(d instanceof Decl){
                Decl x = (Decl) d;
                x.accept(this);
            }else if(d instanceof Fun){
                Fun  x = (Fun) d;
                x.accept(this);
            }
        }
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
        String nameVar = namesStack.pop();
        String nameData = namesStack.peek();
        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(TypeBool e) {
        String nameVar = namesStack.pop();
        String nameData = namesStack.peek();
        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(TypeChar e) {
        String nameVar = namesStack.pop();
        String nameData = namesStack.peek();
        dataTypesEnv.get(nameData).put(nameVar, e);
    }

    @Override
    public void visit(TypeFloat e) {
        String nameVar = namesStack.pop();
        String nameData = namesStack.peek();
        dataTypesEnv.get(nameData).put(nameVar, e);
    }

//    @Override
//    public void visit(Fun p) {
//        String nameData = namesStack.peek();
//        String nameFun = p.getID();
//        dataTypesEnv.get(nameData).put(nameFun, p);
//    }

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

    }

    public void visit(Add e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            operands.push(new Integer(esq.intValue() + dir.intValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Sub e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            operands.push(new Integer(esq.intValue() - dir.intValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Mul e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            operands.push(new Integer(esq.intValue() * dir.intValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Div e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            operands.push(new Float(esq.intValue() / dir.intValue()));
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
            operands.push(new Integer(esq.intValue() % dir.intValue()));
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
            operands.push(new Boolean((Boolean) esq && (Boolean) dir));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Lt e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push(new Boolean((Integer) esq < (Integer) dir));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    public void visit(Eq e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            operands.push(new Boolean(operands.pop().equals(operands.pop())));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Diff e) {

    }



    @Override
    public void visit(MinusExpr e) {

    }

    public void visit(NotExpr e) {
        try {
            e.getExpression().accept(this);
            operands.push(new Boolean(!(Boolean) operands.pop()));
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

    @Override
    public void visit(CallFunctionAccess e) {

    }

    @Override
    public void visit(VarExpr e) {

    }

    @Override
    public void visit(ID e) {

    }

    @Override
    public void visit(CharValue e) {

    }

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
            operands.push(new Boolean(true));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(IdLValue e) {

    }

    @Override
    public void visit(NullValue e) {

    }

    public void visit(FalseValue e) {
        try {
            operands.push(new Boolean(false));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(FieldLValue e) {

    }

    public void visit(IntValue e) {
        try {
            operands.push(new Integer(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(LValueExp e) {

    }

    public void visit(FloatValue e) {
        try {
            operands.push(new Float(e.getValue()));
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
            int i = (Integer) operands.pop();;
            while (i >= 0) {
                e.getBody().accept(this);
                i--;
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(IdItCond e) {

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
        if(f.getParams() != null) {
            for (int i = f.getParams().getParamList().size() - 1; i >= 0; i--) {
                localEnv.put(f.getParams().getParamList().get(i).getId(), operands.pop());
            }
        }
        env.push(localEnv);
        f.getCmd().accept(this);
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
        for(Expr r: e.getExpressions()){
            r.accept(this);
        }
        retMode = true;
    }
}
