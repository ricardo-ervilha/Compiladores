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

public class LocalEnv<A> extends TyEnv<A> {
    private String id; 
    private SType type;//tipo da função
    
    public LocalEnv(String id, SType type){
       this.type = type;
       this.id = id;
    }
    
    public String getFuncID(){ return id;}
    
    public SType getFuncType(){ return type;}

     public String toString(){
         String s = "--------------- (" + id + "," + type.toString() + ") ---------------\n";
         s += super.toString();
         return s;
     }
    
       
}
