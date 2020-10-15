package com.hluther.compiler.AST;

import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.gui.VCompilerFrame;

/**
 * Clase de retorno.
 * @author helmuth
 */
public class Return implements Instruction{
    
    private final Instruction returnValue;
    
    /**
     * Constructor para un retorno que no es de tipo VOID
     * @param returnValue Instrucción que contiene el valor de retorno de la función
     */
    public Return(Instruction returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        return "return " + returnValue.generateOnlyReadThreeAddressCode(threeAddressCodeDriver) + ";\n";
    }

    /**
     * Método que ejecuta la instrucción retorno, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable  tabla de símbolos del ámbito padre de la sentencia
     * @return Esta instrucción retorna el valor producido por la operación que se ejecutó
     */ 
    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {
        return returnValue.analyze(symbolTable, ast, vCompilerFrame);
           
    }
    
}