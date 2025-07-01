package ast;

import visitors.Visitor;

public class IdLValue extends LValue {
    private String id;

    private LValue lvalue;

    public IdLValue(int line, int col, LValue lvalue, String id) {
        super(line, col);
        this.id = id;
        this.lvalue = lvalue;
        
    }

    public String getId() {
        return id;
    }

    public LValue getLvalue() {
        return lvalue;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
