package Negocio;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Vector;

public class clsDiagrama implements Serializable
{
  private LinkedList<clsTabla> tablas           = new LinkedList<clsTabla>();
  private LinkedList<clsRelacion> relaciones    = new LinkedList<clsRelacion>();
  private Vector<String>  ids                   = new Vector<String>();
  
  public clsDiagrama()
  {}
  
  public LinkedList<clsTabla> getTablas()
  {
    return tablas;
  }

  public void setTablas(LinkedList<clsTabla> tablas)
  {
    this.tablas = tablas;
  }

  public LinkedList<clsRelacion> getRelacion()
  {
    return relaciones;
  }

  public void setConector(LinkedList<clsRelacion> relaciones)
  {
    this.relaciones = relaciones;
  }
  //  FIN SELECTORES
  public void addTabla(clsTabla objclase)
  {
    tablas.add(objclase);
  }
  
  public void delTabla(int index)
  {
    tablas.remove(index);
  }
  
  public void addRelacion(clsRelacion objconector)
  {
    relaciones.add(objconector);
  }
  
  public void delRelacion(int index)
  {
    relaciones.remove(index);
  }
  
  public void setIds(Vector<String> ids)
  {
    this.ids = ids;
  }
  
  public Vector<String> getIds()
  {
    return ids;
  }
  
  public void addIds(String id)
  {
    ids.add(id);
  }
  
  public void delIds(String id)
  {
    ids.remove(id);
  }
  
  public int generarId()
  {
    //  dim = ids.size(),
    int id = 1;
    while (ids.contains(new String(""+id)))
      id++;
    ids.add(""+id);
    return id;
  }
    
  public void addTabla(LinkedList<clsTabla> clase)
  {
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsTabla objclase = clase.get(i);
      //ids.add(""+objclase.getId());
      addTabla(objclase);
    }
  }
  
  public void addRelacion(LinkedList<clsRelacion> conector)
  {
    int dim = conector.size();
    for (int i = 0; i < dim; i++)
    {
      clsRelacion objconector = conector.get(i);
      //ids.add(""+objconector.getId());
      addRelacion(objconector);
    }
  }
  
  public void addIds(Vector<String> ids)
  {
    int dim = ids.size();
    for (int i = 0; i < dim; i++)
      this.ids.add(ids.get(i));
  }
  
  public boolean verificarTabla(String nombre)
  {
    int dim = tablas.size();
    for (int i = 0; i < dim; i++)
    {
      clsTabla objclase = tablas.get(i);
      if (objclase.getNombreTabla().equals(nombre))
        return true;
    }
    return false;
  }
}
