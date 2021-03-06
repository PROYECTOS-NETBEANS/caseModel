package plantillas;

import Comun.Constantes;
import Negocio.clsColumna;
import Negocio.clsRelacion;
import Negocio.clsTabla;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static plantillas.sqlBase.SALTO_LINEA;
import propiedades.Resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class mysql extends sqlBase{
    private LinkedList<clsTabla> tablas;
    private LinkedList<clsRelacion> relaciones;
    private Resources resource;
    private PrintWriter outputFile;
    
    public mysql(LinkedList<clsRelacion> rels, LinkedList<clsTabla> tablas){
       super();
        try {
            this.tablas = tablas;
            this.relaciones = rels;
            this.resource = new Resources("mysql.properties");            
        } catch (IOException ex) {
            Logger.getLogger(postgresql.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    @Override
    public String createColumnas(LinkedList<clsColumna> columnas, String nombreTabla) {
        String m = "";
        int i = 1;
        
        String llaves = this.getLlavesPrimarias(columnas);
        
        for (clsColumna columna : columnas) {
            String data = "";
            if(columna.getTipo().equals(Constantes.CADENA)){
                data = columna.getNombre() + " " + resource.getValue(columna.getTipo()) + "(" + columna.getSize() + ")";
            }else{
                if(columna.getTipo().equals(Constantes.DECIMAL)){
                    data = columna.getNombre() + " " + resource.getValue(columna.getTipo()) + " (" + columna.getSize() + ", " + columna.getScala() + ")";
                }else{
                    data = columna.getNombre() + " " + resource.getValue(columna.getTipo());
                }
            }            
            
            if(columna.isPrimaryKey()){
                if(columna.getTipo().equals(Constantes.ENTERO))
                    m += " " + data + " NOT NULL AUTO_INCREMENT";
                else
                    m += " " + data + " NOT NULL ";
            }
            else
                m += " " + data;
            
            if(i < columnas.size())
                m += ", " + SALTO_LINEA;
            else
                m += " " + SALTO_LINEA;
            
            i+=1;
        }
        // CONSTRAINT `PK_Table3` PRIMARY KEY (`id2`,`id1`)
        if(llaves.length() > 0)
            m += ", CONSTRAINT PK_" + nombreTabla + " PRIMARY KEY ( "+ llaves + ")" + SALTO_LINEA;
        return m;
    }
    public String crearScript(){
        String m = "/* MYSQL generador de codigo para ingenieria de softrware I */" + this.SALTO_LINEA;
        for (clsTabla tabla : tablas) {
            m += this.createTable(tabla.getNombreTabla(), tabla.getColumnas());            
        }
        
        String tabla_destino = "";
        String tabla_origen  = "";
        
        for (clsRelacion relacion : relaciones) {
            tabla_destino = this.getNombreTabla(relacion.getDestino());
            tabla_origen = this.getNombreTabla(relacion.getOrigen());
            m += this.createLlaveExterna(tabla_destino, relacion.getCol_nombre_destino(), tabla_origen, relacion.getCol_nombre_origen());            
        }        

        return m;
    }
    /**
     * busca el nombre de la tabla a partir de su id de tabla
     * @param id de la tabla
     * @return 
     */
    private String getNombreTabla(int idTabla){
        String nombre = "";
        for (clsTabla tabla : tablas) {
            if(tabla.getId() == idTabla){
                nombre = tabla.getNombreTabla();
            }
        }
        
        return nombre;
    }
    private String getLlavesPrimarias(LinkedList<clsColumna> columnas){
        String m = "";
        for (clsColumna columna : columnas) {
            if(columna.isPrimaryKey()){
                m += " " + columna.getNombre() + ",";
            }
        }
        String ms = "";
        if(m.length() > 0){
            ms = m.substring(0, m.length()-1);
            System.out.println("llaves : " + ms);
        }
            
        return ms;
    }
    
    public void guardarFile(String sql, File a){
        try{ 
            outputFile = new PrintWriter(new FileWriter(a));
            outputFile.println(sql);
            outputFile.close();
        }
        catch(IOException e){ 
            System.out.println("ERROR");  
        }
    }
}
