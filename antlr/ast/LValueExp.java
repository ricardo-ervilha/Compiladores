package ast;

import visitors.Visitor;

public class LValueExp extends LValue {
    private LValue lvalue;  // O array ou objeto que contém o elemento
    private Expr index;      // O índice de acesso ao array

    public LValueExp(int line, int col, LValue lvalue, Expr exp) {
        super(line, col);
        this.lvalue = lvalue;
        this.index = exp;
    }

    public LValue getLvalue() {
        return lvalue;
    }

    public Expr getIndex() {
        return index;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
