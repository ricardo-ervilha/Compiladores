package util;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;

public class TyEnv<A> {
    // Mapeia o nome de uma função para um LocalEnv
    // O LocalEnv por sua vez tem o nome da função (id do tipo String) e o tipo da função (type do tipo StyFun)
    // LocalEnv extends TyEnv, então cada LocalEnv, vai ter também um typeEnv que mapeia nome da variavel para o tipo dela
    // Então o TyEnv mapeia tanto nome da função para o tipo dela, quanto o nome de uma variavel para o tipo dela
    // Troquei de TreeMap para LinkedHashMap para manter os campos ordenados
    private LinkedHashMap<String, A> typeEnv;


    public TyEnv() {
        typeEnv = new LinkedHashMap<String, A>();
    }

    public void set(String id, A t) {
        typeEnv.put(id, t);
    }

    public A get(String id) {
        return typeEnv.get(id);
    }

    public boolean containsKey(String k) {
        return typeEnv.containsKey(k);
    }


    public void printTable() {
        System.out.println(toString());
    }

    public String toString() {
        String s = "";
        Object[] x = (typeEnv.keySet().toArray());
        for (int i = 0; i < x.length; i++) {
            s += ((String) x[i]) + " : " + (typeEnv.get(x[i])).toString() + "\n";
        }
        return s;
    }

    public Set<String> getKeys() {
        return typeEnv.keySet();
    }
}
