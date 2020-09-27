package com.hluther.compiler.AST;

import java.util.LinkedList;
/**
 * Clase que ejecuta las acciones de una instrucción mientras y que implementa
 * la interfaz de instrucción
 * @author Alexander Luther
 */
public class While implements Instruction{

    private final Operation condition;
    private final LinkedList<Instruction> instructionsList;

    /**
     * Constructor de la clase Mientras
     * @param condition condición que debe evaluarse para ejecutar el ciclo
     * @param instructionsList instrucciones que deben ejecutarse si la condición se cumpliera
     */
    public While(Operation condition, LinkedList<Instruction> instructionsList) {
        this.condition = condition;
        this.instructionsList = instructionsList;
    }

    /**
     * Método que ejecuta la instrucción mientras, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable tabla de símbolos del ámbito padre de la sentencia
     * @return Esta instrucción retorna nulo porque no produce ningun valor
     */
    @Override
    public Object analyze(SymbolTable symbolTable) {
        while((Boolean)condition.analyze(symbolTable)){
            System.out.println("Dentro del bucle");
            if(instructionsList != null){
                SymbolTable localTable = new SymbolTable();
                localTable.addAll(symbolTable);
                for(int i = instructionsList.size() -1; i >= 0; i--){
                    instructionsList.get(i).analyze(localTable);
                }
            }
        }
        return null;
    }   
}