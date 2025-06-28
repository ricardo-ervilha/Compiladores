package ast;

import visitors.Visitor;
import java.util.List;

public class CallFunctionAccess extends Expr{
    private Expr exp;
    private List<Expr> exps;
    public CallFunctionAccess(int line, int col, String id, List<Expr> exps, Expr exp){
        super(line,col);
        this.exp = exp;
        this.exps = exps;
    }
    
    public void accept(Visitor v){ v.visit(this);}
}
