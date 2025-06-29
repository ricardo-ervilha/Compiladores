package ast;

// as classes concretas são IdLvalue, ArrayLvalue, ou FieldLvalue
public abstract class LValue extends Expr {
    public LValue(int line, int col) {
        super(line, col);
    }
}
