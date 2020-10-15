package com.hluther.compiler.AST;

import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.gui.VCompilerFrame;

/**
 * Clase que ejecuta las acciones de una instrucción imprimir y que implementa
 * la interfaz de instrucción.
 * @author helmuth
 */
public class Print implements Instruction{

    private final Operation content;
    private final PrintType type;
    private final char T_VALUE = 194; 
    
    public Print(Operation content, PrintType type){
        this.content = content;
        this.type = type;
    }
    
    @Override
    public String generateOnlyReadThreeAddressCode( ThreeAddressCodeDriver threeAddressCodeDriver) {
        String code;
        String message = content.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        if(content.getOperationType() == Operation.OperationType.CONCAT){
            code = message;
        } else{
            if(message.startsWith(String.valueOf(T_VALUE))){
                code = message;
                code += "print " + threeAddressCodeDriver.getCurrentT() + ";\n";
            } else{
                code = "print " + message + ";\n";
            }
        }
       
        if(type == PrintType.PRINTLN){
            code +=  "print \"\\n\"" + ";\n";
        }
        return code;
    }

     /**
     * Método que ejecuta la accion de imprimir un valor, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable  Tabla de símbolos del ámbito padre de la sentencia.
     * @param ast
     * @param vCompilerFrame
     * @return Esta instrucción retorna nulo porque no produce ningun valor al ser
     * ejecutada.
     */
    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {
        content.analyze(symbolTable, ast, vCompilerFrame).toString();    
        return null;
    }
    
    public static enum PrintType{
        PRINT,
        PRINTLN
    }
}
