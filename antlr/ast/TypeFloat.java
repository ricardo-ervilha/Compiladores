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

public class TypeFloat extends BType {

    public TypeFloat(int line, int col) {
        super(line, col);
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}
