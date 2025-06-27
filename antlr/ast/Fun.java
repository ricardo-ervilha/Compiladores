package ast;

import java.util.List;

import visitors.Visitor;


public class Fun extends Def {
    String id;
    Params params;
    List<Type> returnTypes;
    Cmd cmd;

    public Fun(int line, int col, String id, Params params, List<Type> returnTypes, Cmd cmd) {
        super(line, col);
        this.id = id;
        this.params = params;
        this.returnTypes = returnTypes;
        this.cmd = cmd;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
