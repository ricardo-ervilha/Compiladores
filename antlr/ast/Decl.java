package ast;

import visitors.Visitor;

public class Decl extends Node {
    private String id;
    private Type type;

    public Decl(int line, int col, String id, Type type) {
        super(line, col);
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
