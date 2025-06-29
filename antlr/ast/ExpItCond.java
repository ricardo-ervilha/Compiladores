package ast;

import visitors.Visitor;

public class ExpItCond extends Itcond {
    private Expr expression;

    public ExpItCond(int line, int col, Expr expression) {
        super(line, col);
        this.expression = expression;
    }

    public Expr getExpression() { return expression; }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
