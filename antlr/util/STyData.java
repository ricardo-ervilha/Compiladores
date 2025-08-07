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

import java.util.LinkedHashMap;

public class STyData extends SType {

     private String typeName;
     private LinkedHashMap<String, SType> attrsData = new LinkedHashMap<>();
     private LinkedHashMap<String, STyFun> funcsData = new LinkedHashMap<>();


     // Se for um Absctract Data
     public STyData(String typeName) {
          this.typeName = typeName;
     }

     // Se for um Absctract Data
     public STyData(String typeName, LinkedHashMap<String, SType> atributosData, LinkedHashMap<String, STyFun> funcoesData) {
          this.typeName = typeName;
          this.attrsData = atributosData;
          this.funcsData = funcoesData;
     }

     // Se for um Data
     public STyData(String typeName, LinkedHashMap<String, SType> atributosData) {
          this.typeName = typeName;
          this.attrsData = atributosData;
     }

     public String getTypeName() {
          return this.typeName;
     }

     public LinkedHashMap<String, STyFun> getFuncsData() {
          return funcsData;
     }

     public LinkedHashMap<String, SType> getAttrsData() {
          return attrsData;
     }

     @Override
     public boolean match(SType v) {
          return (v instanceof STyErr) || (v instanceof STyData);
     }

     @Override
     public String toString() {
          return typeName;
     }
}

