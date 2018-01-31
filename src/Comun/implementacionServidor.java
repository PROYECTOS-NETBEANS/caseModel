package Comun;

import Negocio.*;

import java.awt.Point;

import java.awt.Rectangle;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.util.*;
import javax.swing.JList;

public class implementacionServidor extends UnicastRemoteObject implements interfaceServidor
{
  //private int puerto  = 1099;
  public Registry registro;
  private int puerto  = 3333;
  private String ip = "127.0.0.1";
  private String name = "DIAGRAMA_SECUENCIA";
  private transient Hashtable usuarios = new Hashtable();
  private Vector nombre_clase = new Vector();
  private Vector nombre_actor = new Vector();
  
  
  private clsDiagramaSecuencia objds = null;//, importar = null;
  private Object figura = null;
  
  private JList listUsuario;
  
  public implementacionServidor() throws RemoteException
  { super();  }
  
  public void iniciarServidor()
  {
    try 
    {
      registro = LocateRegistry.createRegistry(puerto);
      registro.rebind(name, this);
      /*LocateRegistry.createRegistry(puerto);
      Naming.rebind(name, this);*/
      System.out.println("============== Servidor Iniciado ==============");
      objds = new clsDiagramaSecuencia();
    }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
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
    enviarDiagramaSecuencia(nombre_usuario, true);
    return Mensaje.OK;
  }
    
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
  
  public void enviarMensajeChat(String nombre_usuario, String mensajechat) throws RemoteException
  { enviarMensajeChat(nombre_usuario+": "+mensajechat);  }
  
