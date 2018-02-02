package Negocio;

import java.awt.Point;
import java.awt.Rectangle;

import java.io.Serializable;

public class clsRelacion implements Serializable
{
  //private 
  private int id;
  private int origen;       //  SI ES UN ACTOR O UNA CLASE (id)
  private int destino;      //  ES UNA CLASE (id)
  
  private Rectangle enlace;
  private Point o, d;

  public clsRelacion()
  {
    id = origen = destino = 0;    
    enlace = new Rectangle();
    o = new Point();
    d = new Point();
  }
  
  public clsRelacion(int id, int origen, int destino, Point o, Point d)
  {
    this.id         = id;
    this.origen     = origen;
    this.destino    = destino;
    //this.enlace     = enlace;
    /*o = new Point(this.enlace.x, this.enlace.y + (this.enlace.height/2));
    d = new Point(this.enlace.x + this.enlace.width, this.enlace.y + (this.enlace.height/2));
    if (this.enlace.width < 0)
      this.enlace = new Rectangle(d.x, this.enlace.y, Math.abs(this.enlace.width), 8);*/
    this.o = o;
    this.d = d;
    if (o.x < d.x)
      enlace = new Rectangle(o.x, o.y-5, d.x-o.x, 10);
    else
      enlace = new Rectangle(d.x, o.y-5, o.x-d.x, 10);
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
   
  public int getOrigen()
  {
    return origen;
  }

  public void setOrigen(int origen)
  {
    this.origen = origen;
  }

  public int getDestino()
  {
    return destino;
  }

  public void setDestino(int destino)
  {
    this.destino = destino;
  }

  public Rectangle getEnlace()
  {
    return enlace;
  }

  public void setEnlace(Rectangle enlace)
  {
    this.enlace = enlace;
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
  
  public String toString()
  {
    return "id="+id+", origen="+origen+", destino="+destino+", rectangle="+enlace+", o="+o+", d="+d;
  }
}
