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

import ast.CmdReturn;
import ast.Param;
import ast.Type;

import java.util.List;

public class AbstractFunctionData {

    private List<Param> parametros;
    private List<Type> retornos;

    public AbstractFunctionData(List<Param> parametros, List<Type> retornos) {
        this.parametros = parametros;
        this.retornos = retornos;
    }

    public List<Param> getParametros() {
        return parametros;
    }

    public List<Type> getRetornos() {
        return retornos;
    }

}
