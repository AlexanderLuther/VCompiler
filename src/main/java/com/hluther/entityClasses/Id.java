package com.hluther.entityClasses;
/**
 *
 * @author helmuth
 */
public class Id {
    
    private String id;
    private int row;
    private int column;

    public Id(String id, int row, int column) {
        this.id = id;
        this.row = row;
        this.column = column;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        
}
