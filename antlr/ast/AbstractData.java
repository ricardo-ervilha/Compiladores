package ast;

import java.util.List;

public abstract class AbstractData extends Data{

    public AbstractData(int l, int c, TYID tyId, List<Node> declFun){
        super(l, c, tyId, declFun);
   }
}
