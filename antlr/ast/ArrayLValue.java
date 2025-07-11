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

public class ArrayLValue extends LValue {
    private LValue lvalue;  // O array ou objeto que contém o elemento
    private Expr index;      // O índice de acesso ao array

    public ArrayLValue(int line, int col, LValue lvalue, Expr index) {
        super(line, col);
        this.lvalue = lvalue;
        this.index = index;
    }

    public LValue getLvalue() {
        return lvalue;
    }

    public Expr getIndex() {
        return index;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
