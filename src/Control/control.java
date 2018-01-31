package Control;

import Presentacion.formCliente;

import Comun.interfaceServidor;

import Negocio.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.LinkedList;
import Presentacion.*;

public class control implements MouseListener, MouseMotionListener
{
  private static final String PUNTERO  = "Puntero";
  private static final String CONECTOR = "Conector";
  private static final String ACTOR    = "Actor";
  private static final String INTERFAZ = "Interfaz";
  private static final String PROCESO  = "Proceso";
  private static final String ENTIDAD  = "Entidad";
  /*private static final String FUENTE   = "Fuente";
  private static final String COLOR    = "Color";*/
  
  private String usuario  = "";
  private String modo     = "Puntero";   //  modo => del 0..8  (puntero, conector, actor, interfaz, proceso, entidad, fuente, color)
  private clsDiagramaSecuencia objds;
  //private Object figura = null;      //  figura => si el objecto es selecionado(actor, clase, conector)*/
  
  public clsClase objclase = null;
  public clsActor objactor = null;
  public clsEnlace objconector = null;
  
  private Point p, o = null, d = null;
  private Point dif1, dif2, dif3, dif4, pm;
  private Rectangle fs, fi;
  private Point po, pd;
  private boolean esfigura = false, arbolsw = false;
  private int click = 1;
  private clsActor origen_a, destino_a;
  private clsClase origen_c, destino_c, arbolobj = null;

  private interfaceServidor objservidor;
  private graficadorDiagSec objgraficador;
  private formCliente objcliente;
  private DynamicTree arbol;
  
  public frmClase form2;
  
  public control(interfaceServidor objservidor, graficadorDiagSec objgraficador)
  {
    this.objservidor    = objservidor;
    this.objgraficador  = objgraficador;
  }
  
  public String getUsuario()
  {
    return usuario;
  }

  public void setUsuario(String usuario)
  {
    this.usuario = usuario;
  }
  
  public String getModo()
  {
    return modo;
  }

  public void setModo(String modo)
  {
    this.modo = modo;
  }
  
  public clsDiagramaSecuencia getObjds()
  {
    return objds;
  }

  public void setObjds(clsDiagramaSecuencia objds)
  {
    this.objds = objds;
  }

  /*public Object getFigura()
  {
    return figura;
  }

  public void setFigura(Object figura)
  {
    this.figura = figura;
  }*/
  
  public void setFormCliente(formCliente objcliente)
  {
    this.objcliente = objcliente;
  }
  
  public interfaceServidor getObjservidor()
  {
    return objservidor;
  }

  public void setObjservidor(interfaceServidor objservidor)
  {
    this.objservidor = objservidor;
  }
  
  public DynamicTree getArbol()
  {
    return arbol;
  }

  public void setArbol(DynamicTree arbol)
  {
    this.arbol = arbol;
  }
  
  private boolean verificarActor()
  {
    try 
    {
      objactor = objservidor.verificarActor(p);
      objclase = null;
      objconector = null;
    }
    catch (Exception ex) 
    { ex.printStackTrace(); }
    return objactor != null;
  }
  
  public boolean verificarClase()
  {
    try 
    {
      objactor = null;
      objclase = objservidor.verificarClase(p);
      objconector = null;
    }
    catch (Exception ex) 
    { ex.printStackTrace(); }
    return objclase != null;
  }
  
  public boolean verificarConector()
  {
    try 
    {
      objactor = null;
      objclase = null;
      objconector = objservidor.verificarConector(p);
    }
    catch (Exception ex) 
    { ex.printStackTrace(); }
    return objconector != null;
  }
  
  public void enviarActor(clsActor objactor)
  {
    try 
    {
      objservidor.addActor(objactor, usuario);
    }
    catch (RemoteException f) 
    { f.printStackTrace();  }
    modo = PUNTERO;
    esfigura = true;
  }
  
