package visitors;

import ast.*;
import util.LocalEnv;
import util.STyArr;
import util.STyBool;
import util.STyChar;
import util.STyData;
import util.STyFloat;
import util.STyInt;
import util.SType;
import util.TyEnv;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.*;

import javax.management.monitor.StringMonitorMBean;
import javax.print.attribute.HashAttributeSet;

import util.Debug;

public class CPPVisitor extends Visitor{
    // variável global que pega os templates
    private STGroup groupTemplate;

    HashMap<String, String> MapNameToLength;
    String nameArray;
    String length;
    String currentFuncName;

    // variável global para saber variaveis e tipos
    TyEnv<LocalEnv<SType>> env;

    // lista de funções do programa
    ArrayList<ST> funcs;
    ArrayList<ST> data_declarations;

    ST type;
    ST stmt; // GERAL
    ST expr; // GUARDA RESUTLADO DAS EXPRESSOES

    // VARIAVEL GLOBAL QUE TEM A STRING DO QUE É UM PARAMETRO (nome + stmt_type)
    ST param;

    // VARIAVEL GLOBAL QUE TEM A STRING DO BLOCO ATUAL de comandos
    ArrayList<ST> blockCmds;

    /* para os laços, coloco um nome diferente */
    String var_name =  "__while_var_";
    int id_var_loop = 0; // gerencia a quantidade de laços aninhados

    public CPPVisitor(TyEnv<LocalEnv<SType>> env){
        groupTemplate = new STGroupFile("./template/cpp.stg");
        this.env = env;
        this.MapNameToLength = new HashMap<String, String>();
    }

    /* Função AUXILIAR para recuperar o tipo na hora de declarar variaveis */
    public String recoverType(SType s){
        if(s instanceof STyInt)
            return "int";
        else if(s instanceof STyFloat)
            return "float";
        else if(s instanceof STyChar)
            return "char";
        else if(s instanceof STyBool)
            return "bool";
        else if(s instanceof STyArr){
            
            SType aux = ((STyArr) s);
            String pointers = "";
            while(aux instanceof STyArr){
                pointers = pointers.concat("*");
                aux = ((STyArr)aux).getElemType();
            }
            return recoverType(aux).concat(pointers);
        }else if(s instanceof STyData){
            return ((STyData)s).getTypeName().concat("*");
        }
        return "";
    }

    @Override
    public void visit(Program p){
        Debug.log("Visit Program");
        
        ST template = groupTemplate.getInstanceOf("program");
        
        // lista global guardando a string de todas as funções.
        funcs = new ArrayList<ST>();
        data_declarations = new ArrayList<ST>();

        // declarações de funções por causa do C++
        ArrayList<ST> decl_funcs = new ArrayList<ST>(); 

        for(Def f: p.getDefinitions()){
            if(!(f instanceof Fun)){
                f.accept(this);
            }            
        }

        for(Def f : p.getDefinitions()){
            if(f instanceof Fun){
                ST aux = groupTemplate.getInstanceOf("decl_func");
                
                Fun funcao = (Fun) f;
                
                // TRATO o nome da main do Lang
                String name = funcao.getID();
                if(name.equals("main"))
                    name = "main_aux";
                aux.add("name", name);
                
                // trata retornos...
                ArrayList<ST> types = new ArrayList<ST>();
                for(Type t : funcao.getReturnTypes()){
                    t.accept(this);
                    types.add(type);
                }
                aux.add("types", types); 

                // trata parametros...
                ArrayList<ST> params = new ArrayList<ST>();
                if(funcao.getParams() != null){
                    for (Param parametro : funcao.getParams().getParamList()) {
                        parametro.accept(this);
                        params.add(param);
                    }
                }
                aux.add("params", params);

                decl_funcs.add(aux); // FINALMENTE RESOLVE as declarações
            }
        }

        // resolve cabeçalho e corpo das funções
        for(Def f : p.getDefinitions()){
            if(f instanceof Fun){
                f.accept(this);
            }
        }

        template.add("deta_decls", data_declarations);
        template.add("decl_funcs", decl_funcs);
        template.add("funcs", funcs);

        System.out.println(template.render());
    }

