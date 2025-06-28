package ast;

import visitors.Visitor;

public class NullValue extends Expr{
    public NullValue(int line, int col){
        super(line,col);
    }
    
    public void accept(Visitor v){ v.visit(this);}
}
