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

import java.util.Stack;

public class StackOp <T> extends Stack<T> {
    private static int maxSize = 0;

    @Override
    public T push(T item) {
        T result = super.push(item);
        if (size() > maxSize) {
            maxSize = size();
        }
        return result;
    }

    public int getMaxSize() {
        return maxSize*2;
    }
}
