package Comun;


import Negocio.*;

import java.awt.Point;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import javax.swing.event.EventListenerList;

public class implementacionCliente extends UnicastRemoteObject implements interfaceCliente
{
  private transient EventListenerList listaevento = new EventListenerList();
  //private String mensajechat = "";
  
  public implementacionCliente() throws RemoteException
  { super();  }
  
  public void enviarMensajeChat(String mensajechat) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setMensajeChat(mensajechat);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirMensajeChat(objevento);
    }
  }
  
  public void enviarDiagramaSecuencia(String nombre_usuario, clsDiagramaSecuencia objds, boolean updatearbol) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjds(objds);
    objevento.setUpdatearbol(updatearbol);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirDiagramaSecuencia(objevento);
    }
  }

  public void actualizarListaUsuario(Vector usuarios) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setListaUsuario(usuarios);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento) 
        ((interfaceEvento) array[i]).actualizarListaUsuario(objevento);
    }
  }
  
  public void actualizarDiagramaSecuencia(clsDiagramaSecuencia objds) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjds(objds);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirDiagramaSecuencia(objevento);
    }
  }
  
  
  
  
  public void enviarActor(clsDiagramaSecuencia objds, clsActor objactor) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjds(objds);
    objevento.setObjactor(objactor);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirActor(objevento);
    }
  }
  
  public void enviarClase(clsDiagramaSecuencia objds, clsClase objclase, boolean updatearbol) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjds(objds);
    objevento.setObjclase(objclase);
    objevento.setUpdatearbol(updatearbol);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirClase(objevento);
    }
  }
  
  public void enviarConector(clsDiagramaSecuencia objds, clsEnlace objconector) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjds(objds);
    objevento.setObjconector(objconector);
      for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirConector(objevento);
    }
  }
  
  public void actualizarActor(clsActor objactor) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjactor(objactor);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirActualizarActor(objevento);
    }
  }
  
  public void actualizarClase(clsClase objclase, boolean updatearbol) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjclase(objclase);
    objevento.setUpdatearbol(updatearbol);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirActualizarClase(objevento);
    }
  }
  
  public void actualizarConector(clsEnlace objconector) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjconector(objconector);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirActualizarConector(objevento);
    }
  }
  
  public void enviarMensajeError(String mensaje) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setMensajeerror(mensaje);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirMensajeError(objevento);
    }
  }
  
  
  
  public void enviarAtributo(clsDiagramaSecuencia objds, clsClase objclase, boolean updatearbol) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjds(objds);
    objevento.setObjclase(objclase);
    objevento.setUpdatearbol(updatearbol);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirAtributo(objevento);
    }
  }
  
  public void enviarMetodo(clsDiagramaSecuencia objds, clsClase objclase, boolean updatearbol) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjds(objds);
    objevento.setObjclase(objclase);
    objevento.setUpdatearbol(updatearbol);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirMetodo(objevento);
    }
  }
  
  /*public void actualizarPropiedadActor(actor objactor) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjactor(objactor);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirPropiedadActor(objevento);
    }
  }
  
  public void actualizarPropiedadClase(clase objclase) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjclase(objclase);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirPropiedadClase(objevento);
    }
  }
  
  public void actualizarPropiedadConector(conector objconector) throws RemoteException
  {
  }*/
  
  /*public void enviarNumero(Object n) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setN(n);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento) 
        ((interfaceEvento) array[i]).transferirN(objevento);
    }
  }*/
  
  /*public void actualizarImportar(diagramaSecuencia objds) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjds(objds);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        //((interfaceEvento) array[i]).transferirActualizarConector(objevento);
        ((interfaceEvento) array[i]).transferirActualizarImportar(objevento);
    }
  }*/
  
  public void adicionarEscuchador(interfaceEvento listener)
  { listaevento.add(interfaceEvento.class, listener); }
}