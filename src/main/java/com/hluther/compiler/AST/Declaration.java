package com.hluther.compiler.AST;

import com.hluther.compiler.AST.Symbol.Type;
import com.hluther.compiler.AST.Symbol.Role;
import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.gui.VCompilerFrame;
/**
 * @author Alexander Luther
 */
public class Declaration implements Instruction{
   
    private final String id;
    private final Type type;
    private final Role role;
    private final int row;
    private final int column;
    
    /**
     * Constructor de la clase
     * @param id Identificador de la variable que será declarada
     * @param type Tipo de dato de la variable que será declarada
     * @param role
     * @param row
     * @param column
     */
    public Declaration(String id, Type type, Role role, int row, int column){
        this.id = id;
        this.type = type;
        this.role = role;
        this.row = row;
        this.column = column;
    }
    
    public String getId() {
        return id;
    }
     
    public Type getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
     
    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        if(role == Role.CONSTANT){
            return "const " + id + "\n";
        }
        return "";
    }

    /**
     * Método que ejecuta la accion de analizar la declaracion de una variable,
     * constante o parametro.
     * @param symbolTable  Tabla de símbolos del ámbito padre.
     * @param ast
     * @param vCompilerFrame
     * @return No retorna nada porque no es una sentencia que genere un valor.
     */
    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {     
        if(!symbolTable.addSymbol(new Symbol(id, type, role))){
            switch(role){
                case VAR:  
                    vCompilerFrame.printMessage("Fila: " +row+ " Columna: " +column+ " Error Semantico " + "Ya existe una variable declarada con el identidicador [" +id+ "] en este ambito.");
                    break;
                    
                case CONSTANT:
                    vCompilerFrame.printMessage("Fila: " +row+ " Columna: " +column+ " Error Semantico " + "Ya existe una constante declarada con el identidicador [" +id+ "] en este ambito.");
                    break;
                    
                case PARAM:
                    vCompilerFrame.printMessage("Fila: " +row+ " Columna: " +column+ " Error Semantico " + "Ya existe un parametro declarado con el identidicador [" +id+ "] en este ambito.");
                    break;
            }
        } 
        return null;
    }
}
