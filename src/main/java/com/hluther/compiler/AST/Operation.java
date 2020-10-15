package com.hluther.compiler.AST;

import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.entityClasses.SemanticException;
import com.hluther.gui.VCompilerFrame;
import java.util.Objects;
/**
 * Clase que ejecuta las acciones de una operación, ya sea aritmética, realacional
 * o logica. Implementa la interfaz Instruction.
 * @author Alexander Luther
 */
public class Operation implements Instruction{
    
    private OperationType type;
    private Operation leftOperator;
    private Operation rightOperator;
    private Object value;
    private int leftRow;
    private int leftColumn;
    private final char T_VALUE = 194;
    
    /**
     * Constructor de la clase para operaciones binarias.
     * @param leftOperator
     * @param rightOperator
     * @param type Tipo de la operación
     */
    public Operation(Operation leftOperator, Operation rightOperator, OperationType type) {
        this.type = type;
        this.leftOperator = leftOperator;
        this.rightOperator = rightOperator;
    }
    
    /**
     * Constructor para operaciones unarias,
     * @param leftOperator
     * @param type
     */
    public Operation(Operation leftOperator, OperationType type) {
        this.type = type;
        this.leftOperator = leftOperator;
    }
  
    /**
     * Constructor para operaciones unarias cuyo operador es específicamente un INTEGER.
     * @param value Valor de tipo int que representa la operación value realizar.
     */
    public Operation(int value) {
        this.value = value;
        this.type = OperationType.INTEGER;
    }
    
    /**
     * Constructor para operaciones unarias cuyo operador es específicamente un DOUBLE.
     * @param value Valor de tipo double que representa la operación value realizar.
     */
    public Operation(double value) {
        this.value = value;
        this.type = OperationType.DOUBLE;
    }
    
    /**
     * Constructor para operaciones unarias cuyo operador es específicamente un CHARACTER.
     * @param value Valor de tipo char que representa la operación value realizar.
     */
    public Operation(char value){
        this.value = value;
        this.type  = OperationType.CHARACTER;
    }
    
    /**
     * Constructor para operaciones unarias cuyo operador se recibe como parametro.
     * @param value Valor de tipo String que representa la operación value realizar.
     * @param type
     * @param leftRow
     * @param leftColumn
     */
    public Operation(String value, OperationType type, int leftRow, int leftColumn){
        this.value = value;
        this.type  = type;
        this.leftRow = leftRow;
        this.leftColumn = leftColumn;
    }

    public Object getValue() {
        return value;
    }
    
    public OperationType getOperationType(){
        return type;
    }
    /**
     * Metodo que genera una representacion de solo lectura de codigo de 3 
     * direcciones de las operaciones aritmeticas +, -, /, *, %.
     * @param operator Cadena que contiene el operador a utilizar.
     * @param threeAddressCodeDriver
     * @return El codigo de tres direcciones resultante.
     */
    private String getArithmeticOperation3AC(String operator, ThreeAddressCodeDriver threeAddressCodeDriver){
        String code = "";
        String op1 = leftOperator.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        String op2 = rightOperator.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        if(op1.startsWith(String.valueOf(T_VALUE)) && op2.startsWith(String.valueOf(T_VALUE))){
            code += op1 + op2;
            op1 = op1.substring(0, 2);
            op2 = op2.substring(0, 2);
        } else if(op1.startsWith(String.valueOf(T_VALUE))){
            code += op1;
            op1 = threeAddressCodeDriver.getCurrentT();
        } else if(op2.startsWith(String.valueOf(T_VALUE))){
            code += op2;
            op2 = threeAddressCodeDriver.getCurrentT();
        } 
        return code + threeAddressCodeDriver.getNewT() +" = "+ op1 +operator+ op2 +";\n";
    }
    
