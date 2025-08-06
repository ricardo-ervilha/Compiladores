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

public class STyFun extends SType {

    private final SType[] paramTypes;
    private final SType[] returnTypes;

    public STyFun(SType[] paramTypes, SType[] returnTypes) {
        this.paramTypes = paramTypes;
        this.returnTypes = returnTypes;
    }

    public SType[] getParamTypes() {
        return paramTypes;
    }

    public SType[] getReturnTypes() {
        return returnTypes;
    }

    public boolean match(SType v) {
        boolean r = false;
        if (v instanceof STyFun) {
            if ((((STyFun) v).getParamTypes().length == paramTypes.length) && (((STyFun) v).getReturnTypes().length == returnTypes.length)) {
                r = true;

                for (int i = 0; i < paramTypes.length; i++) {
                    r = r && paramTypes[i].match(((STyFun) v).getParamTypes()[i]);
                }

                for (int i = 0; i < returnTypes.length; i++) {
                    r = r && returnTypes[i].match(((STyFun) v).getReturnTypes()[i]);
                }
            }
        }
        return r;
    }

    public String toString() {
        String s = "";
        if (paramTypes != null && paramTypes.length > 0) {
            s = paramTypes[0].toString();
            for (int i = 1; i < paramTypes.length; i++) {
                s += "->" + paramTypes[i].toString();
            }

            for (int i = 1; i < returnTypes.length; i++) {
                s += "->" + returnTypes[i].toString();
            }
        }
        return s;
    }


}
