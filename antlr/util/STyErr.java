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

public class STyErr extends SType {
     
      private static STyErr st = new STyErr();;
     
     private STyErr(){
     }
     
     public static STyErr newSTyErr(){ return st; }
     
     public boolean match(SType v){
          return true;
     }
     
     public String toString(){
         return "TyError";
     }
}
