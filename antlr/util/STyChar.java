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

public class STyChar extends SType {

     private static final STyChar st = new STyChar();

     private STyChar() {
     }

     public static STyChar newSTyChar() {
          return st;
     }

     @Override
     public boolean match(SType v) {
          return (v instanceof STyErr) || (v instanceof STyChar);
     }

     @Override
     public String toString() {
          return "Char";
     }
}