    @Override
    public  void visit(Fun p){
        Debug.log("Visit Fun");

        ST fun = groupTemplate.getInstanceOf("func");
        
        // faz uma troca do main por main_aux
        if(p.getID().equals("main"))
            fun.add("name", "main_aux"); 
        else
            fun.add("name", p.getID());

        currentFuncName = p.getID(); // SALVA PARA O ITERATE

        // pega o ambiente especifico dessa função
        LocalEnv<SType> local = env.get(p.getID());
		Set<String> keys = local.getKeys();

        // pega os retornos
        List<Type> returns = p.getReturnTypes();

        // lista para guardar os tipos que resolve dps de visitar
        ArrayList<ST> types = new ArrayList<ST>();
        for(Type t : returns){
            t.accept(this);
            types.add(type);
        }
        fun.add("types", types); // joga no template as strings


        ArrayList<ST> params = new ArrayList<ST>();
        if(p.getParams() != null){
            for (Param parametro : p.getParams().getParamList()) {
                keys.remove(parametro.getId()); // tira o parametro pois nn é variavel do programa
                parametro.accept(this);
                params.add(param);
            }
        }
        fun.add("params", params);

        
        /* Declarando todas as variáveis que serão utilizadas na função... */
        ArrayList<ST> vardecls = new ArrayList<ST>(); 
        for (String key : keys) {
            String type_aux = "";
            
            type_aux = recoverType(local.get(key)); // recupera o tipo
            
            ST declaration = groupTemplate.getInstanceOf("var_decl")
                .add("name", key)
                .add("type", type_aux);
            
            vardecls.add(declaration);
        }
        fun.add("vardecls", vardecls); // adiciona declaração de VARIAVEIS



        // chama para resolver os comandos
        blockCmds = new ArrayList<ST>();
        p.getCmd().accept(this);

        

        fun.add("blockCmds", blockCmds);
        
        funcs.add(fun);
    }

    @Override
    public  void visit(Param e){
        Debug.log("Visit Param");
        param = groupTemplate.getInstanceOf("param");
        e.getType().accept(this);
		param.add("name", e.getId());
		param.add("type", type);
    }

    @Override
    public  void visit(Block b){
        Debug.log("Visit Block");
        for(Cmd c: b.getCommands()) {
            c.accept(this); // chama para aceitar um stmt
            blockCmds.add(stmt);
        }
    }

    @Override
    public  void visit(TYID e){
        Debug.log("Visit TYID");
        type = groupTemplate.getInstanceOf("id");
        type.add("name", e.getName().concat("*"));
    }

    @Override
    public  void visit(TypeBool e){
        Debug.log("Visit TypeBool");
        type = groupTemplate.getInstanceOf("bool_type");
    }
    
    @Override
    public  void visit(TypeChar e){
        Debug.log("Visit TypeChar");
        type = groupTemplate.getInstanceOf("char_type");
    }
    
    @Override
    public  void visit(TypeFloat e){
        Debug.log("Visit TypeFloat");
        type = groupTemplate.getInstanceOf("float_type");
    }
    
    @Override
    public  void visit(TypeInt e){
        Debug.log("Visit TypeInt");
        type = groupTemplate.getInstanceOf("int_type");
    }

    @Override
    public void visit(DataDecl p){
        Debug.log("Visit DataDecl");

        ST aux = groupTemplate.getInstanceOf("struct_def");
        aux.add("name", p.getTypeId());
        ArrayList<ST> dcls_aux = new ArrayList<ST>();
        for(Node n : p.getDeclarations()){
            n.accept(this);
            dcls_aux.add(stmt);
        }
        aux.add("var_decls", dcls_aux);
        
        data_declarations.add(aux);
    }

