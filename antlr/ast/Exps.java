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

import java.util.List;

public class Exps extends Expr {
    private List<Expr> expressions;

    public Exps(int line, int col, List<Expr> expressions) {
        super(line, col);
        this.expressions = expressions;
    }

    public List<Expr> getExpressions() {
        return expressions;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}