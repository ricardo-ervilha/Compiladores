package ast;

import visitors.Visitor;

public class CmdIf extends  Cmd{
    private Expr condition;
    private Cmd thenCmd;
    private Cmd elseCmd;

    public CmdIf(int line, int col, Expr condition, Cmd thenCmd, Cmd elseCmd) {
        super(line, col);
        this.condition = condition;
        this.thenCmd = thenCmd;
        this.elseCmd = elseCmd;
    }

    public CmdIf(int line, int col, Expr condition, Cmd thenCmd){
        super(line, col);
        this.condition = condition;
        this.thenCmd = thenCmd;
    }

    public Expr getCondition() { return condition; }
    public Cmd getThenCmd() { return thenCmd; }
    public Cmd getElseCmd() { return elseCmd; }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
