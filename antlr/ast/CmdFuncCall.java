package ast;

import visitors.Visitor;

import java.util.List;

public class CmdFuncCall extends  Cmd{

    private String id;
    private List<Expr> arguments;
    private List<Lvalue> lvalues;

    public CmdFuncCall(int line, int col, String id, List<Expr> arguments, List<Lvalue> lvalues) {
        super(line, col);
        this.id = id;
        this.arguments = arguments;
        this.lvalues = lvalues;
    }

    public String getId() { return id; }
    public List<Expr> getArguments() { return arguments; }
    public List<Lvalue> getLvalues() { return lvalues; }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
