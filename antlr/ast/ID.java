package ast;

import visitors.Visitor;

public class ID extends LValue{

    public ID(int l, int c, String name){
        super(l, c);
   }

    public void accept(Visitor v){ v.visit(this);}
}