    @Override
    public  void visit(AbstractDataDecl p){
        Debug.log("Visit AbstractDataDecl");
        
        ST aux = groupTemplate.getInstanceOf("class_def");
        aux.add("name", p.getTypeId());
        ArrayList<ST> dcls_aux = new ArrayList<ST>();
        ArrayList<ST> abstract_funs_aux = new ArrayList<ST>();
        for(Node n : p.getDeclFuns()){
            if(n instanceof Decl){
                n.accept(this);
                dcls_aux.add(stmt);
            }else{
                n.accept(this);
                abstract_funs_aux.add(stmt);
            }
        }
        aux.add("var_decls", dcls_aux);
        aux.add("abstract_funs", abstract_funs_aux);
        
        data_declarations.add(aux);
    }

    @Override
    public  void visit(Decl p)
    {
        Debug.log("Visit Decl");
        ST aux = groupTemplate.getInstanceOf("var_decl");
        aux.add("name", p.getId());
        p.getType().accept(this);
        aux.add("type", type);
        stmt = aux;
    }

    @Override
    public void visit(FunAbstractData f){
        Debug.log("Visit FunAbstactData");
        ST fun = groupTemplate.getInstanceOf("func");
        
        // pega o ambiente especifico dessa função
        LocalEnv<SType> local = env.get(f.getID());
        Set<String> keys = local.getKeys();

        // pega os retornos
        List<Type> returns = f.getReturnTypes();

        // lista para guardar os tipos que resolve dps de visitar
        ArrayList<ST> types = new ArrayList<ST>();
        for(Type t : returns){
            t.accept(this);
            types.add(type);
        }
        fun.add("types", types); // joga no template as strings


        ArrayList<ST> params = new ArrayList<ST>();
        if(f.getParams() != null){
            for (Param parametro : f.getParams().getParamList()) {
                keys.remove(parametro.getId()); // tira o parametro pois nn é variavel do programa
                parametro.accept(this);
                params.add(param);
            }
        }
        fun.add("params", params);

        
        /* Declarando todas as variáveis que serão utilizadas na função... */
        ArrayList<ST> vardecls = new ArrayList<ST>(); 
        for (String key : keys) {
            String type_aux = "";
            
            type_aux = recoverType(local.get(key)); // recupera o tipo
            
            ST declaration = groupTemplate.getInstanceOf("var_decl")
                .add("name", key)
                .add("type", type_aux);
            
            vardecls.add(declaration);
        }
        fun.add("vardecls", vardecls); // adiciona declaração de VARIAVEIS


        // chama para resolver os comandos
        blockCmds = new ArrayList<ST>();
        f.getCmd().accept(this);
        
        fun.add("blockCmds", blockCmds);
        
        stmt = fun;
    }

    @Override 
    public void visit(Params p){
        // nada
    }


    @Override
    public  void visit(ArrayExpr e){
        Debug.log("Visit ArrayExpr");
        
        ST expr_arr = groupTemplate.getInstanceOf("new_array");
        
        e.getType().accept(this);
        expr_arr.add("type", type);
        
        e.getExp().accept(this);
        expr_arr.add("index", expr);
        length = expr.render();
        
        expr = expr_arr;

    }

    @Override
    public  void visit(ArrayType e){
        Debug.log("Visit ArrayType");
        e.getType().accept(this);
        ST aux = type; // guarda o que está atualmente em type
        type = groupTemplate.getInstanceOf("stmt_type");
        type.add("stmt1", aux);
        type.add("stmt2", groupTemplate.getInstanceOf("pointer"));
        // concatena o que tinha antes com *
    }

    @Override
    public void visit(CmdAssign p){
        expr = null;
        Debug.log("Visit CmdAssign");
        stmt = groupTemplate.getInstanceOf("attr");

        p.getLvalue().accept(this);
        nameArray = expr.render();
        stmt.add("var", expr);
        
        expr = null;
        p.getExpression().accept(this);
        stmt.add("expr", expr);

        if(p.getExpression() instanceof ArrayExpr){
            MapNameToLength.put(nameArray, length);
        }

    }

