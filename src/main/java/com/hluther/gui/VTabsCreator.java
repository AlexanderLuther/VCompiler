package com.hluther.gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
/**
 *
 * @author helmuth
 */
public class VTabsCreator {
    
    private LineNumber lineNumber;
    private JScrollPane scrollPane;
    private JPanel panel;
        
    /*
    * Metodo encargado de agregar un nuevo panel al JTabbedPane.
    * Dentro del panel se agrega un JTextArea que tendra una barra lateral que 
    * marca el numero de linea.
    * Agrega dos eventos sobre en nuevo panel para visualizar la linea y columna
    * donde se encuentra el cursos y para cerrar el panel actual.
    */
    public VTab addVTab(VCompilerFrame parent, JTabbedPane tabbedPane, VTab vTab){
        vTab.setLineWrap(true);
        lineNumber= new LineNumber(vTab);
        lineNumber.setBackground(new Color(52,53,57));
        lineNumber.setForeground(Color.WHITE);
        scrollPane = new JScrollPane(vTab);
        scrollPane.setRowHeaderView(lineNumber);
        panel = new JPanel(new GridLayout());
        panel.add(scrollPane);
        tabbedPane.addTab(vTab.getName() +"."+ vTab.getExtension(), panel); 
        return vTab;
    }
    
}
