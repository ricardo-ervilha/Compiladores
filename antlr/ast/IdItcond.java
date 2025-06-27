package ast;

import visitors.Visitor;

public class IdItcond extends Itcond {
    private String id;
    private Expr expression;

    public IdItcond(int line, int col, String id, Expr expression) {
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
