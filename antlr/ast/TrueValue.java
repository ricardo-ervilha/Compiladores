package ast;

import visitors.Visitor;

public class TrueValue extends Expr{
    public TrueValue(int line, int col){
        super(line,col);
    }
    
    public void accept(Visitor v){ v.visit(this);}
}
