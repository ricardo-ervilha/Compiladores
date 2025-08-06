package visitors;

import ast.*;
import util.LocalEnv;
import util.SType;
import util.TyEnv;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import util.Debug;

public class CPPVisitor extends Visitor{
    
    private STGroup groupTemplate;
	private ST type, cmd, expr, stmt;
	private List<ST> funcs, params, types, cmds;

    TyEnv<LocalEnv<SType>> env;

    public CPPVisitor(TyEnv<LocalEnv<SType>> env){
        groupTemplate = new STGroupFile("./template/cpp.stg");
        this.env = env;
    }

    @Override
    public void visit(Program p){
        Debug.log("VISIT PROGRAM");
        ST template = groupTemplate.getInstanceOf("program");
        funcs = new ArrayList<ST>();

        for(Def f : p.getDefinitions()){
            if(f instanceof Fun){
                Fun funcao = (Fun) f;
                f.accept(this);
            }
        }

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

    
    @Override
    public  void visit(Fun p){
        Debug.log("VISIT FUN");
        ST fun = groupTemplate.getInstanceOf("func");
        if(p.getID().equals("main"))
            fun.add("name", "main_aux");
        else
            fun.add("name", p.getID());

        LocalEnv<SType> local = env.get(p.getID());
		Set<String> keys = local.getKeys();

        List<Type> returns = p.getReturnTypes();
        types = new ArrayList<ST>();
        for(Type t : returns){
            t.accept(this);
            types.add(type);
        }
        fun.add("types", types);

        params = new ArrayList<ST>();
        if(p.getParams() != null){
            for (Param parametro : p.getParams().getParamList()) {
                keys.remove(parametro.getId());
                parametro.accept(this);
            }
        }
        fun.add("params", params);
        
        p.getCmd().accept(this);
        fun.add("cmds", cmds);
        
        funcs.add(fun);
    }

    @Override 
    public void visit(Params p){
        // nada
    }

    @Override
    public  void visit(Param e){
        Debug.log("VISIT PARAM");
        ST param = groupTemplate.getInstanceOf("param");
		e.getType().accept(this);
		param.add("type", type);
		param.add("name", e.getId());
		params.add(param);
    }
    
    @Override
    public  void visit(ArrayExpr e){
        
    }

    // --- Commands (Statements) ---
    @Override
    public  void visit(Block b){
        Debug.log("VISIT BLOCK");
        cmds = new ArrayList<ST>();
        for(Cmd c: b.getCommands()) {
            c.accept(this);
            cmds.add(stmt);
        }
    }

    @Override
    public void visit(CmdAssign p){
        Debug.log("VISIT CMDASSIGN");
        stmt = groupTemplate.getInstanceOf("attr");
        p.getLvalue().accept(this);
        stmt.add("var", expr);
        p.getExpression().accept(this);
        stmt.add("expr", expr);
    }
    
    @Override
    public  void visit(CmdFuncCall p)
    {
        
    }
    
    @Override
    public  void visit(CmdIf p){
        
    }
    
    @Override
    public  void visit(CmdPrint p){
        
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
    

    // --- Expressions (Binary Ops) ---
    
    @Override
    public  void visit(Add e){
        
    }
    
    @Override
    public  void visit(Sub e){
        
    }
    
    @Override
    public  void visit(Mul e){
        
    }
    
    @Override
    public  void visit(Div e){
        
    }
    
    @Override
    public  void visit(Mod e){
        
    }
    
    @Override
    public  void visit(And e){
        
    }
    
    @Override
    public  void visit(Lt e){
        
    }
    
    @Override
    public  void visit(Eq e){
        
    }
    
    @Override
    public  void visit(Diff e){
        
    }
    
    @Override
    public  void visit(ExpItCond e){
        
    }
    

    @Override
    public  void visit(MinusExpr e){
        
    }
    
    @Override
    public  void visit(NotExpr e){
        
    }
    

    // --- Expression helpers ---
    
    @Override
    public  void visit(Exps e){
        
    }
    
    @Override
    public  void visit(ArrayType e){
        
    }
    
    @Override
    public  void visit(CallFunctionAccess e){
        
    }
    
    @Override
    public  void visit(VarExpr e){
        
    }
    

    // --- Types ---
    
    @Override
    public  void visit(ID e){
        Debug.log("VISIT ID");
        ST id = groupTemplate.getInstanceOf("id");
        id.add("name", e.getName());
        expr = id;
    }
    
    @Override
    public  void visit(TYID e){
        
    }
    
    @Override
    public  void visit(TypeBool e){
        Debug.log("VISIT TYPEBOOL");
        type = groupTemplate.getInstanceOf("bool_type");
    }
    
    @Override
    public  void visit(TypeChar e){
        Debug.log("VISIT TYPECHAR");
        type = groupTemplate.getInstanceOf("char_type");
    }
    
    @Override
    public  void visit(TypeFloat e){
        Debug.log("VISIT TYPEFLOAT");
        type = groupTemplate.getInstanceOf("float_type");
    }
    
    @Override
    public  void visit(TypeInt e){
        Debug.log("VISIT TYPEINT");
        type = groupTemplate.getInstanceOf("int_type");
    }
    

    @Override
    public  void visit(CharValue e){
        
    }
    
    @Override
    public  void visit(FalseValue e){
        
    }
    
    @Override
    public  void visit(FloatValue e){
        
    }
    
    @Override
    public  void visit(IntValue e){
        Debug.log("VISIT INTVALUE");
        expr = groupTemplate.getInstanceOf("int_expr");
        expr.add("value", e.getValue());
    }

    @Override
    public  void visit(LValueExp e){
        
    }

    @Override
    public  void visit(TrueValue e){
        
    }

    @Override
    public  void visit(IdItCond e){
        
    }
    
    
    @Override
    public  void visit(IdLValue e){
        
    }

    @Override
    public  void visit(NullValue e){
        
    }
}
