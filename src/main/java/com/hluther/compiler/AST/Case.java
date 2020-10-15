package com.hluther.compiler.AST;

import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.gui.VCompilerFrame;
import java.util.LinkedList;
/**
 *
 * @author helmuth
 */
public class Case implements Instruction{

    private final Operation caseValue;
    private final LinkedList<Instruction> instructions;
    private boolean conditionValue;
    private final boolean isDefault;
    private Operation comparationValue;
    private int row;
    private int column;
 
    /**
     * Constructor para el Case
     * @param value
     * @param instructions 
     * @param row 
     * @param column 
     */
    public Case(Operation value, LinkedList<Instruction> instructions, int row, int column) {
        this.caseValue = value;
        this.instructions = instructions;
        this.row = row;
        this.column = column;
        this.isDefault = false;
    }
    
    /**
     * Constructor para la instruccion Default
     * @param instructions 
     */
    public Case(LinkedList<Instruction> instructions) {
        this.caseValue = null;
        this.instructions = instructions;
        this.isDefault = true;
    }
    
    public Boolean getConditionValue() {
        return conditionValue || isDefault;
    }

    public Operation getCaseValue(){
        return caseValue;
    }
    
    public void setComparationValue(Operation comparationValue){
        this.comparationValue = comparationValue;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
         
    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        //Crear las variables a utilizar
        String code = "";
        String falseLabel;
        
        //Obtener el valor de la etiqueta cuando la condicion sea falsa.
        falseLabel = threeAddressCodeDriver.getNewLabel();
        
        //Si es un case con valor existe una comparacion a realizar, si es Default no.
        if(!isDefault){
            //Agregar la condicion negada  y las instrucciones respectivas 
            code += "if " +caseValue.generateOnlyReadThreeAddressCode(threeAddressCodeDriver)+ " != " +comparationValue.generateOnlyReadThreeAddressCode(threeAddressCodeDriver)+ " goto " + falseLabel + ";\n";
            code = instructions.stream().map(instruction -> instruction.generateOnlyReadThreeAddressCode(threeAddressCodeDriver)).reduce(code, String::concat);
        } else{
            //Agregar las instrucciones
            code = instructions.stream().map(instruction -> instruction.generateOnlyReadThreeAddressCode(threeAddressCodeDriver)).reduce(code, String::concat);
        }
            
        //Agregar la etiqueta falsa
        code += falseLabel + ":\n";
        return code;
    }

    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {   
        if(isDefault == true) conditionValue = true;
        else conditionValue = (Boolean) new Operation(comparationValue, caseValue, Operation.OperationType.EQUALS).analyze(symbolTable, ast, vCompilerFrame);
       /*        
        if(conditionValue || isDefault){
            SymbolTable localTable = new SymbolTable(symbolTable);
            for(var instruction: instructions){
                Object returnValue;
                returnValue = instruction.analyze(localTable, ast, vCompilerFrame);
                if(returnValue != null){
                    return returnValue;
                }
            }
        }*/
        return null;
    }
    
}
