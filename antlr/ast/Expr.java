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

import util.SType;

public abstract class Expr extends Node {

    private SType sType;

    public Expr(int l, int c){
        super(l, c);
    }

    public SType getSType() { return sType; }

    public void setSType(SType sType) { this.sType = sType; }

}
