package com.hluther.vcompiler;

import com.hluther.gui.VCompilerFrame;
/**
 *
 * @author helmuth
 */
public class VCompiler {

    public static void main(String args[]){  
        VCompilerFrame vCompilerFrame = new VCompilerFrame();
        vCompilerFrame.setExtendedState(vCompilerFrame.MAXIMIZED_BOTH);
        vCompilerFrame.setLocationRelativeTo(null);
        vCompilerFrame.setVisible(true);
    } 
}
