package ast;

/*
 * Esta classe representa um comando de Impressão.
 * Expr
 */
import java.util.HashMap; 
 
public class Print extends Node {
      
      private Expr e; 
      
      public Print(int l, int c, Expr e){
           super(l,c);
           this.e  = e;
      }
      
      public Expr getExpr(){ return e;}
      
      //@Override
      public String toString(){
         return   "" + e.toString(); 
      }
      
      public int interpret(HashMap<String,Integer> m){
          int n = e.interpret(m);
          System.out.println("" + n);
          return n;
      }
}