    @Override
    public  void visit(CmdFuncCall p)
    {
        stmt = groupTemplate.getInstanceOf("func_call");
        ArrayList<ST> vars = new ArrayList<ST>();
        int id = 0;
        if(!p.getLvalues().isEmpty()){
                for(LValue l : p.getLvalues()){
                expr = null;
                l.accept(this);
                ST aux = groupTemplate.getInstanceOf("var_unpacking");
                aux.add("var", expr);
                aux.add("id", id);
                id++;
                vars.add(aux);
            }  
            stmt.add("flag", "");
        }
        
        ArrayList<ST> param_exprs = new ArrayList<>();
        for(Expr q : p.getExps().getExpressions()){
            expr = null;
            q.accept(this);
            param_exprs.add(expr);
        }

       
        stmt.add("name", p.getId());
        stmt.add("params", param_exprs);
        stmt.add("vars", vars); 
    }

    @Override
    public void visit(CmdIf p) {
        expr = null;
        Debug.log("Visit CmdIF");

        ST aux = groupTemplate.getInstanceOf("if");
        p.getCondition().accept(this);
        aux.add("expr", expr);

        ArrayList<ST> aux_cmdsBlock = blockCmds; // chega blocos parciais para ele

        if(p.getThenCmd() instanceof Block){
            blockCmds = new ArrayList<ST>(); // crio o novo bloco desse if e else
            p.getThenCmd().accept(this);
            aux.add("thn", blockCmds);
        }else{
            p.getThenCmd().accept(this);
            aux.add("thn", stmt);
        }

        if(p.getElseCmd() != null){
            if(p.getElseCmd() instanceof Block){
                blockCmds = new ArrayList<ST>();
                p.getElseCmd().accept(this);
                aux.add("els", blockCmds);
            }else{
                p.getElseCmd().accept(this);
                aux.add("els", stmt);
            }
        }

        stmt = aux;
        blockCmds = aux_cmdsBlock;
    }

    @Override
    public void visit(CmdPrint p){
        Debug.log("Visit CmdPrint");
        ST aux = groupTemplate.getInstanceOf("print");
        expr = null;
        p.getExpression().accept(this);
        aux.add("expr", expr);
        stmt = aux;
    }

    @Override
    public  void visit(CmdRead p){
        expr = null;
        Debug.log("Visit CmdRead");
        ST aux = groupTemplate.getInstanceOf("read");
        p.getLvalue().accept(this);
        aux.add("expr", expr);
        stmt = aux;
    }

    @Override
    public  void visit(CmdReturn p){
        ArrayList<ST> list_exprs = new ArrayList<ST>();
        for(Expr r: p.getExpressions()){
            expr = null;
            r.accept(this);
            list_exprs.add(expr);
        }
        stmt = groupTemplate.getInstanceOf("return");
        stmt.add("list_exprs", list_exprs);
    }

