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
  
  public void enviarDiagramaSecuencia(String nombre_usuario, boolean updatearbol) throws RemoteException;
  
  //public void nuevoDiagramaSecuencia(String nombre_usuario) throws RemoteException;
  public void nuevoDiagramaSecuencia(String nombre_usuario) throws RemoteException;
  
  public void abrirDiagramaSecuencia(String nombre_usuario, clsDiagramaSecuencia aux) throws RemoteException;
  
  public void importarDiagramaSecuencia(String nombre_usuario, clsDiagramaSecuencia aux) throws RemoteException;
  
  public void actualizarDiagramaSecuencia(String nombre_usuario) throws RemoteException;
  
  //public void actualizarDiagramaSecuencia(diagramaSecuencia aux) throws RemoteException;
  
  public clsDiagramaSecuencia getObjds() throws RemoteException;
  
  //public void setObjds(diagramaSecuencia objds) throws RemoteException;
  
  public Object getFigura() throws RemoteException;
  
  public void addActor(clsActor objactor, String nombre_usuario) throws RemoteException;
  
  public void addClase(clsClase objclase, String nombre_usuario) throws RemoteException;
    
  public void addConector(clsEnlace objconector, String nombre_usuario) throws RemoteException;
  
  public void actualizarActor(String nombre_usuario, clsActor objactor) throws RemoteException;
  
  public void actualizarClase(String nombre_usuario, clsClase objclase, boolean updatearbol) throws RemoteException;
  
  public void actualizarConector(String nombre_usuario, clsEnlace objconector) throws RemoteException;
  
  public clsActor verificarActor(Point p) throws RemoteException;
  
  public clsClase verificarClase(Point p) throws RemoteException;
  
  public clsEnlace verificarConector(Point p) throws RemoteException;
  
  
  
  public void addAtributo(int id, clsAtributo objatributo, boolean updatearbol) throws RemoteException;
  
  public void delAtributo(int id, String nombre_atributo, boolean updatearbol) throws RemoteException;
  
  public void addMetodo(int id, clsMetodo objmetodo, boolean updatearbol) throws RemoteException;
  
  public void delMetodo(int id, clsMetodo objmetodo, boolean updatearbol) throws RemoteException;
  
  
  public void eliminarActor(int id) throws RemoteException;
  
  public void eliminarClase(int id, boolean updatearbol) throws RemoteException;
  
  public void eliminarEnlace(int id) throws RemoteException;
}