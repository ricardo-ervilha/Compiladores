package ast;

import visitors.Visitor;

public class FloatValue extends Expr{
    private String value;
    
    public FloatValue(int line, int col, String value){
        super(line,col);
        this.value = value;
    }
    
    public void accept(Visitor v){ v.visit(this);}
}