    /**
     * Metodo que genera una representacion de solo lectura de codigo de 3 
     * direcciones de las operaciones relacionales >, <, >=. <=. ==, !=.
     * @param operator Cadena que contiene el operador a utilizar.
     * @param threeAddressCodeDriver
     * @return El codigo de tres direcciones resultante.
     */
    private String getRelationalOperation3AC(String operator, ThreeAddressCodeDriver threeAddressCodeDriver){
        String code = "";
        String op1 = leftOperator.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        String op2 = rightOperator.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        if(op1.startsWith(String.valueOf(T_VALUE)) && op2.startsWith(String.valueOf(T_VALUE))){
            code += op1 + op2;
            op1 = op1.substring(0, 2);
            op2 = op2.substring(0, 2);
        } else if(op1.startsWith(String.valueOf(T_VALUE))){
            code += op1;
            op1 = threeAddressCodeDriver.getCurrentT();
        } else if(op2.startsWith(String.valueOf(T_VALUE))){
            code += op2;
            op2 = threeAddressCodeDriver.getCurrentT();
        } 
        code += "if " + op1 + operator + op2+ " goto " + threeAddressCodeDriver.getAndAddNewLabel() + ";\n";
        code += "goto " + threeAddressCodeDriver.getAndAddNewLabel() +";\n";
        return code;
    }
    
     /**
     * Metodo que genera una representacion de solo lectura de codigo de 3 
     * direcciones de las operaciones logicas and, or.
     * @param threeAddressCodeDriver
     * @return El codigo de tres direcciones resultante.
     */
    private String getLogicalOperation3AC(ThreeAddressCodeDriver threeAddressCodeDriver){
        String code = "";
        String newLabel = "";
        String op1 = leftOperator.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        String op2 = rightOperator.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        switch(type){
            case AND:
                code += op1;
                code += threeAddressCodeDriver.removeLabel() + ":\n";
                code += op2; 
                newLabel += threeAddressCodeDriver.removeLabel() +":";
                threeAddressCodeDriver.addLabel(threeAddressCodeDriver.removeLabel());
                newLabel += threeAddressCodeDriver.removeLabel();
                threeAddressCodeDriver.addLabel(newLabel);
                return code;

            case OR:
                newLabel += threeAddressCodeDriver.removeLabel() +":";
                code += op1;
                code += threeAddressCodeDriver.removeLabel() + ":\n";
                code += op2;
                newLabel += threeAddressCodeDriver.removeLabel();
                threeAddressCodeDriver.addLabel(newLabel);
                threeAddressCodeDriver.addLabel(threeAddressCodeDriver.removeLabel());
                return code;
        }
        return null;
    }
    
