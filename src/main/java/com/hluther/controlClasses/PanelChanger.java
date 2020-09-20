package com.hluther.controlClasses;
import javax.swing.JPanel;

public class PanelChanger{
    
    /**
     * Metodo estatico que remueve todo lo contenido dentro del panel padre y agrega al panel padre el nuevo panel.
     * @param parentPanel Panel padre donde se agregara el nuevo panel.
     * @param newPanel  Panel que se desea agregar al panel padre.
     */ 
    public static void changePanel(JPanel parentPanel, JPanel newPanel){
        parentPanel.removeAll();
        parentPanel.add(newPanel);
        parentPanel.revalidate();
        parentPanel.repaint();
    }
    
}
