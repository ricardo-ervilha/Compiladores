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

import visitors.Visitable;

public abstract class Node implements Visitable{
      
      private int line,col;
      
      public Node(int l, int c){
           line = l;
           col = c;
      }
      
      public int getLine(){ return line;}
      public int getCol(){ return col;}  
            
}
