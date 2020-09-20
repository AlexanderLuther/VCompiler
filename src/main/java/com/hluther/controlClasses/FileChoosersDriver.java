package com.hluther.controlClasses;

import com.hluther.controlClasses.FilesDriver;
import com.hluther.gui.VTab;
import com.hluther.gui.VCompilerFrame;
import javax.swing.JFileChooser;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author helmuth
 */
public class FileChoosersDriver {
    
    private FilesDriver filesDriver;
    private JFileChooser fileChooser;
    private final FileNameExtensionFilter MLG_FILTER = new FileNameExtensionFilter("MLG","mlg");
    
    public FileChoosersDriver(FilesDriver filesDriver){
        this.filesDriver = filesDriver;
    }
    
    /**
     * Metodo que muestra un JFileChooser y obtiene el archivo seleccionado.
     * Crea y retorna una nueva intancia de la clase Tab enviando como parametos
     * la data contenida dentro del archivo, el nombre y la extension. 
     * @param frame Frame padre para el JFileChooser
     * @return Nueva instancia de vTab si el proceso es correcto, null de lo 
     *         contrario
     */
    public VTab openFile(VCompilerFrame frame){
        String data = "";
        String name = "";
        String extension = "";
        String path = "";
        fileChooser = new JFileChooser();
        fileChooser.setApproveButtonText("Abrir");
        fileChooser.setDialogTitle("Abrir Archivo");  
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(MLG_FILTER);
        int selection = fileChooser.showOpenDialog(frame);      
        if(selection == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            data = filesDriver.readFile(file.toString());
            name = file.getName();
            extension = FilenameUtils.getExtension(name);
            path = file.getPath();
            return new VTab(name, extension, data, path, null); 
        }
        return null;
    }
    
    
    /**
     * Metodo que abre un JFileChooser para seleccionar la ruta donde se guardara un nuevo archivo
     * Obtiene la ruta y se llama a la creacion de un nuevo archivo vacio con la ruta obtenida. Se
     * establecen los atributos name, extension, path y tittle de la instancia de la clase vTab
     * recibida como parametro en base al archivo creado. Por ultimo se llama al metodo writeFile
     * para llenar con informacion el archivo.
     * @param frame Frame padre sobre el cual se abrira el JFileChooser
     * @param vTab Instancia que porporciona los datos.
     * @return True si el proceso fue correcto, false de lo contrario
     * @throws IOException 
     */
    public boolean saveFile(VCompilerFrame frame, VTab vTab) throws IOException{
        String path = "";
        fileChooser = new JFileChooser();
        fileChooser.setApproveButtonText("Guardar");
        fileChooser.setDialogTitle("Guardar Archivo");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(MLG_FILTER);
        fileChooser.setSelectedFile(new File(vTab.getName() +"."+ vTab.getExtension()));       
        int selection = fileChooser.showOpenDialog(frame);   
        if(selection == JFileChooser.APPROVE_OPTION){
            //Crear archivo en blanco.
            path = fileChooser.getSelectedFile().getAbsolutePath();
            File file = filesDriver.createFile(path);
            //Establecer los atributos de la instancia vTab.
            vTab.setName(FilenameUtils.getBaseName(file.getName()));
            vTab.setExtension(FilenameUtils.getExtension(file.getName()));
            vTab.setPath(file.getPath());
            //Escribir dentro del archivo creado.
            filesDriver.writeFile(vTab.getPath(), vTab.getData());
            return true;
        }
        return false;
    }

}
