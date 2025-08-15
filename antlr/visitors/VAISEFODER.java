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

    // Variaveis auxiliares;
    ArrayList<ST> typesGlobal; // guarda os tipos de quando visita TypeInt, TypeFloat, etc.
    ST param; // auxiliar para guardar o parametro quando o visita
    ArrayList<ST> cmdsBlock; // guarda os comandos do bloco atual ao qual está sendo processado. Pode ser do if, while, etc.
    ST stmt; // processa o comando atual.
    ST expr; // processa qual expressao atual.
    ArrayList<ST> lvaluesGlobal; // RESOLVE OS NOMES

    /* para os laços */
    String var_name =  "__while_var_";
    int id_var_loop = 0;

    public CPPVisitor(TyEnv<LocalEnv<SType>> env){
        groupTemplate = new STGroupFile("./template/cpp.stg");
        this.env = env;
    }

    @Override
    public void visit(Program p){
        Debug.log("Visit Program");
        
        ST template = groupTemplate.getInstanceOf("program");
        
        // lista global guardando a string de todas as funções.
        funcs = new ArrayList<ST>();

        // declarações de funções por causa do C++
        ArrayList<ST> decl_funcs = new ArrayList<ST>(); 

        for(Def f : p.getDefinitions()){
            if(f instanceof Fun){
                ST aux = groupTemplate.getInstanceOf("decl_func");
                
                Fun funcao = (Fun) f;
                
                // TRATO A main do Lang
                String name = funcao.getID();
                if(name.equals("main"))
                    name = "main_aux";
                aux.add("name", name);
                
                // trata retorno...
                ArrayList<ST> types = new ArrayList<ST>();
                for(Type t : funcao.getReturnTypes()){
                    typesGlobal = new ArrayList<ST>();
                    t.accept(this);
                    types.add(typesGlobal);
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

                decl_funcs.add(aux);
                funcao.accept(this); // chama para processar o corpo da função !
            }
        }

        template.add("decl_funcs", decl_funcs);
        template.add("funcs", funcs);

        System.out.println(template.render());
    }

    @Override
    public void visit(DataDecl p){

    }

    @Override
    public  void visit(AbstractDataDecl p){

    }

    @Override
    public  void visit(Decl p)
    {

    }

    @Override
    public void visit(FunAbstractData f){

    }
    
    /* Função para recuperar o tipo */
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
    public  void visit(Fun p){
        /* FUNÇÃO COM CORPO */
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
            typesGlobal = new ArrayList<ST>();
            t.accept(this);
            types.add(typesGlobal);
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
        cmdsBlock = new ArrayList<ST>();
        p.getCmd().accept(this);

        fun.add("cmds", cmdsBlock);
        
        funcs.add(fun);
    }

    @Override 
    public void visit(Params p){
        // nada
    }

    @Override
    public  void visit(Param e){
        Debug.log("Visit Param");
        param = groupTemplate.getInstanceOf("param");
		typesGlobal = new ArrayList<ST>();
        e.getType().accept(this);
		param.add("name", e.getId());
		param.add("type", typesGlobal);
    }
    
    @Override
    public  void visit(ArrayExpr e){
        Debug.log("Visit ArrayExpr");
    }

    @Override
    public  void visit(ArrayType e){
        Debug.log("Visit ArrayType");
        ST arraytype = groupTemplate.getInstanceOf("array_type");
        typesGlobal.add(groupTemplate.getInstanceOf("pointer"));
        e.getType();
    }

    @Override
    public  void visit(Block b){
        Debug.log("Visit Block");
        for(Cmd c: b.getCommands()) {
            c.accept(this); // chama para aceitar um stmt
            cmdsBlock.add(stmt);
        }
    }

    @Override
    public void visit(CmdAssign p){
        Debug.log("Visit CmdAssign");
        stmt = groupTemplate.getInstanceOf("attr");
        
        p.getLvalue().accept(this);
        
        stmt.add("var", lvalue);
        
        p.getExpression().accept(this);
        stmt.add("expr", expr);
    }
    
    @Override
    public  void visit(CmdFuncCall p)
    {
        ArrayList<ST> vars = new ArrayList<ST>();
        int id = 0;
        for(LValue l : p.getLvalues()){
            l.accept(this);
            ST aux = groupTemplate.getInstanceOf("var_unpacking");
            aux.add("var", lvalue);
            aux.add("id", id);
            id++;
            vars.add(aux);
        }   
        ArrayList<ST> param_exprs = new ArrayList<>();
        for(Expr q : p.getExps().getExpressions()){
            q.accept(this);
            param_exprs.add(expr);
        }

        stmt = groupTemplate.getInstanceOf("func_call");
        stmt.add("name", p.getId());
        stmt.add("params", param_exprs);
        stmt.add("vars", vars);
    }
    
    @Override
    public void visit(CmdIf p) {
        Debug.log("Visit CmdIF");
        ST aux = groupTemplate.getInstanceOf("if");
        p.getCondition().accept(this);
        aux.add("expr", expr);

        ArrayList<ST> aux_cmdsBlock = cmdsBlock; // chega blocos parciais para ele
        
        if(p.getThenCmd() instanceof Block){
            cmdsBlock = new ArrayList<ST>(); // crio o novo bloco desse if e else
            p.getThenCmd().accept(this);
            aux.add("thn", cmdsBlock);
        }else{
            p.getThenCmd().accept(this);
            aux.add("thn", stmt);
        }

        if(p.getElseCmd() != null){
            if(p.getElseCmd() instanceof Block){
                cmdsBlock = new ArrayList<ST>();
                p.getElseCmd().accept(this);
                aux.add("els", cmdsBlock);
            }else{
                p.getElseCmd().accept(this);
                aux.add("els", stmt);
            }
        }

        stmt = aux; // vai ter o if
        cmdsBlock = aux_cmdsBlock; // vai ter o q tinha antes do if
    }


    
    @Override
    public void visit(CmdPrint p){
        Debug.log("Visit CmdPrint");
        stmt = groupTemplate.getInstanceOf("print");
        p.getExpression().accept(this);
        stmt.add("expr", expr);
    }
    
    @Override
    public  void visit(CmdRead p){
        Debug.log("Visit CmdRead");
        stmt = groupTemplate.getInstanceOf("read");
        p.getLvalue().accept(this);
        stmt.add("expr", lvalue);
    }
    
    @Override
    public  void visit(CmdReturn p){
        ArrayList<ST> list_exprs = new ArrayList<ST>();
        for(Expr r: p.getExpressions()){
            r.accept(this);
            list_exprs.add(expr);
        }
        stmt = groupTemplate.getInstanceOf("return");
        stmt.add("list_exprs", list_exprs);
    }
    
    @Override
    public  void visit(CmdIterate p){
        Debug.log("Visit CmdIterate");
        ArrayList<ST> aux_cmdsBlock = cmdsBlock; // chega blocos parciais para ele
        ST aux = null;
        if (p.getCondition() instanceof ExpItCond) {
            // caso seja loop com numero tipo while(10)
            aux = groupTemplate.getInstanceOf("while");
            p.getCondition().accept(this);;
            aux.add("type", ""); // faz adicionar tipo
            aux.add("expr", expr);
            aux.add("var_name", var_name + Integer.toString(id_var_loop));
            id_var_loop = id_var_loop + 1;
            cmdsBlock = new ArrayList<ST>(); // crio novo bloco pra esse iterate
            p.getBody().accept(this);
            aux.add("cmds", cmdsBlock);
            id_var_loop = id_var_loop - 1;
        }else{
            // loop tem id
            aux = groupTemplate.getInstanceOf("while");
            IdItCond q = (IdItCond) p.getCondition();
            aux.add("var_name", q.getId());
            q.getExpression().accept(this);
            aux.add("expr", expr);
            cmdsBlock = new ArrayList<ST>(); // crio novo bloco pra esse iterate
            p.getBody().accept(this);
            aux.add("cmds", cmdsBlock);
        }
        cmdsBlock = aux_cmdsBlock;
        stmt = aux;
    }
    

    // --- Expressions (Binary Ops) ---
    
    @Override
    public void visit(Add e){
        ST aux = groupTemplate.getInstanceOf("add_expr");
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Sub e){
        ST aux = groupTemplate.getInstanceOf("sub_expr");
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Mul e){
        ST aux = groupTemplate.getInstanceOf("mul_expr");
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Div e){
        ST aux = groupTemplate.getInstanceOf("div_expr");
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Mod e){
        ST aux = groupTemplate.getInstanceOf("mod_expr");
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Lt e){
        ST aux = groupTemplate.getInstanceOf("lt_expr");
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(And e){
        ST aux = groupTemplate.getInstanceOf("and_expr");
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Eq e){
        ST aux = groupTemplate.getInstanceOf("equals_expr");
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Diff e){
        ST aux = groupTemplate.getInstanceOf("diff_expr");
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(MinusExpr e){
        ST aux = groupTemplate.getInstanceOf("minus_expr");
        e.getExpr().accept(this);
        aux.add("expr", expr);
        expr = aux;
    }

    @Override
    public void visit(NotExpr e){
        ST aux = groupTemplate.getInstanceOf("not_expr");
        e.getExpression().accept(this);
        aux.add("expr", expr);
        expr = aux;
    }

    
    @Override
    public  void visit(ExpItCond e){
        e.getExpression().accept(this);;
        ST aux = groupTemplate.getInstanceOf("int_expr");
        aux.add("value", expr);
        expr = aux;
    }
    

    // --- Expression helpers ---
    
    @Override
    public  void visit(Exps e){
        
    }
    
    
    @Override
    public  void visit(CallFunctionAccess e){
        
    }
    
    @Override
    public  void visit(VarExpr e){
        
    }
    

    // --- Types ---
    
    
    @Override
    public  void visit(TYID e){
        
    }
    
    @Override
    public  void visit(TypeBool e){
        type = groupTemplate.getInstanceOf("bool_type");
        types.add(type);
    }
    
    @Override
    public  void visit(TypeChar e){
        type = groupTemplate.getInstanceOf("char_type");
        types.add(type);
    }
    
    @Override
    public  void visit(TypeFloat e){
        type = groupTemplate.getInstanceOf("float_type");
        types.add(type);
    }
    
    @Override
    public  void visit(TypeInt e){
        Debug.log("Visit TypeInt");
        type = groupTemplate.getInstanceOf("int_type");
        types.add(type);
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

/* CRITICO ------------- */

    @Override
    public  void visit(LValueExp e){
        Debug.log("Visit LvalueExp");
    }

    @Override
    public  void visit(ID e){
        Debug.log("Visit ID");
    }

    @Override
    public  void visit(IdLValue e){
        Debug.log("Visit IdLvalue");
    }

/* CRITICO ------------- */
   

    @Override
    public  void visit(IdItCond e){
        
    }
    

    @Override
    public  void visit(NullValue e){
        expr = groupTemplate.getInstanceOf("null_expr");
        expr.add("value", null);
    }
}
