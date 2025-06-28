package ast;

import visitors.Visitor;

public class MinusExpr extends Expr{
    private Expr exp;
    
    public MinusExpr(int line, int col, Expr exp){
        super(line,col);
        this.exp = exp;
    }
    
    public void accept(Visitor v){ v.visit(this);}
}
