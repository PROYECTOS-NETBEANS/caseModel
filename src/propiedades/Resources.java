/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propiedades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Resources {
    private  Properties propiedad;
    
    public Resources(String nombre_archivo) throws IOException{
        try {
            propiedad = new Properties();
            
            URL ruta = Resources.class.getResource(nombre_archivo);
            System.out.println("file path : " + ruta.getPath());

            propiedad.load(new FileInputStream(ruta.getPath()));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public String getValue(String key){
        return propiedad.getProperty(key);
    }
}
