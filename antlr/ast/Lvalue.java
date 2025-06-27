package ast;

// as classes concretas são IdLvalue, ArrayLvalue, ou FieldLvalue
public abstract class Lvalue extends Expr {
    public Lvalue(int line, int col) {
        super(line, col);
    }
}
