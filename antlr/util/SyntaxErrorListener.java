package util;

import org.antlr.v4.runtime.*;

public class SyntaxErrorListener extends BaseErrorListener {
    public static final SyntaxErrorListener INSTANCE = new SyntaxErrorListener();

    private boolean hasErrors = false;

    public boolean hasErrors() {
        return hasErrors;
    }
    public void reset() {
        hasErrors = false;
    }
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e) {
        hasErrors = true;
        throw new RuntimeException("Erro na linha " + line + ":" + charPositionInLine + " - " + msg);
    }
}
