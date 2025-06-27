package ast;

import visitors.Visitor;

import java.util.List;

public class Block extends Cmd {
    private List<Cmd> commands;

    public Block(int line, int col, List<Cmd> commands) {
        super(line, col);
        this.commands = commands;
    }

    public List<Cmd> getCommands() {
        return commands;
    }


    public void accept(Visitor v) {
        v.visit(this);
    }
}
