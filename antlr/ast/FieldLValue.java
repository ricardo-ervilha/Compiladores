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

public class FieldLValue extends LValue {
    private LValue lvalue;
    private String field;

    public FieldLValue(int line, int col, LValue lvalue, String field) {
        super(line, col);
        this.lvalue = lvalue;
        this.field = field;
    }

    public LValue getLvalue() {
        return lvalue;
    }

    public String getField() {
        return field;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
