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

public class CmdFuncCall extends  Cmd{

    private String id;
    private Exps exps;
    private List<LValue> LValues;

    public CmdFuncCall(int line, int col, String id, Exps exps, List<LValue> LValues) {
        super(line, col);
        this.id = id;
        this.exps = exps;
        this.LValues = LValues;
    }

    public String getId() { return id; }
    public Exps getExps() { return exps; }
    public List<LValue> getLvalues() { return LValues; }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
