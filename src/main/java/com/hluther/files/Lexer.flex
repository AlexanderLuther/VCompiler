package com.hluther.compiler.lexer;
import com.hluther.gui.VCompilerFrame;
import com.hluther.compiler.parser.VSym;
import java_cup.runtime.*;
%%//

/* 
----------------------------------- Opciones y declaraciones de JFlex -----------------------------------
*/
%class VLexer
%cup
%cupdebug
%line 
%column
%public
%states VISUAL_BASIC, JAVA, PHYTON, C

Letter = [a-zA-Z]
Number = [0-9]
InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

/* 
----------------------------------- Codigo Java -----------------------------------
*/
%{
    private VCompilerFrame vCompilerFrame;
    private String errorLexeme; 
    private int line;
    private int column;

    public VLexer(java.io.Reader in, VCompilerFrame vCompilerFrame) {
        this.vCompilerFrame = vCompilerFrame;    
        this.zzReader = in;
        this.errorLexeme = "";
        line = -1;
        column = -1;
    }
        
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn, value);
    }

    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn);
    }

    private void printToken(String token){
        System.out.println(token);
    }

    private void createErrorLexeme(String lexeme, int line, int column){
        if(this.line == -1 && this.column == -1){
            this.line = line;
            this.column = column;
        }
        errorLexeme = errorLexeme + lexeme;
    }

    private void printError(){
        if(line != -1 && column != -1){
            vCompilerFrame.printMessage("Fila: " +line+ " Columna" +column+ " Error Lexico: Lexema ["+errorLexeme+"] no reconocido en esta seccion del archivo.");
            errorLexeme = "";
            line = -1;
            column = -1;
        }
    }

%}
%%//

/* 
----------------------------------- Reglas Lexicas ----------------------------------- 
*/
<YYINITIAL>{
    "%%VB"                                          { yybegin(VISUAL_BASIC); printToken("VB_SEPARATOR"); printError(); return symbol(VSym.VB_SEPARATOR); }
    "//"{InputCharacter}* {LineTerminator}?         { printToken("LINECOMMENT"); printError(); } //Ignore
    ("/*" [^*/]* "*/") | ("/*" [^/]~ "*/")          { printToken("BLOCKCOMMENT"); printError(); } //Ignore
    {WhiteSpace}                                    { printError(); } //Ignore
    [^]                                             { printToken("ERROR"); createErrorLexeme(yytext(), (yyline+1), yycolumn); } 
}

