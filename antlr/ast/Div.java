package ast;

/*
 * Esta classe representa uma operação de divisão.
 * Expr + Expr
 */

import visitors.Visitor;

public class Div extends BinOP {

      public Div(int line, int col, Expr l, Expr r){
          super(line, col, l,r);
      }
      
      public String toString(){
         String s = getLeft().toString();
         String ss = getRight().toString();
         if(getRight() instanceof Add){
            ss = "(" + ss + ")";
         }
         return   s + " / " + ss;
      }
      
      public void accept(Visitor v){ v.visit(this);}
}
