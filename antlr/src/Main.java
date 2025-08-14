/*
 *
 *  * Alunos:
 *  * - Nome: Lucas Silva Santana      Matrícula: 202165092C
 *  * - Nome: Ricardo Ervilha Silva       Matrícula: 202165561C
 *  *
 *  * Disciplina: DCC045 - Teoria de Compiladores
 *
 *
 *
 */

package src;
import ast.Node;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.*;
import util.*;
import visitors.InterpretVisitor;
import visitors.JasminVisitor;
import visitors.TypeCheckVisitor;

public class Main {

     public static TyEnv<LocalEnv<VarInfo>> getEnvWithVarIndex(TyEnv<LocalEnv<SType>> env) {
        TyEnv<LocalEnv<VarInfo>> newEnv = new TyEnv<>();

        for (String funcName : env.getKeys()) {
            LocalEnv<SType> oldLocalEnv = env.get(funcName);
            LocalEnv<VarInfo> newLocalEnv = new LocalEnv<>(
                    oldLocalEnv.getFuncID(),
                    oldLocalEnv.getFuncType()
            );

            int index = 0;

            for (String varName : oldLocalEnv.getKeys()) {
                SType type = oldLocalEnv.get(varName);
                newLocalEnv.set(varName, new VarInfo(type, index));
                index++;
            }

            newEnv.set(funcName, newLocalEnv);
        }

        return newEnv;
    }

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

            Node ast = parser.prog().ast;
            if (errorListener.hasErrors()) {
                System.out.println("reject");
                System.exit(1);
            }
            switch (directive) {
                case "-syn":
                    // Tenta fazer o parse do programa: já foi feito antes por default
                    System.out.println("accept");
                    break;

                case "-i":
                    // Tenta fazer a interpretação do

                    TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();
                    ast.accept(typeCheckVisitor);

                    if(typeCheckVisitor.getNumErrors() > 0){
                        typeCheckVisitor.printErrors();
                        System.out.println("reject");
                        System.exit(1);
                    }

                    InterpretVisitor iv = new InterpretVisitor();
                    ast.accept(iv);
                    break;

                case "-t":
                    System.out.println("\n--------------------Executando a verificação de tipos do programa.--------------------\n");
                    TypeCheckVisitor tcv = new TypeCheckVisitor();
                    ast.accept(tcv);
                    if(tcv.getNumErrors() > 0){
                        tcv.printErrors();
                        System.out.println("reject");
                        System.exit(1);
                    }else{
                        System.out.println("accept");
                    }
                    break;

                case "-src":
                    System.out.println("Gera código de alto n ível");
                    break;

                case "-gen":
                    System.out.println("Verificação de tipos.");

                    TypeCheckVisitor typeCheckS2J = new TypeCheckVisitor();
                    ast.accept(typeCheckS2J);

                    if(typeCheckS2J.getNumErrors() > 0){
                        typeCheckS2J.printErrors();
                        System.out.println("reject");
                        System.exit(1);
                    }

                    TyEnv<LocalEnv<SType>> env = typeCheckS2J.getEnv();
                    TyEnv<LocalEnv<VarInfo>> envWithIndices = getEnvWithVarIndex(env);

                    System.out.println("Gera código para jasmin.");
                    JasminVisitor jasminVisitor = new JasminVisitor(envWithIndices);

                    ast.accept(jasminVisitor);

                    break;

                default:
                    System.err.println("Diretiva desconhecida: " + directive);
                    System.exit(1);
            }

        } catch (InterpretException interpretException){
            System.out.println("Erro ao interpretar o programa...");
            System.err.println(interpretException.getMessage());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace(); // TODO: remover quando entregar o trabalho, isso imprime a stackTrace facilitando o debug
            System.err.println(e.getMessage());
            System.out.println("reject");
            System.exit(1);
        }
    }
}
