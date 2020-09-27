package com.hluther.compiler.AST;

import java.util.LinkedList;
/**
 *
 * @author helmuth
 */
public class Function implements Instruction{
    
    private AccessModifier accessModifier;
    private String id;
    private Type type;
    private LinkedList<Type> params;
    private LinkedList<Instruction> instructionsList;

    public Function(AccessModifier accessModifier, Type type, String id, LinkedList<Type> params, LinkedList<Instruction> instructionsList) {
        this.accessModifier = accessModifier;
        this.type = type;
        this.id = id;
        this.params = params;
        this.instructionsList = instructionsList;
    }
    
    @Override
    public Object analyze(SymbolTable symbolTable) {
        if(params != null){
            System.out.println("Funcion " + id +" Tipo: "+ type +" Parametros: "+ params.toString());
        
        }
        else{
            System.out.println("Funcion " + id +" Tipo: "+ type);  
        }
            for(var instruction : instructionsList){
                instruction.analyze(null);
            }
        
        
        return null;
    } 
}
