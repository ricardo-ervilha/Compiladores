package ast;

import visitors.Visitor;

public class IdLvalue extends Lvalue{
    private String id;
    private Lvalue lvalue;

    public IdLvalue(int line, int col, Lvalue lvalue, String id) {
        super(line, col);
        this.id = id;
        this.lvalue = lvalue;
        
    }

    public String getId() {
        return id;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
