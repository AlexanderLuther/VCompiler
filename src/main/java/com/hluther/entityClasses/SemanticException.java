package com.hluther.entityClasses;
/**
 *
 * @author helmuth
 */
public class SemanticException extends Exception{
    
    private int row;
    private int column;
    private String message;

    public SemanticException(String message) {
        this.message = message;
    }
   
    public SemanticException(int row, int column, String message) {
        this.row = row;
        this.column = column;
        this.message = message;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String getMessage() {
        return "Fila: " +row+ " Columna: " +column+ " Error Semantico " + message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
 
}
