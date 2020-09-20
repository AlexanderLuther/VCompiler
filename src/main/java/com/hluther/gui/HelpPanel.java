package com.hluther.gui;

import com.hluther.controlClasses.FilesDriver;
import javax.swing.JOptionPane;
/**
 *
 * @author helmuth
 */
public class HelpPanel extends javax.swing.JPanel {

    private VCompilerFrame vCompilerFrame;
    private FilesDriver filesDriver;

    public HelpPanel(VCompilerFrame vCompilerFrame, FilesDriver filesDriver){
        initComponents();
        this.vCompilerFrame = vCompilerFrame;        
        this.filesDriver = filesDriver;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        technicalManualButton = new rojeru_san.RSButton();
        userManualButton = new rojeru_san.RSButton();
        aboutButton = new rojeru_san.RSButton();

        setBackground(new java.awt.Color(60, 63, 68));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(300, 100));
        setMinimumSize(new java.awt.Dimension(300, 100));
        setPreferredSize(new java.awt.Dimension(300, 100));
        setLayout(new java.awt.BorderLayout());

        technicalManualButton.setBackground(new java.awt.Color(67, 70, 75));
        technicalManualButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/technicalManual.png"))); // NOI18N
        technicalManualButton.setText("<html><center>Manual<br>Tecnico</center></html>");
        technicalManualButton.setToolTipText("");
        technicalManualButton.setBorderPainted(false);
        technicalManualButton.setColorHover(new java.awt.Color(12, 173, 183));
        technicalManualButton.setFocusable(false);
        technicalManualButton.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        technicalManualButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        technicalManualButton.setMaximumSize(new java.awt.Dimension(100, 20));
        technicalManualButton.setMinimumSize(new java.awt.Dimension(100, 20));
        technicalManualButton.setPreferredSize(new java.awt.Dimension(100, 20));
        technicalManualButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        technicalManualButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                technicalManualButtonActionPerformed(evt);
            }
        });
        add(technicalManualButton, java.awt.BorderLayout.EAST);

        userManualButton.setBackground(new java.awt.Color(67, 70, 75));
        userManualButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userManual.png"))); // NOI18N
        userManualButton.setText("<html><center>Manual de<br>Usuario</center></html>");
        userManualButton.setBorderPainted(false);
        userManualButton.setColorHover(new java.awt.Color(12, 173, 183));
        userManualButton.setFocusable(false);
        userManualButton.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        userManualButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        userManualButton.setMaximumSize(new java.awt.Dimension(100, 20));
        userManualButton.setMinimumSize(new java.awt.Dimension(100, 20));
        userManualButton.setPreferredSize(new java.awt.Dimension(100, 20));
        userManualButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        userManualButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userManualButtonActionPerformed(evt);
            }
        });
        add(userManualButton, java.awt.BorderLayout.CENTER);

        aboutButton.setBackground(new java.awt.Color(67, 70, 75));
        aboutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/about.png"))); // NOI18N
        aboutButton.setText("<html><center>Acerca<br>de..</center></html>");
        aboutButton.setBorderPainted(false);
        aboutButton.setColorHover(new java.awt.Color(12, 173, 183));
        aboutButton.setFocusable(false);
        aboutButton.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        aboutButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aboutButton.setMaximumSize(new java.awt.Dimension(100, 20));
        aboutButton.setMinimumSize(new java.awt.Dimension(100, 20));
        aboutButton.setPreferredSize(new java.awt.Dimension(100, 20));
        aboutButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });
        add(aboutButton, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        JOptionPane.showMessageDialog(vCompilerFrame, "<html><center><font size=4 face=\"times new roman\">Desarrollado por:<br>Helmuth Alexander Luther Montejo<br>201730457<br>Version:1.0<br>©Todos los derechos reservados</font></center></html>", "Acerca de...", 1);
    }//GEN-LAST:event_aboutButtonActionPerformed

    private void userManualButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userManualButtonActionPerformed
        filesDriver.openPDF("userManual.pdf");
    }//GEN-LAST:event_userManualButtonActionPerformed

    private void technicalManualButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_technicalManualButtonActionPerformed
        filesDriver.openPDF("technicalManual.pdf");
    }//GEN-LAST:event_technicalManualButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton aboutButton;
    private rojeru_san.RSButton technicalManualButton;
    private rojeru_san.RSButton userManualButton;
    // End of variables declaration//GEN-END:variables
}
