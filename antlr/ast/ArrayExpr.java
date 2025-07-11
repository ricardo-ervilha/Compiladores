/*
 *
 *  * Alunos:
 *  * - Nome: Lucas Silva Santana      Matrícula: 202165092C
 *  * - Nome: Ricardo Ervilha Silva       Matrícula: 202165561C
 *  *
 *  * Disciplina: DCC045 - Teoria de Compiladores
 *
 *
 *
 */

package ast;

import visitors.Visitor;

public class ArrayExpr extends Expr{
    private Expr exp;
    private Type type;
    public ArrayExpr(int line, int col, Type type, Expr exp){
        super(line,col);
        this.exp = exp;
        this.type = type;
    }

    public Expr getExp() {
        return exp;
    }

    public Type getType() {
        return type;
    }

    public void accept(Visitor v){ v.visit(this);}
}
