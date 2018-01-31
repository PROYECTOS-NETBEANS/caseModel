package Negocio;

import java.io.Serializable;

public class clsAtributo implements Serializable
{
  //private int id;
  private String nombre;
  private String tipo;
  private String acceso;
  
  /*private boolean add = false;
  private boolean del = false;*/
  
  public clsAtributo()
  {
    nombre = tipo = "";
    acceso = "private";
  }

  public clsAtributo(String nombre, String tipo, String acceso)
  {
    // this.id     = id;
    this.nombre = nombre;
    this.tipo   = tipo;
    this.acceso = acceso;
  }
  
  //  SELECTORES
  /*public int getId()
  { 
    return id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }*/
  
  public String getNombre()
  {
    return nombre;
  }

  public void setNombre(String nombre)
  {
    this.nombre = nombre;
  }

  public String getTipo()
  {
    return tipo;
  }

  public void setTipo(String tipo)
  {
    this.tipo = tipo;
  }

  public String getAcceso()
  {
    return acceso;
  }

  public void setAcceso(String acceso)
  {
    this.acceso = acceso;
  }
  
  /*public boolean getAdd()
  {
    return add;
  }

  public void setAdd(boolean add)
  {
    this.add = add;
  }
  
  public boolean getDel()
  {
    return del;
  }

  public void setDel(boolean del)
  {
    this.del = del;
  }*/
  //  FIN SELECTORES
  
  public String toString()
  {
    //return "acceso="+acceso+", tipo="+tipo+", nombre="+nombre;
    return nombre;
  }
}
