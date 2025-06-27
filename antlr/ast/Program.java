package ast;

import java.util.List;

import visitors.Visitor;

public class Program extends Node {
    public final List<Def> definitions;

    public Program(int line, int col, List<Def> defs) {
        super(line, col);
        this.definitions = defs;
    }

    public List<Def> getDefinitions() {
        return definitions;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
