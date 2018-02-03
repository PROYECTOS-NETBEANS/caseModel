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
  private static final String CONECTOR = "Relacion";
  private static final String TABLA    = "Tabla";
  /*private static final String FUENTE   = "Fuente";
  private static final String COLOR    = "Color";*/
  
  private String usuario  = "";
  private String modo     = "Puntero";   //  modo => del 0..8  (puntero, conector, actor, interfaz, proceso, entidad, fuente, color)
  private clsDiagrama objds;
  //private Object figura = null;      //  figura => si el objecto es selecionado(actor, clase, conector)*/
  
  public clsTabla objTabla = null;
  public clsRelacion objconector = null;
  
  private Point p, o = null, d = null;
  private Point dif1, dif2, dif3, dif4, pm;
  private Rectangle fs, fi;
  private Point po, pd;
  private boolean esfigura = false;
  private int click = 1;
  private clsTabla origen_c, destino_c = null;

  private interfaceServidor objservidor;
  private graficadorDiagSec objgraficador;
  private formCliente objcliente;
  
  public frmTabla form2;
  
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
  
  public clsDiagrama getObjds()
  {
    return objds;
  }

  public void setObjds(clsDiagrama objds)
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
  
  public boolean verificarTabla()
  {
    try 
    {

      objTabla = objservidor.verificarTabla(p);
      objconector = null;
    }
    catch (Exception ex) 
    { ex.printStackTrace(); }
    return objTabla != null;
  }
  
  public boolean verificarConector()
  {
    try 
    {
      objTabla = null;
      objconector = objservidor.verificarRelacion(p);
    }
    catch (Exception ex) 
    { ex.printStackTrace(); }
    return objconector != null;
  }
    
  public void enviarTabla(clsTabla objclase)
  {
    try 
    {
        System.out.println("control.enviarTabla");
        if(objservidor == null)
            System.out.println("soy servior nulo");
        objservidor.addTabla(objclase, usuario);
    }
    catch (RemoteException f)
    { f.printStackTrace();  }
    modo = PUNTERO;
    esfigura = true;
  }
  
  private void enviarTabla()
  {
    clsTabla objclase = null;
    //objclase = new clsTabla(0, 2, getModo(), "", "public", new Rectangle(p.x, p.y, 70, 70), new Rectangle(p.x+30, p.y+70, 10, 300));
    objclase = new clsTabla(0, "", new Rectangle(p.x, p.y, 70, 70), new Rectangle(p.x+30, p.y+70, 10, 300));
    System.out.println("Control.control.enviarTabla() " + objclase);
    enviarTabla(objclase);
  }
  
  private void enviarConector()
  {
      /* ESTO SE BLOQUEO  POR FALT A DE HACER LIMBERT
    //clase destino = (clase) this.destino;    
    clsRelacion objconector;
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
      */
  }
    
  public void addRelacion(clsRelacion objconector)
  {
    objds.addRelacion(objconector);
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
    
  private void enviarCoordenadaTabla(Point dif1, Point dif2, Point dif3, Point dif4, Point pm, clsTabla objclase)
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
    enviarActualizarTabla(objclase, false);
  }
  
  public void enviarActualizarTabla(clsTabla objclase, boolean updatearbol)
  {
    try{ 
        objservidor.actualizarTabla(usuario, objclase); 
    }
    catch (RemoteException f) 
    { f.printStackTrace();  }
  }
  
  private void enviarCoordenadaRelacion(Point dif1, Point dif2, Point dif3, Point pm, clsRelacion objconector)
  {
    Rectangle fs = objconector.getEnlace();
    fs.setLocation(fs.x, pm.y - dif1.y);
    Point o = objconector.getPuntoO();
    Point d = objconector.getPuntoD();
    //o.setLocation(pm.x - dif3.x, pm.y - dif3.y);
    //d.setLocation(pm.x - dif4.x, pm.y - dif4.y);
    o.setLocation(o.x, pm.y - dif2.y);
    d.setLocation(d.x, pm.y - dif3.y);
    enviarActualizarRelacion(objconector);
  }
  
  public void enviarActualizarRelacion(clsRelacion objconector)
  {
    try 
    { objservidor.actualizarRelacion(usuario, objconector);  }
    catch (RemoteException f) 
    { f.printStackTrace();  }
  }
    
  private void actualizarRelacion(int id, Point po, Point pd)
  {
    LinkedList<clsRelacion> conector = objds.getRelacion();
    int dim = conector.size();
    for (int i = 0; i < dim; i++)
    {
      clsRelacion aux = conector.get(i);
      if (id == aux.getOrigen())// || objclase.getId() == aux.getDestino())
      {
        objds.delRelacion(i);
        Point poco = aux.getPuntoO();
        Point pdco = aux.getPuntoD();
        clsRelacion objconector;
        objconector = new clsRelacion(aux.getId(), aux.getOrigen(), aux.getDestino(), new Point(po.x, poco.y), new Point(pdco.x, poco.y));
        objds.addRelacion(objconector);
      }
      else
      {
        if (id == aux.getDestino())
        {
          Point poco = aux.getPuntoO();
          //Point pdco = aux.getPuntoD();
          objds.delRelacion(i);
          clsRelacion objconector = new clsRelacion(aux.getId(), aux.getOrigen(), aux.getDestino(), new Point(poco.x, poco.y), new Point(pd.x, poco.y));
          objds.addRelacion(objconector);
        }
      }
    }
  }
  
  public void actualizarTabla(clsTabla objclase)
  {
    LinkedList<clsTabla> clase = objds.getTablas();
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsTabla aux = clase.get(i);
      if (objclase.getId() == aux.getId())
      {
        objds.delRelacion(i);
        objds.addTabla(objclase);
        //arbol.actualizarArbolClase(objclase);
        actualizarRelacion(objclase.getId(), objclase.getPuntoO(), objclase.getPuntoD());
        break;
      }
    }
    
  }
  
  public void actualizarRelacion(clsRelacion objconector)
  {
    LinkedList<clsRelacion> conector = objds.getRelacion();
    int dim = conector.size();
    for (int i = 0; i < dim; i++)
    {
      clsRelacion aux = conector.get(i);
      if (objconector.getId() == aux.getId())
      {
        objds.delRelacion(i);
        objds.addRelacion(objconector);
        break;
      }
    }
  }
  
  private boolean verificar(Point p)    //  ESTE FALTA PARA EL SERVIDOR
  {
    System.out.println("control.verificar !!! deshabilitado! x limbert");
   /*
    LinkedList<clsTabla> tabla = objds.getTablas();
    int dim = tabla.size();
    for (int i = 0; i < dim; i++)
    {
      clsActor objactor = tabla.get(i);
      Rectangle fig = objactor.getInferior();
      if (fig.contains(p))
      {
        if (click == 1)
          origen_c = objactor;
        else
          destino_c = objactor;
        return true;
      }
    }
  
    LinkedList<clsTabla> clase = objds.getClase();
    dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsTabla objclase = clase.get(i);
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
   */
   return false;
  }
  
  
  private clsTabla obtenerTabla(int iddestino)
  {
    LinkedList<clsTabla> tablas = objds.getTablas();
    int dim = tablas.size();
    for (int i = 0; i < dim; i++)
    {
      clsTabla objclase = tablas.get(i);
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
        esfigura = verificarTabla();
        if (esfigura)
        {
          fs = objTabla.getSuperior();
          fi = objTabla.getInferior();
          po = objTabla.getPuntoO();
          pd = objTabla.getPuntoD();
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
    //objgraficador.actualizarGrafica();
  }
  
  public void mouseReleased(MouseEvent e)
  {
    p = e.getPoint();
    if (modo.equals(TABLA))
        enviarTabla();
    if (modo.equals(CONECTOR))
    {
      if (click == 1)
      {
        //origen = verificar(p);
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
      
    if (esfigura && objTabla != null && cont == 2)
    {
      form2 = new frmTabla(objcliente, "?", true);
      form2.cargarPropiedad(this, objTabla);
      form2.setVisible(true);
    }
    
    if (esfigura && objconector != null && cont == 2)
    {
      int iddestino = objconector.getDestino();
      clsTabla destino_c = obtenerTabla(iddestino);
      if (destino_c != null)
      {
        frmRelacion formConector = new frmRelacion(objcliente, "?", true);
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
      if (objTabla != null)
        enviarCoordenadaTabla(dif1, dif2, dif3, dif4, pm, objTabla);
      if (objconector != null)
        enviarCoordenadaRelacion(dif1, dif2, dif3, pm, objconector);
    }
  }

  public void mouseMoved(MouseEvent e){}  
  //**************** PARA LAS PROPIEDADES *********************
  //******** ATRIBUTO
  public void addColumna(int id, clsColumna objatributo)
  {
    try 
    { objservidor.addColumna(id, objatributo); }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void delColumna(int id, String nombre_atributo)
  {
    try 
    { objservidor.delColumna(id, nombre_atributo); }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
  
  public void actualizarFormClaseAtributo(clsTabla objclase)
  {
    if (form2 != null)
      form2.actualizarAtributo(objclase);
  }  
 
  public void eliminarTabla(int id)
  {
    try
    {
      if (objTabla != null)
      {
        objservidor.eliminarTabla(objTabla.getId());
        objTabla = null;
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
        objservidor.eliminarRelacion(objconector.getId());
        objconector = null;
      }
    }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }
}