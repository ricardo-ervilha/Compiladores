package ast;

import visitors.Visitor;

public class IdLvalue extends Lvalue{
    private String id;

    public IdLvalue(int line, int col, String id) {
        super(line, col);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
