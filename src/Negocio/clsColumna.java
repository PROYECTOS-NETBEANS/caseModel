package Negocio;

import java.io.Serializable;

public class clsColumna implements Serializable
{
    private String nombre;
    private String tipo;
    private String size;
    private String scala;
    private boolean primaryKey;

    public clsColumna()
    {
      nombre = tipo = "";
    }

    public clsColumna(String nombre, String tipo, String size, String scala, boolean key)
    {
      this.nombre = nombre;
      this.tipo   = tipo;
      this.size   = size;
      this.scala  = scala;
      this.primaryKey = key;
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

    @Override
    public String toString()
    {
      return nombre;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getScala() {
        return scala;
    }

    public void setScala(String scala) {
        this.scala = scala;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }  
  
}