  public void enviarDiagramaSecuencia(String nombre_usuario, boolean updatearbol) throws RemoteException
  {
    interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
    objcliente.enviarDiagramaSecuencia(nombre_usuario, objds, updatearbol);
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
  
  public void nuevoDiagramaSecuencia(String nombre_usuario) throws RemoteException
  {
    /*clsDiagramaSecuencia aux = objds;
    objds = new clsDiagramaSecuencia();
    LinkedList<clsClase> clase = aux.getClase();
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsClase objclase = clase.get(i);
      if (objclase.getTipo() == 1)
        objds.addClase(objclase);
    }*/
    objds = new clsDiagramaSecuencia();
    actualizarDiagramaSecuencia(nombre_usuario);
  }
  
  public void abrirDiagramaSecuencia(String nombre_usuario, clsDiagramaSecuencia aux) throws RemoteException
  {
    nuevoDiagramaSecuencia(nombre_usuario);
    objds.addActor(aux.getActor());
    objds.addClase(aux.getClase());
    objds.addConector(aux.getConector());
    objds.addIds(aux.getIds());
    actualizarDiagramaSecuencia(nombre_usuario);
  }
  
  public void importarDiagramaSecuencia(String nombre_usuario, clsDiagramaSecuencia aux) throws RemoteException
  {
    objds.addActor(aux.getActor());
    objds.addClase(aux.getClase());
    objds.addConector(aux.getConector());
    objds.addIds(aux.getIds());
    actualizarDiagramaSecuencia(nombre_usuario);
  }
  
  public void actualizarDiagramaSecuencia(String nombre_usuario) throws RemoteException
  {
    /*System.out.println("**************** TAMAÑOS ******************");
    System.out.println("Actor - SIZE => "+objds.getActor().size());
    System.out.println("Clase - SIZE => "+objds.getClase().size());
    System.out.println("Conector - SIZE => "+objds.getConector().size());*/
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = sKeys.nextElement().toString();
      enviarDiagramaSecuencia(nombre, true);
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
  
  public void addActor(clsActor objactor, String nombre_usuario) throws RemoteException
  {
    objactor.setId(objds.generarId());
    objactor.setNombre(generarNombreActor());
    objds.addActor(objactor);
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      if (nombre.equals(nombre_usuario))
        enviarActor(nombre, objactor);
      else
        enviarDiagramaSecuencia(nombre, false);        
    }
  }
  
  public void enviarActor(String nombre_usuario, clsActor objactor)
  {
    interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
    try 
    { objcliente.enviarActor(objds, objactor);  }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void enviarMensajeError(String nombre_usuario, String nombre)
  {
    interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
    try 
    { objcliente.enviarMensajeError("ya existe el objecto con el mismo nombre => "+nombre);  }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void addClase(clsClase objclase, String nombre_usuario) throws RemoteException
  {
    boolean error = false;
    if (objclase.getTipo() == 1)
    {
      System.out.println("del arbol insertando...");
      if (objds.verificarClase(objclase.getNombre()))
        error = true;
      else
      {
        int id = objds.generarId();
        LinkedList<clsClase> clase = objds.getClase();
        int dim = clase.size();
        System.out.println("***************** ANTES DE INSERTAR => "+dim);
        for (int i = 0; i < dim; i++)
        {
          clsClase aux = clase.get(i);
          if (aux.getTipo() == 1 && objclase.getNombre().equals(aux.getNombre()))
          {
            aux.setId(id);
            break;
          }
        }
        objclase.setId(id);
        objclase.setTipo(2);
        objds.addClase(objclase);
        System.out.println("***************** DESPUES DE INSERTAR => "+objds.getClase().size());
      }
    }
    else
    {
      System.out.println("de la paleta insertando...");
      int id = objds.generarId();
      objclase.setId(id);
      objclase.setNombre(generarNombreClase());
      objds.addClase(objclase);
      clsClase aux = new clsClase(id, 1, objclase.getClassinterface(), objclase.getNombre(), objclase.getAcceso(), objclase.getSuperior(), objclase.getInferior());
      //clase aux = new clase(id, 1, objclase.getClassinterface(), objclase.getNombre(), objclase.getAcceso(), null, null);
      objds.addClase(aux);
    }
    /*if (error)
      enviarMensajeError(nombre_usuario, objclase.getNombre());
    else
    {*/
      Enumeration sKeys = usuarios.keys();
      while (sKeys.hasMoreElements()) 
      {
        String nombre = (String) sKeys.nextElement();
        if (nombre.equals(nombre_usuario))
          enviarClase(nombre_usuario, objclase, true);
        else
          enviarDiagramaSecuencia(nombre, true);
      }
    //}
  }
  
  public void enviarClase(String nombre_usuario, clsClase objclase, boolean updatearbol)
  {
    interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
    try 
    { objcliente.enviarClase(objds, objclase, updatearbol);  }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void enviarConector(String nombre_usuario, clsEnlace objconector)
  {
    interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
    try 
    { objcliente.enviarConector(objds, objconector);  }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void addConector(clsEnlace objconector, String nombre_usuario) throws RemoteException
  {
    objconector.setId(objds.generarId());
    objds.addConector(objconector);
    
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements())
    {
      String nombre = (String) sKeys.nextElement();
      if (nombre.equals(nombre_usuario))
        enviarConector(nombre_usuario, objconector);
      else
        enviarDiagramaSecuencia(nombre, false);
    }
  }
  
  private void actualizarActor(clsActor objactor, String nombre, Rectangle s, Rectangle i, Point o, Point d)
  {
    objactor.setNombre(nombre);
    objactor.setSuperior(s);
    objactor.setInferior(i);
    objactor.setPuntoO(o);
    objactor.setPuntoD(d);
  }
  
  public void actualizarActor(String nombre_usuario, clsActor objactor) throws RemoteException
  {
    LinkedList<clsActor> actor = objds.getActor();
    int dim = actor.size();
    clsActor aux1 = null;
    for (int i = 0; i < dim; i++)
    {
      clsActor aux2 = actor.get(i);
      if (aux2.getId() == objactor.getId())
      {
        actualizarActor(aux2, objactor.getNombre(), objactor.getSuperior(), objactor.getInferior(), objactor.getPuntoO(), objactor.getPuntoD());
        aux1 = aux2;
        actualizarConector(aux2.getId(), aux2.getPuntoO(), aux2.getPuntoD());
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      if (nombre.equals(nombre_usuario))
        enviarActor(nombre_usuario, aux1);
      else
        enviarDiagramaSecuencia(nombre, false);        
    }
  }
  
  public void actualizarClase(String nombre_usuario, clsClase objclase, boolean updatearbol) throws RemoteException
  {
    LinkedList<clsClase> clase = objds.getClase();
    int dim = clase.size();
    clsClase aux1 = null;
    for (int i = 0; i < dim; i++)
    {
      clsClase aux2 = clase.get(i);
      //System.out.println("Metodo actualizarClase => tipo="+aux2.getTipo());
      if (aux2.getTipo() == 2 && objclase.getId() == aux2.getId())
      {
        actualizarClase(aux2, objclase.getClassinterface(), objclase.getNombre(), objclase.getAcceso(), objclase.getAtributos(), objclase.getMetodos(), objclase.getTipo(), objclase.getSuperior(), objclase.getInferior(), objclase.getPuntoO(), objclase.getPuntoD());
        aux1 = aux2;
        actualizarConector(aux2.getId(), aux2.getPuntoO(), aux2.getPuntoD());
      }
      else
      {
        if (aux2.getTipo() == 1 && objclase.getId() == aux2.getId())
          actualizarClase(aux2, objclase.getClassinterface(), objclase.getNombre(), objclase.getAcceso(), objclase.getAtributos(), objclase.getMetodos(), 1, null, null, null, null);
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements())
    {
      String nombre = (String) sKeys.nextElement();
      if (nombre.equals(nombre_usuario))
        enviarClase(nombre, aux1, updatearbol);
      else
        enviarDiagramaSecuencia(nombre, updatearbol);
    }
  }
  
  public void actualizarClase(clsClase obj, String estereo, String nombre, String acceso, LinkedList<clsAtributo>a, LinkedList<clsMetodo>m, int tipo, Rectangle s, Rectangle i, Point o, Point d)
  {
    obj.setClassinterface(estereo);
    obj.setNombre(nombre);
    obj.setAcceso(acceso);
    //  actualiza la lista de atributos, si existe el mismo atributo no se añade
    /*generarCodigo objcodigo = new generarCodigo();
    int dim = a.size();
    for (int index = 0; index < dim; index++)
    {
      atributos obja = a.get(index);
      sd
    }*/
        
    //  actualiza la lista de metodos, si existe el mismo metodo no se añade
    
    
    obj.setTipo(tipo);
    obj.setSuperior(s);
    obj.setInferior(i);
    obj.setPuntoO(o);
    obj.setPuntoD(d);
  }
  
  private void actualizarConector(int id, Point po, Point pd)
  {
    LinkedList<clsEnlace> conector = objds.getConector();
    int dim = conector.size();
    for (int i = 0; i < dim; i++)
    {
      clsEnlace aux = conector.get(i);
      if (id == aux.getOrigen())// || objclase.getId() == aux.getDestino())
      {
        objds.delConector(i);
        Point poco = aux.getPuntoO();
        Point pdco = aux.getPuntoD();
        clsEnlace objconector;
        objconector = new clsEnlace(aux.getId(), aux.getOrigen(), aux.getDestino(), aux.getObjmetodo(), new Point(po.x, poco.y), new Point(pdco.x, poco.y));
        objds.addConector(objconector);
      }
      else
      {
        if (id == aux.getDestino())
        {
          Point poco = aux.getPuntoO();
          //Point pdco = aux.getPuntoD();
          objds.delConector(i);
          clsEnlace objconector = new clsEnlace(aux.getId(), aux.getOrigen(), aux.getDestino(), aux.getObjmetodo(), new Point(poco.x, poco.y), new Point(pd.x, poco.y));
          objds.addConector(objconector);
        }
      }
    }
  }
  
  public void actualizarConector(String nombre_usuario, clsEnlace objconector) throws RemoteException
  {
    LinkedList<clsEnlace> conector = objds.getConector();
    int dim = conector.size();
    clsEnlace aux = null;
    for (int i = 0; i < dim; i++)
    {
      aux = conector.get(i);
      //  if (objconector.getOrigen() == aux.getId() || objconector.getDestino() == aux.getId())
      if (objconector.getId() == aux.getId())
      {
        objds.delConector(i);
        objds.addConector(objconector);
        aux = objconector;
        break;
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      if (nombre.equals(nombre_usuario))
        enviarConector(nombre, aux);
      else
        enviarDiagramaSecuencia(nombre, false);        
    }
  }
  
  public clsActor verificarActor(Point p) throws RemoteException
  {
    LinkedList<clsActor> actor = objds.getActor();
    int dim = actor.size();
    for (int i = 0; i < dim; i++)
    {
      clsActor objactor = actor.get(i);
      if (objactor.getSuperior().contains(p) || objactor.getInferior().contains(p))
        return objactor;
    }
    return null;
  }
  
  public clsClase verificarClase(Point p) throws RemoteException
  {
    LinkedList<clsClase> clase = objds.getClase();
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsClase objclase = clase.get(i);
      if (objclase.getTipo() == 2)
      {
        if (objclase.getSuperior().contains(p) || objclase.getInferior().contains(p))
          return objclase;
      }
    }
    return null;
  }
  
  public clsEnlace verificarConector(Point p) throws RemoteException
  {
    LinkedList<clsEnlace> conector = objds.getConector();
    int dim = conector.size();
    for (int i = 0; i < dim; i++)
    {
      clsEnlace objconector = conector.get(i);
      if (objconector.getEnlace().contains(p))
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
    
    listUsuario.setListData(usuarios);
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
  
  public void setJList(JList listUsuario)
  {
    this.listUsuario = listUsuario;
  }
  
  public clsDiagramaSecuencia getObjds() throws RemoteException
  { return objds; }
  
  public void setObjds(clsDiagramaSecuencia objds) throws RemoteException
  { this.objds = objds; }

  public Object getFigura() throws RemoteException
  { return figura;  }
  
  public String generarNombreActor()
  {
    String nombre = "Actor";
    int id = 1;
    while (nombre_actor.contains(nombre+id))
      id++;
    nombre_actor.add(nombre+id);
    return nombre+id;
  }
  
  public String generarNombreClase()
  {
    String nombre = "Object";
    int id = 1;
    while (nombre_clase.contains(nombre+id))
      id++;
    nombre_clase.add(nombre+id);
    return nombre+id;
  }
  
  //********************** PROPIEDADES *******************
  public void addAtributo(int id, clsAtributo objatributo, boolean updatearbol)  throws RemoteException
  {
    LinkedList<clsClase> clase = objds.getClase();
    int dim = clase.size();
    clsClase aux1 = null;
    for (int i = 0; i < dim; i++)
    {
      clsClase aux2 = clase.get(i);
      if (aux2.getTipo() == 2 && id == aux2.getId())
      {
        aux2.addAtributo(objatributo);
        aux1 = aux2;
      }
      else
      {
        if (aux2.getTipo() == 1 && id == aux2.getId())
          aux2.addAtributo(objatributo);
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      enviarAtributo(nombre, aux1, updatearbol);
    }
  }
  
  public void delAtributo(int id, String nombre_atributo, boolean updatearbol) throws RemoteException
  {
    LinkedList<clsClase> clase = objds.getClase();
    int dim = clase.size();
    clsClase aux1 = null;
    for (int i = 0; i < dim; i++)
    {
      clsClase aux2 = clase.get(i);
      if (aux2.getTipo() == 2 && id == aux2.getId())
      {
        aux2.eliminarAtributo(nombre_atributo);
        aux1 = aux2;
      }
      else
      {
        if (aux2.getTipo() == 1 && id == aux2.getId())
          aux2.eliminarAtributo(nombre_atributo);
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      enviarAtributo(nombre, aux1, updatearbol);
    }
  }
  
  public void enviarAtributo(String nombre_usuario, clsClase objclase, boolean updatearbol)
  {
    interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
    try 
    { objcliente.enviarAtributo(objds, objclase, updatearbol); }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void addMetodo(int id, clsMetodo objmetodo, boolean updatearbol) throws RemoteException
  {
    //generarCodigo objcodigo = new generarCodigo();
    LinkedList<clsClase> clase = objds.getClase();
    int dim = clase.size();
    clsClase aux1 = null;
    for (int i = 0; i < dim; i++)
    {
      clsClase aux2 = clase.get(i);
      if (aux2.getTipo() == 2 && id == aux2.getId())
      {
        aux2.addMetodo(objmetodo);
        aux1 = aux2;
      }
      else
      {
        if (aux2.getTipo() == 1 && id == aux2.getId())
          aux2.addMetodo(objmetodo);
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      enviarMetodo(nombre, aux1, updatearbol);
    }
  }
  
  public void delMetodo(int id, clsMetodo objmetodo, boolean updatearbol) throws RemoteException
  {
    LinkedList<clsClase> clase = objds.getClase();
    int dim = clase.size();
    clsClase aux1 = null;
    for (int i = 0; i < dim; i++)
    {
      clsClase aux2 = clase.get(i);
      if (aux2.getTipo() == 2 && id == aux2.getId())
      {
        aux2.eliminarMetodo(objmetodo.getNombre());
        aux1 = aux2;
      }
      else
      {
        if (aux2.getTipo() == 1 && id == aux2.getId())
          aux2.eliminarMetodo(objmetodo.getNombre());
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      enviarMetodo(nombre, aux1, updatearbol);
    }
  }
  
  public void enviarMetodo(String nombre_usuario, clsClase objclase, boolean updatearbol)
  {
    interfaceCliente objcliente = (interfaceCliente) usuarios.get(nombre_usuario);
    try 
    { objcliente.enviarMetodo(objds, objclase, updatearbol); }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  
  
  public void eliminarActor(int id) throws RemoteException
  {
    LinkedList<clsActor> actor = objds.getActor();
    int dim = actor.size();
    for (int i = 0; i < dim; i++)
    {
      clsActor objactor = actor.get(i);
      if (id == objactor.getId())
      {
        eliminarTodosEnlace(id);
        objds.delActor(i);
        break;
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      enviarDiagramaSecuencia(nombre, false);        
    }
  }
  
  public void eliminarClase(int id, boolean updatearbol) throws RemoteException
  {
    LinkedList<clsClase> clase = objds.getClase();
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsClase objclase = clase.get(i);
      if (id == objclase.getId() && objclase.getTipo() == 2)
      {
        eliminarTodosEnlace(id);
        objds.delClase(i);
        break;
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      enviarDiagramaSecuencia(nombre, false);
    }
  }
  
  public void eliminarEnlace(int id) throws RemoteException
  {
    LinkedList<clsEnlace> enlace = objds.getConector();
    int dim = enlace.size();
    for (int i = 0; i < dim; i++)
    {
      clsEnlace objenlace = enlace.get(i);
      if (id == objenlace.getId())
      {
        objds.delConector(i);
        break;
      }
    }
    Enumeration sKeys = usuarios.keys();
    while (sKeys.hasMoreElements()) 
    {
      String nombre = (String) sKeys.nextElement();
      enviarDiagramaSecuencia(nombre, false);        
    }
  }
  
  private void eliminarTodosEnlace(int id)
  {
    LinkedList<clsEnlace> enlace = objds.getConector();
    int dim = enlace.size();
    //System.out.println("=========================> TAMAÑO: "+dim);
    for (int i = 0; i < dim; i++)
    {
      clsEnlace aux = enlace.get(i);
      //System.out.println(" "+id+"="+aux.getOrigen()+", "+id+"="+aux.getDestino());
      if (id == aux.getOrigen() || id == aux.getDestino())
      {
        objds.delConector(i);
        enlace =  objds.getConector();
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