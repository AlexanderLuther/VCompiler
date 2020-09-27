package com.hluther.compiler.AST;
/**
 * Clase que ejecuta las acciones de una instrucción de declaración y que implementa
 * la interfaz de instrucción
 * @author Alexander Luther
 */
public class Statement implements Instruction{
    
    private final String id;
    private final Type type;
    /**
     * Constructor de la clase
     * @param id Identificador de la variable que será declarada
     * @param type Tipo de dato de la variable que será declarada
     */
    public Statement(String id, Type type) {
        this.id = id;
        this.type = type;
    }
    
    /**
     * Método que ejecuta la accion de declarar una variable, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable Tabla de símbolos del ámbito padre.
     * @return No retorna nada porque no es una sentencia que genere un valor.
     */
    @Override
    public Object analyze(SymbolTable symbolTable) {
        //symbolTable.add(new Symbol(id, type));
        System.out.println("Declaracion: "  + id +" "+ type);
        return null;
    }
    
}
