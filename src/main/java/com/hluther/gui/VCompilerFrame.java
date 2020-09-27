package com.hluther.gui;

import com.hluther.controlClasses.PanelChanger;
import com.hluther.controlClasses.FilesDriver;
import com.hluther.entityClasses.MenuType;
import java.awt.Color;
import java.awt.Cursor;
import rojeru_san.RSButton;
import javax.swing.JPanel;
/**
 *
 * @author helmuth
 */
public class VCompilerFrame extends javax.swing.JFrame {
        
    private RSButton menuButtons[];
    private JPanel optionPanels[];
    private VTab vTabs[];
    
    //Manejadores de datos
    private FilesDriver filesDriver;
    private VTabsCreator vTabsCreator;

    //Paneles que conforman las opciones respectivas de cada menu
    private final FilePanel filePanel;
    private final GeneratePanel generatePanel;
    private final ExecutePanel executePanel;
    private final ReportPanel reportPanel;
    private final HelpPanel helpPanel;
    
    private final Color HOVER_COLOR = new Color(12,173,183);
    private final Color SELECTED_COLOR = new Color(67, 70, 75);
    
    public VCompilerFrame() {
        initComponents();
        
        filesDriver = new FilesDriver();
        vTabsCreator = new VTabsCreator();
        
        vTabs = new VTab[4];
        
        filePanel = new FilePanel(this, filesDriver, vTabsCreator, vTabs, vTabsArea, positionLabel, informationLabel);
        generatePanel = new GeneratePanel(this, vTabs, informationLabel, messagesArea);
        executePanel = new ExecutePanel(this);
        reportPanel = new ReportPanel(this);
        helpPanel = new HelpPanel(this, filesDriver);
       

        RSButton menuButtons[] = {fileButton, generateButton, executeButton, reportButton, helpButton};
        JPanel optionPanels[] = {filePanel, generatePanel, executePanel, reportPanel, helpPanel};
        this.menuButtons = menuButtons;
        this.optionPanels = optionPanels;
        
        setSelectedMenu(MenuType.FILE_MENU);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
    }
 
      
    //ACCIONES SOBRE LA BARRA DE MENU
    /**
     * Metodo que modifica los atributos selected y colorHover de los botones
     * que forman parte del arreglo menuButtons.
     */
    private void reestoreButtonsValues(){
        for(var button : menuButtons){
            button.setSelected(false);
            button.setColorHover(HOVER_COLOR);
        }
    }    
    
    /**
     * Metodo que verifica si el tipo de menu recibido no es el que se encuentra seleccionado
     * actualemente, de ser asi reestablece el valor de todos los botones y modifica los atributos
     * colorHover y selected del boton correspondiente al tipo recibido como parametro. Agrega al
     * panel de acciones el panel correspondiente dentro del arreglo optionPanels.. 
     * @param selectedMenu 
     */
    private void setSelectedMenu(MenuType selectedMenu){
        if(!menuButtons[selectedMenu.ordinal()].isSelected()){
            reestoreButtonsValues();
            menuButtons[selectedMenu.ordinal()].setColorHover(SELECTED_COLOR);
            menuButtons[selectedMenu.ordinal()].setSelected(true);
            PanelChanger.changePanel(actionsPanel, optionPanels[selectedMenu.ordinal()]);
        }
    }
    
