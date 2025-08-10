package src;

import ast.Node;
import parser.*;

import org.antlr.v4.runtime.*;

import util.LocalEnv;
import util.SType;
import util.SyntaxErrorListener;
import util.TyEnv;
import visitors.CPPVisitor;
import visitors.InterpretVisitor;
import visitors.TypeCheckVisitor;

public class MainCPP {
    
    public static void main(String[] args){
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
            
            // faz o semântico para ganhar o env.
            TypeCheckVisitor v = new TypeCheckVisitor();
            ast.accept(v);

            if(v.getNumErrors() != 0) {
                System.out.println( " Erros ocorreram durante a análise semântica.\nAbortando ");
                v.printErrors();
                System.exit(1);
            }

            TyEnv<LocalEnv<SType>> env = v.getEnv();
            ast.accept(new CPPVisitor(env));

            // Se chegou até aqui, não houve erro sintático
            System.out.println("accept");

        } catch (Exception e) {
            // Em caso de qualquer erro de parsing
            System.out.println("reject: "+e.getMessage());
        }
    }
}