  private void enviarActor()
  {
    clsActor objactor = new clsActor(0, "", new Rectangle(p.x, p.y, 70, 70), new Rectangle(p.x+30, p.y+70, 10, 300));
    System.out.println("Control.control.enviarActor() " + objactor);
    enviarActor(objactor);
  }
  
  public void enviarClase(clsClase objclase)
  {
    try 
    {
      objservidor.addClase(objclase, usuario);
    }
    catch (RemoteException f)
    { f.printStackTrace();  }
    modo = PUNTERO;
    esfigura = true;
  }
  
  private void enviarClase()
  {
    clsClase objclase = null;
    if (arbolsw && arbolobj != null)
    {
      objclase = new clsClase(0, 1, PROCESO, arbolobj.getNombre(), arbolobj.getAcceso(), new Rectangle(p.x, p.y, 70, 70), new Rectangle(p.x+30, p.y+70, 10, 300));
      objclase.setAtributos(arbolobj.getAtributos());
      objclase.setMetodos(arbolobj.getMetodos());
      arbolsw = false;
      arbolobj = null;
    }
    else
      objclase = new clsClase(0, 2, getModo(), "", "public", new Rectangle(p.x, p.y, 70, 70), new Rectangle(p.x+30, p.y+70, 10, 300));
      System.out.println("Control.control.enviarClase() " + objclase);
    enviarClase(objclase);
  }
  
  private void enviarConector()
  {
    //clase destino = (clase) this.destino;    
    clsEnlace objconector;
    int idorigen = 0, iddestino = 0;
    //metodos objmetodo = new metodos();
    Point po = null, pd = null;
    if (origen_a != null)
    {
      //ro = new Rectangle(origen_a.getInferior());
      po = new Point(origen_a.getPuntoO());
      idorigen = origen_a.getId();
    }
    if (destino_a != null)
    {
      //rd = new Rectangle(destino_a.getInferior());
      pd = new Point(destino_a.getPuntoD());
      iddestino = destino_a.getId();
    }
    if (origen_c != null)
    {
      //ro = new Rectangle(origen_c.getInferior());
      po = new Point(origen_c.getPuntoO());
      idorigen = origen_c.getId();
      
    }
    if (destino_c != null)
    {
      pd = new Point(destino_c.getPuntoD());
      iddestino = destino_c.getId();
    }
    //objconector = new conector(0, idorigen, iddestino, new metodos(), new Rectangle(po.x, o.y, rd.x-ro.x, 8));
    try
    {
      objconector = new clsEnlace(0, idorigen, iddestino, new clsMetodo(), new Point(po.x, o.y), new Point(pd.x, o.y));
      objservidor.addConector(objconector, usuario);
    }
    catch (RemoteException f) 
    { f.printStackTrace();  }
    modo = PUNTERO;
    esfigura = true;
  }
  
  public void addActor(clsActor objactor)
  {
    objds.addActor(objactor);
    objds.addIds(""+objactor.getId());
    modo = PUNTERO;
  }
  
  /*public void addClase(clase objclase)
  {
    objds.addClase(objclase);
    objds.addIds(""+objclase.getId());
    modo = PUNTERO;
    //arbol.addObject(null, objclase, true);
  }*/
  
  public void addConector(clsEnlace objconector)
  {
    objds.addConector(objconector);
    objds.addIds(""+objconector.getId());
    modo = PUNTERO;
  }
  
  public void actualizarCoordenadasDiagramaSecuenciaTodos()
  {
    /*try 
    { objservidor.actualizarCoordenadasDiagramaSecuenciaTodos(usuario, figura); }
    catch (RemoteException f) 
    { f.printStackTrace();  }*/
  }
  
  private void enviarCoordenadaActor(Point dif1, Point dif2, Point dif3, Point dif4, Point pm, clsActor objactor)
  {
    Rectangle fs = objactor.getSuperior();
    fs.setLocation(pm.x - dif1.x, pm.y - dif1.y);
    Rectangle fi = objactor.getInferior();
    fi.setLocation(pm.x - dif2.x, pm.y - dif2.y);
    Point o = objactor.getPuntoO();
    Point d = objactor.getPuntoD();
    o.setLocation(pm.x - dif3.x, pm.y - dif3.y);
    d.setLocation(pm.x - dif4.x, pm.y - dif4.y);
    enviarActualizarActor(objactor);
  }
  
