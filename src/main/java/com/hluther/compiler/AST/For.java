package com.hluther.compiler.AST;

import java.util.LinkedList;
/**
 *
 * @author helmuth
 */
public class For implements Instruction{
    
    private Instruction counter;
    private Instruction limitValue;
    private Instruction step;
    private LinkedList<Instruction> instructionsList;

    public For(Instruction counter, Instruction limitValue, Instruction step, LinkedList<Instruction> instructionsList) {
        this.counter = counter;
        this.limitValue = limitValue;
        this.step = step;
        this.instructionsList = instructionsList;
    }
    
    @Override
    public Object analyze(SymbolTable symbolTable) {
        
        
        
        return null;
    }
    
}


