package Comun;

import Negocio.*;
import java.util.*;

public class transferirEvento extends EventObject
{
  private Vector listausuario = null;
  private String mensajechat  = "", mensajeerror;
  
  private clsDiagrama objds = null;
  private Object figura = null;
  
  private clsTabla objtabla;
  private clsRelacion objconector;
    
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

  public clsDiagrama getObjds()
  {
    return objds;
  }

  public void setObjds(clsDiagrama objds)
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

  public clsTabla getObjclase()
  {
    return objtabla;
  }

  public void setObjclase(clsTabla objclase)
  {
    this.objtabla = objclase;
  }

  public clsRelacion getObjconector()
  {
    return objconector;
  }

  public void setObjconector(clsRelacion objconector)
  {
    this.objconector = objconector;
  }

  public String getMensajeerror()
  {
    return mensajeerror;
  }

  public void setMensajeerror(String mensajeerror)
  {
    this.mensajeerror = mensajeerror;
  }

}
