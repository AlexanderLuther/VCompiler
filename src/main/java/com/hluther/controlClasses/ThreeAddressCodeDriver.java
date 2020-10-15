package com.hluther.controlClasses;

import java.util.LinkedList;
/**
 *
 * @author helmuth
 */
public class ThreeAddressCodeDriver{
    
    private int tCounter;
    private int labelCounter;
    private LinkedList<String> queue;
    private final char T_VALUE = 194;
    private final char L_VALUE = 192;
    
    
    public ThreeAddressCodeDriver(){
        this.tCounter = -1;
        this.labelCounter = -1;
        this.queue = new LinkedList<>();
    }
    
    public String getNewT(){
        tCounter++;
        return String.valueOf(T_VALUE) + tCounter;
    }
    
    public String getCurrentT(){
        return String.valueOf(T_VALUE) + tCounter;
    }
    
    public String getNewLabel(){
        labelCounter++;
        return String.valueOf(L_VALUE) + labelCounter;
    }
    
    public String getAndAddNewLabel(){
        labelCounter++;
        this.addLabel(String.valueOf(L_VALUE) + labelCounter);
        return String.valueOf(L_VALUE) + labelCounter;
    }
    
    public String getCurrentLabel(){
        return String.valueOf(L_VALUE) + labelCounter;
    }
    
    public void addLabel(String labelValue){
        queue.addLast(labelValue);
    }
    
    public String removeLabel(){
        return queue.removeFirst();
    }
    
}