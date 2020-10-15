package com.hluther.compiler.AST;

import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.gui.VCompilerFrame;
/**
 *
 * @author helmuth
 */
public interface Instruction {

    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver);
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame);
}
