package ast;

import visitors.Visitor;

public class FieldLvalue  extends Lvalue{
    private Lvalue lvalue;
    private String field;

    public FieldLvalue(int line, int col, Lvalue lvalue, String field) {
        super(line, col);
        this.lvalue = lvalue;
        this.field = field;
    }

    public Lvalue getLvalue() {
        return lvalue;
    }

    public String getField() {
        return field;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
