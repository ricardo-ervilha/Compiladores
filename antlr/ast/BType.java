package ast;

import visitors.Visitor;

public abstract class BType extends Type{
    public BType(int line, int col){
        super(line, col);
    }
}
