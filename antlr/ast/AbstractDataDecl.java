package ast;

import java.util.List;
import visitors.Visitor;

public class AbstractDataDecl extends Def {
    private String typeId;
    private List<Node> declFun;

    public AbstractDataDecl(int line, int col, String typeId, List<Node> declFun) {
        super(line, col);
        this.typeId = typeId;
        this.declFun = declFun;
    }

    public String getTypeId() {
        return typeId;
    }

    public List<Node> getDeclFuns() {
        return declFun;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}