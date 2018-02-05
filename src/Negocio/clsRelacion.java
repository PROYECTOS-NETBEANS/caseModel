package Negocio;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import java.io.Serializable;

public class clsRelacion implements Serializable
{
  private int id;
  
  private int origen;
  private String col_nombre_origen;
  private String cardinalidad_origen;
  private Point d;
  
  private int destino;
  private String col_nombre_destino;
  private String cardinalidad_destino;
  private Point o;

  private Polygon poligono;
  
  public clsRelacion()
  {
    id = origen = destino = 0;
    o = null;
    d = null;
  }
  
  public clsRelacion(int id, int origen, int destino, Point o, Point d, String col_origen, String col_destino, String card_origen, String card_destino)
  {
    this.id                     = id;

    this.origen                 = origen;
    this.o                      = o;
    this.cardinalidad_origen    = card_origen;
    this.col_nombre_origen      = col_origen;
    
    this.destino                = destino;
    this.d                      = d;
    this.cardinalidad_destino   = card_destino;
    this.col_nombre_destino     = col_destino;
    
    calcularPoligono();
  }
  
  public Polygon getPoligono(){
      return this.poligono;
  }
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
  
  public void setPuntoO(Point o)
  {
    this.o = o;
    this.calcularPoligono();
  }
  
  public Point getPuntoO()
  { 
    return o;
  }
  
  public void setPuntoD(Point d){
    this.d = d;
    this.calcularPoligono();
  }
  private void calcularPoligono(){
      poligono = new Polygon();
      // es del origen
      poligono.addPoint(o.x-2, o.y-2); 
      poligono.addPoint(o.x+2, o.y+2);
      
      // es del destino
      poligono.addPoint(d.x+2, d.y+2);
      poligono.addPoint(d.x-2, d.y-2);
  }
  
  public Point getPuntoD()
  {
    return d;
  }
  //  FIN SELECTORES

    public String getCol_nombre_origen() {
        return col_nombre_origen;
    }

    public void setCol_nombre_origen(String col_nombre_origen) {
        this.col_nombre_origen = col_nombre_origen;
    }

    public String getCardinalidad_origen() {
        return cardinalidad_origen;
    }

    public void setCardinalidad_origen(String cardinalidad_origen) {
        this.cardinalidad_origen = cardinalidad_origen;
    }

    public String getCol_nombre_destino() {
        return col_nombre_destino;
    }

    public void setCol_nombre_destino(String col_nombre_destino) {
        this.col_nombre_destino = col_nombre_destino;
    }

    public String getCardinalidad_destino() {
        return cardinalidad_destino;
    }

    public void setCardinalidad_destino(String cardinalidad_destino) {
        this.cardinalidad_destino = cardinalidad_destino;
    }
  
  @Override
  public String toString()
  {
    return "id="+id+", origen="+origen+", destino="+destino+", o="+o+", d="+d;
  }
}
