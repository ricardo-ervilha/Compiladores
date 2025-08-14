package visitors;

import ast.*;
import util.LocalEnv;
import util.STyArr;
import util.STyBool;
import util.STyChar;
import util.STyFloat;
import util.STyInt;
import util.SType;
import util.TyEnv;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import javax.management.monitor.StringMonitorMBean;

import util.Debug;

public class CPPVisitor extends Visitor{
    // variável global que pega os templates
    private STGroup groupTemplate;

    // variável global para saber variaveis e tipos
    TyEnv<LocalEnv<SType>> env;

    // lista de funções do programa
    ArrayList<ST> funcs;
    ArrayList<ST> data_declarations;

    ST stmt_type;
    ST stmt_lvalue;
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
                    types.add(stmt_type);
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

        // pega o ambiente especifico dessa função
        LocalEnv<SType> local = env.get(p.getID());
		Set<String> keys = local.getKeys();

        // pega os retornos
        List<Type> returns = p.getReturnTypes();

        // lista para guardar os tipos que resolve dps de visitar
        ArrayList<ST> types = new ArrayList<ST>();
        for(Type t : returns){
            t.accept(this);
            types.add(stmt_type);
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
		param.add("type", stmt_type);
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
    public  void visit(TypeBool e){
        Debug.log("Visit TypeBool");
        stmt_type = groupTemplate.getInstanceOf("bool_type");
    }
    
    @Override
    public  void visit(TypeChar e){
        Debug.log("Visit TypeChar");
        stmt_type = groupTemplate.getInstanceOf("char_type");
    }
    
    @Override
    public  void visit(TypeFloat e){
        Debug.log("Visit TypeFloat");
        stmt_type = groupTemplate.getInstanceOf("float_type");
    }
    
    @Override
    public  void visit(TypeInt e){
        Debug.log("Visit TypeInt");
        stmt_type = groupTemplate.getInstanceOf("int_type");
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
        aux.add("type", stmt_type);
        stmt = aux;
    }

    @Override
    public void visit(FunAbstractData f){
        Debug.log("Visit FunAbstactData");
        ST fun = groupTemplate.getInstanceOf("func");
        
        // pega o ambiente especifico dessa função
        LocalEnv<SType> local = env.get(f.getID());
        System.out.println("NOME: " + f.getID());
        System.out.println("AMBIENTE: " + local);
        Set<String> keys = local.getKeys();
        System.out.println("VARIAVEIS: " + keys);

        // pega os retornos
        List<Type> returns = f.getReturnTypes();

        // lista para guardar os tipos que resolve dps de visitar
        ArrayList<ST> types = new ArrayList<ST>();
        for(Type t : returns){
            t.accept(this);
            types.add(stmt_type);
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
        expr_arr.add("type", stmt_type);
        e.getExp().accept(this);
        expr_arr.add("index", expr);
        expr = expr_arr;
    }

    @Override
    public  void visit(ArrayType e){
        Debug.log("Visit ArrayType");
        e.getType().accept(this);
        ST aux = stmt_type;
        stmt_type = groupTemplate.getInstanceOf("stmt_type");
        stmt_type.add("stmt1", aux);
        stmt_type.add("stmt2", groupTemplate.getInstanceOf("pointer"));
    }

    @Override
    public void visit(CmdAssign p){
        stmt_lvalue = null;
        Debug.log("Visit CmdAssign");
        stmt = groupTemplate.getInstanceOf("attr");

        p.getLvalue().accept(this);
        stmt.add("var", stmt_lvalue);
        p.getExpression().accept(this);
        stmt.add("expr", expr);
    }

    @Override
    public  void visit(CmdFuncCall p)
    {
    }

    @Override
    public void visit(CmdIf p) {
        Debug.log("Visit CmdIF");
    }

    @Override
    public void visit(CmdPrint p){
    }

    @Override
    public  void visit(CmdRead p){
    }

    @Override
    public  void visit(CmdReturn p){
    }

    @Override
    public  void visit(CmdIterate p){
    }

    @Override
    public void visit(Add e){
        
    }

    @Override
    public void visit(Sub e){
        
    }

    @Override
    public void visit(Mul e){
        
    }

    @Override
    public void visit(Div e){
        
    }

    @Override
    public void visit(Mod e){
        
    }

    @Override
    public void visit(Lt e){
        
    }

    @Override
    public void visit(And e){
        
    }

    @Override
    public void visit(Eq e){
        
    }

    @Override
    public void visit(Diff e){
       
    }

    @Override
    public void visit(MinusExpr e){
       
    }

    @Override
    public void visit(NotExpr e){
        
    }

    
    @Override
    public  void visit(ExpItCond e){
        
    }
    

    @Override
    public  void visit(Exps e){
        
    }
    
    
    @Override
    public  void visit(CallFunctionAccess e){
        
    }
    
    @Override
    public  void visit(VarExpr e){
        
    }
    
    
    @Override
    public  void visit(TYID e){
        
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
        e.getLvalue().accept(this);
        ST aux_lvalue = stmt_lvalue;
        stmt_lvalue = groupTemplate.getInstanceOf("stmt_lvalue");
        stmt_lvalue.add("stmt1", aux_lvalue);
        ST array_access = groupTemplate.getInstanceOf("array_access");
        e.getIndex().accept(this);
        array_access.add("expr", expr);
        stmt_lvalue.add("stmt2", array_access);
    }

    @Override
    public  void visit(ID e){
        Debug.log("Visit ID");
        ST aux = stmt_lvalue;
        stmt_lvalue = groupTemplate.getInstanceOf("stmt_lvalue");
        stmt_lvalue.add("stmt1", e.getName());
        stmt_lvalue.add("stmt2", aux);

    }

    @Override
    public  void visit(IdLValue e){
        Debug.log("Visit IdLvalue");
    }

    @Override
    public  void visit(IdItCond e){
        
    }
    

    @Override
    public  void visit(NullValue e){
       
    }
}