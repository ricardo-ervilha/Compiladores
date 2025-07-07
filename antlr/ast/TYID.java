package ast;

import visitors.Visitor;

public class TYID extends BType {

    String name;

    public TYID(int l, int c, String name) {
        super(l, c);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
