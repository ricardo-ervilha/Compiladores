package ast;

import visitors.Visitor;

public class ArrayType extends Type{

    private Type type;

    public ArrayType(int line, int col, Type type){
        super(line, col);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }


}
