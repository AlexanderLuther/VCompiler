package com.hluther.controlClasses;

import com.hluther.compiler.AST.AbstractSyntaxTree;
import com.hluther.gui.VCompilerFrame;
import com.hluther.compiler.lexer.VLexer;
import com.hluther.compiler.parser.VParser;
import java.io.StringReader;
/**
 *
 * @author helmuth
 */
public class AnalysisDriver {
    
    public AbstractSyntaxTree doAnalysis(String data, VCompilerFrame vCompilerFrame){
        try {
            VParser parser = new VParser(new VLexer(new StringReader(data), vCompilerFrame), vCompilerFrame);
            parser.parse();
            return parser.getAST(); 
        } catch (Exception ex) {
            System.out.println("Error al analizar: " +ex.getMessage());
        }
        return null;
    }
}