    @Override
    public  void visit(CmdIterate p){
        expr = null;
        Debug.log("Visit CmdIterate");
        ArrayList<ST> aux_cmdsBlock = blockCmds; // chega blocos parciais para ele
        ST aux = null; // VAI ARMAZENAR A STRING
        if (p.getCondition() instanceof ExpItCond) {
            // caso seja loop com numero tipo while(10)

            aux = groupTemplate.getInstanceOf("while");
            p.getCondition().accept(this);
            aux.add("type", ""); // faz adicionar tipo
            
            aux.add("expr", expr);
            
            aux.add("var_name", var_name + Integer.toString(id_var_loop)); // usa um nome criado
            
            id_var_loop = id_var_loop + 1;
            blockCmds = new ArrayList<ST>(); // crio novo bloco pra esse iterate
            p.getBody().accept(this);
            
            aux.add("cmds", blockCmds);
            id_var_loop = id_var_loop - 1;
        }else{
            // loop tem id
            
            IdItCond q = (IdItCond) p.getCondition();
            q.getExpression().accept(this); // RESOLVE DE UMA VEZ
            if(env.get(currentFuncName).get(expr.render()) instanceof STyArr){
                aux = groupTemplate.getInstanceOf("while_array");
                aux.add("type", ""); // faz adicionar tipo

                aux.add("loop_name", q.getId()); // variavel q será usada no loop
                aux.add("aux_loop", var_name + Integer.toString(id_var_loop)); // variavel auxiliar do for
                id_var_loop = id_var_loop + 1;

                
                aux.add("array_name", expr); // adiciona nome do array

                aux.add("length", MapNameToLength.get(expr.render()));

                blockCmds = new ArrayList<ST>(); // crio novo bloco pra esse iterate
                p.getBody().accept(this);
                
                aux.add("cmds", blockCmds);

            }else{
                aux = groupTemplate.getInstanceOf("while");
                aux.add("var_name", q.getId());
            
                aux.add("expr", expr);
                
                blockCmds = new ArrayList<ST>(); // crio novo bloco pra esse iterate
                p.getBody().accept(this);
                
                aux.add("cmds", blockCmds);
            }
        }

        blockCmds = aux_cmdsBlock;
        stmt = aux;
    }

    @Override
    public void visit(Add e){
        ST aux = groupTemplate.getInstanceOf("add_expr");
        
        expr = null;
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        
        expr = null;
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        
        expr = aux;
    }

    @Override
    public void visit(Sub e){
        ST aux = groupTemplate.getInstanceOf("sub_expr");

        expr = null;
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        
        expr = null;
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        
        expr = aux;
    }

    @Override
    public void visit(Mul e){
        ST aux = groupTemplate.getInstanceOf("mul_expr");

        expr = null;
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        
        expr = null;
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        
        expr = aux;
    }

    @Override
    public void visit(Div e){
        ST aux = groupTemplate.getInstanceOf("div_expr");

        expr = null;
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        
        expr = null;
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        
        expr = aux;
    }

    @Override
    public void visit(Mod e){
        ST aux = groupTemplate.getInstanceOf("mod_expr");

        expr = null;
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        
        expr = null;
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        
        expr = aux;
    }

    @Override
    public void visit(Lt e){
        ST aux = groupTemplate.getInstanceOf("lt_expr");

        expr = null;
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        
        expr = null;
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        
        expr = aux;
    }

    @Override
    public void visit(And e){
        ST aux = groupTemplate.getInstanceOf("and_expr");

        expr = null;
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        
        expr = null;
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        
        expr = aux;
    }

    @Override
    public void visit(Eq e){
        ST aux = groupTemplate.getInstanceOf("equals_expr");

        expr = null;
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        
        expr = null;
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        
        expr = aux;
    }

    @Override
    public void visit(Diff e){
       ST aux = groupTemplate.getInstanceOf("diff_expr");

       expr = null;
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        
        expr = null;
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        
        expr = aux;
    }

    @Override
    public void visit(MinusExpr e){
       ST aux = groupTemplate.getInstanceOf("minus_expr");
       expr = null;
       e.getExpr().accept(this);
       aux.add("expr", expr);
       expr = aux;
    }

    @Override
    public void visit(NotExpr e){
        ST aux = groupTemplate.getInstanceOf("not_expr");
        expr = null;
       e.getExpression().accept(this);
       aux.add("expr", expr);
       expr = aux;
    }

    
    @Override
    public  void visit(ExpItCond e){
        e.getExpression().accept(this);
        ST aux = groupTemplate.getInstanceOf("int_expr");
        aux.add("value", expr);
        expr = aux;
    }
    

