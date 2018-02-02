package Comun;

import Negocio.*;

import java.awt.Point;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfaceServidor extends Remote
{
  public String conectar(String nombre_usuario, interfaceCliente objcliente) throws RemoteException;
  
  public String desconectar(String nombre_usuario) throws RemoteException;
  
  public void enviarMensajeChat(String nombre_usuario, String mensajechat) throws RemoteException;
  
  public void enviarDiagrama(String nombre_usuario) throws RemoteException;
  
  public void nuevoDiagrama(String nombre_usuario) throws RemoteException;
  
  public void abrirDiagrama(String nombre_usuario, clsDiagrama aux) throws RemoteException;
  
  public void importarDiagrama(String nombre_usuario, clsDiagrama aux) throws RemoteException;
  
  public void actualizarDiagrama(String nombre_usuario) throws RemoteException;
  
  //public void actualizarDiagramaSecuencia(diagramaSecuencia aux) throws RemoteException;
  
  public clsDiagrama getObjds() throws RemoteException;
  
  //public void setObjds(diagramaSecuencia objds) throws RemoteException;
  
  public Object getFigura() throws RemoteException;
    
  public void addTabla(clsTabla objclase, String nombre_usuario) throws RemoteException;
    
  public void addRelacion(clsRelacion objconector, String nombre_usuario) throws RemoteException;
  
  public void actualizarTabla(String nombre_usuario, clsTabla objclase) throws RemoteException;
  
  public void actualizarRelacion(String nombre_usuario, clsRelacion objconector) throws RemoteException;
  
  public clsTabla verificarTabla(Point p) throws RemoteException;
  
  public clsRelacion verificarRelacion(Point p) throws RemoteException;
  
  public void addColumna(int id, clsColumna objatributo) throws RemoteException;
  
  public void delColumna(int id, String nombre_atributo) throws RemoteException;  
  

  public void eliminarTabla(int id) throws RemoteException;
  
  public void eliminarRelacion(int id) throws RemoteException;
}