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

// ID '(' (exps)? ')' '[' exp ']'
// função chamada dentro de uma expressão: print fat(10)[0];
public class CallFunctionAccess extends Expr{
    private String functionName;
    private Expr exp;
    private Exps exps;
    public CallFunctionAccess(int line, int col, String id, Exps exps, Expr exp){
        super(line,col);
        this.exp = exp;
        this.exps = exps;
        this.functionName = id;
    }

    public String getFunctionName() {
        return functionName;
    }
    public Exps getExps() {
        return exps;
    }

    public Expr getExp() {
        return exp;
    }
    public void accept(Visitor v){ v.visit(this);}
}
