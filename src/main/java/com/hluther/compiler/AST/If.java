package com.hluther.compiler.AST;

import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.gui.VCompilerFrame;
import java.util.LinkedList;
/**
 * Clase que ejecuta las acciones de una instrucción si...entonces y que implementa
 * la interfaz de instrucción
 * @author Alexander Luther
 */
public class If implements Instruction{

    private final Instruction condition;
    private final LinkedList<Instruction> instructions;
    private final LinkedList<Instruction> elseInstructions;
    
    /**
     * Constructor utilizado cuando la sentencia solamente tiene un if 
     * @param condition
     * @param instructions
     */
    public If(Instruction condition, LinkedList<Instruction> instructions) {
        this.condition = condition;
        this.instructions = instructions;
        this.elseInstructions = new LinkedList<>();
    }
    
    /**
     * Constructor utilizado cuando la sentencia tiene un if y un else 
     * @param condition
     * @param instructions
     * @param elseInstructions
     */
    public If(Instruction condition, LinkedList<Instruction> instructions, LinkedList<Instruction> elseInstructions) {
        this.condition = condition;
        this.instructions = instructions;
        this.elseInstructions = elseInstructions;
    }
    
    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        //Crear las variables a utilizar
        String code;
        String trueLabel;
        String falseLabel;
        String jumpLabel;
        
        //Agregar la condicion del if
        code = condition.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        
        //Obtener el valor de las etiquetas a utilizar si la condicion es falsa o verdadera.
        trueLabel = threeAddressCodeDriver.removeLabel();
        falseLabel = threeAddressCodeDriver.removeLabel();
        
        //Agregar la etiqueta verdadera y sus respectivas instrucciones.
        code += trueLabel + ":\n";
        for(var instruction : instructions){
            code += instruction.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        }
        jumpLabel = threeAddressCodeDriver.getNewLabel();
        code += "goto " + jumpLabel + "\n";
     
        //Agregar la etiqueta falsa y sus respectivas instrucciones.
        code += falseLabel + ":\n";
        for(var instruction : elseInstructions){
            code += instruction.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        }
        
        //Agregar una etiqueta para el salto desde la etiqueta verdadera
        code += jumpLabel + ":\n";
        
        return code;
    }

    /**
     * Método que ejecuta la instrucción si..entonces, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable  tabla de símbolos del ámbito padre de la sentencia.
     * @param ast
     * @param vCompilerFrame
     * @return Estra instrucción retorna nulo porque no produce ningún valor en 
     * su ejecución
     */
    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {
        //Analizar la condicion
        condition.analyze(symbolTable, ast, vCompilerFrame);
        
        //Analizar las instrucciones del if
        SymbolTable localTable = new SymbolTable(symbolTable);   
        Object returnValue = null;
        for(var instruction : instructions){
            returnValue = instruction.analyze(localTable, ast, vCompilerFrame);
        }
        
        //Analizar las instrucciones del else
        for(var instruction : elseInstructions){
            returnValue = instruction.analyze(localTable, ast, vCompilerFrame);
        }
        
        return returnValue;
    }
}
