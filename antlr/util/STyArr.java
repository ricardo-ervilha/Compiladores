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

public class STyArr extends SType {
     
     private SType a;
     
     public STyArr(SType t){
         a = t;
     }
     
     public SType getArg(){return a;}
          
     public boolean match(SType v){
          return (v instanceof STyErr) || (v instanceof STyArr) && (a.match( ((STyArr)v).getArg() ) );
     }
     
     public String toString(){
         return a.toString() + "[]";
     }
     

}
