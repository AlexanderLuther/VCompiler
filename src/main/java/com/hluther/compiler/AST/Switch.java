package com.hluther.compiler.AST;

import com.hluther.gui.VCompilerFrame;
import com.hluther.compiler.AST.Symbol.Type;
import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.entityClasses.SemanticException;
import java.util.LinkedList;
/**
 *
 * @author helmuth
 */
public class Switch implements Instruction{
    
    private final Operation comparationValue;
    private final LinkedList<Case> caseList;
    private Type type;
    
    public Switch(Operation comparationValue, LinkedList<Case> caseList){
        this.comparationValue = comparationValue;
        this.caseList = caseList;
    }

    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        String code = "";
        //Agregar cada uno de los Case y sus respectivas instrucciones.
        for(var instruction : caseList){
            ((Case)instruction).setComparationValue(comparationValue);
            code += instruction.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        }
        return code;
    }

    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {
        try{
            Object value = this.comparationValue.analyze(symbolTable, ast, vCompilerFrame);
            if(value instanceof Integer){
                type = Type.INT;
            } else if(value instanceof Double){
                type = Type.FLOAT;
            } else{
                type = Type.CHAR;
            }
            
            boolean semanticError = false;
            for(Case instruction : caseList){
                if(instruction.getCaseValue() != null){
                    Object caseValue = instruction.getCaseValue().analyze(symbolTable, ast, vCompilerFrame);
                    if(!Symbol.validateType(type, caseValue) && !Symbol.validateTypeConvertion(type, caseValue)){
                        semanticError = true;
                        SemanticException ex = new SemanticException("El tipo de dato de la variable de comparacion y el tipo de dato del case no son compatibles.");
                        ex.setRow(((Case)instruction).getRow());
                        ex.setColumn(((Case)instruction).getColumn());
                        throw ex;
                    }
                }
            }
            
            if(!semanticError){
                Object returnValue;
                for(var instruction: caseList){
                    ((Case)instruction).setComparationValue(comparationValue);
                    returnValue = instruction.analyze(symbolTable, ast, vCompilerFrame);
                    if(((Case)instruction).getConditionValue())
                        return returnValue;
                }
            }
            
        } catch(SemanticException ex){
            vCompilerFrame.printMessage(ex.getMessage());
        }
        return null;
    }
    
}