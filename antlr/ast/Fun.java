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

import java.util.List;

import visitors.Visitor;


public class Fun extends Def {
    String id;

    Params params;
    List<Type> returnTypes;
    Cmd cmd;

    public Fun(int line, int col, String id, Params params, List<Type> returnTypes, Cmd cmd) {
        super(line, col);
        this.id = id;
        this.params = params;
        this.returnTypes = returnTypes;
        this.cmd = cmd;
    }

    public String getID(){
        return this.id;
    }

    public Params getParams() {
        return params;
    }

    public List<Type> getReturnTypes() {
        return returnTypes;
    }

    public Cmd getCmd() {
        return cmd;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
