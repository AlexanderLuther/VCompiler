package com.hluther.compiler.AST;

/**
 *
 * @author helmuth
 */
public class Print implements Instruction{

    private Operation message;
    private PrintType type;
    
    public Print(Operation message, PrintType type){
        this.message = message;
        this.type = type;
    }
    
    @Override
    public Object analyze(SymbolTable symbolTable) {
        System.out.println(message.analyze(symbolTable));
        return null;
    }
    
    public static enum PrintType{
        PRINT,
        PRINTLN
    }
}
