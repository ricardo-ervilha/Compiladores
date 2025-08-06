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

public class STyFloat extends SType {
     
     private static STyFloat st = new STyFloat();
     
     private STyFloat(){
     }
     
     public static STyFloat newSTyFloat(){ return st; }
     
     public boolean match(SType v){
          return (v instanceof STyErr) || (v instanceof STyFloat);
     }
     
     public String toString(){
         return "Float";
     }
     

}
