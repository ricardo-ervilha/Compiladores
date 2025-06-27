package ast;

// Classe Def abstrata considerando que Data e Fun vão ser geradas de forma alternada
public abstract class Def extends Node{

    public Def(int line, int col){
        super(line, col);
    }
    
}