  public void enviarActualizarActor(clsActor objactor)
  {
    try 
    { objservidor.actualizarActor(usuario, objactor);  }
    catch (RemoteException f) 
    { f.printStackTrace();  }
  }
  
  private void enviarCoordenadaClase(Point dif1, Point dif2, Point dif3, Point dif4, Point pm, clsClase objclase)
  {
    //System.out.println("ENVIAR CORD CLASE => "+objclase);
    Rectangle fs = objclase.getSuperior();
    fs.setLocation(pm.x - dif1.x, pm.y - dif1.y);
    Rectangle fi = objclase.getInferior();
    fi.setLocation(pm.x - dif2.x, pm.y - dif2.y);
    Point o = objclase.getPuntoO();
    Point d = objclase.getPuntoD();
    o.setLocation(pm.x - dif3.x, pm.y - dif3.y);
    d.setLocation(pm.x - dif4.x, pm.y - dif4.y);
    enviarActualizarClase(objclase, false);
  }
  
  public void enviarActualizarClase(clsClase objclase, boolean updatearbol)
  {
    try 
    { objservidor.actualizarClase(usuario, objclase, updatearbol); }
    catch (RemoteException f) 
    { f.printStackTrace();  }
  }
  
  private void enviarCoordenadaConector(Point dif1, Point dif2, Point dif3, Point pm, clsEnlace objconector)
  {
    Rectangle fs = objconector.getEnlace();
    fs.setLocation(fs.x, pm.y - dif1.y);
    Point o = objconector.getPuntoO();
    Point d = objconector.getPuntoD();
    //o.setLocation(pm.x - dif3.x, pm.y - dif3.y);
    //d.setLocation(pm.x - dif4.x, pm.y - dif4.y);
    o.setLocation(o.x, pm.y - dif2.y);
    d.setLocation(d.x, pm.y - dif3.y);
    enviarActualizarConector(objconector);
  }
  
  public void enviarActualizarConector(clsEnlace objconector)
  {
    try 
    { objservidor.actualizarConector(usuario, objconector);  }
    catch (RemoteException f) 
    { f.printStackTrace();  }
  }
  
  //******************************
  public void actualizarActor(clsActor objactor)
  {
    LinkedList<clsActor> actor = objds.getActor();
    int dim = actor.size();
    for (int i = 0; i < dim; i++)
    {
      clsActor aux = actor.get(i);
      if (objactor.getId() == aux.getId())
      {
        objds.delActor(i);
        objds.addActor(objactor);
        actualizarConector(objactor.getId(), objactor.getPuntoO(), objactor.getPuntoD());
        break;
      }
    }
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
  
  public void actualizarClase(clsClase objclase)
  {
    LinkedList<clsClase> clase = objds.getClase();
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsClase aux = clase.get(i);
      if (objclase.getId() == aux.getId())
      {
        objds.delClase(i);
        objds.addClase(objclase);
        //arbol.actualizarArbolClase(objclase);
        actualizarConector(objclase.getId(), objclase.getPuntoO(), objclase.getPuntoD());
        break;
      }
    }
    
  }
  
  public void actualizarConector(clsEnlace objconector)
  {
    LinkedList<clsEnlace> conector = objds.getConector();
    int dim = conector.size();
    for (int i = 0; i < dim; i++)
    {
      clsEnlace aux = conector.get(i);
      if (objconector.getId() == aux.getId())
      {
        objds.delConector(i);
        objds.addConector(objconector);
        break;
      }
    }
  }
  
