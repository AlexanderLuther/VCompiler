package com.hluther.gui;
/**
 *
 * @author helmuth
 */
public class GeneratePanel extends javax.swing.JPanel {

    private VCompilerFrame vCompilerFrame;
        
    public GeneratePanel(VCompilerFrame vCompilerFrame){
        this.vCompilerFrame = vCompilerFrame;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        optimizedCodeButton = new rojeru_san.RSButton();
        codeButton = new rojeru_san.RSButton();
        assemblerButton = new rojeru_san.RSButton();

        setBackground(new java.awt.Color(60, 63, 68));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(300, 100));
        setMinimumSize(new java.awt.Dimension(300, 100));
        setPreferredSize(new java.awt.Dimension(300, 100));
        setLayout(new java.awt.BorderLayout());

        optimizedCodeButton.setBackground(new java.awt.Color(67, 70, 75));
        optimizedCodeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/optimizedCode.png"))); // NOI18N
        optimizedCodeButton.setText("<html><center>Codigo<br>Optimizado</center></html>");
        optimizedCodeButton.setToolTipText("");
        optimizedCodeButton.setBorderPainted(false);
        optimizedCodeButton.setColorHover(new java.awt.Color(12, 173, 183));
        optimizedCodeButton.setFocusable(false);
        optimizedCodeButton.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        optimizedCodeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        optimizedCodeButton.setMaximumSize(new java.awt.Dimension(100, 20));
        optimizedCodeButton.setMinimumSize(new java.awt.Dimension(100, 20));
        optimizedCodeButton.setPreferredSize(new java.awt.Dimension(100, 20));
        optimizedCodeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(optimizedCodeButton, java.awt.BorderLayout.CENTER);

        codeButton.setBackground(new java.awt.Color(67, 70, 75));
        codeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code.png"))); // NOI18N
        codeButton.setText("<html><center>Codigo de<br>tres direcciones</center></html>");
        codeButton.setBorderPainted(false);
        codeButton.setColorHover(new java.awt.Color(12, 173, 183));
        codeButton.setFocusable(false);
        codeButton.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        codeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        codeButton.setMaximumSize(new java.awt.Dimension(100, 20));
        codeButton.setMinimumSize(new java.awt.Dimension(100, 20));
        codeButton.setPreferredSize(new java.awt.Dimension(100, 20));
        codeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(codeButton, java.awt.BorderLayout.WEST);

        assemblerButton.setBackground(new java.awt.Color(67, 70, 75));
        assemblerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assembler.png"))); // NOI18N
        assemblerButton.setText("<html><center>Codigo<br>Assembler</center></html>");
        assemblerButton.setBorderPainted(false);
        assemblerButton.setColorHover(new java.awt.Color(12, 173, 183));
        assemblerButton.setFocusable(false);
        assemblerButton.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        assemblerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        assemblerButton.setMaximumSize(new java.awt.Dimension(100, 20));
        assemblerButton.setMinimumSize(new java.awt.Dimension(100, 20));
        assemblerButton.setPreferredSize(new java.awt.Dimension(100, 20));
        assemblerButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(assemblerButton, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton assemblerButton;
    private rojeru_san.RSButton codeButton;
    private rojeru_san.RSButton optimizedCodeButton;
    // End of variables declaration//GEN-END:variables
}
