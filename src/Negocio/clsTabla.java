package Negocio;

import java.awt.Point;
import java.awt.Rectangle;

import java.io.Serializable;

import java.util.LinkedList;

public class clsTabla implements Serializable
{
  private int id;
  private String nombreTabla;
  private String acceso;
  private LinkedList<clsColumna> columnas = new LinkedList<clsColumna>();
    
  private Rectangle superior;
  private Point o, d;
  private Rectangle inferior;
  public clsTabla()
  { 
    id = 0;
    nombreTabla = acceso = "";
    o = new Point();
    d = new Point();
    superior = new Rectangle();
    inferior = new Rectangle();
  }
  
  public clsTabla(int id, String nombreTabla, String acceso, Rectangle superior, Rectangle inferior)
  {
    this.id             = id;
    this.acceso         = acceso;
    this.superior       = superior;
    o = new Point(inferior.x + (inferior.width/2), inferior.y);
    d = new Point(inferior.x + (inferior.width/2), inferior.y + inferior.height);
  }
  
  public clsTabla(int id, String classinterface, String nombre, String acceso, LinkedList<clsColumna> columnas, Rectangle superior, Rectangle inferior)
  {
    this.id             = id;
    this.nombreTabla    = nombre;
    this.acceso         = acceso;
    this.columnas      = columnas;
    this.superior = superior;
    this.inferior = inferior;
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

  public String getAcceso()
  {
    return acceso;
  }

  public void setAcceso(String acceso)
  {
    this.acceso = acceso;
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

  public Rectangle getInferior()
  {
    return inferior;
  }

  public void setInferior(Rectangle inferior)
  {
    this.inferior = inferior;
  }
  
  public void setPuntoO(Point o)
  {
    this.o = o;
  }
  
  public Point getPuntoO()
  { 
    return o;
  }
  
  public void setPuntoD(Point d)
  {
    this.d = d;
  }
  
  public Point getPuntoD()
  {
    return d;
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