  private boolean verificar(Point p)    //  ESTE FALTA PARA EL SERVIDOR
  {
    LinkedList<clsActor> actor = objds.getActor();
    int dim = actor.size();
    for (int i = 0; i < dim; i++)
    {
      clsActor objactor = actor.get(i);
      Rectangle fig = objactor.getInferior();
      if (fig.contains(p))
      {
        if (click == 1)
          origen_a = objactor;
        else
          destino_a = objactor;
        return true;
      }
    }
  
    LinkedList<clsClase> clase = objds.getClase();
    dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsClase objclase = clase.get(i);
      if (objclase.getTipo() == 2)
      {
        Rectangle fig = objclase.getInferior();
        if (fig.contains(p))
        {
          if (click == 1)
            origen_c = objclase;
          else
            destino_c = objclase;
          return true;
        }
      }
    }
    return false;
  }
  
  
  private clsClase obtenerClase(int iddestino)
  {
    LinkedList<clsClase> clase = objds.getClase();
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsClase objclase = clase.get(i);
      if (objclase.getId() == iddestino)
        return objclase;
    }
    return null;
  }
  
  //******************* EVENTOS DEL MOUSE ****************************
  public void mousePressed(MouseEvent e)
  {
    //figura = null;
    p = e.getPoint();
    if (modo.equals(PUNTERO))
    {
      esfigura = verificarActor();
      if (esfigura)
      {
        fs = objactor.getSuperior();
        fi = objactor.getInferior();
        po = objactor.getPuntoO();
        pd = objactor.getPuntoD();
        dif1 = new Point(p.x - fs.x, p.y - fs.y);
        dif2 = new Point(p.x - fi.x, p.y - fi.y);
        dif3 = new Point(p.x - po.x, p.y - po.y);
        dif4 = new Point(p.x - pd.x, p.y - pd.y);
        //System.out.println("SOY OBJECTO, actor = "+objactor);
      }
      else
      {
        esfigura = verificarClase();
        if (esfigura)
        {
          fs = objclase.getSuperior();
          fi = objclase.getInferior();
          po = objclase.getPuntoO();
          pd = objclase.getPuntoD();
          dif1 = new Point(p.x - fs.x, p.y - fs.y);
          dif2 = new Point(p.x - fi.x, p.y - fi.y);
          dif3 = new Point(p.x - po.x, p.y - po.y);
          dif4 = new Point(p.x - pd.x, p.y - pd.y);
          //System.out.println("SOY OBJECTO, clase = "+objclase);
        }
        else
        {
          esfigura = verificarConector();
          if (esfigura)
          {
            fs = objconector.getEnlace();
            po = objconector.getPuntoO();
            pd = objconector.getPuntoD();
            dif1 = new Point(p.x - fs.x, p.y - fs.y);
            dif2 = new Point(p.x - po.x, p.y - po.y);
            dif3 = new Point(p.x - pd.x, p.y - pd.y);
            //System.out.println("SOY OBJECTO, conector = "+objconector);
          }
        }
      }
    }
    //objgraficador.actualizarGrafica();
  }
  
  public void mouseReleased(MouseEvent e)
  {
    p = e.getPoint();
    if (modo.equals(ACTOR))
      enviarActor();
    if (modo.equals(INTERFAZ) || modo.equals(PROCESO) || modo.equals(ENTIDAD))
      enviarClase();
    if (modo.equals(CONECTOR))
    {
      if (click == 1)
      {
        //origen = verificar(p);
        origen_a = null;
        origen_c = null;
        if (verificar(p))
        {
          o = new Point(p);
          click = 2;
        }
        else
          modo = PUNTERO;
      }
      else
      {
        //destino = verificar(p);
        destino_a = null;
        destino_c = null;
        if (verificar(p))
        {
          d = new Point(p);
          click = 1;
          enviarConector();
        }
        else
          modo = PUNTERO;
      }
    }
    objgraficador.actualizarGrafica();
  }

  public void mouseClicked(MouseEvent e)
  {
    int cont = e.getClickCount();
    
    if (esfigura && objactor != null && cont == 2)
    {
      frmActor formActor = new frmActor(objcliente, "?", true);
      formActor.cargarPropiedad(this, objactor);
      formActor.setVisible(true);
    }
    
    if (esfigura && objclase != null && cont == 2)
    {
      form2 = new frmClase(objcliente, "?", true);
      form2.cargarPropiedad(this, objclase);
      form2.setVisible(true);
    }
    
    if (esfigura && objconector != null && cont == 2)
    {
      int iddestino = objconector.getDestino();
      clsClase destino_c = obtenerClase(iddestino);
      if (destino_c != null)
      {
        frmEnlace formConector = new frmEnlace(objcliente, "?", true);
        formConector.cargarPropiedad(this, destino_c, objconector);
        formConector.setVisible(true);
      }
    }
    
    /*
     *       if (destino_c != null)
      {
        propiedadConector formConector = new propiedadConector(objcliente, "?", true);
        formConector.cargarPropiedad(this, destino_c);
        formConector.setVisible(true);
      }
     */
  }
  
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  
  
  public void mouseDragged(MouseEvent e) 
  {
    //System.out.println("SOY DRAGGED => esfigra = "+esfigura+", actor="+objactor+", clase="+objclase+", conector="+objconector);
    if (esfigura)
    {
      pm = e.getPoint();
      if (objactor != null)
        enviarCoordenadaActor(dif1, dif2, dif3, dif4, pm, objactor);
      if (objclase != null)
        enviarCoordenadaClase(dif1, dif2, dif3, dif4, pm, objclase);
      if (objconector != null)
        enviarCoordenadaConector(dif1, dif2, dif3, pm, objconector);
    }
  }

  public void mouseMoved(MouseEvent e)
  {}

  public boolean isArbolsw()
  {
    return arbolsw;
  }

  public void setArbolsw(boolean arbolsw)
  {
    this.arbolsw = arbolsw;
  }

  public clsClase getArbolobj()
  {
    return arbolobj;
  }

  public void setArbolobj(clsClase arbolobj)
  {
    this.arbolobj = arbolobj;
  }
  
  //**************** PARA LAS PROPIEDADES *********************
  //******** ATRIBUTO
  public void addAtributo(int id, clsAtributo objatributo, boolean updatearbol)
  {
    try 
    { objservidor.addAtributo(id, objatributo, updatearbol); }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void delAtributo(int id, String nombre_atributo, boolean updatearbol)
  {
    try 
    { objservidor.delAtributo(id, nombre_atributo, updatearbol); }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void actualizarFormClaseAtributo(clsClase objclase)
  {
    if (form2 != null)
      form2.actualizarAtributo(objclase);
  }
  
  //********* METODO
  public void addMetodo(int id, clsMetodo objmetodo, boolean updatearbol)
  {
    //System.out.println("PASO 1 PARA ADD METODO id="+id);
    try
    { objservidor.addMetodo(id, objmetodo, updatearbol); }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void delMetodo(int id, clsMetodo objmetodo, boolean updatearbol)
  {
    try 
    { objservidor.delMetodo(id, objmetodo, updatearbol); }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void actualizarFormClaseMetodo(clsClase objclase)
  {
    if (form2 != null)
      form2.actualizarMetodo(objclase);
  }
  
  //*************************** ELIMINACION DE ACTOR, CLASE, ENLACE
  public void eliminarActor(int id)
  {
    try
    {
      if (objactor != null)
      {
        objservidor.eliminarActor(objactor.getId());
        objactor = null;
      }
    }
    catch (Exception ex) 
    { ex.printStackTrace(); }
    
  }
  
  public void eliminarClase(int id)
  {
    try
    {
      if (objclase != null)
      {
        objservidor.eliminarClase(objclase.getId(), true);
        objclase = null;
      }
    }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void eliminarEnlace(int id)
  {
    try
    {
      if (objconector != null)
      {
        objservidor.eliminarEnlace(objconector.getId());
        objconector = null;
      }
    }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
}