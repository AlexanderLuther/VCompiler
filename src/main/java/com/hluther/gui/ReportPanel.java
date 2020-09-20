package com.hluther.gui;
/**
 *
 * @author helmuth
 */
public class ReportPanel extends javax.swing.JPanel {

    private VCompilerFrame vCompilerFrame;
        
    public ReportPanel(VCompilerFrame vCompilerFrame){
        this.vCompilerFrame = vCompilerFrame;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reportButton = new rojeru_san.RSButton();

        setBackground(new java.awt.Color(60, 63, 68));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(110, 100));
        setMinimumSize(new java.awt.Dimension(110, 100));
        setPreferredSize(new java.awt.Dimension(110, 100));
        setLayout(new java.awt.BorderLayout());

        reportButton.setBackground(new java.awt.Color(67, 70, 75));
        reportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/report.png"))); // NOI18N
        reportButton.setText("<html><center>Reporte de<br>Optimizacion</center></html>");
        reportButton.setToolTipText("");
        reportButton.setBorderPainted(false);
        reportButton.setColorHover(new java.awt.Color(12, 173, 183));
        reportButton.setFocusable(false);
        reportButton.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        reportButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reportButton.setMaximumSize(new java.awt.Dimension(110, 20));
        reportButton.setMinimumSize(new java.awt.Dimension(110, 20));
        reportButton.setPreferredSize(new java.awt.Dimension(110, 20));
        reportButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(reportButton, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton reportButton;
    // End of variables declaration//GEN-END:variables
}
