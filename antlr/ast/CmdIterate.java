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

public class CmdIterate extends  Cmd{

    private Itcond condition;
    private Cmd body;

    public CmdIterate(int line, int col, Itcond condition, Cmd body) {
        super(line, col);
        this.condition = condition;
        this.body = body;
    }

    public Itcond getCondition() { return condition; }
    public Cmd getBody() { return body; }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
