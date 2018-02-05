package Negocio;

import Comun.Constantes;
import java.awt.Point;
import java.awt.Rectangle;

import java.io.Serializable;

import java.util.LinkedList;

public class clsTabla implements Serializable
{
    private int id;
    private String nombreTabla;
    private LinkedList<clsColumna> columnas = new LinkedList<>();

    private Rectangle superior;
    private int anchura_defecto = 0;
    private int altura_defecto = 0;
    public clsTabla()
    { 
      id = 0;
      nombreTabla = "";
      superior = new Rectangle();
    }
  
    public clsTabla(int id, String nombreTabla, Rectangle superior)
    {
      this.id             = id;    
      this.nombreTabla    = nombreTabla;
      this.columnas       = new LinkedList<>();
      this.superior       = superior;
      this.altura_defecto = superior.height;
      this.anchura_defecto = superior.width;
      this.calcularRectanguloColumna();
    }
  
    public clsTabla(int id, String nombre, LinkedList<clsColumna> columnas, Rectangle superior)
    {
      this.id             = id;
      this.nombreTabla    = nombre;
      this.columnas      = columnas;
      this.superior = superior;
      this.altura_defecto = superior.height;
      this.anchura_defecto = superior.width;
      
      this.calcularRectanguloColumna();
    }
    //  SELECTORES
    public int getId()
    { 
      return id;
    }

    public void setId(int id)
    {
      this.id = id;
    }

    public String getNombreTabla()
    {
      return nombreTabla;
    }

    public void setNombreTabla(String nombre)
    {
      this.nombreTabla = nombre;
    }

    public LinkedList<clsColumna> getColumnas()
    {
      return this.columnas;
    }

    public void setColumnas(LinkedList<clsColumna> columnas)
    {
        this.columnas = columnas;
        calcularRectanguloColumna();
    }

    public Rectangle getSuperior()
    {
      return superior;
    }

    public void setSuperior(Rectangle superior)
    {
      this.superior = superior;
      this.calcularRectanguloColumna();
    }
    //  FIN SELECTORES

    public void addColumna(clsColumna obj)
    {
      columnas.add(obj);
      this.calcularRectanguloColumna();
    }

    public void delColumna(int index)
    {
      columnas.remove(index);
      calcularRectanguloColumna();
    }

    public String toString()
    {
      return nombreTabla;
    }
  
    public void eliminarColumna(String nombre_atributo)
    {
      int dim = columnas.size();
      for (int i = 0; i < dim; i++)
      {
        clsColumna objatributo = columnas.get(i);
        if (objatributo.getNombre().equals(nombre_atributo))
        {
          delColumna(i);
          break;
        }
      }
      this.calcularRectanguloColumna();
    }
    /**
     * Metodo que 
     */
    private void calcularRectanguloColumna(){
        // calcular el ancho en base al numero de caracteres que tenga la mas grande palabra
        
        int i = 1;
        int may = this.nombreTabla.length();
        for (clsColumna columna : columnas) {
            if((columna.getNombre() + ": " + columna.getTipo()).length() > may ){
                may = (columna.getNombre() + ": " + columna.getTipo()).length();                
            }
            i+=1;
        }
        
        // anchura
        if(((may * 6)+5) > this.anchura_defecto){
            this.superior.width = (may * 6)+5;
        }else{
            this.superior.width = this.anchura_defecto;
        }
        
        System.out.println("altura ant :" + String.valueOf(superior.height) + ", new : " + String.valueOf(i * 5) + "defecto : " + String.valueOf(this.altura_defecto));
        // altura
        if((i * Constantes.TAMAÑO_LETRA) > this.altura_defecto){
            this.superior.height = (i * Constantes.TAMAÑO_LETRA) + 5;
        }else{
            this.superior.height = this.altura_defecto;
        }
            
    }
}
