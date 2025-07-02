package ast;

import visitors.Visitor;

public class VarExpr extends Expr{

    private Expr exp;
    private Type type;
    public VarExpr(int line, int col, Type type, Expr exp){
        super(line,col);
        this.exp = exp;
        this.type = type;
    }

    public Expr getExp() {
        return exp;
    }

    public Type getType() {
        return type;
    }
    
    public void accept(Visitor v){ v.visit(this);}
}
