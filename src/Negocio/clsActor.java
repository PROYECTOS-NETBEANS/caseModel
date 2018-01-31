package Negocio;

import java.awt.Point;
import java.awt.Rectangle;

import java.io.Serializable;

public class clsActor implements Serializable
{
  private int id;
  private String nombre;
  
  private Rectangle superior;
  private Rectangle inferior;
  
  private Point o, d;

  public clsActor()
  {
    id = 0;
    nombre = "";
    superior = new Rectangle();
    inferior = new Rectangle();
    o = new Point();
    d = new Point();
  }
  
  public clsActor(int id, String nombre, Rectangle superior, Rectangle inferior)
  {
    this.id       = id;
    this.nombre   = nombre;
    this.superior = superior;
    this.inferior = inferior;
    o = new Point(inferior.x + (inferior.width/2), inferior.y);
    d = new Point(inferior.x + (inferior.width/2), inferior.y + inferior.height);
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

  public String getNombre()
  {
    return nombre;
  }

  public void setNombre(String nombre)
  {
    this.nombre = nombre;
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
}