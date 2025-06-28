package ast;

import visitors.Visitor;

public class CharValue extends Expr{
    private String value;
    
    public CharValue(int line, int col, String value){
        super(line,col);
        this.value = value;
    }
    
    public void accept(Visitor v){ v.visit(this);}
}
