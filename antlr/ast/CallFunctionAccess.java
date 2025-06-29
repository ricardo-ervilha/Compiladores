package ast;

import visitors.Visitor;
import java.util.List;

public class CallFunctionAccess extends Expr{
    private Expr exp;
    private Exps exps;
    public CallFunctionAccess(int line, int col, String id, Exps exps, Expr exp){
        super(line,col);
        this.exp = exp;
        this.exps = exps;
    }
    
    public void accept(Visitor v){ v.visit(this);}
}
