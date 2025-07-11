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

public class CharValue extends Expr{

    private String value;
    
    public CharValue(int line, int col, String value){
        super(line,col);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public void accept(Visitor v){ v.visit(this);}
}
