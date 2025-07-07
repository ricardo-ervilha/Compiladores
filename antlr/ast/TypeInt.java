package ast;

import visitors.Visitor;

public class TypeInt extends BType {

    public TypeInt(int line, int col) {
        super(line, col);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
