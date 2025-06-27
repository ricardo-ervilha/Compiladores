package ast;

import visitors.Visitor;

public class CmdPrint extends  Cmd{

    private Expr expression;

    public CmdPrint(int line, int col, Expr expression) {
        super(line, col);
        this.expression = expression;
    }

    public Expr getExpression() { return expression; }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
