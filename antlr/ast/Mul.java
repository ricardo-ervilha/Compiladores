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
 * Esta classe representa uma expressão de Multiplicação.
 * Expr * Expr
 */
import visitors.Visitor;

public class Mul extends BinOP {
      public Mul(int line, int col, Expr l, Expr r){
          super(line, col, l,r);
      }
      
      //@Override
      public String toString(){
         String s = getLeft().toString();
         if(getLeft() instanceof Mul || getLeft() instanceof Add){
            s = "(" + s + ")";
         }
         String ss = getRight().toString();
         if( getRight() instanceof Add){
            ss = "(" + ss+ ")";
         }
         return   s + " * " + ss;
      }
      
      public void accept(Visitor v){ v.visit(this);}
}
