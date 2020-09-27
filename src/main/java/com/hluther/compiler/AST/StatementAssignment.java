package com.hluther.compiler.AST;
/**
 *
 * @author helmuth
 */
public class StatementAssignment implements Instruction{
    
    private final String id;
    private final Type type;
    private Operation value;
    
    /**
     * Constructor de la clase
     * @param id Identificador de la variable que será declarada.
     * @param type Tipo de la clase que será declarada.
     * @param value Valor que se la a asignar.
     */
    public StatementAssignment(String id, Operation value, Type type) {
        this.id = id;
        this.value = value;
        this.type = type;  
    }

    @Override
    public Object analyze(SymbolTable symbolTable) {
        System.out.println("Declaracion y asignacion: " + id +" "+ value.analyze(null));
        //symbolTable.add(new Symbol(id, type));
        //symbolTable.setValue(id, value.analyze(symbolTable));
        return null;
    }     
}
