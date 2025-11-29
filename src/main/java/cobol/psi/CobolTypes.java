package cobol.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class CobolTypes {
    // Token types for COBOL keywords and symbols
    public static final IElementType LINE_NUMBER = new CobolTokenType("LINE_NUMBER");
    public static final IElementType IDENTIFICATION = new CobolTokenType("IDENTIFICATION");
    public static final IElementType DIVISION = new CobolTokenType("DIVISION");
    public static final IElementType PROGRAM_ID = new CobolTokenType("PROGRAM_ID");
    public static final IElementType DATA = new CobolTokenType("DATA");
    public static final IElementType WORKING_STORAGE = new CobolTokenType("WORKING_STORAGE");
    public static final IElementType SECTION = new CobolTokenType("SECTION");
    public static final IElementType PROCEDURE = new CobolTokenType("PROCEDURE");
    public static final IElementType PERFORM = new CobolTokenType("PERFORM");
    public static final IElementType VARYING = new CobolTokenType("VARYING");
    public static final IElementType FROM = new CobolTokenType("FROM");
    public static final IElementType BY = new CobolTokenType("BY");
    public static final IElementType UNTIL = new CobolTokenType("UNTIL");
    public static final IElementType STOP = new CobolTokenType("STOP");
    public static final IElementType RUN = new CobolTokenType("RUN");
    public static final IElementType DISPLAY = new CobolTokenType("DISPLAY");
    public static final IElementType PIC = new CobolTokenType("PIC");
    public static final IElementType VALUE = new CobolTokenType("VALUE");

    // Symbol tokens
    public static final IElementType DOT = new CobolTokenType("DOT");
    public static final IElementType EQUALS = new CobolTokenType("EQUALS");
    public static final IElementType LPAREN = new CobolTokenType("LPAREN");
    public static final IElementType RPAREN = new CobolTokenType("RPAREN");

    // Basic tokens
    public static final IElementType IDENTIFIER = new CobolTokenType("IDENTIFIER");
    public static final IElementType STRING = new CobolTokenType("STRING");
    public static final IElementType NUMBER = new CobolTokenType("NUMBER");
    public static final IElementType COMMENT = new CobolTokenType("COMMENT");
    public static final IElementType CRLF = new CobolTokenType("CRLF");

    // Element types for grammar rules
    public static final IElementType COBOL_FILE = new CobolElementType("COBOL_FILE");
    public static final IElementType COBOL_LINE = new CobolElementType("COBOL_LINE");
    public static final IElementType IDENTIFICATION_DIVISION = new CobolElementType("IDENTIFICATION_DIVISION");
    public static final IElementType PROGRAM_ID_STATEMENT = new CobolElementType("PROGRAM_ID_STATEMENT");
    public static final IElementType DATA_DIVISION = new CobolElementType("DATA_DIVISION");
    public static final IElementType WORKING_STORAGE_SECTION = new CobolElementType("WORKING_STORAGE_SECTION");
    public static final IElementType DATA_ITEM = new CobolElementType("DATA_ITEM");
    public static final IElementType PIC_CLAUSE = new CobolElementType("PIC_CLAUSE");
    public static final IElementType PROCEDURE_DIVISION = new CobolElementType("PROCEDURE_DIVISION");
    public static final IElementType PARAGRAPH = new CobolElementType("PARAGRAPH");
    public static final IElementType PERFORM_STATEMENT = new CobolElementType("PERFORM_STATEMENT");
    public static final IElementType STOP_STATEMENT = new CobolElementType("STOP_STATEMENT");
    public static final IElementType DISPLAY_STATEMENT = new CobolElementType("DISPLAY_STATEMENT");

    public static class Factory {
        public static PsiElement createElement(ASTNode node) {
            return new ASTWrapperPsiElement(node);
        }
    }
}
