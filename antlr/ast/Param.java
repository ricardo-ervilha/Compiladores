package ast;

public class Param {
    private String id;
    private Type type;

    public Param(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }
}
