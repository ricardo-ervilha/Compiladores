package ast;

import visitors.Visitor;

import java.util.List;

public class CmdReturn extends  Cmd{

    private List<Expr> expressions;

    public CmdReturn(int line, int col, List<Expr> expressions) {
        super(line, col);
        this.expressions = expressions;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
