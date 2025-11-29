package cobol;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static cobol.psi.CobolTypes.*;

%%

%{
  public CobolLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class CobolLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

LINE_NUMBER=[0-9]{2}
IDENTIFIER=[A-Z][A-Z0-9\-]*
STRING=\'[^\']*\'
NUMBER=[0-9]+
WHITE_SPACE=[ \t\n\x0B\f\r]+
COMMENT=\*.*

%%

<YYINITIAL> {
  {LINE_NUMBER}        { return LINE_NUMBER; }
  "IDENTIFICATION"     { return IDENTIFICATION; }
  "DIVISION"           { return DIVISION; }
  "PROGRAM-ID"         { return PROGRAM_ID; }
  "DATA"               { return DATA; }
  "WORKING-STORAGE"    { return WORKING_STORAGE; }
  "SECTION"            { return SECTION; }
  "PROCEDURE"          { return PROCEDURE; }
  "PERFORM"            { return PERFORM; }
  "VARYING"            { return VARYING; }
  "FROM"               { return FROM; }
  "BY"                 { return BY; }
  "UNTIL"              { return UNTIL; }
  "STOP"               { return STOP; }
  "RUN"                { return RUN; }
  "DISPLAY"            { return DISPLAY; }
  "PIC"                { return PIC; }
  "VALUE"              { return VALUE; }
  "."                  { return DOT; }
  "="                  { return EQUALS; }
  "("                  { return LPAREN; }
  ")"                  { return RPAREN; }
  {IDENTIFIER}         { return IDENTIFIER; }
  {STRING}             { return STRING; }
  {NUMBER}             { return NUMBER; }
  {COMMENT}            { return COMMENT; }
  {WHITE_SPACE}        { return WHITE_SPACE; }
  [^]                  { return BAD_CHARACTER; }
}
