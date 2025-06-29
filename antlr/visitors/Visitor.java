package visitors;

import ast.*;

public abstract class Visitor {

    // --- Program and Declarations ---
    public abstract void visit(Program p);

    public abstract void visit(DataDecl p);
    public abstract void visit(AbstractDataDecl p);
    public abstract void visit(Decl p);
    public abstract void visit(Fun p);
    public abstract void visit(Params e);

    // --- Commands (Statements) ---
    public abstract void visit(Block b);
    public abstract void visit(CmdAssign p);
    public abstract void visit(CmdFuncCall p);
    public abstract void visit(CmdIf p);
    public abstract void visit(CmdPrint p);
    public abstract void visit(CmdRead p);
    public abstract void visit(CmdReturn p);
    public abstract void visit(CmdIterate p);

    // --- Expressions (Binary Ops) ---
    public abstract void visit(Add e);
    public abstract void visit(Sub e);
    public abstract void visit(Mul e);
    public abstract void visit(Div e);
    public abstract void visit(Mod e);
    public abstract void visit(And e);
    public abstract void visit(Lt e);
    public abstract void visit(Eq e);
    public abstract void visit(Diff e);
    public abstract void visit(ExpItcond e);

    public abstract void visit(MinusExpr e);
    public abstract void visit(NotExpr e);

    // --- Expression helpers ---
    public abstract void visit(Exps e);
    public abstract void visit(ArrayLvalue e);
    public abstract void visit(ArrayType e);
    public abstract void visit(CallFunctionAccess e);
    public abstract void visit(VarExpr e);

    // --- Types ---
    public abstract void visit(ID e);
    public abstract void visit(TYID e);
    public abstract void visit(TypeBool e);
    public abstract void visit(TypeChar e);
    public abstract void visit(TypeFloat e);
    public abstract void visit(TypeInt e);

    public abstract void visit(CharValue e);
    public abstract void visit(FalseValue e);
    public abstract void visit(FieldLvalue e);
    public abstract void visit(FloatValue e);
    public abstract void visit(IntValue e);
    public abstract void visit(LvalueExp e);
    public abstract void visit(TrueValue e);

    public abstract void visit(IdItcond e);
    public abstract void visit(IdLvalue e);
    public abstract void visit(NullValue e);
}
