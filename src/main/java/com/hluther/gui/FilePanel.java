package com.hluther.gui;

import com.hluther.controlClasses.FileChoosersDriver;
import com.hluther.controlClasses.FilesDriver;
import com.hluther.controlClasses.ThreadsDriver;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
/**
 *
 * @author helmuth
 */
public class FilePanel extends javax.swing.JPanel {

    private VCompilerFrame vCompilerFrame;
    private FilesDriver filesDriver;
    private FileChoosersDriver fileChoosersDriver;
    private VTabsCreator vTabsCreator;
    private JTabbedPane tabbedPane;
    private JLabel positionLabel;
    private JLabel informationLabel;
    private VTab[] vTabs;
    private VTab vTab;
    private int counter = 0;
        
    public FilePanel(VCompilerFrame vCompilerFrame, FilesDriver filesDriver, VTabsCreator vTabsCreator, VTab[] vTabs, JTabbedPane tabbedPane, JLabel positionLabel, JLabel informationLabel){
        this.vCompilerFrame = vCompilerFrame;
        this.filesDriver = filesDriver;
        fileChoosersDriver = new FileChoosersDriver(this.filesDriver);
        this.vTabsCreator = vTabsCreator;
        this.tabbedPane = tabbedPane;
        this.positionLabel = positionLabel;
        this.informationLabel = informationLabel;
        this.vTabs = vTabs;
        initComponents();
        newButtonActionPerformed(null);
    }
    
    
    /**
     * Metodo que realiza el proceso de guardado.
     * Valida si el vTab recibido como parametro tiene algun path asociado o no. Si 
     * tiene un path toda la informacion se guarda en el archivo correspondiente, 
     * De lo contario se llama al metodo saveAs(). Por ultimo se muestra un mensaje
     * indicando la respuesta del sistema. 
     * @param vTab vTab que contiene los datos a guardar. 
     */
    private void save(VTab vTab){
        if(vTab.getPath().isEmpty()){
            saveAs(vTab);
        }
        else{
            if(filesDriver.writeFile(vTab.getPath(), vTab.getData())) informationLabel.setText("Guardado en: " + vTab.getPath());
            else informationLabel.setText("Error al guardar el archivo.");
        }
        ThreadsDriver.clearLabel(informationLabel);
    }
    
    /**
     * Metodo que realiza el proceso de guardar como
     * Llama a la creacion y escritura de un nuevo archivo. Por ultimo
     * muestra un mensaje indicando la respuesta del sistema.
    */
    private void saveAs(VTab vTab){
        try {
            if(fileChoosersDriver.saveFile(vCompilerFrame, vTab)) informationLabel.setText("Guardado en: " + vTab.getPath());
            else informationLabel.setText("Guardado cancelado.");
        } catch (IOException ex) {
            informationLabel.setText("Error al guardar: " + ex.getMessage());
        }
        ThreadsDriver.clearLabel(informationLabel);
    }
   
