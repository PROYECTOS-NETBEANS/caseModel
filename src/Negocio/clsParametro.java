package Negocio;

import java.io.Serializable;

public class clsParametro implements Serializable
{
  private String nombre;
  private String tipo;
  
  public clsParametro()
  { nombre = tipo = ""; }
  
  public clsParametro(String nombre, String tipo)
  { 
    this.nombre = nombre; 
    this.tipo = tipo; 
  }

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
  
  public String toString()
  {
    return "tipo="+tipo+", nombre="+nombre;
  }
}
