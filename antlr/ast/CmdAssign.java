package ast;

import visitors.Visitor;

public class CmdAssign extends  Cmd{

    private Lvalue lvalue;
    private Expr expression;

    public CmdAssign(int line, int col, Lvalue lvalue, Expr expression) {
        super(line, col);
        this.lvalue = lvalue;
        this.expression = expression;
    }

    public Lvalue getLvalue() { return lvalue; }
    public Expr getExpression() { return expression; }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
