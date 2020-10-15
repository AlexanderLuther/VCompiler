package com.hluther.compiler.AST;

import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.gui.VCompilerFrame;
import com.hluther.compiler.AST.Symbol.Type;
/**
 *
 * @author helmuth
 */
public class Input implements Instruction{

    private final Type inputType;
    private final Print print;

    public Input(Type inputType, Print print) {
        this.inputType = inputType;
        this.print = print;
    }

    public Input(Type inputType) {
        this.inputType = inputType;
        this.print = null;
    }
   
    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        String code = "";
        if(print != null){
            code += print.generateOnlyReadThreeAddressCode(threeAddressCodeDriver);
        }
        code += threeAddressCodeDriver.getNewT() + " = read;\n";
        return code;
    }

    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame) {
        return null;
    }
    
}
