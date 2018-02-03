package Negocio;

import java.awt.Point;
import java.awt.Rectangle;

import java.io.Serializable;

import java.util.LinkedList;

public class clsTabla implements Serializable
{
  private int id;
  private String nombreTabla;
  private LinkedList<clsColumna> columnas = new LinkedList<clsColumna>();
    
  private Rectangle superior;

  public clsTabla()
  { 
    id = 0;
    nombreTabla = "";
    superior = new Rectangle();
  }
  
  public clsTabla(int id, String nombreTabla, Rectangle superior)
  {
    this.id             = id;
    this.superior       = superior;
  }
  
  public clsTabla(int id, String nombre, LinkedList<clsColumna> columnas, Rectangle superior)
  {
    this.id             = id;
    this.nombreTabla    = nombre;
    this.columnas      = columnas;
    this.superior = superior;
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
  }

  public Rectangle getSuperior()
  {
    return superior;
  }

  public void setSuperior(Rectangle superior)
  {
    this.superior = superior;
  }
  //  FIN SELECTORES
  
  public void addColumna(clsColumna obj)
  {
    columnas.add(obj);
  }
  
  public void delColumna(int index)
  {
    columnas.remove(index);
  }
  
  public String toString()
  {
    //return "classinterface="+classinterface+", acceso="+acceso+", nombre="+nombre+", po="+o+", pd="+d;
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
  }  
}