    /**
     * * Metodo que genera una representacion de solo lectura de codigo de 3 
     * direcciones de las operacion de concatenacion.
     * @param threeAddressCodeDriver
     * @return El codigo de tres direcciones resultante. 
     */
    private String getConcatenation(ThreeAddressCodeDriver threeAddressCodeDriver){
        String code = "";
        String op1 = leftOperator.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        if(leftOperator.getOperationType() == OperationType.CONCAT){
            code += op1;
        } else if(op1.startsWith(String.valueOf(T_VALUE))){
            code += op1;
            code += "print " + threeAddressCodeDriver.getCurrentT() + ";\n";
        } else{
            code  += "print " +op1+ ";\n";
        }
        String op2 = rightOperator.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        if(rightOperator.getOperationType() == OperationType.CONCAT){
            code += op2;
        } else if(op2.startsWith(String.valueOf(T_VALUE))){
            code += op2;
            code += "print " + threeAddressCodeDriver.getCurrentT() + ";\n";
        } else{
            code += "print " +op2 + ";\n";
        }
        return code;
    }
    
    
    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        String code = "";
        switch(type){
            case INTEGER:               return value.toString();
            case DOUBLE:                return value.toString();
            case CHARACTER:             return "'" +value.toString()+ "'";
            case LITERAL:               return value.toString();
            case ID:                    return value.toString();
            case PLUS:                  return leftOperator.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
            case MINUS:                 return threeAddressCodeDriver.getNewT() +" = -"+ leftOperator.generateOnlyReadThreeAddressCode(threeAddressCodeDriver) +";\n";
            case MULTIPLICATION:        return getArithmeticOperation3AC(" * ", threeAddressCodeDriver);
            case DIVISION:              return getArithmeticOperation3AC(" / ", threeAddressCodeDriver);
            case MOD:                   return getArithmeticOperation3AC(" % ", threeAddressCodeDriver);
            case SUM:                   return getArithmeticOperation3AC(" + ", threeAddressCodeDriver);
            case SUBTRACTION:           return getArithmeticOperation3AC(" - ", threeAddressCodeDriver);
            case GREATER_EQUAL_THAN:    return getRelationalOperation3AC(" >= ", threeAddressCodeDriver);
            case LESS_EQUAL_THAN:       return getRelationalOperation3AC(" <= ", threeAddressCodeDriver);
            case LESS_THAN:             return getRelationalOperation3AC(" < ", threeAddressCodeDriver);
            case GREATER_THAN:          return getRelationalOperation3AC(" > ", threeAddressCodeDriver);
            case NOTEQUAL:              return getRelationalOperation3AC(" != ", threeAddressCodeDriver);   
            case EQUALS:                return getRelationalOperation3AC(" == ", threeAddressCodeDriver);
            case OR:                    return getLogicalOperation3AC(threeAddressCodeDriver);
            case AND:                   return getLogicalOperation3AC(threeAddressCodeDriver);
            case NOT:                   
                code += leftOperator.generateOnlyReadThreeAddressCode(threeAddressCodeDriver); 
                threeAddressCodeDriver.addLabel(threeAddressCodeDriver.removeLabel());
                return code;            
            case CONCAT:                return getConcatenation(threeAddressCodeDriver); 
            default:                    return null;
        }
    }    
    
    /**
     * Método que ejecuta la instrucción operación, es una sobreescritura del
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable tabla de símbolos del ámbito padre de la sentencia.
     * @param ast
     * @param vCompilerFrame
     * @return Esta instrucción retorna el value producido por la operación que se ejecutó.
     */
    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {
        try{
            if(null == type){
                return null;
            }
            else{
                Object operator1;
                Object operator2;
                switch(type){
                    case INTEGER:
                        return (Integer)value;
                    
                    case DOUBLE:                
                        return (Double)value;

                    case CHARACTER: 
                        return (Character)value;
                        
                    case LITERAL: 
                        return ((String)value).replaceAll("\"", "");

                    case ID: 
                        Symbol symbol = symbolTable.getSymbol((String)value);
                        if(symbol != null){

                        }
                        else throw new SemanticException("No existe ninguna variable con el identificador [" +(String)value+ "].");
                       
                    case CONCAT:                
                        return leftOperator.analyze(symbolTable, ast, vCompilerFrame).toString() + rightOperator.analyze(symbolTable, ast, vCompilerFrame).toString();
    
                    case PLUS:
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        if(operator1 instanceof Integer) return (Integer)operator1;
                        else if(operator1 instanceof Double) return (Double)operator1;
                        else return (int)(Character)operator1;
                        
                    case MINUS:
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        if(operator1 instanceof Integer) return Integer.parseInt(operator1.toString()) *-1;
                        else if(operator1 instanceof Double) return Double.parseDouble(operator1.toString()) * -1;
                        else return (int)(Character)operator1 * -1;
                        
                    case MULTIPLICATION:        
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        operator2 = rightOperator.analyze(symbolTable, ast, vCompilerFrame);   
                        if(operator1 instanceof Double || operator2 instanceof Double){
                            if(operator1 instanceof Character) return (int)(Character)operator1 * Double.parseDouble(operator2.toString());
                            else if(operator2 instanceof Character) return Double.parseDouble(operator1.toString()) * (int)(Character)operator2;
                            else return Double.parseDouble(operator1.toString()) * Double.parseDouble(operator2.toString());
                        } else if(operator1 instanceof Integer || operator2 instanceof Integer){
                            if(operator1 instanceof Character) return (int)(Character)operator1 * Integer.parseInt(operator2.toString());
                            else if(operator2 instanceof Character) return Integer.parseInt(operator1.toString()) * (int)(Character)operator2;
                            else return Integer.parseInt(operator1.toString()) * Integer.parseInt(operator2.toString());
                        } else return (int)(Character)operator1 * (int)(Character)operator2;
                        
                    case DIVISION:
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        operator2 = rightOperator.analyze(symbolTable, ast, vCompilerFrame);   
                        if(operator1 instanceof Double || operator2 instanceof Double){
                            if(operator1 instanceof Character) return (int)(Character)operator1 / Double.parseDouble(operator2.toString());
                            else if(operator2 instanceof Character) return Double.parseDouble(operator1.toString()) / (int)(Character)operator2;
                            else return Double.parseDouble(operator1.toString()) / Double.parseDouble(operator2.toString());
                        } else if(operator1 instanceof Integer || operator2 instanceof Integer){
                            if(operator1 instanceof Character) return (int)(Character)operator1 / Integer.parseInt(operator2.toString());
                            else if(operator2 instanceof Character) return Integer.parseInt(operator1.toString()) / (int)(Character)operator2;
                            else return Integer.parseInt(operator1.toString()) / Integer.parseInt(operator2.toString());
                        } else return (int)(Character)operator1 / (int)(Character)operator2;
                        
                    case MOD:                   
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        operator2 = rightOperator.analyze(symbolTable, ast, vCompilerFrame);   
                        if(operator1 instanceof Double || operator2 instanceof Double){
                            if(operator1 instanceof Character) return (int)(Character)operator1 % Double.parseDouble(operator2.toString());
                            else if(operator2 instanceof Character) return Double.parseDouble(operator1.toString()) % (int)(Character)operator2;
                            else return Double.parseDouble(operator1.toString()) % Double.parseDouble(operator2.toString());
                        } else if(operator1 instanceof Integer || operator2 instanceof Integer){
                            if(operator1 instanceof Character) return (int)(Character)operator1 % Integer.parseInt(operator2.toString());
                            else if(operator2 instanceof Character) return Integer.parseInt(operator1.toString()) % (int)(Character)operator2;
                            else return Integer.parseInt(operator1.toString()) % Integer.parseInt(operator2.toString());
                        } else return (int)(Character)operator1 % (int)(Character)operator2;
                      
                    case SUM: 
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        operator2 = rightOperator.analyze(symbolTable, ast, vCompilerFrame);   
                        if(operator1 instanceof Double || operator2 instanceof Double){
                            if(operator1 instanceof Character) return (int)(Character)operator1 + Double.parseDouble(operator2.toString());
                            else if(operator2 instanceof Character) return Double.parseDouble(operator1.toString()) + (int)(Character)operator2;
                            else return Double.parseDouble(operator1.toString()) + Double.parseDouble(operator2.toString());
                        } else if(operator1 instanceof Integer || operator2 instanceof Integer){
                            if(operator1 instanceof Character) return (int)(Character)operator1 + Integer.parseInt(operator2.toString());
                            else if(operator2 instanceof Character) return Integer.parseInt(operator1.toString()) + (int)(Character)operator2;
                            else return Integer.parseInt(operator1.toString()) + Integer.parseInt(operator2.toString());
                        } else return (int)(Character)operator1 + (int)(Character)operator2;
                        
                    case SUBTRACTION: 
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        operator2 = rightOperator.analyze(symbolTable, ast, vCompilerFrame);   
                        if(operator1 instanceof Double || operator2 instanceof Double){
                            if(operator1 instanceof Character) return (int)(Character)operator1 - Double.parseDouble(operator2.toString());
                            else if(operator2 instanceof Character) return Double.parseDouble(operator1.toString()) - (int)(Character)operator2;
                            else return Double.parseDouble(operator1.toString()) - Double.parseDouble(operator2.toString());
                        } else if(operator1 instanceof Integer || operator2 instanceof Integer){
                            if(operator1 instanceof Character) return (int)(Character)operator1 - Integer.parseInt(operator2.toString());
                            else if(operator2 instanceof Character) return Integer.parseInt(operator1.toString()) - (int)(Character)operator2;
                            else return Integer.parseInt(operator1.toString()) - Integer.parseInt(operator2.toString());
                        } else return (int)(Character)operator1 - (int)(Character)operator2;
                        
                    case LESS_EQUAL_THAN:
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        operator2 = rightOperator.analyze(symbolTable, ast, vCompilerFrame);
                        if(operator1 instanceof Character && operator2 instanceof Character) return (Boolean)((Character)operator1 <= (Character)operator2);
                        else if(operator1 instanceof Character) return (Boolean) ( (Character)operator1 <= Double.parseDouble(operator2.toString()) );  
                        else if(operator2 instanceof Character) return (Boolean)(Double.parseDouble(operator1.toString()) <= (Character)operator2);
                        else return (Boolean)(Double.parseDouble(operator1.toString()) <= Double.parseDouble(operator2.toString()));
           
                    case GREATER_EQUAL_THAN:
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        operator2 = rightOperator.analyze(symbolTable, ast, vCompilerFrame);
                        if(operator1 instanceof Character && operator2 instanceof Character) return (Boolean)((Character)operator1 >= (Character)operator2);
                        else if(operator1 instanceof Character) return (Boolean) ( (Character)operator1 >= Double.parseDouble(operator2.toString()) );  
                        else if(operator2 instanceof Character) return (Boolean)(Double.parseDouble(operator1.toString()) >= (Character)operator2);
                        else return (Boolean)(Double.parseDouble(operator1.toString()) >= Double.parseDouble(operator2.toString()));
                        
                    case LESS_THAN:   
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        operator2 = rightOperator.analyze(symbolTable, ast, vCompilerFrame);
                        if(operator1 instanceof Character && operator2 instanceof Character) return (Boolean)((Character)operator1 < (Character)operator2);
                        else if(operator1 instanceof Character) return (Boolean) ( (Character)operator1 < Double.parseDouble(operator2.toString()) );  
                        else if(operator2 instanceof Character) return (Boolean)(Double.parseDouble(operator1.toString()) < (Character)operator2);
                        else return (Boolean)(Double.parseDouble(operator1.toString()) < Double.parseDouble(operator2.toString()));
                        
                    case GREATER_THAN:   
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        operator2 = rightOperator.analyze(symbolTable, ast, vCompilerFrame);
                        if(operator1 instanceof Character && operator2 instanceof Character) return (Boolean)((Character)operator1 > (Character)operator2);
                        else if(operator1 instanceof Character) return (Boolean) ( (Character)operator1 > Double.parseDouble(operator2.toString()) );  
                        else if(operator2 instanceof Character) return (Boolean)(Double.parseDouble(operator1.toString()) > (Character)operator2);
                        else return (Boolean)(Double.parseDouble(operator1.toString()) > Double.parseDouble(operator2.toString()));
                        
                    case NOTEQUAL:  
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        operator2 = rightOperator.analyze(symbolTable, ast, vCompilerFrame);
                        if(operator1 instanceof Character && operator2 instanceof Character) return (!Objects.equals((Character)operator1, (Character)operator2));
                        else if(operator1 instanceof Character) return (Boolean) ( (Character)operator1 != Double.parseDouble(operator2.toString()) );  
                        else if(operator2 instanceof Character) return (Boolean)(Double.parseDouble(operator1.toString()) != (Character)operator2);
                        else return (Boolean)(Double.parseDouble(operator1.toString()) != Double.parseDouble(operator2.toString()));
                        
                    case EQUALS:
                        operator1 = leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                        operator2 = rightOperator.analyze(symbolTable, ast, vCompilerFrame);
                        if(operator1 instanceof Character && operator2 instanceof Character) return (Objects.equals((Character)operator1, (Character)operator2));
                        else if(operator1 instanceof Character) return (Boolean) ( (Character)operator1 == Double.parseDouble(operator2.toString()) );  
                        else if(operator2 instanceof Character) return (Boolean)(Double.parseDouble(operator1.toString()) == (Character)operator2);
                        else return (Boolean)(Double.parseDouble(operator1.toString()) == Double.parseDouble(operator2.toString()));
 
                    case AND:  
                        return (Boolean)leftOperator.analyze(symbolTable, ast, vCompilerFrame) && (Boolean)rightOperator.analyze(symbolTable, ast, vCompilerFrame);
                    
                    case OR:                    
                        return (Boolean)leftOperator.analyze(symbolTable, ast, vCompilerFrame) || (Boolean)rightOperator.analyze(symbolTable, ast, vCompilerFrame);
                    
                    case NOT:                   
                        return !(Boolean)leftOperator.analyze(symbolTable, ast, vCompilerFrame);
                    
                    default:                    
                        return null;    
                }
            }
        } catch(SemanticException e){
            e.setRow(leftRow);
            e.setColumn(leftColumn);
            vCompilerFrame.printMessage(e.getMessage());
            return null;
        }
    }  
    
    /**
     * Enumeración del type de operación que puede ser ejecutada por esta clase.
     */
    public static enum OperationType{
        INTEGER,
        DOUBLE,
        CHARACTER,
        LITERAL,
        ID,
        PLUS,
        MINUS,
        MULTIPLICATION,
        DIVISION,
        MOD,
        SUM,
        SUBTRACTION,
        LESS_EQUAL_THAN,
        GREATER_EQUAL_THAN,
        LESS_THAN,
        GREATER_THAN,
        NOTEQUAL,
        EQUALS, 
        AND,
        OR, 
        NOT,
        CONCAT;
    }
}