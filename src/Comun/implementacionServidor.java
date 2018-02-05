package Comun;

import Negocio.*;

import java.awt.Point;

import java.awt.Rectangle;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class implementacionServidor extends UnicastRemoteObject implements interfaceServidor
{
  public Registry registro;
  private final transient Hashtable usuarios = new Hashtable();
  private final Vector nombre_clase = new Vector();
  
  
  private clsDiagrama objds = null;
  
  public implementacionServidor() throws RemoteException{ 
      super();
  }
  
  public void iniciarServidor()
  {
    try 
    {
      registro = LocateRegistry.createRegistry(Constantes.PORT);
      registro.bind(Constantes.NAME, this);
      System.out.println("============== Servidor Iniciado ==============");
      objds = new clsDiagrama();
    }
    catch (RemoteException ex){
        ex.printStackTrace(); 
    }catch (AlreadyBoundException ex){
        Logger.getLogger(implementacionServidor.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @Override
  public String conectar(String nombre_usuario, interfaceCliente objcliente) throws RemoteException
  {
    System.out.println("Nombre Usuario => "+nombre_usuario);
    if (usuarios.containsKey(nombre_usuario))
    {
      //System.out.println("NO ENTRAR: "+Mensaje.NOT);
      return Mensaje.NOT;
    }
    usuarios.put(nombre_usuario, objcliente);
    actualizarUsuarios();
    enviarMensajeChat(nombre_usuario+" acaba de "+Mensaje.ON);
    //actualizarGraficaDiagramaSecuencia(objds, null);
    enviarDiagrama(nombre_usuario);
    return Mensaje.OK;
  }
    
  @Override
  public String desconectar(String nombre_usuario) throws RemoteException
  {
    if (!usuarios.containsKey(nombre_usuario))
      return Mensaje.NOT_USER;
    usuarios.remove(nombre_usuario);
    actualizarUsuarios();
    enviarMensajeChat(nombre_usuario+" acaba de "+Mensaje.OFF);
    /*if (usuarios.size() == 0)   //  SI EXISTE 0 USUARIOS => EL objds se pone en null
      objds = null;  */   
    return Mensaje.OFF;
  }
  
  @Override
  public void enviarMensajeChat(String nombre_usuario, String mensajechat) throws RemoteException
  { enviarMensajeChat(nombre_usuario+": "+mensajechat);  }
  
  @Override
  public void enviarDiagrama(String nombre_usuario) throws RemoteException
  {
    interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
    objcliente.enviarDiagrama(nombre_usuario, objds);
  }
  
  /*public void nuevoDiagramaSecuencia(String nombre_usuario) throws RemoteException
  {
    diagramaSecuencia aux = objds;
    objds = new diagramaSecuencia();
    LinkedList<clase> clase = aux.getClase();
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clase objclase = clase.get(i);
      if (objclase.getTipo() == 1)
        objds.addClase(objclase);
    }
    actualizarDiagramaSecuencia();
  }*/
  
  @Override
  public void nuevoDiagrama(String nombre_usuario) throws RemoteException
  {
    /*clsDiagrama aux = objds;
    objds = new clsDiagrama();
    LinkedList<clsClase> clase = aux.getClase();
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsClase objclase = clase.get(i);
      if (objclase.getTipo() == 1)
        objds.addClase(objclase);
    }*/
    objds = new clsDiagrama();
    actualizarDiagrama(nombre_usuario);
  }
  
  public void abrirDiagrama(String nombre_usuario, clsDiagrama aux) throws RemoteException
  {
    nuevoDiagrama(nombre_usuario);
    objds.addTabla(aux.getTablas());
    objds.addRelacion(aux.getRelacion());
    objds.addIds(aux.getIds());
    actualizarDiagrama(nombre_usuario);
  }
  
  @Override
  public void importarDiagrama(String nombre_usuario, clsDiagrama aux) throws RemoteException
  {
    objds.addTabla(aux.getTablas());
    objds.addRelacion(aux.getRelacion());
    objds.addIds(aux.getIds());
    actualizarDiagrama(nombre_usuario);
  }
  
  @Override
  public void actualizarDiagrama(String nombre_usuario) throws RemoteException
  {
    /*System.out.println("**************** TAMAÑOS ******************");
    System.out.println("Actor - SIZE => "+objds.getActor().size());
    System.out.println("Clase - SIZE => "+objds.getClase().size());
    System.out.println("Conector - SIZE => "+objds.getConector().size());*/
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = sKeys.nextElement().toString();
      enviarDiagrama(nombre);
      /*interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
      objcliente.enviarDiagramaSecuencia(nombre_usuario, objds);*/
    }
  }
    
  /*public void actualizarDiagramaSecuencia(diagramaSecuencia aux) throws RemoteException
  {
    objds.addActor(aux.getActor());
    objds.addClase(aux.getClase());
    objds.addConector(aux.getConector());
    objds.addIds(aux.getIds());
    actualizarDiagramaSecuencia();
  }*/
  
  public void enviarMensajeError(String nombre_usuario, String nombre)
  {
    interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
    try 
    { objcliente.enviarMensajeError("ya existe el objecto con el mismo nombre => "+nombre);  }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void addTabla(clsTabla objclase, String nombre_usuario) throws RemoteException
  {
    boolean error = false;
    
    System.out.println("de la paleta insertando...");
    Utils.verTabla(objclase);
    
    int id = objds.generarId();
    objclase.setId(id);
    objclase.setNombreTabla(generarNombreTabla());
    objds.addTabla(objclase);
    Utils.verTabla(objclase);
    //clsTabla aux = new clsTabla(id, objclase.getNombreTabla(), objclase.getAcceso(), objclase.getSuperior(), objclase.getInferior());
    System.out.println("qqqqqqqqqqqq...");
    //objds.addTabla(aux);
    System.out.println("88888...");    

    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      if (nombre.equals(nombre_usuario))
        enviarTabla(nombre_usuario, objclase);
      else
        enviarDiagrama(nombre);
    }
  }
  
  public void enviarTabla(String nombre_usuario, clsTabla objclase)
  {
    interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
    try{ 
        objcliente.enviarTabla(objds, objclase);  
    }
    catch (RemoteException ex) 
    { ex.printStackTrace(); }
  }
  
    public void enviarConector(String nombre_usuario, clsRelacion objconector)
    {
        interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
        try {
            System.out.println("enviando relacion al cliente");
            objcliente.enviarRelacion(objds, objconector);
        }
        catch (RemoteException ex){ 
            ex.printStackTrace(); 
        }
    }
  
    @Override
    public void addRelacion(clsRelacion objconector, String nombre_usuario) throws RemoteException
    {
        System.out.println("llegue relacion del cliente col_org : " + objconector.getCol_nombre_origen());
        objconector.setId(objds.generarId());
        objds.addRelacion(objconector);

        Enumeration sKeys = usuarios.keys();
        while (sKeys.hasMoreElements())
        {
          String nombre = (String) sKeys.nextElement();
          if (nombre.equals(nombre_usuario))
            enviarConector(nombre_usuario, objconector);
          else
            enviarDiagrama(nombre);
        }
    }
    
  @Override
  public void actualizarTabla(String nombre_usuario, clsTabla objclase) throws RemoteException
  {
    LinkedList<clsTabla> clase = objds.getTablas();
    int dim = clase.size();
    clsTabla aux1 = null;
    for (int i = 0; i < dim; i++)
    {
      clsTabla aux2 = clase.get(i);
      //System.out.println("Metodo actualizarClase => tipo="+aux2.getTipo());
      if (objclase.getId() == aux2.getId())
      {
        actualizarTabla(aux2, objclase.getNombreTabla(), objclase.getColumnas(), objclase.getSuperior());
        aux1 = aux2;
        // tengo que actualizar los puntos de la relacion
        actualizarRelacion(aux1.getId(), aux1.getSuperior());
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements())
    {
      String nombre = (String) sKeys.nextElement();
      if (nombre.equals(nombre_usuario))
        enviarTabla(nombre, aux1);
      else
        enviarDiagrama(nombre);
    }
  }
  
  public void actualizarTabla(clsTabla obj, String nombre, LinkedList<clsColumna>a, Rectangle s)
  {
    obj.setNombreTabla(nombre);
    obj.setSuperior(s);
  }
  
    private void actualizarRelacion(int idTabla, Rectangle rec)
    {

        LinkedList<clsRelacion> relaciones = objds.getRelacion();
        
        for (int i = 0; i < relaciones.size(); i++){            
            clsRelacion rel = relaciones.get(i);
            
            if (idTabla == rel.getOrigen())
            {
                int newoX = rec.getLocation().x + (((int) rec.getWidth()/2));
                int newoY = rec.getLocation().y + (((int) rec.getHeight()/2));
                Point o = new Point(newoX, newoY);
                
                rel.setPuntoO(o);
                clsRelacion newr = new clsRelacion(rel.getId(), rel.getOrigen(), rel.getDestino(), rel.getPuntoO(), rel.getPuntoD(), 
                                    rel.getCol_nombre_origen(), rel.getCol_nombre_destino(), rel.getCardinalidad_origen(), rel.getCardinalidad_destino());
                objds.delRelacion(i);
                objds.addRelacion(newr);
            }
            else
            {
              if (idTabla == rel.getDestino())
              {
                int newdX = rec.getLocation().x + (((int) rec.getWidth()/2));
                int newdY = rec.getLocation().y + (((int) rec.getHeight()/2));
                Point d = new Point(newdX, newdY);

                rel.setPuntoD(d);
                clsRelacion newr = new clsRelacion(rel.getId(), rel.getOrigen(), rel.getDestino(), rel.getPuntoO(), rel.getPuntoD(), 
                                    rel.getCol_nombre_origen(), rel.getCol_nombre_destino(), rel.getCardinalidad_origen(), rel.getCardinalidad_destino());                
                objds.delRelacion(i);
                objds.addRelacion(newr);
              }
            }
        }
    }

    /**
     * metodo que llega desde el cliente para actualizar la relacion del servidor
     * @param nombre_usuario
     * @param objconector
     * @throws RemoteException 
     */
    @Override
    public void actualizarRelacion(String nombre_usuario, clsRelacion objconector) throws RemoteException
    {
        LinkedList<clsRelacion> conector = objds.getRelacion();
        int dim = conector.size();
        clsRelacion aux = null;
        for (int i = 0; i < dim; i++){
            aux = conector.get(i);
            if (objconector.getId() == aux.getId()){
                objds.delRelacion(i);
                objds.addRelacion(objconector);
                aux = objconector;
                break;
            }
        }
        Enumeration sKeys = usuarios.keys();
        while (sKeys.hasMoreElements()){
            String nombre = (String) sKeys.nextElement();
            if (nombre.equals(nombre_usuario))
                enviarConector(nombre, aux);
            else
                enviarDiagrama(nombre);
        }
    }
  @Override
  public clsTabla verificarTabla(Point p) throws RemoteException
  {
    LinkedList<clsTabla> clase = objds.getTablas();
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
        clsTabla objclase = clase.get(i);
        if (objclase.getSuperior().contains(p))
            return objclase;      
    }
    return null;
  }
  
  @Override
  public clsRelacion verificarRelacion(Point p) throws RemoteException
  {
    LinkedList<clsRelacion> conector = objds.getRelacion();
    int dim = conector.size();
    for (int i = 0; i < dim; i++)
    {
      clsRelacion objconector = conector.get(i);
      if (objconector.getPoligono().contains(p))
        return objconector;
    }
    return null;
  }
  
  private void actualizarUsuarios()
  {
    interfaceCliente objcliente = null;
    Enumeration userKeys = this.usuarios.keys();
    Vector<Object> usuarios = new Vector<Object>();
    
    while (userKeys.hasMoreElements())
    { usuarios.add(userKeys.nextElement()); }
    
    System.out.println("usuarios aqui llegan los nuevos");
    try
    {
      Enumeration sKeys = this.usuarios.keys();
      while (sKeys.hasMoreElements())
      {
        objcliente = (interfaceCliente) this.usuarios.get((String) sKeys.nextElement());
        objcliente.actualizarListaUsuario(usuarios);
      }
    }
    catch (Exception e)
    { e.printStackTrace();  }
  }
  
  private void enviarMensajeChat(String mensajechat)
  {
    interfaceCliente objcliente = null;
    try
    {
      Enumeration sKeys = usuarios.keys();
      while (sKeys.hasMoreElements()) 
      {
        objcliente = (interfaceCliente) usuarios.get((String) sKeys.nextElement());
        objcliente.enviarMensajeChat(mensajechat);
      }
    }
    catch (Exception e)
    { e.printStackTrace();  }
  }  
  @Override
  public clsDiagrama getObjds() throws RemoteException
  { return objds; }
  
  public void setObjds(clsDiagrama objds) throws RemoteException
  { this.objds = objds; }
   
  public String generarNombreTabla()
  {
    String nombre = "Tabla";
    int id = 1;
    while (nombre_clase.contains(nombre+id))
      id++;
    nombre_clase.add(nombre + Integer.toString(id));
    return nombre + Integer.toString(id);
  }
  
  //********************** PROPIEDADES *******************
  @Override
  public void addColumna(int id, clsColumna objatributo)  throws RemoteException
  {    
    LinkedList<clsTabla> clase = objds.getTablas();
    int dim = clase.size();
    clsTabla aux1 = null;
    for (int i = 0; i < dim; i++)
    {
      clsTabla aux2 = clase.get(i);
      if (id == aux2.getId())
      {
        aux2.addColumna(objatributo);
        aux1 = aux2;
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      System.out.println("llego columna y se envia la columna");
      enviarColumna(nombre, aux1);
    }
  }
  
  @Override
  public void delColumna(int id, String nombre_atributo) throws RemoteException
  {
    LinkedList<clsTabla> clase = objds.getTablas();
    int dim = clase.size();
    clsTabla aux1 = null;
    for (int i = 0; i < dim; i++)
    {
      clsTabla aux2 = clase.get(i);
      if (id == aux2.getId())
      {
        aux2.eliminarColumna(nombre_atributo);
        aux1 = aux2;
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      enviarColumna(nombre, aux1);
    }
  }
  
  public void enviarColumna(String nombre_usuario, clsTabla objtabla)
  {
    interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
    try 
    { 
        objcliente.enviarColumna(objds, objtabla); 
    }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
       
  @Override
  public void eliminarTabla(int id) throws RemoteException
  {
    LinkedList<clsTabla> clase = objds.getTablas();
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsTabla objclase = clase.get(i);
      if (id == objclase.getId())
      {
        eliminarTodosEnlace(id);
        objds.delTabla(i);
        break;
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      enviarDiagrama(nombre);
    }
  }
  
  @Override
  public void eliminarRelacion(int id) throws RemoteException
  {
    LinkedList<clsRelacion> enlace = objds.getRelacion();
    int dim = enlace.size();
    for (int i = 0; i < dim; i++)
    {
      clsRelacion objenlace = enlace.get(i);
      if (id == objenlace.getId())
      {
        objds.delRelacion(i);
        break;
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      enviarDiagrama(nombre);        
    }
  }
  
  private void eliminarTodosEnlace(int id)
  {
    LinkedList<clsRelacion> enlace = objds.getRelacion();
    int dim = enlace.size();
    //System.out.println("=========================> TAMAÑO: "+dim);
    for (int i = 0; i < dim; i++)
    {
      clsRelacion aux = enlace.get(i);
      //System.out.println(" "+id+"="+aux.getOrigen()+", "+id+"="+aux.getDestino());
      if (id == aux.getOrigen() || id == aux.getDestino())
      {
        objds.delRelacion(i);
        enlace =  objds.getRelacion();
        dim = enlace.size();
        i--;
      }
      /*else
      {
        if (id == aux.getDestino())
          objds.delConector(i);
      }*/
    }
  }
  
}