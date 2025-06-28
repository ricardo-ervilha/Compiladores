package ast;

import visitors.Visitor;

public class LvalueExp extends Lvalue{
    private Lvalue lvalue;  // O array ou objeto que contém o elemento
    private Expr index;      // O índice de acesso ao array

    public LvalueExp(int line, int col, Lvalue lvalue, Expr exp) {
        super(line, col);
        this.lvalue = lvalue;
        this.index = exp;
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
