package com.hluther.compiler.AST;

import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.gui.VCompilerFrame;
import java.util.LinkedList;
/**
 * Clase que ejecuta las acciones de una instrucción mientras y que implementa
 * la interfaz de instrucción.
 * @author Alexander Luther
 */
public class While implements Instruction{

    private final Operation condition;
    private final LinkedList<Instruction> instructions;

    /**
     * Constructor de la clase Mientras
     * @param condition condición que debe evaluarse para ejecutar el ciclo
     * @param instructions instrucciones que deben ejecutarse si la condición se cumpliera
     */
    public While(Operation condition, LinkedList<Instruction> instructions) {
        this.condition = condition;
        this.instructions = instructions;
    }

    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        //Crear las variables a utilizar
        String code;
        String whileLabel;
        String trueLabel;
        String falseLabel;
        
        //Establecer el valor de la etiqueta de inicio del ciclo
        whileLabel = threeAddressCodeDriver.getNewLabel();
         
        //Agregar la etiqueta de inicio del bucle
        code = whileLabel + ":\n";
        
        //Agregar la condicion del bucle
        code += condition.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        
        //Obtener el valor de las etiquetas a utilizar si la condicion es falsa o verdadera.
        trueLabel = threeAddressCodeDriver.removeLabel();
        falseLabel = threeAddressCodeDriver.removeLabel();
        
        //Agregar la etiqueta verdadera y sus respectivas instrucciones
        code += trueLabel + ":\n";
        for(var instruction : instructions){
            code += instruction.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        }
        code += "goto " + whileLabel + "\n";
        
        //Agregar la etiqueta falsa
        code += falseLabel + ":\n";
        
        return code;
    }   
    
    /**
     * Método que ejecuta la instrucción mientras, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción.
     * @param symbolTable tabla de símbolos del ámbito padre de la sentencia.
     * @param ast
     * @param vCompilerFrame
     * @return Esta instrucción retorna nulo porque no produce ningun valor.
     */
    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {
        //Analizar la condicion
        condition.analyze(symbolTable, ast, vCompilerFrame);
        
        //Analizar las instrucciones del while
        SymbolTable localTable = new SymbolTable(symbolTable);   
        Object returnValue = null;
        for(var instruction : instructions){
            returnValue = instruction.analyze(localTable, ast, vCompilerFrame);
        }
        return returnValue;
    } 
}
