package com.hluther.entityClasses;
/**
 *
 * @author helmuth
 */
public class Quadruple {
    
    private String operator;
    private String result;
    private String argument1;
    private String argument2;
    
    public Quadruple(String operator, String result, String argument1, String argument2){
        this.operator = operator;
        this.result = result;
        this.argument1 = argument1;
        this.argument2 = argument2;
    }
    
     public Quadruple(String operator, String result, String argument1){
        this.operator = operator;
        this.result = result;
        this.argument1 = argument1;
        this.argument2 = "";
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getArgument1() {
        return argument1;
    }

    public void setArgument1(String argument1) {
        this.argument1 = argument1;
    }

    public String getArgument2() {
        return argument2;
    }

    public void setArgument2(String argument2) {
        this.argument2 = argument2;
    }


    
    
    
}
