/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantillas;

import Negocio.clsColumna;
import java.util.LinkedList;

/**
 *
 * @author usuario
 */
public abstract class sqlBase {
    
    public static final String SALTO_LINEA = " \n";
    
    public sqlBase(){
    
    }
    
    public abstract String createColumnas(LinkedList<clsColumna> columnas, String nombreTabla);
    
    public String  createTable(String tabla, LinkedList<clsColumna> columnas){
        String msg = eliminarTabla(tabla);
        msg += "CREATE TABLE " + tabla + SALTO_LINEA;
        msg += "( " + SALTO_LINEA;
        msg += createColumnas(columnas, tabla);
        msg += "); " + SALTO_LINEA;
        return msg;
    }
    
    public String createLlaveprimaria(String llaves, String tabla){
        if(llaves.length()<=0)
            return "";
        
        String m = " ALTER TABLE " + tabla + " ADD CONSTRAINT pk_" + tabla + " ";
        m += " PRIMARY KEY (" + llaves + ");" + SALTO_LINEA;
        
        return m;
    }
    
    public String createLlaveExterna(String tablaDestino, String colDestino, String tablaOrigen, String colOrigen){
        String m = "";
        m += " ALTER TABLE " + tablaOrigen + " ADD CONSTRAINT FK_" + tablaOrigen + "_" + colOrigen;
        m += " FOREIGN KEY ("+ colOrigen + ") REFERENCES " + tablaDestino + " (" + colDestino + ")  ON DELETE No Action ON UPDATE No Action " + SALTO_LINEA;
        return m;
    }
    private String eliminarTabla(String tabla){
        return "DROP TABLE IF EXISTS " + tabla + " CASCADE;" + SALTO_LINEA;
    }
}
