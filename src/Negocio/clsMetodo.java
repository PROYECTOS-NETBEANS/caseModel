package Negocio;

import java.io.Serializable;

import java.util.LinkedList;

public class clsMetodo implements Serializable
{
  private int secuencia;
  private String nombre;
  private String retorna;
  private String acceso;
  private LinkedList<clsParametro> parametros = new LinkedList<clsParametro>();
  
  /*private boolean add = false;
  private boolean del = false;*/
  
  public clsMetodo()
  {
    secuencia = 0;
    nombre = "?";
    retorna = "";
    acceso = "public";
  }
  
  public clsMetodo(String nombre, String retorna, String acceso)
  {
    secuencia = 0;
    this.nombre = nombre;
    this.retorna= retorna;
    this.acceso = acceso;
  }

  //  SELECTORES
  public int getSecuencia()
  {
    return secuencia;
  }
  
  public void setSecuencia(int secuencia)
  {
    this.secuencia = secuencia;
  }
   
  public String getNombre()
  {
    return nombre;
  }

  public void setNombre(String nombre)
  {
    this.nombre = nombre;
  }

  public String getRetorna()
  {
    return retorna;
  }

  public void setRetorna(String retorna)
  {
    this.retorna = retorna;
  }

  public String getAcceso()
  {
    return acceso;
  }

  public void setAcceso(String acceso)
  {
    this.acceso = acceso;
  }

  public LinkedList<clsParametro> getParametros()
  {
    return parametros;
  }

  public void setParametros(LinkedList <clsParametro>parametros)
  {
    this.parametros = parametros;
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
  
  public void addParametro(clsParametro objparametro)
  {
    parametros.add(objparametro);
  }
  
  public void delParametro(clsParametro objparametro)
  {
    parametros.remove(objparametro);
  }
  
  public String toString()
  {
    //System.out.println();
    //return "acceso="+acceso+", nombre="+nombre+", retorno="+retorna;
    return nombre;
  }
  
  //  CLASE ANIDADA
  /*protected class parametros
  {
    private String nombre;
    private String tipo;
    
    public parametros()
    { nombre = tipo = ""; }
    
    public parametros(String nombre, String tipo)
    { 
      this.nombre = nombre; 
      this.tipo = tipo; 
    }

    public String getNombre()
    { return nombre;  }

    public void setNombre(String nombre)
    { this.nombre = nombre; }

    public String getTipo()
    { return tipo;  }

    public void setTipo(String tipo)
    { this.tipo = tipo; }
  }*/
}