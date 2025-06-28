package ast;

import visitors.Visitor;

public class FalseValue extends Expr{
    public FalseValue(int line, int col){
        super(line,col);
    }
    
    public void accept(Visitor v){ v.visit(this);}
}
