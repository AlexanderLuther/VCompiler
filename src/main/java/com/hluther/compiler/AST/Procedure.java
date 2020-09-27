package com.hluther.compiler.AST;

import java.util.LinkedList;

/**
 *
 * @author helmuth
 */
public class Procedure implements Instruction {
    
    private AccessModifier accessModifier;
    private String id;
    private LinkedList<Type> params;
    private LinkedList<Instruction> instructionsList;

    public Procedure(AccessModifier accessModifier, String id, LinkedList<Type> params, LinkedList<Instruction> instructionsList) {
        this.accessModifier = accessModifier;
        this.id = id;
        this.params = params;
        this.instructionsList = instructionsList;
    }
    
    @Override
    public Object analyze(SymbolTable symbolTable) {
        if(params != null){
            System.out.println("Procedimiento " + id + " Parametros: "+ params.toString());
        
        }
        else{
            System.out.println("Procedimiento " + id);  
        }
            for(var instruction : instructionsList){
                instruction.analyze(null);
            }
        
        
        return null;
    }  
}