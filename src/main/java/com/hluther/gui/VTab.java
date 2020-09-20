package com.hluther.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
/**
 *
 * @author helmuth
 */
public class VTab extends JTextArea implements CaretListener{

    private String name;
    private String extension;
    private String data;
    private String path;
    private String savedData;
    private JLabel positionLabel;
    
    public VTab(String name, String extension, String data, JLabel positionLabel) {
        this.name = name;
        this.extension = extension;
        this.data = data;
        this.path = "";
        this.positionLabel = positionLabel;
        setAttributes();    
    }

    public VTab(String name, String extension, String data, String path, JLabel positionLabel) {
        this.name = name;
        this.extension = extension;
        this.data = data;
        this.path = path;
        this.positionLabel = positionLabel;
        setAttributes();
    }
    
    private void setAttributes(){
        savedData = data;
        this.setText(data);
        this.addCaretListener(this);
        this.setBackground(new Color(43,43,43));
        this.setCaretColor(Color.WHITE);
        this.setForeground(new Color(187, 187, 187));
        this.setFont(new Font("Source Code Pro", 0, 14));
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getData() {
        return this.getText();
    }

    public void setData(String data) {
        this.data = data;
        this.setText(data);
    }   

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSavedData() {
        return savedData;
    }

    public void setPositionLabel(JLabel positionLabel) {
        this.positionLabel = positionLabel;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        try {
            int position = this.getCaretPosition();
            int line = this.getLineOfOffset(position);
            int column = position - this.getLineStartOffset(line);
            line += 1;
            positionLabel.setText("Lin: " + line + "  Col: " + column);
        }
        catch(BadLocationException ex){
            System.out.println("Error al capturar el evento");
        }
    }
    
}