    public void printMessage(String message){
        messagesArea.setText(messagesArea.getText() + message);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        menusPanel = new javax.swing.JPanel();
        fileButton = new rojeru_san.RSButton();
        generateButton = new rojeru_san.RSButton();
        executeButton = new rojeru_san.RSButton();
        reportButton = new rojeru_san.RSButton();
        helpButton = new rojeru_san.RSButton();
        actionsPanel = new javax.swing.JPanel();
        toolBarPanel = new javax.swing.JPanel();
        messagesPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        positionLabel = new javax.swing.JLabel();
        informationLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messagesArea = new javax.swing.JTextPane();
        textEditorPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        vTabsArea = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VCompiler");
        setMinimumSize(new java.awt.Dimension(700, 500));

        mainPanel.setLayout(new java.awt.BorderLayout());

        menuPanel.setMaximumSize(new java.awt.Dimension(0, 150));
        menuPanel.setMinimumSize(new java.awt.Dimension(0, 150));
        menuPanel.setPreferredSize(new java.awt.Dimension(896, 150));
        menuPanel.setLayout(new java.awt.BorderLayout());

        menusPanel.setBackground(new java.awt.Color(52, 53, 57));
        menusPanel.setMaximumSize(new java.awt.Dimension(480, 25));
        menusPanel.setMinimumSize(new java.awt.Dimension(480, 25));
        menusPanel.setPreferredSize(new java.awt.Dimension(0, 25));
        menusPanel.setLayout(new javax.swing.BoxLayout(menusPanel, javax.swing.BoxLayout.LINE_AXIS));

        fileButton.setBackground(new java.awt.Color(52, 53, 57));
        fileButton.setText("Archivo");
        fileButton.setBorderPainted(false);
        fileButton.setColorHover(new java.awt.Color(12, 173, 183));
        fileButton.setFocusable(false);
        fileButton.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 12)); // NOI18N
        fileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileButtonActionPerformed(evt);
            }
        });
        menusPanel.add(fileButton);

        generateButton.setBackground(new java.awt.Color(52, 53, 57));
        generateButton.setText("Generar");
        generateButton.setBorderPainted(false);
        generateButton.setColorHover(new java.awt.Color(12, 173, 183));
        generateButton.setFocusable(false);
        generateButton.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 12)); // NOI18N
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });
        menusPanel.add(generateButton);

        executeButton.setBackground(new java.awt.Color(52, 53, 57));
        executeButton.setText("Ejecutar");
        executeButton.setBorderPainted(false);
        executeButton.setColorHover(new java.awt.Color(12, 173, 183));
        executeButton.setFocusable(false);
        executeButton.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 12)); // NOI18N
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeButtonActionPerformed(evt);
            }
        });
        menusPanel.add(executeButton);

        reportButton.setBackground(new java.awt.Color(52, 53, 57));
        reportButton.setText("Reporte");
        reportButton.setBorderPainted(false);
        reportButton.setColorHover(new java.awt.Color(12, 173, 183));
        reportButton.setFocusable(false);
        reportButton.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 12)); // NOI18N
        reportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportButtonActionPerformed(evt);
            }
        });
        menusPanel.add(reportButton);

        helpButton.setBackground(new java.awt.Color(52, 53, 57));
        helpButton.setText("Ayuda");
        helpButton.setBorderPainted(false);
        helpButton.setColorHover(new java.awt.Color(12, 173, 183));
        helpButton.setFocusable(false);
        helpButton.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 12)); // NOI18N
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        menusPanel.add(helpButton);

        menuPanel.add(menusPanel, java.awt.BorderLayout.PAGE_START);

        actionsPanel.setBackground(new java.awt.Color(67, 70, 75));
        actionsPanel.setMaximumSize(new java.awt.Dimension(0, 75));
        actionsPanel.setMinimumSize(new java.awt.Dimension(0, 75));
        actionsPanel.setPreferredSize(new java.awt.Dimension(851, 75));
        actionsPanel.setLayout(new javax.swing.BoxLayout(actionsPanel, javax.swing.BoxLayout.LINE_AXIS));
        menuPanel.add(actionsPanel, java.awt.BorderLayout.CENTER);

        toolBarPanel.setBackground(new java.awt.Color(52, 53, 57));
        toolBarPanel.setMaximumSize(new java.awt.Dimension(32767, 30));
        toolBarPanel.setMinimumSize(new java.awt.Dimension(0, 30));
        toolBarPanel.setPreferredSize(new java.awt.Dimension(851, 30));
        toolBarPanel.setLayout(new javax.swing.BoxLayout(toolBarPanel, javax.swing.BoxLayout.LINE_AXIS));
        menuPanel.add(toolBarPanel, java.awt.BorderLayout.PAGE_END);

        mainPanel.add(menuPanel, java.awt.BorderLayout.PAGE_START);

        messagesPanel.setMaximumSize(new java.awt.Dimension(2147483647, 170));
        messagesPanel.setMinimumSize(new java.awt.Dimension(46, 170));
        messagesPanel.setPreferredSize(new java.awt.Dimension(851, 170));
        messagesPanel.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(67, 70, 75));
        jPanel3.setMaximumSize(new java.awt.Dimension(15, 32767));
        jPanel3.setMinimumSize(new java.awt.Dimension(15, 100));
        jPanel3.setPreferredSize(new java.awt.Dimension(15, 100));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );

        messagesPanel.add(jPanel3, java.awt.BorderLayout.WEST);

        jPanel4.setBackground(new java.awt.Color(67, 70, 75));
        jPanel4.setMaximumSize(new java.awt.Dimension(15, 32767));
        jPanel4.setMinimumSize(new java.awt.Dimension(15, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(15, 100));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        messagesPanel.add(jPanel4, java.awt.BorderLayout.EAST);

        jPanel5.setBackground(new java.awt.Color(52, 53, 57));
        jPanel5.setMaximumSize(new java.awt.Dimension(32767, 30));
        jPanel5.setMinimumSize(new java.awt.Dimension(0, 30));
        jPanel5.setPreferredSize(new java.awt.Dimension(851, 30));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(67, 70, 75));
        jPanel6.setMaximumSize(new java.awt.Dimension(15, 32767));
        jPanel6.setMinimumSize(new java.awt.Dimension(15, 100));
        jPanel6.setPreferredSize(new java.awt.Dimension(15, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanel8.setBackground(new java.awt.Color(67, 70, 75));
        jPanel8.setMaximumSize(new java.awt.Dimension(15, 32767));
        jPanel8.setMinimumSize(new java.awt.Dimension(15, 100));
        jPanel8.setPreferredSize(new java.awt.Dimension(15, 100));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel8, java.awt.BorderLayout.EAST);

        jPanel9.setBackground(new java.awt.Color(67, 70, 75));
        jPanel9.setLayout(new java.awt.BorderLayout());

        positionLabel.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 14)); // NOI18N
        positionLabel.setForeground(new java.awt.Color(12, 173, 183));
        positionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        positionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        positionLabel.setMaximumSize(new java.awt.Dimension(200, 17));
        positionLabel.setMinimumSize(new java.awt.Dimension(200, 17));
        positionLabel.setPreferredSize(new java.awt.Dimension(200, 17));
        jPanel9.add(positionLabel, java.awt.BorderLayout.LINE_END);

        informationLabel.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 12)); // NOI18N
        informationLabel.setForeground(new java.awt.Color(12, 173, 183));
        jPanel9.add(informationLabel, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel9, java.awt.BorderLayout.CENTER);

        messagesPanel.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel7.setBackground(new java.awt.Color(67, 70, 75));
        jPanel7.setMaximumSize(new java.awt.Dimension(32767, 25));
        jPanel7.setMinimumSize(new java.awt.Dimension(0, 25));
        jPanel7.setPreferredSize(new java.awt.Dimension(851, 25));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 851, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        messagesPanel.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        messagesArea.setEditable(false);
        messagesArea.setBackground(new java.awt.Color(43, 43, 43));
        messagesArea.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(12, 173, 183), 1, true));
        messagesArea.setToolTipText("Area de Mensajes");
        messagesArea.setPreferredSize(new java.awt.Dimension(62, 20));
        jScrollPane1.setViewportView(messagesArea);

        messagesPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        mainPanel.add(messagesPanel, java.awt.BorderLayout.PAGE_END);

        textEditorPanel.setBackground(new java.awt.Color(43, 43, 43));
        textEditorPanel.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(67, 70, 75));
        jPanel1.setMaximumSize(new java.awt.Dimension(15, 32767));
        jPanel1.setMinimumSize(new java.awt.Dimension(15, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(15, 194));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 109, Short.MAX_VALUE)
        );

        textEditorPanel.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(67, 70, 75));
        jPanel2.setMaximumSize(new java.awt.Dimension(15, 32767));
        jPanel2.setMinimumSize(new java.awt.Dimension(15, 100));
        jPanel2.setPreferredSize(new java.awt.Dimension(15, 194));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 109, Short.MAX_VALUE)
        );

        textEditorPanel.add(jPanel2, java.awt.BorderLayout.LINE_END);

        vTabsArea.setBackground(new java.awt.Color(43, 43, 43));
        vTabsArea.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(12, 173, 183), 1, true));
        vTabsArea.setForeground(new java.awt.Color(52, 148, 162));
        vTabsArea.setFocusable(false);
        vTabsArea.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 12)); // NOI18N
        textEditorPanel.add(vTabsArea, java.awt.BorderLayout.CENTER);

        jPanel10.setBackground(new java.awt.Color(67, 70, 75));
        jPanel10.setMaximumSize(new java.awt.Dimension(32767, 15));
        jPanel10.setMinimumSize(new java.awt.Dimension(0, 15));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 851, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        textEditorPanel.add(jPanel10, java.awt.BorderLayout.PAGE_START);

        mainPanel.add(textEditorPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileButtonActionPerformed
        setSelectedMenu(MenuType.FILE_MENU);
    }//GEN-LAST:event_fileButtonActionPerformed
        
    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        setSelectedMenu(MenuType.GENERATE_MENU);
    }//GEN-LAST:event_generateButtonActionPerformed

    private void executeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
        setSelectedMenu(MenuType.EXECUTE_MENU);
    }//GEN-LAST:event_executeButtonActionPerformed

    private void reportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportButtonActionPerformed
        setSelectedMenu(MenuType.REPORT_MENU);
    }//GEN-LAST:event_reportButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        setSelectedMenu(MenuType.HELP_MENU);
    }//GEN-LAST:event_helpButtonActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VCompilerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VCompilerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VCompilerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VCompilerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VCompilerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsPanel;
    private rojeru_san.RSButton executeButton;
    private rojeru_san.RSButton fileButton;
    private rojeru_san.RSButton generateButton;
    private rojeru_san.RSButton helpButton;
    private javax.swing.JLabel informationLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel menusPanel;
    private javax.swing.JTextPane messagesArea;
    private javax.swing.JPanel messagesPanel;
    private javax.swing.JLabel positionLabel;
    private rojeru_san.RSButton reportButton;
    private javax.swing.JPanel textEditorPanel;
    private javax.swing.JPanel toolBarPanel;
    private javax.swing.JTabbedPane vTabsArea;
    // End of variables declaration//GEN-END:variables
}
