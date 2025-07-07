package ast;

import visitors.Visitor;

public class TypeFloat extends BType {

    public TypeFloat(int line, int col) {
        super(line, col);
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}
