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
  
  @Override
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
  
  @Override
  public void enviarDiagrama(String nombre_usuario, clsDiagrama objds) throws RemoteException
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

  @Override
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
  
  @Override
  public void actualizarDiagrama(clsDiagrama objds) throws RemoteException
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
  
  @Override
  public void enviarTabla(clsDiagrama objds, clsTabla objclase) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjds(objds);
    objevento.setObjclase(objclase);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirTabla(objevento);
    }
  }
  
  @Override
  public void enviarRelacion(clsDiagrama objds, clsRelacion objconector) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjds(objds);
    objevento.setObjconector(objconector);
      for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirRelacion(objevento);
    }
  }
  
  
  @Override
  public void actualizarTabla(clsTabla objclase) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjclase(objclase);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirActualizarTabla(objevento);
    }
  }
  
  @Override
  public void actualizarRelacion(clsRelacion objconector) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjconector(objconector);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirActualizarRelacion(objevento);
    }
  }
  
  @Override
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
  
  
  
  @Override
  public void enviarColumna(clsDiagrama objds, clsTabla objclase) throws RemoteException
  {
    EventListener array[] = listaevento.getListeners(interfaceEvento.class);
    transferirEvento objevento = new transferirEvento(this);
    objevento.setObjds(objds);
    objevento.setObjclase(objclase);
    for (int i = 0; i <= array.length - 1; i++)
    {
      if (array[i] instanceof interfaceEvento)
        ((interfaceEvento) array[i]).transferirColumna(objevento);
    }
  }
    
  public void adicionarEscuchador(interfaceEvento listener)
  { listaevento.add(interfaceEvento.class, listener); }
}