<VISUAL_BASIC>{
    "%%JAVA"                                            { yybegin(JAVA); printToken("J_SEPARATOR"); printError(); return symbol(VSym.J_SEPARATOR); }
    "Dim"                                               { printToken("DIM"); printError(); return symbol(VSym.DIM); }
    "As"                                                { printToken("AS"); printError(); return symbol(VSym.AS); }
    "Public"                                            { printToken("PUBLIC"); printError(); return symbol(VSym.PUBLIC); }
    "Function"                                          { printToken("FUNCTION"); printError(); return symbol(VSym.FUNCTION); }
    "End"                                               { printToken("END"); printError(); return symbol(VSym.END); }
    "Return"                                            { printToken("RETURN"); printError(); return symbol(VSym.RETURN); }
    "Sub"                                               { printToken("SUB"); printError(); return symbol(VSym.SUB); }
    "ByVal"                                             { printToken("BYVAL"); printError(); return symbol(VSym.BYVAL); }
    "If"                                                { printToken("IF"); printError(); return symbol(VSym.IF); }
    "Then"                                              { printToken("THEN"); printError(); return symbol(VSym.THEN); }
    "Else"                                              { printToken("ELSE"); printError(); return symbol(VSym.ELSE); }
    "Select"                                            { printToken("SELECT"); printError(); return symbol(VSym.SELECT); }
    "Case"                                              { printToken("CASE"); printError(); return symbol(VSym.CASE); }
    "While"                                             { printToken("WHILE"); printError(); return symbol(VSym.WHILE); }
    "Do"                                                { printToken("DO"); printError(); return symbol(VSym.DO); }
    "Loop"                                              { printToken("LOOP"); printError(); return symbol(VSym.LOOP); }
    "For"                                               { printToken("FOR"); printError(); return symbol(VSym.FOR); }
    "Next"                                              { printToken("NEXT"); printError(); return symbol(VSym.NEXT); }
    "To"                                                { printToken("TO"); printError(); return symbol(VSym.TO); }
    "Step"                                              { printToken("STEP"); printError(); return symbol(VSym.STEP); }
    "intinput"                                          { printToken("INTINPUT"); printError(); return symbol(VSym.INTINPUT); }
    "floatinput"                                        { printToken("FLOATINPUT"); printError(); return symbol(VSym.FLOATINPUT); }
    "charinput"                                         { printToken("CHARINPUT"); printError(); return symbol(VSym.CHARINPUT); }
    "Console"                                           { printToken("CONSOLE"); printError(); return symbol(VSym.CONSOLE); }
    "Write"                                             { printToken("WRITE"); printError(); return symbol(VSym.WRITE); }
    "WriteLine"                                         { printToken("WRITELINE"); printError(); return symbol(VSym.WRITELINE); }
    "Integer"                                           { printToken("INTEGER"); printError(); return symbol(VSym.INTEGER); }
    "Double"                                            { printToken("DOUBLE"); printError(); return symbol(VSym.DOUBLE); }
    "Char"                                              { printToken("CHAR"); printError(); return symbol(VSym.CHAR); }
    "And"                                               { printToken("AND"); printError(); return symbol(VSym.AND); }
    "Or"                                                { printToken("OR"); printError(); return symbol(VSym.OR); }  
    "Not"                                               { printToken("NOT"); printError(); return symbol(VSym.NOT); }    
    "+"                                                 { printToken("PLUS"); printError(); return symbol(VSym.PLUS); }
    "-"                                                 { printToken("MINUS"); printError(); return symbol(VSym.MINUS); }
    "/"                                                 { printToken("DIVISION"); printError(); return symbol(VSym.DIVISION); }
    "*"                                                 { printToken("MULTIPLICATION"); printError(); return symbol(VSym.MULTIPLICATION); }
    "Mod"                                               { printToken("MOD"); printError(); return symbol(VSym.MOD); }
    "="                                                 { printToken("COMPARATION"); printError(); return symbol(VSym.COMPARATION); }
    "<"                                                 { printToken("LESSTHAN"); printError(); return symbol(VSym.LESSTHAN); }
    "<="                                                { printToken("LESSEQUALTHAN"); printError(); return symbol(VSym.LESSEQUALTHAN); }
    ">"                                                 { printToken("GREATERTHAN"); printError(); return symbol(VSym.GREATERTHAN); }
    ">="                                                { printToken("GREATEREQUALTHAN"); printError(); return symbol(VSym.GREATEREQUALTHAN); }
    "<>"                                                { printToken("NOTEQUAL"); printError(); return symbol(VSym.NOTEQUAL); }
    "("                                                 { printToken("PARENTHESISO"); printError(); return symbol(VSym.PARENTHESISO); }
    ")"                                                 { printToken("PARENTHESISC"); printError(); return symbol(VSym.PARENTHESISC); }
    ","                                                 { printToken("COMMA"); printError(); return symbol(VSym.COMMA); }
    "."                                                 { printToken("FULLSTOP"); printError(); return symbol(VSym.FULLSTOP); }
    "&"                                                 { printToken("CONCAT"); printError(); return symbol(VSym.CONCAT); }
    ({Letter} | "_") ({Letter} | {Number} | "_")*       { printToken("ID"); printError(); return symbol(VSym.ID, yytext()); }
    ("\"") [^]{1} ("\"")                                { printToken("CHARACTER"); printError(); return symbol(VSym.CHARACTER, yytext().charAt(1)); }
    (\") [^\"]* (\")?                                         { printToken("LITERAL"); printError(); return symbol(VSym.LITERAL, yytext()); }
    "0" | [1-9][0-9]*                                   { printToken("INTEGERNUM"); printError(); return symbol(VSym.INTEGERNUM, Integer.parseInt(yytext())); }
    {Number}+ "." {Number}+                             { printToken("DOUBLENUM"); printError(); return symbol(VSym.DOUBLENUM, Double.parseDouble(yytext())); }
    "//"{InputCharacter}* {LineTerminator}?             { printToken("LINECOMMENT"); printError(); } //Ignore
    ("/*" [^*/]* "*/") | ("/*" [^/]~ "*/")              { printToken("BLOCKCOMMENT"); printError(); } //Ignore
    {WhiteSpace}                                        { printError(); } //Ignore
    [^]                                                 { printToken("ERROR"); createErrorLexeme(yytext(), (yyline+1), yycolumn); } 
}

<JAVA>{
    "%%PY"                                          { yybegin(PHYTON); printToken("P_SEPARATOR"); printError(); return symbol(VSym.P_SEPARATOR); }
    "public"                                        { printToken("PUBLIC"); printError(); return symbol(VSym.PUBLIC); }
    "class"                                         { printToken("CLASS"); printError(); return symbol(VSym.CLASS); }
    "void"                                          { printToken("VOID"); printError(); return symbol(VSym.VOID); }
    "int"                                           { printToken("INTEGER"); printError(); return symbol(VSym.INTEGER); }
    "float"                                         { printToken("FLOAT"); printError(); return symbol(VSym.FLOAT); }
    "char"                                          { printToken("CHAR"); printError(); return symbol(VSym.CHAR); }
    "while"                                         { printToken("WHILE"); printError(); return symbol(VSym.WHILE); }
    "do"                                            { printToken("DO"); printError(); return symbol(VSym.DO); }
    "for"                                           { printToken("FOR"); printError(); return symbol(VSym.FOR); }
    "if"                                            { printToken("IF"); printError(); return symbol(VSym.IF); }
    "else"                                          { printToken("ELSE"); printError(); return symbol(VSym.ELSE); }
    "switch"                                        { printToken("SWITCH"); printError(); return symbol(VSym.SWITCH); }
    "case"                                          { printToken("CASE"); printError(); return symbol(VSym.CASE); }                                                    
    "default"                                       { printToken("DEFAULT"); printError(); return symbol(VSym.DEFAULT); }
    "break"                                         { printToken("BREAK"); printError(); return symbol(VSym.BREAK); }
    "intinput"                                      { printToken("INTINPUT"); printError(); return symbol(VSym.INTINPUT); }
    "floatinput"                                    { printToken("FLOATINPUT"); printError(); return symbol(VSym.FLOATINPUT); }
    "charinput"                                     { printToken("CHARINPUT"); printError(); return symbol(VSym.CHARINPUT); }
    "return"                                        { printToken("RETURN"); printError(); return symbol(VSym.RETURN); }
    "System"                                        { printToken("SYSTEM"); printError(); return symbol(VSym.SYSTEM); }
    "out"                                           { printToken("OUT"); printError(); return symbol(VSym.OUT); }
    "print"                                         { printToken("PRINT"); printError(); return symbol(VSym.PRINT); }
    "println"                                       { printToken("PRINTLN"); printError(); return symbol(VSym.PRINTLN); }
    "&&"                                            { printToken("AND"); printError(); return symbol(VSym.AND); }
    "||"                                            { printToken("OR"); printError(); return symbol(VSym.OR); }  
    "!"                                             { printToken("NOT"); printError(); return symbol(VSym.NOT); }    
    "+"                                             { printToken("PLUS"); printError(); return symbol(VSym.PLUS); }
    "-"                                             { printToken("MINUS"); printError(); return symbol(VSym.MINUS); }
    "/"                                             { printToken("DIVISION"); printError(); return symbol(VSym.DIVISION); }
    "*"                                             { printToken("MULTIPLICATION"); printError(); return symbol(VSym.MULTIPLICATION); }
    "%"                                             { printToken("MOD"); printError(); return symbol(VSym.MOD); }
    "="                                             { printToken("EQUALS"); printError(); return symbol(VSym.EQUALS); }
    "=="                                            { printToken("COMPARATION"); printError(); return symbol(VSym.COMPARATION); }
    "<"                                             { printToken("LESSTHAN"); printError(); return symbol(VSym.LESSTHAN); }
    "<="                                            { printToken("LESSEQUALTHAN"); printError(); return symbol(VSym.LESSEQUALTHAN); }
    ">"                                             { printToken("GREATERTHAN"); printError(); return symbol(VSym.GREATERTHAN); }
    ">="                                            { printToken("GREATEREQUALTHAN"); printError(); return symbol(VSym.GREATEREQUALTHAN); }
    "!="                                            { printToken("NOTEQUAL"); printError(); return symbol(VSym.NOTEQUAL); }
    "("                                             { printToken("PARENTHESISO"); printError(); return symbol(VSym.PARENTHESISO); }
    ")"                                             { printToken("PARENTHESISC"); printError(); return symbol(VSym.PARENTHESISC); }
    "{"                                             { printToken("CURLYBRACKETO"); printError(); return symbol(VSym.CURLYBRACKETO); }
    "}"                                             { printToken("CURLYBRACKETC"); printError(); return symbol(VSym.CURLYBRACKETC); }
    ","                                             { printToken("COMMA"); printError(); return symbol(VSym.COMMA); }
    "."                                             { printToken("FULLSTOP"); printError(); return symbol(VSym.FULLSTOP); }
    ";"                                             { printToken("SEMICOLON"); printError(); return symbol(VSym.SEMICOLON); }
    ":"                                             { printToken("COLON"); printError(); return symbol(VSym.COLON); }
    (\") [^\"]* (\")?                               { printToken("LITERAL"); printError(); return symbol(VSym.LITERAL, yytext()); }
    ("'") [^]{1} ("'")                              { printToken("CHARACTER"); printError(); return symbol(VSym.CHARACTER, yytext().charAt(1)); }
    ({Letter} | "_") ({Letter} | {Number} | "_")*   { printToken("ID"); printError(); return symbol(VSym.ID, yytext()); }
    "0" | [1-9][0-9]*                               { printToken("INTEGERNUM"); printError(); return symbol(VSym.INTEGERNUM, Integer.parseInt(yytext())); }
    {Number}+ "." {Number}+                         { printToken("DOUBLENUM"); printError(); return symbol(VSym.DOUBLENUM, Double.parseDouble(yytext())); }
    "//"{InputCharacter}* {LineTerminator}?         { printToken("LINECOMMENT"); printError(); } //Ignore
    ("/*" [^*/]* "*/") | ("/*" [^/]~ "*/")          { printToken("BLOCKCOMMENT"); printError(); } //Ignore
    {WhiteSpace}                                    { printError(); } //Ignore
}

<PHYTON>{
    "%%PROGRAMA"                                        { yybegin(C); printToken("C_SEPARATOR"); printError(); return symbol(VSym.C_SEPARATOR); }   
}

<C>{
    "#include"                                                      { printToken("INCLUDE"); printError(); return symbol(VSym.INCLUDE); }
    "\"VB\""                                                        { printToken("VB_INCLUDE"); printError(); return symbol(VSym.VB_INCLUDE); }
    "\"PY\""                                                        { printToken("PY_INCLUDE"); printError(); return symbol(VSym.PY_INCLUDE); }
    "\"JAVA.*\""                                                    { printToken("J_INCLUDE_ALL"); printError(); return symbol(VSym.J_INCLUDE_ALL); }
    "\"JAVA." ({Letter} | "_") ({Letter} | {Number} | "_")* "\""    { printToken("J_INCLUDE"); printError(); return symbol(VSym.J_INCLUDE, yytext()); }  
    "const"                                                         { printToken("CONST"); printError(); return symbol(VSym.CONST); }
    "void"                                                          { printToken("VOID"); printError(); return symbol(VSym.VOID); }
    "main"                                                          { printToken("MAIN"); printError(); return symbol(VSym.MAIN); }
    "int"                                                           { printToken("INTEGER"); printError(); return symbol(VSym.INTEGER); }
    "float"                                                         { printToken("FLOAT"); printError(); return symbol(VSym.FLOAT); }
    "char"                                                          { printToken("CHAR"); printError(); return symbol(VSym.CHAR); }
    "while"                                                         { printToken("WHILE"); printError(); return symbol(VSym.WHILE); }
    "do"                                                            { printToken("DO"); printError(); return symbol(VSym.DO); }
    "for"                                                           { printToken("FOR"); printError(); return symbol(VSym.FOR); }
    "if"                                                            { printToken("IF"); printError(); return symbol(VSym.IF); }
    "else"                                                          { printToken("ELSE"); printError(); return symbol(VSym.ELSE); }
    "printf"                                                        { printToken("PRINTF"); printError(); return symbol(VSym.PRINTF); }   
    "switch"                                                        { printToken("SWITCH"); printError(); return symbol(VSym.SWITCH); }
    "case"                                                          { printToken("CASE"); printError(); return symbol(VSym.CASE); }                                                    
    "default"                                                       { printToken("DEFAULT"); printError(); return symbol(VSym.DEFAULT); }
    "break"                                                         { printToken("BREAK"); printError(); return symbol(VSym.BREAK); }
    "scanf"                                                         { printToken("SCANF"); printError(); return symbol(VSym.SCANF); }
    "%d"                                                            { printToken("INTYTPE"); printError(); return symbol(VSym.INTTYPE); }
    "%c"                                                            { printToken("CHARTYPE"); printError(); return symbol(VSym.CHARTYPE); }
    "%f"                                                            { printToken("FLOATTYPE"); printError(); return symbol(VSym.FLOATTYPE); }
    "clrscr"                                                        { printToken("CLRSCR"); printError(); return symbol(VSym.CLRSCR); }
    "getch"                                                         { printToken("GETCH"); printError(); return symbol(VSym.GETCH); }
    "&&"                                                            { printToken("AND"); printError(); return symbol(VSym.AND); }
    "||"                                                            { printToken("OR"); printError(); return symbol(VSym.OR); }  
    "!"                                                             { printToken("NOT"); printError(); return symbol(VSym.NOT); }    
    "+"                                                             { printToken("PLUS"); printError(); return symbol(VSym.PLUS); }
    "-"                                                             { printToken("MINUS"); printError(); return symbol(VSym.MINUS); }
    "/"                                                             { printToken("DIVISION"); printError(); return symbol(VSym.DIVISION); }
    "*"                                                             { printToken("MULTIPLICATION"); printError(); return symbol(VSym.MULTIPLICATION); }
    "%"                                                             { printToken("MOD"); printError(); return symbol(VSym.MOD); }
    "="                                                             { printToken("EQUALS"); printError(); return symbol(VSym.EQUALS); }
    "=="                                                            { printToken("COMPARATION"); printError(); return symbol(VSym.COMPARATION); }
    "<"                                                             { printToken("LESSTHAN"); printError(); return symbol(VSym.LESSTHAN); }
    "<="                                                            { printToken("LESSEQUALTHAN"); printError(); return symbol(VSym.LESSEQUALTHAN); }
    ">"                                                             { printToken("GREATERTHAN"); printError(); return symbol(VSym.GREATERTHAN); }
    ">="                                                            { printToken("GREATEREQUALTHAN"); printError(); return symbol(VSym.GREATEREQUALTHAN); }
    "!="                                                            { printToken("NOTEQUAL"); printError(); return symbol(VSym.NOTEQUAL); }
    "("                                                             { printToken("PARENTHESISO"); printError(); return symbol(VSym.PARENTHESISO); }
    ")"                                                             { printToken("PARENTHESISC"); printError(); return symbol(VSym.PARENTHESISC); }
    "["                                                             { printToken("SQUAREBRACKETO"); printError(); return symbol(VSym.SQUAREBRACKETO); }
    "]"                                                             { printToken("SQUAREBRACKETC"); printError(); return symbol(VSym.SQUAREBRACKETC); }
    "{"                                                             { printToken("CURLYBRACKETO"); printError(); return symbol(VSym.CURLYBRACKETO); }
    "}"                                                             { printToken("CURLYBRACKETC"); printError(); return symbol(VSym.CURLYBRACKETC); }
    ","                                                             { printToken("COMMA"); printError(); return symbol(VSym.COMMA); }
    "."                                                             { printToken("FULLSTOP"); printError(); return symbol(VSym.FULLSTOP); }
    ";"                                                             { printToken("SEMICOLON"); printError(); return symbol(VSym.SEMICOLON); }
    ":"                                                             { printToken("COLON"); printError(); return symbol(VSym.COLON); }
    "&"                                                             { printToken("AMPERSAND"); printError(); return symbol(VSym.AMPERSAND); }
    (\") [^\"]* (\")?                                               { printToken("LITERAL"); printError(); return symbol(VSym.LITERAL, yytext()); }
    ("'") [^]{1} ("'")                                              { printToken("CHARACTER"); printError(); return symbol(VSym.CHARACTER, yytext().charAt(1)); }
    ({Letter} | "_") ({Letter} | {Number} | "_")*                   { printToken("ID"); printError(); return symbol(VSym.ID, yytext()); }
    "0" | [1-9][0-9]*                                               { printToken("INTEGERNUM"); printError(); return symbol(VSym.INTEGERNUM, Integer.parseInt(yytext())); }
    {Number}+ "." {Number}+                                         { printToken("DOUBLENUM"); printError(); return symbol(VSym.DOUBLENUM, Double.parseDouble(yytext())); }
    "//"{InputCharacter}* {LineTerminator}?                         { printToken("LINECOMMENT"); printError(); } //Ignore
    ("/*" [^*/]* "*/") | ("/*" [^/]~ "*/")                          { printToken("BLOCKCOMMENT"); printError(); } //Ignore
    {WhiteSpace}                                                    { printError(); } //Ignore
    [^]                                                             { printToken("ERROR"); createErrorLexeme(yytext(), (yyline+1), yycolumn); } 
}