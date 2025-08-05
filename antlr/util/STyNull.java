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

public class STyNull extends SType {

     private static final STyNull st = new STyNull();

     private STyNull() {
     }

     public static STyNull newSTyNull() {
          return st;
     }

     @Override
     public boolean match(SType v) {
          return (v instanceof STyErr) || (v instanceof STyNull);
     }

     @Override
     public String toString() {
          return "Null";
     }
}

