package ast;

import visitors.Visitor;

import java.util.List;

public class Params extends Node {

    List<Param> paramList;

    public Params(int line, int col, List<Param> paramList) {
        super(line, col);
        this.paramList = paramList;
    }

    public List<Param> getParamList() {
        return paramList;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
