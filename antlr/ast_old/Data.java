package ast_old;

import java.util.List;

public abstract class Data extends Node{

    public Data(int l, int c, TYID tyId, List<Node> declFun){
        super(l, c);
   }
}
