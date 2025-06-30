package util;

import ast.CmdReturn;
import ast.Param;
import ast.Type;
import jdk.nashorn.internal.ir.ReturnNode;

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
