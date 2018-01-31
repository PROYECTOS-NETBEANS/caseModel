package Negocio;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Vector;

public class clsDiagramaSecuencia implements Serializable
{
  private LinkedList<clsActor> actor       = new LinkedList<clsActor>();
  private LinkedList<clsClase> clase       = new LinkedList<clsClase>();
  private LinkedList<clsEnlace> conector = new LinkedList<clsEnlace>();
  private Vector<String>  ids           = new Vector<String>();
  
  public clsDiagramaSecuencia()
  {}
  
  /*public diagramaSecuencia(actor objactor, LinkedList<clase> clase, LinkedList<conector> conector)
  {
    this.objactor = objactor;
    this.clase    = clase;
    this.conector = conector;
  }*/

  //  SELECTORES
   public LinkedList<clsActor> getActor()
   {
     return actor;
   }

   public void setActor(LinkedList<clsActor> actor)
   {
     this.actor = actor;
   }

  public LinkedList<clsClase> getClase()
  {
    return clase;
  }

  public void setClase(LinkedList<clsClase> clase)
  {
    this.clase = clase;
  }

  public LinkedList<clsEnlace> getConector()
  {
    return conector;
  }

  public void setConector(LinkedList<clsEnlace> conector)
  {
    this.conector = conector;
  }
  //  FIN SELECTORES
  public void addActor(clsActor objactor)
  {
    actor.add(objactor);
  }
  
  public void delActor(int index)
  {
    actor.remove(index);
  }
  
  public void addClase(clsClase objclase)
  {
    clase.add(objclase);
  }
  
  public void delClase(int index)
  {
    clase.remove(index);
  }
  
  public void addConector(clsEnlace objconector)
  {
    conector.add(objconector);
  }
  
  public void delConector(int index)
  {
    conector.remove(index);
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
  
  public void addActor(LinkedList<clsActor> actor)
  {
    int dim = actor.size();
    for (int i = 0; i < dim; i++)
    {
      clsActor objactor = actor.get(i);
      //ids.add(""+objactor.getId());
      addActor(objactor);
    }
  }
  
  public void addClase(LinkedList<clsClase> clase)
  {
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsClase objclase = clase.get(i);
      //ids.add(""+objclase.getId());
      addClase(objclase);
    }
  }
  
  public void addConector(LinkedList<clsEnlace> conector)
  {
    int dim = conector.size();
    for (int i = 0; i < dim; i++)
    {
      clsEnlace objconector = conector.get(i);
      //ids.add(""+objconector.getId());
      addConector(objconector);
    }
  }
  
  public void addIds(Vector<String> ids)
  {
    int dim = ids.size();
    for (int i = 0; i < dim; i++)
      this.ids.add(ids.get(i));
  }
  
  public boolean verificarClase(String nombre)
  {
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsClase objclase = clase.get(i);
      if (objclase.getTipo() == 2 && objclase.getNombre().equals(nombre))
        return true;
    }
    return false;
  }
}
