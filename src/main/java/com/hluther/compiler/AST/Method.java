package com.hluther.compiler.AST;

import com.hluther.compiler.AST.Symbol.Type;
import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.gui.VCompilerFrame;
import java.util.LinkedList;
/**
 * Clase que ejecuta las acciones de una función y que implementa la interfaz instrucción
 * @author helmuth
 */
public class Method implements Instruction{
    
    private final AccessModifier accessModifier;
    private final Type type;
    private final String id;
    private final LinkedList<Declaration> params;
    private LinkedList<Instruction> paramsValue;
    private LinkedList<Instruction> instructions;
    private boolean imported;
    private final int row;
    private final int column;

    /*
    * Constructor de la clase Method cuando esta recibe parámetros.
    * @param type Tipo de la función
    * @param newId Identificador de la función
    * @param params Lista de parámetros definidos
    * @param instructions Lista de instrucciones contenidas por la función
    */
   public Method(AccessModifier accessModifier, Type type, String id, LinkedList<Declaration> params, LinkedList<Instruction> instructions, int row, int column) {
        this.accessModifier = accessModifier; 
        this.type = type;
        this.id = id;
        this.params = params;
        this.instructions = instructions;
        this.row = row;
        this.column = column;
    }
   
    /*
    * Constructor de la clase Method cuando esta no recibe parámetros.
    * @param type Tipo de la función
    * @param newId Identificador de la función
    * @param params Lista de parámetros definidos
    * @param instructions Lista de instrucciones contenidas por la función
    */
    public Method(AccessModifier accessModifier, Type type, String id, LinkedList<Instruction> instructions, int row, int column) {
        this.accessModifier = accessModifier;
        this.type = type;
        this.id = id;
        this.params = new LinkedList<>();
        this.instructions = instructions;
        this.row = row;
        this.column = column;
    }
    
    public void setParamsValue(LinkedList<Instruction> params) {
        paramsValue = params;
    }
    
    public Type getType() {
        return type;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public String getName(){
        return id;
    }
    
    /**
     * Méotodo que devuelve el identificador de la función
     * Se crea un identificador único de las funciones con base en su Id 
     * más el tipo de sus parametros de la forma id_tipo..._tipo().
     * @return Identificador de la función
     */
    public String getId() {
        String newId = this.id;
        if (params != null) {
            newId = params.stream().map(param -> "_" + param.getType().toString()).reduce(newId, String::concat);
        }
        newId += "()";
        return newId.toLowerCase();
    }
        
    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        String code = "void ";
        code += getId() + "{\n";
        for(var instruction : instructions){
            code += instruction.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);;
        }

        code += "}\n";
        return code;
    } 

    /**
     * Método que ejecuta la una función, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable  tabla de símbolos del ámbito padre de la sentencia.
     * @return Esta instrucción retorna el valor de retorno de la función o bien
     * una instancia de retorno para el caso de las funciones void.
     */
    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {        
        SymbolTable localTable = new SymbolTable(symbolTable);

        //Ingresar a la tabla de simbolos los parametros del metodo.
        for(int i = 0; i < params.size(); i++){
            Declaration declaration = params.get(i);
            declaration.analyze(localTable, ast, vCompilerFrame);
        }
        
        //Analizar las instrucciones del metodo
        Object returnValue = null;
        for(var instruction : instructions){
            returnValue = instruction.analyze(localTable, ast, vCompilerFrame);
        }   
        
        switch(type){
            case INT:
                break;
            case FLOAT:
                break;
            case CHAR:
                break;
            case VOID:
                if(returnValue != null){
                    vCompilerFrame.printMessage("Fila: " +row+ " Columna: " +column+ " Error Semantico " + "Se encontro una declaracion return dentro del metodo void [" +id+ "].");
                }
                break;
        }
        return returnValue;
    }
    
    public static enum AccessModifier {
    PUBLIC,
    DEFAULT
}
    
}
    


