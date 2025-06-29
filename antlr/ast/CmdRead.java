package ast;

import visitors.Visitor;

public class CmdRead extends  Cmd{

    private LValue lvalue;

    public CmdRead(int line, int col, LValue lvalue) {
        super(line, col);
        this.lvalue = lvalue;
    }

    public LValue getLvalue() { return lvalue; }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
