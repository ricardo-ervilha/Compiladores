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

public class CPPVisitor extends Visitor{
    
    private STGroup groupTemplate;
	private ST type, stmt, expr;
	private List<ST> funcs, params;

    TyEnv<LocalEnv<SType>> env;

    public CPPVisitor(TyEnv<LocalEnv<SType>> env){
        groupTemplate = new STGroupFile("./template/cpp.stg");
        this.env = env;
    }

    @Override
    public void visit(Program p){
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
        ST fun = groupTemplate.getInstanceOf("func");
        if(p.getID().equals("main"))
            fun.add("name", "main_aux");
        else
            fun.add("name", p.getID());

        LocalEnv<SType> local = env.get(p.getID());
		Set<String> keys = local.getKeys();

        List<Type> returns = p.getReturnTypes();
        for(Type t : returns){
            t.accept(this);
            fun.add("type", type);
        }

        params = new ArrayList<ST>();
        if(p.getParams() != null){
            for (Param parametro : p.getParams().getParamList()) {
                keys.remove(parametro.getId());
                parametro.accept(this);
            }
        }
        System.out.println(params);
        fun.add("params", params);
    }

    @Override 
    public void visit(Params p){
        // nada
    }

    @Override
    public  void visit(Param e){
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
        
    }

    @Override
    public  void visit(CmdAssign p){
        
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
        
    }
    
    @Override
    public  void visit(TYID e){
        
    }
    
    @Override
    public  void visit(TypeBool e){
        type = groupTemplate.getInstanceOf("bool_type");
    }
    
    @Override
    public  void visit(TypeChar e){
        type = groupTemplate.getInstanceOf("char_type");
    }
    
    @Override
    public  void visit(TypeFloat e){
        type = groupTemplate.getInstanceOf("float_type");
    }
    
    @Override
    public  void visit(TypeInt e){
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
