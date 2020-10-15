package com.hluther.compiler.AST;

import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.gui.VCompilerFrame;
import java.util.LinkedList;
/**
 * Clase For, ejecuta las acciones de una instrucción For y que implementa
 * la interfaz de instrucción
 * @author helmuth
 */
public class For implements Instruction{
    
    private final Declaration declaration;
    private final Assignment initializer;
    private final Instruction condition;
    private final Assignment increasing;
    private final LinkedList<Instruction> instructions;
    
    /**
     * Constructor de la clase For
     * @param initializer
     * @param condition
     * @param increasing
     * @param instructions 
     */
    public For(Assignment initializer, Instruction condition, Assignment increasing, LinkedList<Instruction> instructions) {
        this.declaration = null;
        this.initializer = initializer;
        this.condition = condition;
        this.increasing = increasing;
        this.instructions = instructions;
    }
    
    /**
     * Constructor de la clase For
     * @param declaration
     * @param initializer
     * @param condition
     * @param increasing
     * @param instructions 
     */
    public For(Declaration declaration, Assignment initializer, Instruction condition, Assignment increasing, LinkedList<Instruction> instructions) {
        this.declaration = declaration;
        this.initializer = initializer;
        this.condition = condition;
        this.increasing = increasing;
        this.instructions = instructions;
    }
    
    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {    
        //Crear las variables a utilizar
        String code;
        String forLabel;
        String trueLabel;
        String falseLabel;
        
        //Agregar la asignacion que inicializa al contador del bucle.
        code = initializer.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        
        //Establecer el valor de la etiqueta de inicio del ciclo
        forLabel = threeAddressCodeDriver.getNewLabel();
         
        //Agregar la etiqueta de inicio del bucle
        code += forLabel + ":\n";
        
        //Agregar la condicion del bucle
        code += condition.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        
        //Obtener el valor de las etiquetas a utilizar si la condicion es falsa o verdadera.
        trueLabel = threeAddressCodeDriver.removeLabel();
        falseLabel = threeAddressCodeDriver.removeLabel();
        
        //Agregar la etiqueta verdadera, el incremento o decremento al contador y sus respectivas instrucciones
        code += trueLabel + ":\n";
        code += increasing.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        for(var instruction : instructions){
            code += instruction.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        }
        code += "goto " + forLabel + "\n";
        
        //Agregar la etiqueta falsa
        code += falseLabel + ":\n";
        
        return code;
    } 

    /**
     * Método que ejecuta la instrucción For, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable tabla de símbolos del ámbito padre de la sentencia
     * @param ast
     * @param vCompilerFrame
     * @return Esta instrucción retorna nulo porque no produce ningun valor
     */
    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {
        //Analizar la declaracion
        if(declaration != null){
            declaration.analyze(symbolTable, ast, vCompilerFrame);
        }
        
        //Analizar el inicializador
        initializer.analyze(symbolTable, ast, vCompilerFrame);
        
        //Analizar la condicion 
        condition.analyze(symbolTable, ast, vCompilerFrame);
        
        //Analizar el incremento
        increasing.analyze(symbolTable, ast, vCompilerFrame);
        
        //Analizar las instrucciones del For
        SymbolTable localTable = new SymbolTable(symbolTable);
        Object returnValue = null; 
        for(var instruction :instructions){
            returnValue = instruction.analyze(localTable, ast, vCompilerFrame);
        }
        return returnValue;
    }
}
