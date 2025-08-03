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

public class STyFun extends SType {
     
     private SType ty[];
     
     public STyFun(SType t[]){
         ty = t;
     }
       
       
     public SType[] getTypes(){ return ty; }
     
     public boolean match(SType v){
          boolean r = false; 
          if(  v instanceof STyFun ){
              if(((STyFun)v).getTypes().length == ty.length ){
                   r = true;
                   for(int i = 0; i< ty.length; i++ ){
                       r = r && ty[i].match( ((STyFun)v).getTypes()[i] );
                  }
              }
          }
          return r;
     }
     
     public String toString(){
         String s = "";
         if(ty.length > 0){
            s = ty[0].toString();
            for(int i =1; i < ty.length; i++){
               s += "->" + ty[i].toString();
            }
         }
         return s;
     }
     

}
