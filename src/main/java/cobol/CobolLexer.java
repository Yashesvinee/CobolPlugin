package cobol;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import cobol.psi.CobolTypes;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.intellij.psi.TokenType.BAD_CHARACTER;

public class CobolLexer implements FlexLexer {
    private String zzBuffer = "";
    private int zzCurrentPos;
    private int zzMarkedPos;
    private int zzEndRead;

    public CobolLexer(java.io.Reader in) {
        if (in != null) {
            try {
                zzBuffer = "";
                java.io.BufferedReader reader = new java.io.BufferedReader(in);
                String line;
                while ((line = reader.readLine()) != null) {
                    zzBuffer += line + "\n";
                }
                zzEndRead = zzBuffer.length();
            } catch (java.io.IOException e) {
                // Handle exception
            }
        }
    }

    @Override
    public IElementType advance() {
        if (zzCurrentPos >= zzEndRead) {
            return null;
        }

        zzMarkedPos = zzCurrentPos;

        // Skip whitespace
        while (zzCurrentPos < zzEndRead && Character.isWhitespace(zzBuffer.charAt(zzCurrentPos))) {
            if (zzBuffer.charAt(zzCurrentPos) == '\n') {
                zzCurrentPos++;
                return CobolTypes.CRLF;
            }
            zzCurrentPos++;
        }

        if (zzCurrentPos >= zzEndRead) {
            return null;
        }

        // Check for line numbers (2 digits at start of line)
        if (zzCurrentPos == 0 || (zzCurrentPos > 0 && zzBuffer.charAt(zzCurrentPos - 1) == '\n')) {
            if (zzCurrentPos + 1 < zzEndRead &&
                Character.isDigit(zzBuffer.charAt(zzCurrentPos)) &&
                Character.isDigit(zzBuffer.charAt(zzCurrentPos + 1))) {
                zzCurrentPos += 2;
                return CobolTypes.LINE_NUMBER;
            }
        }

        // Check for comments
        if (zzBuffer.charAt(zzCurrentPos) == '*') {
            while (zzCurrentPos < zzEndRead && zzBuffer.charAt(zzCurrentPos) != '\n') {
                zzCurrentPos++;
            }
            return CobolTypes.COMMENT;
        }

        // Check for keywords and identifiers
        String word = getNextWord();
        if (word != null) {
            switch (word) {
                case "IDENTIFICATION": return CobolTypes.IDENTIFICATION;
                case "DIVISION": return CobolTypes.DIVISION;
                case "PROGRAM-ID": return CobolTypes.PROGRAM_ID;
                case "DATA": return CobolTypes.DATA;
                case "WORKING-STORAGE": return CobolTypes.WORKING_STORAGE;
                case "SECTION": return CobolTypes.SECTION;
                case "PROCEDURE": return CobolTypes.PROCEDURE;
                case "PERFORM": return CobolTypes.PERFORM;
                case "VARYING": return CobolTypes.VARYING;
                case "FROM": return CobolTypes.FROM;
                case "BY": return CobolTypes.BY;
                case "UNTIL": return CobolTypes.UNTIL;
                case "STOP": return CobolTypes.STOP;
                case "RUN": return CobolTypes.RUN;
                case "DISPLAY": return CobolTypes.DISPLAY;
                case "PIC": return CobolTypes.PIC;
                case "VALUE": return CobolTypes.VALUE;
                default:
                    if (word.matches("[A-Z][A-Z0-9\\-]*")) {
                        return CobolTypes.IDENTIFIER;
                    } else if (word.matches("[0-9]+")) {
                        return CobolTypes.NUMBER;
                    }
                    break;
            }
        }

        // Check for strings
        if (zzBuffer.charAt(zzCurrentPos) == '\'') {
            zzCurrentPos++; // Skip opening quote
            while (zzCurrentPos < zzEndRead && zzBuffer.charAt(zzCurrentPos) != '\'') {
                zzCurrentPos++;
            }
            if (zzCurrentPos < zzEndRead) {
                zzCurrentPos++; // Skip closing quote
            }
            return CobolTypes.STRING;
        }

        // Check for single character tokens
        char ch = zzBuffer.charAt(zzCurrentPos);
        zzCurrentPos++;
        switch (ch) {
            case '.': return CobolTypes.DOT;
            case '=': return CobolTypes.EQUALS;
            case '(': return CobolTypes.LPAREN;
            case ')': return CobolTypes.RPAREN;
            default: return BAD_CHARACTER;
        }
    }

    private String getNextWord() {
        if (zzCurrentPos >= zzEndRead) return null;

        int start = zzCurrentPos;
        while (zzCurrentPos < zzEndRead &&
               (Character.isLetterOrDigit(zzBuffer.charAt(zzCurrentPos)) ||
                zzBuffer.charAt(zzCurrentPos) == '-')) {
            zzCurrentPos++;
        }

        if (zzCurrentPos > start) {
            return zzBuffer.substring(start, zzCurrentPos);
        }
        return null;
    }

    @Override
    public int getTokenStart() {
        return zzMarkedPos;
    }

    @Override
    public int getTokenEnd() {
        return zzCurrentPos;
    }

    @Override
    public void reset(CharSequence buffer, int start, int end, int initialState) {
        zzBuffer = buffer.toString();
        zzCurrentPos = start;
        zzMarkedPos = start;
        zzEndRead = end;
    }

    public int getState() {
        return 0;
    }

    // Additional required FlexLexer methods
    public int yystate() {
        return 0;
    }

    public void yybegin(int state) {
        // No-op for simple lexer
    }

    public CharSequence yytext() {
        return zzBuffer.subSequence(zzMarkedPos, zzCurrentPos);
    }

    public int yylength() {
        return zzCurrentPos - zzMarkedPos;
    }
}
