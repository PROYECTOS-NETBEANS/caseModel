package Comun;

import Negocio.*;
import java.awt.event.MouseEvent;
import java.util.*;

public class transferirEvento extends EventObject
{
  private Vector listausuario = null;
  private String mensajechat  = "", mensajeerror;
  
  private clsDiagramaSecuencia objds = null;
  private Object figura = null;
  
  
  private clsActor objactor;
  private clsClase objclase;
  private clsEnlace objconector;
  
  private boolean updatearbol;
  //private actor antiguo, nuevo;
  
  public transferirEvento(Object source)
  { super(source);  }
  
  public Vector getListaUsuario()
  { return listausuario;  }

  public void setListaUsuario(Vector listausuario)
  { this.listausuario = listausuario; }
  
  public void setMensajeChat(String mensajechat)
  { this.mensajechat = mensajechat; }
  
  public String getMensajeChat()
  { return mensajechat; }

  public clsDiagramaSecuencia getObjds()
  {
    return objds;
  }

  public void setObjds(clsDiagramaSecuencia objds)
  {
    this.objds = objds;
  }

  public Object getFigura()
  {
    return figura;
  }

  public void setFigura(Object figura)
  {
    this.figura = figura;
  }

  public clsActor getObjactor()
  {
    return objactor;
  }

  public void setObjactor(clsActor objactor)
  {
    this.objactor = objactor;
  }

  public clsClase getObjclase()
  {
    return objclase;
  }

  public void setObjclase(clsClase objclase)
  {
    this.objclase = objclase;
  }

  public clsEnlace getObjconector()
  {
    return objconector;
  }

  public void setObjconector(clsEnlace objconector)
  {
    this.objconector = objconector;
  }

  /*public actor getAntiguo()
  {
    return antiguo;
  }

  public void setAntiguo(actor antiguo)
  {
    this.antiguo = antiguo;
  }

  public actor getNuevo()
  {
    return nuevo;
  }

  public void setNuevo(actor nuevo)
  {
    this.nuevo = nuevo;
  }*

  public diagramaSecuencia getImportar()
  {
    return importar;
  }

  public void setImportar(diagramaSecuencia importar)
  {
    this.importar = importar;
  }*/

  public String getMensajeerror()
  {
    return mensajeerror;
  }

  public void setMensajeerror(String mensajeerror)
  {
    this.mensajeerror = mensajeerror;
  }

  public boolean getUpdatearbol()
  {
    return updatearbol;
  }

  public void setUpdatearbol(boolean updatearbol)
  {
    this.updatearbol = updatearbol;
  }
}
