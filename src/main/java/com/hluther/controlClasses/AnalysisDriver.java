package com.hluther.controlClasses;

import com.hluther.gui.VCompilerFrame;
import com.hluther.compiler.lexer.VLexer;
import com.hluther.compiler.parser.VParser;
import java.io.StringReader;
/**
 *
 * @author helmuth
 */
public class AnalysisDriver {
    
    public void doAnalysis(String data, VCompilerFrame vCompilerFrame){
        try {
            VParser parser = new VParser(new VLexer(new StringReader(data), vCompilerFrame), vCompilerFrame);
            parser.parse();
        } catch (Exception ex) {
            System.out.println("Error al analizar: " +ex.getMessage());
        }
    }
}
