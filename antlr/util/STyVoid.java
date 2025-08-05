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

public class STyVoid extends SType {

     private static STyVoid st = new STyVoid();

     private STyVoid() {
     }

     public static STyVoid newSTyVoid() {
          return st;
     }

     @Override
     public boolean match(SType v) {
          return (v instanceof STyErr) || (v instanceof STyVoid);
     }

     @Override
     public String toString() {
          return "Void";
     }
}

