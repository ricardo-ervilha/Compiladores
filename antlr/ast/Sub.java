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

/*
 * Esta classe representa uma expressão de subtração.
 * Expr - Expr
 */
 
import visitors.Visitor;

public class Sub extends BinOP {

      public Sub(int line, int col, Expr l, Expr r){
          super(line, col, l,r);
      }
      
      public String toString(){
         String s = getLeft().toString();
         String ss = getRight().toString();
         if(getRight() instanceof Add){
            ss = "(" + ss + ")";
         }
         return   s + " - " + ss;
      }
      
      public void accept(Visitor v){ v.visit(this);}
}
