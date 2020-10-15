package com.hluther.compiler.AST;
/**
 * @author helmuth
 */
public class Symbol {
    
    private final String id;
    private final Type type;
    private final Role role;
    private String ambit;
    private int size;
    private int memoryPosition;
    private boolean initialized;
    

    public Symbol(String id, Type type, Role role) {
        this.id = id;
        this.type = type;
        this.role = role;
    }
    
    public Symbol(String id, Type type, Role role, String ambit) {
        this.id = id;
        this.type = type;
        this.role = role;
        this.ambit = ambit;
    }
    
    public String getId(){
        return id;
    }
    
    public Type getType(){
        return type;
    }
    
    public String getAmbit(){
        return ambit;
    }
    
    public Role getRole(){
        return role;
    }
    
    public int getMemoryPosition(){
        return memoryPosition;
    }
    
    public int getSize(){
        return size;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
        
    public static enum Role{
        VAR,
        CONSTANT,
        PARAM, 
        METHOD, 
        CLASS
    }
    
    public static enum Type{
        INT,
        FLOAT,
        CHAR,
        VOID
    }
    
      /**
     * Metodo que valida si es posible una conversion de tipos entre el tipo y el
     * tipo del valor que se reciben como parametros.
     * Int puede ser convetido a Character
     * Float puede ser convertido a un Integer
     * Char puede ser convertido a Integer.
     * Los demas casos de conversiones no son permitidos.
     * @param type Tipo de dato declarado.
     * @param value Valor al cual se desea validar la si es posible la conversion de tipos.
     * @return 
     */
    public static boolean validateTypeConvertion(Type type, Object value){
        switch(type){
            case INT:       return value instanceof Character;
            case FLOAT:     return value instanceof Integer;
            case CHAR:      return value instanceof Integer; 
        }
        return false;
    }
    
    /**
     * Metodo que valida si el tipo recibido como parametro coindice con el tipo
     * del valor recibido tambien como parametro. 
     * @param type Tipo de dato declarado.
     * @param value Valor al cual se desea comparar el tipo.
     * @return
     */
    public static boolean validateType(Type type, Object value){
        switch(type){
            case INT:       return value instanceof Integer;
            case FLOAT:     return value instanceof Double;
            case CHAR:      return value instanceof Character;
        }
        return false;
    }  
}