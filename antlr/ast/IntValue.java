package ast;

import visitors.Visitor;

public class IntValue extends Expr{

    private String value;

    public IntValue(int line, int col, String value){
        super(line,col);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void accept(Visitor v){ v.visit(this);}
}
