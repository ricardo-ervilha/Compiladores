package ast;

import visitors.Visitor;

public class ArrayLvalue extends Lvalue{
    private Lvalue lvalue;  // O array ou objeto que contém o elemento
    private Expr index;      // O índice de acesso ao array

    public ArrayLvalue(int line, int col, Lvalue lvalue, Expr index) {
        super(line, col);
        this.lvalue = lvalue;
        this.index = index;
    }

    public Lvalue getLvalue() {
        return lvalue;
    }

    public Expr getIndex() {
        return index;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
