package Control;

import Comun.Constantes;
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
  
  private String usuario  = "";
  private String modo     = "Puntero";
  private clsDiagrama objds;
  
  public clsTabla objTabla = null;
  public clsRelacion objconector = null;
   
  private int click = 1;

  private interfaceServidor objservidor;
  private lienzo objgraficador;
  
  private formCliente frmClient;
  
  public frmTabla form2;
  public frmRelacion formRel;
  
  public control(interfaceServidor objservidor, lienzo objgraficador)
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
    if(this.modo.equals(Constantes.RELACION)){
        formRel = new frmRelacion(frmClient, "Relacion", true);
        formRel.cargarRelacion(this, new clsRelacion(), true);
        formRel.setVisible(true);
    }        
  }
  
  public clsDiagrama getObjds()
  {
    return objds;
  }

  public void setObjds(clsDiagrama objds)
  {
    this.objds = objds;
  }
  
  public void setFormCliente(formCliente objcliente)
  {
    this.frmClient = objcliente;
  }
  
  public interfaceServidor getObjservidor()
  {
    return objservidor;
  }

  public void setObjservidor(interfaceServidor objservidor)
  {
    this.objservidor = objservidor;
  }
  
  public boolean verificarTabla(Point pto)
  {
    try 
    {

      objTabla = objservidor.verificarTabla(pto);
      objconector = null;
    }
    catch (RemoteException ex) 
    { ex.printStackTrace(); }
    return objTabla != null;
  }
  
  public boolean verificarRelacion(Point p)
  {
    try 
    {
      objTabla = null;
      objconector = objservidor.verificarRelacion(p);
    }
    catch (RemoteException ex) 
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
  }
  
  private void enviarTabla(Point pto)
  {
    clsTabla objclase = null;   
    objclase = new clsTabla(0, "", new Rectangle(pto.x, pto.y, 70, 70));
    System.out.println("Control.control.enviarTabla() " + objclase);
    enviarTabla(objclase);
  }
 
  public void addRelacion(clsRelacion relacion)
  {
    try {
      objds.addRelacion(relacion);
      objds.addIds(""+relacion.getId());
      modo = PUNTERO;
      objservidor.addRelacion(relacion, usuario);
    } catch (RemoteException e) {
        System.out.println("Error en control.addRelacion al enviar al servidor" + e.getMessage());
    }    
  }
  
  public void actualizarCoordenadasDiagramaSecuenciaTodos()
  {
    /*try 
    { objservidor.actualizarCoordenadasDiagramaSecuenciaTodos(usuario, figura); }
    catch (RemoteException f) 
    { f.printStackTrace();  }*/
  }
    
  private void enviarCoordenadaTabla(Point pto, clsTabla objclase)
  {
    System.out.println("ENVIAR CORD CLASE => "+objclase);
    Rectangle fs = objclase.getSuperior();
    fs.setLocation(pto);
    enviarActualizarTabla(objclase);
  }
  
  public void enviarActualizarTabla(clsTabla objclase)
  {
    try{ 
        objservidor.actualizarTabla(usuario, objclase); 
    }
    catch (RemoteException f) 
    { f.printStackTrace();  }
  }
  
  public void enviarActualizarRelacion(clsRelacion objconector)
  {
    try{ 
        objservidor.actualizarRelacion(usuario, objconector);  
    }
    catch (RemoteException f){ 
        f.printStackTrace();  
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
        System.out.println("control.actualizarTabla");
        //objds.delRelacion(i);
        objds.addTabla(objclase);
        //actualizarRelacion(objclase.getId(), "");
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
  //******************* EVENTOS DEL MOUSE ****************************
  @Override
  public void mousePressed(MouseEvent e)
  {
    
    if (modo.equals(PUNTERO))
    {
        if (verificarTabla(e.getPoint()))
        {
            System.out.println("soy tabla");
        }else{
            if(verificarRelacion(e.getPoint())){
                System.out.println("soy relacion cliente");
            }
        }
    }
  }
  
  /**
   * cuando se suelta el raton
   * @param e 
   */
  @Override
  public void mouseReleased(MouseEvent e)
  {   
    if (modo.equals(TABLA))
        enviarTabla(e.getPoint());
    
    objgraficador.actualizarGrafica();
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    int cont = e.getClickCount();
      
    if (objTabla != null && cont == 2)
    {
        form2 = new frmTabla(frmClient, "?", true);
        form2.cargarPropiedad(this, objTabla);
        form2.setVisible(true);
    }else{
        if(objconector != null && cont == 2){
            formRel = new frmRelacion(frmClient, "Relacion", true);
            formRel.cargarRelacion(this, objconector, false);
            formRel.setVisible(true);
        }
    }
  }
  
  @Override
  public void mouseEntered(MouseEvent e){}
  @Override
  public void mouseExited(MouseEvent e){}
  
  
  @Override
  public void mouseDragged(MouseEvent e) 
  {
      
    if (objTabla != null)
      enviarCoordenadaTabla(e.getPoint(), objTabla);
  }

  @Override
  public void mouseMoved(MouseEvent e){}  
  //**************** PARA LAS PROPIEDADES *********************
  //******** ATRIBUTO
  public void addColumna(int id, clsColumna objatributo)
  {
    try 
    { objservidor.addColumna(id, objatributo); }
    catch (RemoteException ex) 
    { ex.printStackTrace(); }
  }
  
  public void delColumna(int id, String nombre_atributo)
  {
    try 
    { objservidor.delColumna(id, nombre_atributo); }
    catch (RemoteException ex) 
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
    catch (RemoteException ex) 
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
    catch (RemoteException ex) 
    { ex.printStackTrace(); }
  }
}