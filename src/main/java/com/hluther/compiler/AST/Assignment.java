package com.hluther.compiler.AST;
/**
 * Clase que ejecuta las acciones de una instrucción de asignación y que implementa
 * la interfaz de instrucción
 * @author Alexander Luther
 */
public class Assignment implements Instruction{
    
    private final String id;
    private Operation value;
    
    /**
     * Constructor de la clase asignación
     * @param id identificador de la variable
     * @param value value que se le va a asignar
     */
    public Assignment(String id, Operation value) {
        this.id = id;
        this.value = value;
    }
    
   
    /**
     * Método que ejecuta la accion de asignar un value, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz instruction
     * @param symbolTable tabla de símbolos del ámbito padre de la sentencia asignación
     * @return En este caso retorna nulo porque no es una sentencia que genere un value.
     */
    @Override
    public Object analyze(SymbolTable symbolTable) {
        symbolTable.setValue(id, value.analyze(symbolTable));
        return null;
    }
}
