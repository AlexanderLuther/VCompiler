package com.hluther.compiler.AST;

import java.util.LinkedList;
/**
 * Clase que ejecuta las acciones de una instrucción si...entonces y que implementa
 * la interfaz de instrucción
 * @author Alexander Luther
 */
public class If implements Instruction{

    private Operation condition;
    private LinkedList<Instruction> instructionsList;
    private LinkedList<Instruction> elseInstructionsList;
    private LinkedList<Instruction> elseIfinstructionsList;
    
    /**
     * Primer constructor de la clase, este se utiliza cuando la instrucción no 
     * tiene clausula ELSE.
     * @param condition Condición del si..entonces
     * @param instructionsList Lista de instrucciones que deberían ejecutarse si la condición se cumple
     */
    public If(Operation condition, LinkedList<Instruction> instructionsList) {
        this.condition = condition;
        this.instructionsList = instructionsList;
    }
    
    /**
     * Segundo constructor de la clase, este se utiliza cuando la instrucción tiene
     * clausula ELSE.
     * @param condition Condición del si..entonces
     * @param instructionsList Lista de instrucciones que deberían ejecutarse si la condición se cumple
     * @param elseInstructionsList Lista de instrucciones que deberían ejecutarse si la condición no se cumple
     */
    public If(Operation condition, LinkedList<Instruction> instructionsList, LinkedList<Instruction> elseInstructionsList) {
        this.condition = condition;
        this.instructionsList = instructionsList;
        this.elseInstructionsList = elseInstructionsList;
    }
    
      /**
     * Tercer constructor de la clase, este se utiliza cuando la instrucción tiene
     * clausula IF (ELSE IF/ ELSE).
     * @param condition Condición del si..entonces
     * @param instructionsList Lista de instrucciones que deberían ejecutarse si la condición se cumple
     * @param elseIfInstructionsList Lista de instrucciones que deberían ejecutarse si la condición ElSE IF se cumple
     * @param elseInstructionsList Lista de instrucciones que deberían ejecutarse si la condición no se cumple
     */
    public If(Operation condition, LinkedList<Instruction> instructionsList, LinkedList<Instruction> elseIfInstructionsList, LinkedList<Instruction> elseInstructionsList) {
        this.condition = condition;
        this.instructionsList = instructionsList;
        this.elseIfinstructionsList = elseIfInstructionsList;
        this.elseInstructionsList = elseInstructionsList;
    }
    
    /**
     * Método que ejecuta la instrucción si..entonces, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable tabla de símbolos del ámbito padre de la sentencia.
     * @return Estra instrucción retorna nulo porque no produce ningún valor en 
     * su ejecución
     */
    @Override
    public Object analyze(SymbolTable symbolTable) {
        
        if((Boolean)condition.analyze(symbolTable)){
            SymbolTable localTable = new SymbolTable();
            localTable.addAll(symbolTable);
            for(var instruction: instructionsList){
                instruction.analyze(localTable);
            }
            return true;
        }
        else{
            boolean bandera = false;
            
            if(elseIfinstructionsList != null){
                for(var elseIfInstruction: elseIfinstructionsList){
                    if((boolean)elseIfInstruction.analyze(symbolTable)){
                        bandera = true;
                        break;
                    }
                }
            }
            
            if(elseInstructionsList != null && !bandera){
                SymbolTable localTable = new SymbolTable();
                localTable.addAll(symbolTable);
                for(var elseInstruction: elseInstructionsList){
                    elseInstruction.analyze(localTable);
                }            
            }
        }
        return false;
    }
        
}
