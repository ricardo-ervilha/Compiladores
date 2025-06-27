package ast;

import visitors.Visitor;

public class CmdRead extends  Cmd{

    private Lvalue lvalue;

    public CmdRead(int line, int col, Lvalue lvalue) {
        super(line, col);
        this.lvalue = lvalue;
    }

    public Lvalue getLvalue() { return lvalue; }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
