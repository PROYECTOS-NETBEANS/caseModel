package Comun;


import Negocio.*;

import java.awt.Point;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface interfaceCliente extends Remote
{
  public void enviarMensajeChat(String mensajechat) throws RemoteException;
  
  public void enviarDiagrama(String nombre_usuario, clsDiagrama objds) throws RemoteException;
  
  public void actualizarListaUsuario(Vector usuarios) throws RemoteException;
  
  public void actualizarDiagrama(clsDiagrama objds) throws RemoteException;
  
  public void enviarTabla(clsDiagrama objds, clsTabla objclase) throws RemoteException;
  
  public void enviarRelacion(clsDiagrama objds, clsRelacion objconector) throws RemoteException;
    
  public void actualizarTabla(clsTabla objclase) throws RemoteException;
  
  public void actualizarRelacion(clsRelacion objconector) throws RemoteException;
  
  public void enviarMensajeError(String mensaje) throws RemoteException;
    
  public void enviarColumna(clsDiagrama objds, clsTabla objclase) throws RemoteException;
    
}
