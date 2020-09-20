package com.hluther.controlClasses;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author helmuth
 */
public class FilesDriver {
    
    /**
     * Metodo que abre un archivo PDF ubicado dentro de la carpeta resources de la aplicacion.
     * @param PDFName Nombre del archivo PDF que se abrira.
     */
    public void openPDF(String PDFName){
        try {
            InputStream pdf = getClass().getClassLoader().getResourceAsStream(PDFName);
            File file = new File(PDFName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while (pdf.available() > 0) {
                fileOutputStream.write(pdf.read());
            }
            fileOutputStream.close();
            Desktop.getDesktop().open(file);
        }   
        catch (IOException e) {
          System.out.println("Error : " + e.getMessage());
        } 
    }
    
    
    /**
     * Metodo que obtiene la informacion contenida dentro de un archivo cuya ruta se 
     * recibe como parametro.
     * @param path Ruta del archivo a leer.
     * @return Una cadena con los datos contenidos dentro del archivo.
     */
    public String readFile(String path){
        String text = "";
        String data;
        BufferedReader bufferReader = null;
	try {
            bufferReader = new BufferedReader(new FileReader(path));
            while ((data = bufferReader.readLine()) != null){    
                text = text  +data+ "\n";
            }
            if(text.length()!=0){
                text = text.substring(0, text.length()-1);
            }
	}
        catch (EOFException ex) {
            System.out.println("ERROR: Lectura finalizada");
	}
        catch (IOException ex) {
            System.out.println("ERROR: No se puede leer archivo");
	}
        finally{
            try {
		bufferReader.close();
            } 
            catch (IOException ex) {
		System.out.println("ERROR: No se pudo cerrar el archivo");
            }
	}
        return text;
    }
    
    /**
     * Metodo que escribe dentro de un archivo ubicado en la ruta que recive como parametro.
     * @param path Ruta donde se encuentra el archivo.
     * @param data Datos a guardar dentro del archivo
     * @return  True si la escritura fue exitosa, de lo contrario false.
     */
    public boolean writeFile(String path, String data){
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.close();
	} catch (IOException ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Metodo que crear un archivo en blanco en la ruta que recibe como parametro.
     * @param path Ruta del archivo a crear.
     * @return El archivo creado
     * @throws IOException 
     */
    public File createFile(String path) throws IOException{
        File file = new File(path);
        if (!file.exists()){
            file.createNewFile();
        }
        return file;
    }
    
}
