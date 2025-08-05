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
    private Expr exp;
    
    public MinusExpr(int line, int col, Expr exp){
        super(line,col);
        this.exp = exp;
    }

    public Expr getExpr(){
        return this.exp;
    }
    
    public void accept(Visitor v){ v.visit(this);}
}
