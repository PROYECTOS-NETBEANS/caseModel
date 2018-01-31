package Negocio;

import java.awt.Point;
import java.awt.Rectangle;

import java.io.Serializable;

import java.util.LinkedList;

public class clsClase implements Serializable
{
  private int id;
  private String classinterface;
  private String nombre;
  private String acceso;
  private LinkedList<clsAtributo> atributos = new LinkedList<clsAtributo>();
  private LinkedList<clsMetodo> metodos = new LinkedList<clsMetodo>();
  
  private int tipo; //  1 => arbol-panel, 2 => panel-dibujo, 3 => 
  
  /*private int sx, sy, sw, sh;
  private int ix, iy, iw, ih;*/
  private Rectangle superior;
  private Rectangle inferior;
  private Point o, d;

  public clsClase()
  { 
    id = 0;
    tipo = 1;
    classinterface = nombre = acceso = "";
    o = new Point();
    d = new Point();
    superior = new Rectangle();
    inferior = new Rectangle();
  }
  
  public clsClase(int id, int tipo, String classinterface, String nombre, String acceso, Rectangle superior, Rectangle inferior)
  {
    this.id             = id;
    this.tipo           = tipo;
    this.classinterface = classinterface;
    this.nombre         = nombre;
    this.acceso         = acceso;
    this.superior       = superior;
    this.inferior       = inferior;
    o = new Point(inferior.x + (inferior.width/2), inferior.y);
    d = new Point(inferior.x + (inferior.width/2), inferior.y + inferior.height);
  }
  
  public clsClase(int id, String classinterface, String nombre, String acceso, LinkedList<clsMetodo> metodos, LinkedList<clsAtributo> atributos, Rectangle superior, Rectangle inferior)
  {
    this.id             = id;
    this.classinterface = classinterface;
    this.nombre         = nombre;
    this.acceso         = acceso;
    this.metodos        = metodos;
    this.atributos      = atributos;
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
  
  public String getClassinterface()
  {
    return classinterface;
  }

  public void setClassinterface(String classinterface)
  {
    this.classinterface = classinterface;
  }
  
  public String getNombre()
  {
    return nombre;
  }

  public void setNombre(String nombre)
  {
    this.nombre = nombre;
  }

  public String getAcceso()
  {
    return acceso;
  }

  public void setAcceso(String acceso)
  {
    this.acceso = acceso;
  }

  public LinkedList<clsMetodo> getMetodos()
  {
    return metodos;
  }

  public void setMetodos(LinkedList<clsMetodo> metodos)
  {
    this.metodos = metodos;
  }

  public LinkedList<clsAtributo> getAtributos()
  {
    return atributos;
  }

  public void setAtributos(LinkedList<clsAtributo> atributos)
  {
    this.atributos = atributos;
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
  
  public void addMetodo(clsMetodo objmetodo)
  {
    metodos.add(objmetodo);
  }
  
  public void delMetodo(int index)
  {
    metodos.remove(index);
  }
  
  public void addAtributo(clsAtributo objatributo)
  {
    atributos.add(objatributo);
  }
  
  public void delAtributo(int index)
  {
    atributos.remove(index);
  }
  
  public String toString()
  {
    //return "classinterface="+classinterface+", acceso="+acceso+", nombre="+nombre+", po="+o+", pd="+d;
    return nombre;
  }

  public int getTipo()
  {
    return tipo;
  }

  public void setTipo(int tipo)
  {
    this.tipo = tipo;
  }
  
  public void eliminarAtributo(String nombre_atributo)
  {
    int dim = atributos.size();
    for (int i = 0; i < dim; i++)
    {
      clsAtributo objatributo = atributos.get(i);
      if (objatributo.getNombre().equals(nombre_atributo))
      {
        delAtributo(i);
        break;
      }
    }
  }
  
  public void eliminarMetodo(String nombre_metodo)
  {
    int dim = metodos.size();
    for (int i = 0; i < dim; i++)
    {
      clsAtributo objatributo = atributos.get(i);
      if (objatributo.getNombre().equals(nombre_metodo))
      {
        delMetodo(i);
        break;
      }
    }
  }
}
