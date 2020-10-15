package com.hluther.compiler.AST;

import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.entityClasses.SemanticException;
import com.hluther.gui.VCompilerFrame;

/**
 *
 * @author helmuth
 */
public class Import implements Instruction{
    
    private final ImportType type;
    private String id;
    private final int row;
    private final int column;
    
    public Import(ImportType type, int row, int column){
        this.type = type;
        this.row = row;
        this.column = column;
    }
   
    public Import(ImportType type, String id, int row, int column){
        this.type = type;
        this.id = id;
        this.row = row;
        this.column = column;
    }
    
    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        return "";
    }

    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {
        try{
            switch(type){
                case VISUAL_BASIC:
                    if(ast.getVisualBasicInstructions() != null){
                        ast.getVisualBasicInstructions().stream().filter(instruction -> (instruction instanceof Method)).forEachOrdered(instruction -> {
                            ((Method) instruction).setImported(true);
                        });
                    }
                    else throw new SemanticException(row, column, "Se realizo un import hacia codigo de Visual Basic inexistente");
                break;
                case PHYTON:
                    if(ast.getPhytonInstructions()!= null){
                        ast.getPhytonInstructions().stream().filter(instruction -> (instruction instanceof Method)).forEachOrdered(instruction -> {
                            ((Method) instruction).setImported(true);
                        });
                    } else throw new SemanticException(row, column, "Se realizo un import hacia codigo de Phyton inexistente");
                break;
                case JAVA_ALL:
                    if(ast.getJavaInstructions() != null){

                    } else throw new SemanticException(row, column, "Se realizo un import hacia codigo de Java inexistente");
                break;
                case JAVA_CLASS:
                    if(ast.getJavaInstructions() != null){

                    } else throw new SemanticException(row, column, "Se realizo un import hacia la clase Java [" +id+ "], la cual es inexistente.");
                break;
            }
        } catch(SemanticException ex){
            vCompilerFrame.printMessage(ex.getMessage());
        }
        return null;
    }
 
    public static enum ImportType{
        VISUAL_BASIC,
        JAVA_ALL,
        JAVA_CLASS,
        PHYTON,
        C_LIBRARY,
        C_FILE
    }
    
}

