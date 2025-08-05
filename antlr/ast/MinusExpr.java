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

public class MinusExpr extends Expr{
    private Expr expr;
    
    public MinusExpr(int line, int col, Expr expr){
        super(line,col);
        this.expr = expr;
    }
    
    public void accept(Visitor v){ v.visit(this);}

    public Expr getExpr() {
        return expr;
    }
}
