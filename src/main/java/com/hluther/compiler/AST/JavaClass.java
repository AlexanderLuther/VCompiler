package com.hluther.compiler.AST;

import com.hluther.compiler.AST.Method.AccessModifier;
import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.gui.VCompilerFrame;
import java.util.LinkedList;

/**
 *
 * @author helmuth
 */
public class JavaClass implements Instruction{

    private final AccessModifier accessModifier;
    private final String id;
    private final LinkedList<Instruction> instructions;
    
    public JavaClass(AccessModifier accessModifier, String id, LinkedList<Instruction> instructions) {
        this.accessModifier = accessModifier;
        this.id = id;
        this.instructions = instructions;
    }

    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        String code = "";
        for(var instruction : instructions){
            code += instruction.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);;
            if(instruction instanceof Method) code+= "\n";
        } 
        return code;
    }

    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
