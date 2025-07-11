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

// as classes concretas são IdLvalue, ArrayLvalue, ou FieldLvalue
public abstract class LValue extends Expr {
    public LValue(int line, int col) {
        super(line, col);
    }
}
