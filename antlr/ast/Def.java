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

// Classe Def abstrata considerando que Data e Fun vão ser geradas de forma alternada
public abstract class Def extends Node{

    public Def(int line, int col){
        super(line, col);
    }
    
}
