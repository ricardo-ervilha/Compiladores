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

public class VarExpr extends Expr{

    private Type type;
    public VarExpr(int line, int col, Type type){
        super(line,col);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
    
    public void accept(Visitor v){ v.visit(this);}
}
