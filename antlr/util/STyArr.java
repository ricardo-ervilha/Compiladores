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

     private final SType elemType;

     public STyArr(SType elemType) {
          this.elemType = elemType;
     }

     public SType getElemType() {
          return elemType;
     }

     @Override
     public boolean match(SType v) {
          return v instanceof STyErr ||
                  (v instanceof STyArr arr && elemType.match(arr.getElemType()));
     }

     @Override
     public String toString() {
          return elemType.toString() + "[]";
     }
}
