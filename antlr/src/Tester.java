package src;
import ast.Node;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import parser.*;
import util.SyntaxErrorListener;
import visitors.InterpretVisitor;

import java.io.File;

import static org.junit.Assert.*;

public class Tester {

    public static boolean checkSyntax(String filePath) {

        SyntaxErrorListener errorListener = SyntaxErrorListener.INSTANCE;
        errorListener.reset();

        try {
            // 1) Alimenta o ANTLR com o arquivo
            CharStream stream = CharStreams.fromFileName(filePath);

            // 2) Cria o lexer e adiciona nosso listener de erro
            LangLexer lexer = new LangLexer(stream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(errorListener);

            // 3) Cria o token stream e o parser
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            LangParser parser = new LangParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);

            // 4) Não construímos a árvore de parse completa (opcional)
            parser.setBuildParseTree(false);

            // 5) Invoca a regra inicial (prog)
            parser.prog();

        } catch (Exception e) {
            // Pode ser erro de I/O ou runtime interno do parser: considera inválido
            return false;
        }

        // Se o listener registrou algum erro, syntax reject
        return !errorListener.hasErrors();
    }

    @Test
    public void testSyntaxCorrectFiles() {
        File dir = new File("examples/tests/sintaxe/certo");
        File[] files = dir.listFiles((d, name) -> name.endsWith(".lan"));
        assertNotNull("Diretório 'certo' não encontrado ou erro ao listar arquivos", files);

        for (File file : files) {
            boolean result = checkSyntax(file.getPath());
            assertTrue("Arquivo deveria ser aceito: " + file.getPath(), result);
        }
    }

    @Test
    public void testSyntaxIncorrectFiles() {
        File dir = new File("examples/tests/sintaxe/errado");
        File[] files = dir.listFiles((d, name) -> name.endsWith(".lan"));
        assertNotNull("Diretório 'errado' não encontrado ou erro ao listar arquivos", files);

        for (File file : files) {
            boolean result = checkSyntax(file.getPath());
            assertFalse("Arquivo deveria ser rejeitado: " + file.getPath(), result);
        }
    }
}
