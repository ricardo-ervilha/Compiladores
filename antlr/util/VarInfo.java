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

package util;

public class VarInfo {
    private SType type;
    private int index;

    public VarInfo(SType type, int index) {
        this.type = type;
        this.index = index;
    }

    public SType getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return type.toString() + " [index=" + index + "]";
    }
}
