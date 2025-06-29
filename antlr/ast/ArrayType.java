package ast;

import visitors.Visitor;

public class ArrayType extends Type{

    public ArrayType(int line, int col){
        super(line, col);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }


}
