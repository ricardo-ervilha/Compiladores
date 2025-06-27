package ast;

import java.util.List;

import visitors.Visitor;

public class DataDecl extends Def {
    private String typeId;
    private List<Decl> declarations;

    public DataDecl(int line, int col, String typeId, List<Decl> declarations) {
        super(line, col);
        this.typeId = typeId;
        this.declarations = declarations;
    }

    public String getTypeId() {
        return typeId;
    }

    public List<Decl> getDeclarations() {
        return declarations;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
