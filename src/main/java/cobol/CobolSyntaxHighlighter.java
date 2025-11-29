package cobol;

import cobol.psi.CobolTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class CobolSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey LINE_NUMBER =
            createTextAttributesKey("COBOL_LINE_NUMBER", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("COBOL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("COBOL_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey IDENTIFIER =
            createTextAttributesKey("COBOL_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("COBOL_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("COBOL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("COBOL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] LINE_NUMBER_KEYS = new TextAttributesKey[]{LINE_NUMBER};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new CobolLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(CobolTypes.LINE_NUMBER)) {
            return LINE_NUMBER_KEYS;
        } else if (isKeyword(tokenType)) {
            return KEYWORD_KEYS;
        } else if (tokenType.equals(CobolTypes.STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(CobolTypes.IDENTIFIER)) {
            return IDENTIFIER_KEYS;
        } else if (tokenType.equals(CobolTypes.NUMBER)) {
            return NUMBER_KEYS;
        } else if (tokenType.equals(CobolTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }

    private boolean isKeyword(IElementType tokenType) {
        return tokenType.equals(CobolTypes.IDENTIFICATION) ||
               tokenType.equals(CobolTypes.DIVISION) ||
               tokenType.equals(CobolTypes.PROGRAM_ID) ||
               tokenType.equals(CobolTypes.DATA) ||
               tokenType.equals(CobolTypes.WORKING_STORAGE) ||
               tokenType.equals(CobolTypes.SECTION) ||
               tokenType.equals(CobolTypes.PROCEDURE) ||
               tokenType.equals(CobolTypes.PERFORM) ||
               tokenType.equals(CobolTypes.VARYING) ||
               tokenType.equals(CobolTypes.FROM) ||
               tokenType.equals(CobolTypes.BY) ||
               tokenType.equals(CobolTypes.UNTIL) ||
               tokenType.equals(CobolTypes.STOP) ||
               tokenType.equals(CobolTypes.RUN) ||
               tokenType.equals(CobolTypes.DISPLAY) ||
               tokenType.equals(CobolTypes.PIC) ||
               tokenType.equals(CobolTypes.VALUE);
    }
}
