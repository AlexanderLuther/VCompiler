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
          //  vCompilerFrame.printMessage("Error Lexico: Lexema ["+errorLexeme+"] no reconocido en esta seccion del archivo. Linea: "+line+" Columna: "+column + "\n");
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
    "ElseIf"                                            { printToken("ELSEIF"); printError(); return symbol(VSym.ELSEIF); }
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
    "="                                                 { printToken("EQUALS"); printError(); return symbol(VSym.EQUALS); }
    "<"                                                 { printToken("LESSTHAN"); printError(); return symbol(VSym.LESSTHAN); }
    "<="                                                { printToken("LESSEQUALTHAN"); printError(); return symbol(VSym.LESSEQUALTHAN); }
    ">"                                                 { printToken("GREATERTHAN"); printError(); return symbol(VSym.GREATERTHAN); }
    ">="                                                { printToken("GREATEREQUALTHAN"); printError(); return symbol(VSym.GREATEREQUALTHAN); }
    "<>"                                                { printToken("NOTEQUAL"); printError(); return symbol(VSym.NOTEQUAL); }
    "("                                                 { printToken("PARENTHESISO"); printError(); return symbol(VSym.PARENTHESISO); }
    ")"                                                 { printToken("PARENTHESISC"); printError(); return symbol(VSym.PARENTHESISC); }
    "["                                                 { printToken("SQUAREBRACKETO"); printError(); return symbol(VSym.SQUAREBRACKETO); }
    "]"                                                 { printToken("SQUAREBRACKETC"); printError(); return symbol(VSym.SQUAREBRACKETC); }
    ","                                                 { printToken("COMMA"); printError(); return symbol(VSym.COMMA); }
    "."                                                 { printToken("FULLSTOP"); printError(); return symbol(VSym.FULLSTOP); }
    "&"                                                 { printToken("CONCAT"); printError(); return symbol(VSym.CONCAT); }
    ({Letter} | "_") ({Letter} | {Number} | "_")*       { printToken("ID"); printError(); return symbol(VSym.ID, yytext()); }
    ("\"") [^]{1} ("\"")                                { printToken("CHARACTER"); printError(); return symbol(VSym.CHARACTER, yytext().charAt(1)); }
    ("\"") [^*]~ ("\"")                                 { printToken("LITERAL"); printError(); return symbol(VSym.LITERAL, yytext()); }
    "0" | [1-9][0-9]*                                   { printToken("INTEGERNUM"); printError(); return symbol(VSym.INTEGERNUM, Integer.parseInt(yytext())); }
    {Number}+ "." {Number}+                             { printToken("DOUBLENUM"); printError(); return symbol(VSym.DOUBLENUM, Double.parseDouble(yytext())); }
    "//"{InputCharacter}* {LineTerminator}?             { printToken("LINECOMMENT"); printError(); } //Ignore
    ("/*" [^*/]* "*/") | ("/*" [^/]~ "*/")              { printToken("BLOCKCOMMENT"); printError(); } //Ignore
    {WhiteSpace}                                        { printError(); } //Ignore
    [^]                                                 { printToken("ERROR"); createErrorLexeme(yytext(), (yyline+1), yycolumn); } 
}



<JAVA>{
    "%%PY"                                              { yybegin(PHYTON); printToken("P_SEPARATOR"); printError(); return symbol(VSym.P_SEPARATOR); }
}

<PHYTON>{
    "%%PROGRAMA"                                        { yybegin(C); printToken("C_SEPARATOR"); printError(); return symbol(VSym.C_SEPARATOR); }
}

<C>{
[^]                                             { printToken("ERROR"); createErrorLexeme(yytext(), (yyline+1), yycolumn); } 
}