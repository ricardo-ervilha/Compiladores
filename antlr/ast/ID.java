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

public class ID extends LValue{

    private String name;
    public ID(int l, int c, String name){
        super(l, c);
        this.name = name;
   }

    public String getName() {
        return name;
    }

    public void accept(Visitor v){ v.visit(this);}
}
