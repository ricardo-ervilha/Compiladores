package ast;

import visitors.Visitor;

public class ExpItcond extends Itcond {
    private Expr expression;

    public ExpItcond(int line, int col, Expr expression) {
        super(line, col);
        this.expression = expression;
    }

    public Expr getExpression() { return expression; }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
