package ast;

import java.util.List;

import visitors.Visitor;

public class DataDecl extends Def {
    private String typeId;
    private List<Node> declarations;

    public DataDecl(int line, int col, String typeId, List<Node> declarations) {
        super(line, col);
        this.typeId = typeId;
        this.declarations = declarations;
    }

    public String getTypeId() {
        return typeId;
    }

    public List<Node> getDeclarations() {
        return declarations;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