    /**
     * Metodo que recorre cada elemento dentro del arreglo vTabs y valida si el 
     * contenido se debe de guardar o no. Cierra cada uno de los vTabs dentro del
     * JTabbedPane
     */
    private void saveFiles(){
        for(int i = 0; i < 4; i++){
            if(vTabs[i] != null){
                if(!vTabs[i].getSavedData().equals(vTabs[i].getText())){
                    if(JOptionPane.showConfirmDialog(tabbedPane, "Desea guardar los cambios realizados en "+vTabs[i].getName()+"?", "Guardar", 0, 1) == JOptionPane.YES_OPTION){
                            save(vTabs[i]);
                    }        
                }
                vTabs[i] = null;
                tabbedPane.remove(i);
            }
        }
        positionLabel.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        newButton = new rojeru_san.RSButton();
        openButton = new rojeru_san.RSButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        saveButton = new rojeru_san.RSButton();
        saveAsButton = new rojeru_san.RSButton();
        jPanel5 = new javax.swing.JPanel();
        exitButton = new rojeru_san.RSButton();

        setBackground(new java.awt.Color(60, 63, 68));
        setMaximumSize(new java.awt.Dimension(525, 100));
        setMinimumSize(new java.awt.Dimension(525, 100));
        setPreferredSize(new java.awt.Dimension(525, 100));
        setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(67, 70, 75));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setMaximumSize(new java.awt.Dimension(200, 32767));
        jPanel4.setMinimumSize(new java.awt.Dimension(200, 100));
        jPanel4.setPreferredSize(new java.awt.Dimension(200, 104));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(31, 187, 166));
        jPanel3.setMaximumSize(new java.awt.Dimension(150, 32767));
        jPanel3.setMinimumSize(new java.awt.Dimension(150, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(150, 100));
        jPanel3.setLayout(new java.awt.BorderLayout());

        newButton.setBackground(new java.awt.Color(67, 70, 75));
        newButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newFile.png"))); // NOI18N
        newButton.setText("Nuevo");
        newButton.setToolTipText("");
        newButton.setBorderPainted(false);
        newButton.setColorHover(new java.awt.Color(12, 173, 183));
        newButton.setFocusable(false);
        newButton.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        newButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newButton.setMaximumSize(new java.awt.Dimension(100, 20));
        newButton.setMinimumSize(new java.awt.Dimension(100, 20));
        newButton.setPreferredSize(new java.awt.Dimension(100, 20));
        newButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });
        jPanel3.add(newButton, java.awt.BorderLayout.WEST);

        openButton.setBackground(new java.awt.Color(67, 70, 75));
        openButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/openFile.png"))); // NOI18N
        openButton.setText("Abrir");
        openButton.setBorderPainted(false);
        openButton.setColorHover(new java.awt.Color(12, 173, 183));
        openButton.setFocusable(false);
        openButton.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        openButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openButton.setMaximumSize(new java.awt.Dimension(100, 20));
        openButton.setMinimumSize(new java.awt.Dimension(100, 20));
        openButton.setPreferredSize(new java.awt.Dimension(100, 20));
        openButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });
        jPanel3.add(openButton, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanel6.setBackground(new java.awt.Color(67, 70, 75));
        jPanel6.setMaximumSize(new java.awt.Dimension(200, 32767));
        jPanel6.setMinimumSize(new java.awt.Dimension(200, 0));
        jPanel6.setPreferredSize(new java.awt.Dimension(200, 100));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(67, 70, 75));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setMaximumSize(new java.awt.Dimension(200, 32767));
        jPanel9.setMinimumSize(new java.awt.Dimension(200, 100));
        jPanel9.setPreferredSize(new java.awt.Dimension(200, 100));
        jPanel9.setLayout(new java.awt.BorderLayout());

        saveButton.setBackground(new java.awt.Color(67, 70, 75));
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saveFile.png"))); // NOI18N
        saveButton.setText("Guardar");
        saveButton.setBorderPainted(false);
        saveButton.setColorHover(new java.awt.Color(12, 173, 183));
        saveButton.setFocusable(false);
        saveButton.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.setMaximumSize(new java.awt.Dimension(100, 20));
        saveButton.setMinimumSize(new java.awt.Dimension(100, 20));
        saveButton.setPreferredSize(new java.awt.Dimension(100, 20));
        saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jPanel9.add(saveButton, java.awt.BorderLayout.WEST);

        saveAsButton.setBackground(new java.awt.Color(67, 70, 75));
        saveAsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saveFile.png"))); // NOI18N
        saveAsButton.setText("<html><center>Guardar<br>Como</center></html>");
        saveAsButton.setToolTipText("");
        saveAsButton.setBorderPainted(false);
        saveAsButton.setColorHover(new java.awt.Color(12, 173, 183));
        saveAsButton.setFocusable(false);
        saveAsButton.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        saveAsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveAsButton.setMaximumSize(new java.awt.Dimension(100, 20));
        saveAsButton.setMinimumSize(new java.awt.Dimension(100, 20));
        saveAsButton.setPreferredSize(new java.awt.Dimension(100, 20));
        saveAsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveAsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsButtonActionPerformed(evt);
            }
        });
        jPanel9.add(saveAsButton, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(67, 70, 75));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setMaximumSize(new java.awt.Dimension(100, 32767));
        jPanel5.setMinimumSize(new java.awt.Dimension(100, 0));
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel5.setLayout(new java.awt.BorderLayout());

        exitButton.setBackground(new java.awt.Color(67, 70, 75));
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exit.png"))); // NOI18N
        exitButton.setText("Salir");
        exitButton.setBorderPainted(false);
        exitButton.setColorHover(new java.awt.Color(12, 173, 183));
        exitButton.setFocusable(false);
        exitButton.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        exitButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exitButton.setMaximumSize(new java.awt.Dimension(100, 20));
        exitButton.setMinimumSize(new java.awt.Dimension(100, 20));
        exitButton.setPreferredSize(new java.awt.Dimension(100, 20));
        exitButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        jPanel5.add(exitButton, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel5, java.awt.BorderLayout.LINE_END);

        add(jPanel6, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        saveFiles(); 
        vTabs[0] = vTabsCreator.addVTab(vCompilerFrame, tabbedPane, new VTab("Untitle " +counter, "mlg", "", positionLabel));   
        counter++;
    }//GEN-LAST:event_newButtonActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        saveFiles();
        vTab = fileChoosersDriver.openFile(vCompilerFrame); 
        if(vTab != null){
            vTab.setPositionLabel(positionLabel);
            vTabs[0] = vTabsCreator.addVTab(vCompilerFrame, tabbedPane, vTab);
        } 
        else{
            informationLabel.setText("Apertura de archivo cancelada o finalizada con error.");
            ThreadsDriver.clearLabel(informationLabel);
        }
    }//GEN-LAST:event_openButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        int index = tabbedPane.getSelectedIndex();
        if(index >= 0){
            save(vTabs[index]);
        } else{
            informationLabel.setText("No hay nada por guardar.");
            ThreadsDriver.clearLabel(informationLabel);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void saveAsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsButtonActionPerformed
        int index = tabbedPane.getSelectedIndex();
        if(index >= 0){
            saveAs(vTabs[index]);
        } else{
            informationLabel.setText("No hay nada por guardar.");
            ThreadsDriver.clearLabel(informationLabel);
        }
    }//GEN-LAST:event_saveAsButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        saveFiles();
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton exitButton;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private rojeru_san.RSButton newButton;
    private rojeru_san.RSButton openButton;
    private rojeru_san.RSButton saveAsButton;
    private rojeru_san.RSButton saveButton;
    // End of variables declaration//GEN-END:variables
}
