package Comun;


import Negocio.*;

import java.awt.Point;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface interfaceCliente extends Remote
{
  public void enviarMensajeChat(String mensajechat) throws RemoteException;
  
  public void enviarDiagramaSecuencia(String nombre_usuario, clsDiagramaSecuencia objds, boolean updatearbol) throws RemoteException;
  
  public void actualizarListaUsuario(Vector usuarios) throws RemoteException;
  
  public void actualizarDiagramaSecuencia(clsDiagramaSecuencia objds) throws RemoteException;
  
  public void enviarActor(clsDiagramaSecuencia objds, clsActor objactor) throws RemoteException;
  
  public void enviarClase(clsDiagramaSecuencia objds, clsClase objclase, boolean updatearbol) throws RemoteException;
  
  public void enviarConector(clsDiagramaSecuencia objds, clsEnlace objconector) throws RemoteException;
  
  public void actualizarActor(clsActor objactor) throws RemoteException;
  
  public void actualizarClase(clsClase objclase, boolean updatearbol) throws RemoteException;
  
  public void actualizarConector(clsEnlace objconector) throws RemoteException;
  
  public void enviarMensajeError(String mensaje) throws RemoteException;
  
  
  
  public void enviarAtributo(clsDiagramaSecuencia objds, clsClase objclase, boolean updatearbol) throws RemoteException;
  
  public void enviarMetodo(clsDiagramaSecuencia objds, clsClase objclase, boolean updatearbol) throws RemoteException;
  
  /*public void setMensajeTurno(String mensajechat) throws RemoteException;
  
  public String getMensajeTurno() throws RemoteException;*/
}
