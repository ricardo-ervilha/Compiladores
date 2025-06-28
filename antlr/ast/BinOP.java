package ast;

/*
 * Esta classe representa uma Operção binária.
 * Expr [opreação] Expr
 */

public abstract class BinOP extends Expr {
      
      private Expr l;
      private Expr r;
      private String oper;
      
      public BinOP(int lin, int col, Expr l, String oper, Expr r){
           super(lin,col);
           this.l = l;
           this.r = r;
           this.oper = oper;
      }
      
      public void setLeft(Expr n){  l = n; }
      public void setRight(Expr n){ r = n; }
      
      public Expr getLeft(){ return l;}
      public Expr getRight(){ return r;}
      
}
