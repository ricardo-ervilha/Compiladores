package ast;

import visitors.Visitor;

public class IdItCond extends Itcond {
    private String id;
    private Expr expression;

    public IdItCond(int line, int col, String id, Expr expression) {
        super(line, col);
        this.id = id;
        this.expression = expression;
    }

    public String getId() { return id; }
    public Expr getExpression() { return expression; }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
