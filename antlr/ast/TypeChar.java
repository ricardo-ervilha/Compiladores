package ast;

import visitors.Visitor;

public class TypeChar extends BType {

    public TypeChar(int line, int col) {
        super(line, col);
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}
