
import ast.IntValue;
import ast.Mul;
import ast.Node;
import parser.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Teste {

    public static void main(String args[]) throws Exception {
        // Create a ANTLR CharStream from a file
        CharStream stream = CharStreams.fromFileName(args[0]);
        // create a lexer that feeds off of stream
        LangLexer lex = new LangLexer(stream);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lex);
        // create a parser that feeds off the tokens buffer
        LangParser parser = new LangParser(tokens);

        // tell ANTLR to does not automatically build an AST
        parser.setBuildParseTree(false);

        Node ast = parser.prog().ast;
        Mul mult = new Mul(0,0, new IntValue(0,0, "2"), new IntValue(0,0, "3") );

        System.out.println(mult);


//        ParseTree tree = parser.prog();
//        System.out.println(tree.toStringTree(parser));
    }
}