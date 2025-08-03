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

public class STyBool extends SType {
     
      private static STyBool st = new STyBool();;
     
     private STyBool(){
     }
     
     public static STyBool newSTyBool(){ return st; }
     
     public boolean match(SType v){
          return (v instanceof STyErr) || (v instanceof STyBool);
     }
     
     public String toString(){
         return "Bool";
     }
     

}
