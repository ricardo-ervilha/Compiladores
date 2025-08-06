package visitors;

import ast.*;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class CPPVisitor {
    private STGroup groupTemplate;
	private ST type, stmt, expr;
	private List<ST> funcs, params;

    public CPPVisitor(){
        groupTemplate = new STGroupFile("./template/cpp.stg");
    }
}
