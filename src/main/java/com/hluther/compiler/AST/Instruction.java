package com.hluther.compiler.AST;
/**
 *
 * @author helmuth
 */
public interface Instruction {
    
    public Object analyze(SymbolTable symbolTable);
}
