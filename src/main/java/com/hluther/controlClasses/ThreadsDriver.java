package com.hluther.controlClasses;
import javax.swing.JLabel;
/**
 *
 * @author helmuthluther
 */
public class ThreadsDriver {
    
    /**
     * Metodo que da una pausa de 3.5 segundos y limpia el texto contenido dentro de la etiqueta que
     * recibe como parametro.
     * @param label Etiqueta a limpiar.
     */
    public static void clearLabel(JLabel label){
        Thread hilo = new Thread(){
        @Override 
        public  void run(){
            try {
                Thread.sleep(3500);
                label.setText("");
            }    
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }};
        hilo.start();
    }
}