    @Override
    public  void visit(Exps e){
        
    }
    
    
    @Override
    public  void visit(CallFunctionAccess e){
        ST aux = groupTemplate.getInstanceOf("func_call_access");
        aux.add("name", e.getFunctionName());

        ArrayList<ST> param_exprs = new ArrayList<>();
        for(Expr q : e.getExps().getExpressions()){
            expr = null;
            q.accept(this);
            param_exprs.add(expr);
        }
        aux.add("params", param_exprs);

        expr = null;
        e.getExp().accept(this);
        aux.add("index", expr);

        expr = aux;
    }
    
    @Override
    public  void visit(VarExpr e){
        e.getType().accept(this);
        ST aux = groupTemplate.getInstanceOf("new_var");
        aux.add("type", type);
        expr = aux;
    }
        

    @Override
    public  void visit(CharValue e){
        Debug.log("Visit CharValue");
        String v = e.getValue();
        if (v.matches("'\\\\[0-7]{3}'")) {
                expr = groupTemplate.getInstanceOf("char_expr_num");
                expr.add("value", Integer.parseInt(v.replace("'", "").replace("\\", "")));
        } else {
                expr = groupTemplate.getInstanceOf("char_expr");
                expr.add("value", v);
        }
    }
    
    @Override
    public void visit(FalseValue e){
        Debug.log("Visit FalseValue");
        expr = groupTemplate.getInstanceOf("boolean_expr");
        expr.add("value", false);
    }

    @Override
    public  void visit(TrueValue e){
        Debug.log("Visit TrueValue");
        expr = groupTemplate.getInstanceOf("boolean_expr");
        expr.add("value", true);
    }
    
    @Override
    public  void visit(FloatValue e){
        Debug.log("Visit FloatValue");
        expr = groupTemplate.getInstanceOf("float_expr");
        expr.add("value", e.getValue());
    }
    
    @Override
    public  void visit(IntValue e){
        Debug.log("Visit IntValue");
        expr = groupTemplate.getInstanceOf("int_expr");
        expr.add("value", e.getValue());
    }

    @Override
    public  void visit(LValueExp e){
        Debug.log("Visit LvalueExp");
        
        ST aux_lvalue_magic_johnson = groupTemplate.getInstanceOf("stmt_lvalue");
        ST aux_expr = expr; // pega a expr atual
        e.getLvalue().accept(this); // resolve a expr atual

        // junta o q tinha antes com o resolvido agora
        aux_lvalue_magic_johnson.add("stmt1", expr);
        aux_lvalue_magic_johnson.add("stmt2", aux_expr);

        expr = null;
        e.getIndex().accept(this);
        ST expr_index = groupTemplate.getInstanceOf("array_access");
        expr_index.add("expr", expr);
        
        expr = groupTemplate.getInstanceOf("stmt_lvalue");
        expr.add("stmt1", aux_lvalue_magic_johnson);
        expr.add("stmt2", expr_index);
    }

    @Override
    public  void visit(ID e){
        Debug.log("Visit ID");
        ST aux = expr;
        expr = groupTemplate.getInstanceOf("stmt_lvalue");
        expr.add("stmt1", e.getName());
        expr.add("stmt2", aux);
    }

    @Override
    public  void visit(IdLValue e){
        Debug.log("visit IdLvalue");
        ST aux_lvalue_magic_johnson = groupTemplate.getInstanceOf("stmt_lvalue");

        e.getLvalue().accept(this); // resolve a expr mais atual

        // junta o q tinha antes com o resolvido agora
        ST aux_IDLVALUE = groupTemplate.getInstanceOf("attr_access");
        aux_IDLVALUE.add("exp", e.getId());
        
        aux_lvalue_magic_johnson.add("stmt2", aux_IDLVALUE);
        aux_lvalue_magic_johnson.add("stmt1", expr);

        expr = aux_lvalue_magic_johnson;
    }

    @Override
    public  void visit(IdItCond e){
        
    }
    

    @Override
    public  void visit(NullValue e){
       
    }
}