package com.hluther.compiler.AST;

import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.entityClasses.SemanticException;
import com.hluther.gui.VCompilerFrame;

/**
 * Clase que ejecuta las acciones de una instrucción de asignación y que implementa
 * la interfaz de instrucción
 * @author Alexander Luther
 */
public class Assignment implements Instruction{
    
    private final String id;
    private final Instruction value;
    private final Input inputValue;
    private final int row;
    private final int column;
    private final char T_VALUE = 194;
    
    /**
     * Constructor de la clase asignación
     * @param id identificador de la variable
     * @param value value que se le va a asignar
     * @param row
     * @param column
     */
    public Assignment(String id, Instruction value, int row, int column) {
        this.id = id;
        this.value = value;
        this.row = row;
        this.column = column;
        if(value instanceof Input){
            inputValue = (Input)value;
        } else{
            inputValue = null;
        }
        
    }
    
    private void setValue(Symbol symbol, SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) throws SemanticException{
        Object object = value.analyze(symbolTable, ast, vCompilerFrame);
        if(object != null){
            if(!Symbol.validateType(symbol.getType(), object) && !Symbol.validateTypeConvertion(symbol.getType(), object)){
                throw new SemanticException("El tipo de dato declarado no coincide con el valor que se trata de asignar a [" +symbol.getId()+ "] ");
            }
        }
    }
    
    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        if(inputValue != null){
            return inputValue.generateOnlyReadThreeAddressCode(threeAddressCodeDriver) +id+ " = " +threeAddressCodeDriver.getCurrentT()+ ";\n";     
        }
        else{
            String assignmentValue = value.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
            if(assignmentValue.startsWith(String.valueOf(T_VALUE))){
                return assignmentValue + id +" = "+ threeAddressCodeDriver.getCurrentT() + "\n";
            }
            else{
                return id +" = "+ assignmentValue + ";\n";
            }
        }
    }

    /**
     * Método que ejecuta la accion de asignar un valor, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable Tabla de símbolos del ámbito padre de la sentencia asignación
     * @param ast
     * @param vCompilerFrame
     * @return En este caso retorna nulo porque no es una sentencia que genere un valor.
     */
    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {
        try{
            Symbol symbol = symbolTable.getSymbol(id);
            if(symbol != null){
                if(symbol.getRole() == Symbol.Role.CONSTANT){
                    if(!symbol.isInitialized()){
                        setValue(symbol, symbolTable, ast, vCompilerFrame);
                        symbol.setInitialized(true);
                    } 
                    else throw new SemanticException("Se esta tratando de modificar el valor de la constante [" +symbol.getId()+ "].");                
                } else setValue(symbol, symbolTable, ast, vCompilerFrame);
            } else throw new SemanticException("No existe ninguna variable con el identificador [" +id+ "].");
        } catch(SemanticException ex){
            ex.setRow(row);
            ex.setColumn(column);
            vCompilerFrame.printMessage(ex.getMessage());
        }
        return null;
    }
}    