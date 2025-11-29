package cobol.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import cobol.psi.CobolTypes;
import org.jetbrains.annotations.NotNull;

public class CobolParser implements PsiParser {
    @NotNull
    @Override
    public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
        PsiBuilder.Marker rootMarker = builder.mark();

        while (!builder.eof()) {
            parseItem(builder);
        }

        rootMarker.done(root);
        return builder.getTreeBuilt();
    }

    private void parseItem(PsiBuilder builder) {
        IElementType token = builder.getTokenType();

        if (token == CobolTypes.COMMENT || token == CobolTypes.CRLF) {
            builder.advanceLexer();
        } else if (token == CobolTypes.LINE_NUMBER || isCobolKeyword(token)) {
            parseCobolLine(builder);
        } else {
            builder.advanceLexer();
        }
    }

    private void parseCobolLine(PsiBuilder builder) {
        PsiBuilder.Marker lineMarker = builder.mark();

        // Parse optional line number
        if (builder.getTokenType() == CobolTypes.LINE_NUMBER) {
            builder.advanceLexer();
        }

        // Parse the statement
        parseStatement(builder);

        lineMarker.done(CobolTypes.COBOL_LINE);
    }

    private void parseStatement(PsiBuilder builder) {
        IElementType token = builder.getTokenType();

        if (token == CobolTypes.IDENTIFICATION) {
            parseIdentificationDivision(builder);
        } else if (token == CobolTypes.PROGRAM_ID) {
            parseProgramId(builder);
        } else if (token == CobolTypes.DATA) {
            parseDataDivision(builder);
        } else if (token == CobolTypes.WORKING_STORAGE) {
            parseWorkingStorageSection(builder);
        } else if (token == CobolTypes.PROCEDURE) {
            parseProcedureDivision(builder);
        } else if (token == CobolTypes.PERFORM) {
            parsePerformStatement(builder);
        } else if (token == CobolTypes.STOP) {
            parseStopStatement(builder);
        } else if (token == CobolTypes.DISPLAY) {
            parseDisplayStatement(builder);
        } else if (token == CobolTypes.NUMBER) {
            parseDataItem(builder);
        } else if (token == CobolTypes.IDENTIFIER) {
            parseParagraph(builder);
        } else {
            builder.advanceLexer();
        }
    }

    private void parseIdentificationDivision(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // IDENTIFICATION
        if (builder.getTokenType() == CobolTypes.DIVISION) {
            builder.advanceLexer();
        }
        if (builder.getTokenType() == CobolTypes.DOT) {
            builder.advanceLexer();
        }
        marker.done(CobolTypes.IDENTIFICATION_DIVISION);
    }

    private void parseProgramId(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // PROGRAM_ID
        if (builder.getTokenType() == CobolTypes.DOT) {
            builder.advanceLexer();
        }
        if (builder.getTokenType() == CobolTypes.IDENTIFIER) {
            builder.advanceLexer();
        }
        if (builder.getTokenType() == CobolTypes.DOT) {
            builder.advanceLexer();
        }
        marker.done(CobolTypes.PROGRAM_ID_STATEMENT);
    }

    private void parseDataDivision(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // DATA
        if (builder.getTokenType() == CobolTypes.DIVISION) {
            builder.advanceLexer();
        }
        if (builder.getTokenType() == CobolTypes.DOT) {
            builder.advanceLexer();
        }
        marker.done(CobolTypes.DATA_DIVISION);
    }

    private void parseWorkingStorageSection(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // WORKING_STORAGE
        if (builder.getTokenType() == CobolTypes.SECTION) {
            builder.advanceLexer();
        }
        if (builder.getTokenType() == CobolTypes.DOT) {
            builder.advanceLexer();
        }
        marker.done(CobolTypes.WORKING_STORAGE_SECTION);
    }

    private void parseDataItem(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // NUMBER (level)
        if (builder.getTokenType() == CobolTypes.IDENTIFIER) {
            builder.advanceLexer();
        }
        if (builder.getTokenType() == CobolTypes.PIC) {
            builder.advanceLexer();
            parsePicClause(builder);
        }
        if (builder.getTokenType() == CobolTypes.VALUE) {
            builder.advanceLexer();
            if (builder.getTokenType() == CobolTypes.NUMBER) {
                builder.advanceLexer();
            }
        }
        if (builder.getTokenType() == CobolTypes.DOT) {
            builder.advanceLexer();
        }
        marker.done(CobolTypes.DATA_ITEM);
    }

    private void parsePicClause(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        if (builder.getTokenType() == CobolTypes.NUMBER) {
            builder.advanceLexer();
        }
        if (builder.getTokenType() == CobolTypes.LPAREN) {
            builder.advanceLexer();
            if (builder.getTokenType() == CobolTypes.NUMBER) {
                builder.advanceLexer();
            }
            if (builder.getTokenType() == CobolTypes.RPAREN) {
                builder.advanceLexer();
            }
        }
        marker.done(CobolTypes.PIC_CLAUSE);
    }

    private void parseProcedureDivision(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // PROCEDURE
        if (builder.getTokenType() == CobolTypes.DIVISION) {
            builder.advanceLexer();
        }
        if (builder.getTokenType() == CobolTypes.DOT) {
            builder.advanceLexer();
        }
        marker.done(CobolTypes.PROCEDURE_DIVISION);
    }

    private void parseParagraph(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // IDENTIFIER
        if (builder.getTokenType() == CobolTypes.DOT) {
            builder.advanceLexer();
        }
        marker.done(CobolTypes.PARAGRAPH);
    }

    private void parsePerformStatement(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // PERFORM

        // Parse PERFORM statement components
        while (!builder.eof() && builder.getTokenType() != CobolTypes.CRLF) {
            builder.advanceLexer();
        }

        marker.done(CobolTypes.PERFORM_STATEMENT);
    }

    private void parseStopStatement(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // STOP
        if (builder.getTokenType() == CobolTypes.RUN) {
            builder.advanceLexer();
        }
        if (builder.getTokenType() == CobolTypes.DOT) {
            builder.advanceLexer();
        }
        marker.done(CobolTypes.STOP_STATEMENT);
    }

    private void parseDisplayStatement(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // DISPLAY

        // Parse display arguments
        while (!builder.eof() && builder.getTokenType() != CobolTypes.DOT && builder.getTokenType() != CobolTypes.CRLF) {
            builder.advanceLexer();
        }

        if (builder.getTokenType() == CobolTypes.DOT) {
            builder.advanceLexer();
        }

        marker.done(CobolTypes.DISPLAY_STATEMENT);
    }

    private boolean isCobolKeyword(IElementType token) {
        return token == CobolTypes.IDENTIFICATION ||
               token == CobolTypes.PROGRAM_ID ||
               token == CobolTypes.DATA ||
               token == CobolTypes.WORKING_STORAGE ||
               token == CobolTypes.PROCEDURE ||
               token == CobolTypes.PERFORM ||
               token == CobolTypes.STOP ||
               token == CobolTypes.DISPLAY ||
               token == CobolTypes.IDENTIFIER ||
               token == CobolTypes.NUMBER;
    }
}
