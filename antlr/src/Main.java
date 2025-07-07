package src;
import ast.Node;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.*;
import util.SyntaxErrorListener;
import visitors.InterpretVisitor;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Uso: java Main <diretiva> <arquivo.lang>");
            System.exit(1);
        }

        String directive = args[0];
        String filePath = args[1];
        SyntaxErrorListener errorListener = SyntaxErrorListener.INSTANCE;
        errorListener.reset();
        try {
            // Criar o stream de caracteres do arquivo
            CharStream stream = CharStreams.fromFileName(filePath);


            // Inicializar lexer e parser
            LangLexer lex = new LangLexer(stream);
            lex.removeErrorListeners();
            lex.addErrorListener(SyntaxErrorListener.INSTANCE);

            CommonTokenStream tokens = new CommonTokenStream(lex);
            LangParser parser = new LangParser(tokens);

            // Remover os listeners padrão e adicionar o customizado
            parser.removeErrorListeners();
            parser.addErrorListener(SyntaxErrorListener.INSTANCE);

            // Não construa árvore sintática padrão (usando AST customizada)
            parser.setBuildParseTree(false);


            Node ast;
            switch (directive) {
                case "-syn":
                    // Tenta fazer o parse do programa
                    parser.prog();
                    if (errorListener.hasErrors()) {
                        System.out.println("reject");
                        System.exit(1);
                    }
                    System.out.println("accept");

                    break;

                case "-i":
                    // Tenta fazer a interpretação do
                    ast = parser.cmd().ast;
                    if (errorListener.hasErrors()) {
                        System.out.println("reject");
                        System.exit(1);
                    }
                    InterpretVisitor iv = new InterpretVisitor(args);
                    ast.accept(iv);
                    break;

                case "-t":
                    System.out.println("Executa a verifica ção de tipos do programa.");
                    break;

                case "-src":
                    System.out.println("Gera código de alto n ível");
                    break;

                case "-gen":
                    System.out.println("Gera código para uma arquitetura alvo.");
                    break;

                default:
                    System.err.println("Diretiva desconhecida: " + directive);
                    System.exit(1);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("reject");
            System.exit(1);
        }
    }
}
