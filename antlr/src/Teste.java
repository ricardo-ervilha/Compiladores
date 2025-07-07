package src;

import ast.Node;
import parser.*;

import org.antlr.v4.runtime.*;
import util.SyntaxErrorListener;
import visitors.InterpretVisitor;

public class Teste {
    public static void main(String[] args) {

        try {
            // Criar o stream de caracteres do arquivo
            CharStream stream = CharStreams.fromFileName(args[0]);

            // Inicializar lexer e parser
            LangLexer lex = new LangLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lex);
            LangParser parser = new LangParser(tokens);

            // Remover os listeners padrão e adicionar o customizado
            parser.removeErrorListeners();
            parser.addErrorListener(SyntaxErrorListener.INSTANCE);

            // Não construa árvore sintática padrão (usando AST customizada)
            parser.setBuildParseTree(false);

            // Tenta fazer o parse do programa
            Node ast = parser.prog().ast;

            // Interpretação (se desejar)
            InterpretVisitor iv = new InterpretVisitor(args);
            ast.accept(iv);

            // Se chegou até aqui, não houve erro sintático
            System.out.println("accept");

        } catch (Exception e) {
            // Em caso de qualquer erro de parsing
            System.out.println("reject: "+e.getMessage());
        }
    }
}
