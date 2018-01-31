package Presentacion;

import Comun.*;
import Negocio.*;
import Control.control;
import java.awt.*;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class graficadorDiagSec extends JPanel
{
  private control objcontrol;
  private clsDiagramaSecuencia objds = null;
  private interfaceServidor objservidor;
  private Object figura = null;      //  figura => si el objecto es selecionado(actor, clase, conector)*/
  
  private clsClase objclase = null;
  private clsActor objactor = null;
  private clsEnlace objconector = null;
  
  public graficadorDiagSec(interfaceServidor objservidor)
  {
    this.objservidor = objservidor;
    objcontrol = new control(this.objservidor, this);
    
    //this.setFocusable(true);
    this.setPreferredSize(new Dimension(1630, 1630));
    this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    //this.setPreferredSize(new Dimension(400, 300));
    //this.setBounds(new Rectangle(0, 0, 1630, 1630));
    this.setBackground(Color.white);
    this.setDoubleBuffered(true);
    this.addMouseListener(objcontrol);
    this.addMouseMotionListener(objcontrol);
  }
  
  public control getObjcontrol()
  {
    return objcontrol;
  }
  
  public void setObjcontrol(control objcontrol)
  {
    this.objcontrol = objcontrol;
  }

  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setStroke(new BasicStroke(1.5f));
    
    g2.setFont(new Font("Dialog", 0, 10));
    
    dibujarActor(g2);
    dibujarClase(g2);
    dibujarConector(g2);
    
    
    /*g2.drawRect(80, 50, 40, 8);
    g2.drawRect(120, 80, 40, 8);*/
  }
  
  private void dibujarActor(Graphics2D g2)
  {
    LinkedList<clsActor> actor = objds.getActor();
    int dim = actor.size();
    for (int i = 0; i < dim; i++)
    {
      clsActor objactor = actor.get(i);
      //System.out.println("OBJ - ACTOR => "+objactor.getSuperior());
      Rectangle sup = objactor.getSuperior();
      //Rectangle inf = objactor.getInferior();
      Point o = objactor.getPuntoO();
      Point d = objactor.getPuntoD();

      int w = sup.width*30/100;
      //int h = sup.height*25/100;
      int h = sup.height*20/100;
      int x = sup.width*35/100;
      //int y = sup.y;
      g2.drawOval(sup.x+x, sup.y, w, h);
      g2.drawLine(sup.x+x+(w/2), sup.y+h, sup.x+x+(w/2), sup.y+h+h);
      g2.drawLine(sup.x+x, sup.y+h+(h/2), sup.x+x+w, sup.y+h+(h/2));
      g2.drawLine(sup.x+x+(w/2), sup.y+h+h, sup.x+x, sup.y+(3*h));
      g2.drawLine(sup.x+x+(w/2), sup.y+h+h, sup.x+x+w, sup.y+(3*h));
      //int xxx = sup.x+x+(w/2);
      int lon = objactor.getNombre().length()/2;
      g2.drawString(objactor.getNombre(), (sup.x*2+sup.width)/2-(lon*5), sup.y+(3*h+(h/2)+5));
      
      //System.out.println("x = "+xxx+", w = "+sup.width);
      //g2.drawLine(inf.x+(inf.width/2), inf.y, inf.x+(inf.width/2), inf.y+inf.height);
      g2.drawLine(o.x, o.y, d.x, d.y);
      
      //if (figura instanceof actor && objactor.getId() == ((actor)figura).getId())
      if (this.objactor != null && this.objactor.getId() == objactor.getId())
      {
        g2.fillRect(sup.x-5, sup.y-5, 5, 5);
        g2.fillRect(sup.x+sup.width, sup.y-5, 5, 5);
        g2.fillRect(sup.x-5, sup.y+sup.height, 5, 5);
        g2.fillRect(sup.x+sup.width, sup.y+sup.height, 5, 5);
        //g2.fillRect(inf.x+(inf.width/2)-2, inf.y+inf.height, 5, 5);
        g2.fillRect(d.x-2, d.y, 5, 5);
      }
    }
  }
  
  private void dibujarClase(Graphics2D g2)
  {
    LinkedList<clsClase> clase = objds.getClase();
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsClase objclase = clase.get(i);
      //System.out.println("GRAFICADOR TIPO => "+objclase.getTipo());
      if (objclase.getTipo() == 2)
      {
      //System.out.println("OBJ - CLASE => "+objclase.getSuperior());
      Rectangle sup = objclase.getSuperior();
      //Rectangle inf = objclase.getInferior();
      Point o = objclase.getPuntoO();
      Point d = objclase.getPuntoD();
      
      int x, y, w, h;
      if (objclase.getTipo() == 2 && objclase.getClassinterface().equals("Interfaz"))
      {
        x = sup.width*20/100;
        y = sup.height*20/100;
        w = sup.width*60/100;
        h = sup.height*60/100;
        g2.drawLine(sup.x+x, sup.y+y, sup.x+x, sup.y+y+(h*67/100));
        g2.drawLine(sup.x+x, sup.y+y+((h*67/100)/2), sup.x+x+(w*33/100), sup.y+y+((h*67/100)/2));
        g2.drawOval(sup.x+x+(w*33/100), sup.y+y, w*67/100, h*67/100);
        int lon = objclase.getNombre().length()/2;
        g2.drawString(objclase.getNombre(), (sup.x*2+sup.width)/2-(lon*5), sup.y+y+h);
        //g2.drawLine(inf.x+(inf.width/2), inf.y, inf.x+(inf.width/2), inf.y+inf.height);
        g2.drawLine(o.x, o.y, d.x, d.y);
      }
      else
      {
        if (objclase.getTipo() == 2 && objclase.getClassinterface().equals("Proceso"))
        {
          x = sup.width*30/100;
          y = sup.height*20/100;
          w = sup.width*40/100;
          h = sup.height*60/100;
          g2.drawOval(sup.x+x, sup.y+y, w, h*67/100);
          g2.drawLine(sup.x+x+w, sup.y+y+(h*67/100)/2, sup.x+x+(w*80/100), sup.y+y+(h*67/100)*70/100);
          g2.drawLine(sup.x+x+w, sup.y+y+(h*67/100)/2, sup.x+x+w+(w*20/100), sup.y+y+(h*67/100)*70/100);
          int lon = objclase.getNombre().length()/2;
          g2.drawString(objclase.getNombre(), (sup.x*2+sup.width)/2-(lon*5), sup.y+y+h);
          //g2.drawLine(inf.x+(inf.width/2), inf.y, inf.x+(inf.width/2), inf.y+inf.height);
          g2.drawLine(o.x, o.y, d.x, d.y);
        }
        else
        {
          if (objclase.getTipo() == 2 && objclase.getClassinterface().equals("Entidad"))
          {
            x = sup.width*30/100;
            y = sup.height*20/100;
            w = sup.width*40/100;
            h = sup.height*60/100;
            g2.drawOval(sup.x+x, sup.y+y, w, h*67/100);
            g2.drawLine(sup.x+x, sup.y+y+(h*67/100), sup.x+x+w, sup.y+y+(h*67/100));
            int lon = objclase.getNombre().length()/2;
            g2.drawString(objclase.getNombre(), (sup.x*2+sup.width)/2-(lon*5), sup.y+y+h);
            g2.drawLine(o.x, o.y, d.x, d.y);
          }
        }
      }
       
      //if (figura instanceof clase && objclase.getId() == ((clase)figura).getId())
      if (this.objclase != null && this.objclase.getId() == objclase.getId())
      {
        //System.out.println("GRAFICADOR => "+sup);
        g2.fillRect(sup.x-5, sup.y-5, 5, 5);
        g2.fillRect(sup.x+sup.width, sup.y-5, 5, 5);
        g2.fillRect(sup.x-5, sup.y+sup.height, 5, 5);
        g2.fillRect(sup.x+sup.width, sup.y+sup.height, 5, 5);
        //g2.fillRect(inf.x+(inf.width/2)-2, inf.y+inf.height, 5, 5);
        g2.fillRect(d.x-2, d.y, 5, 5);
      }
    }
    }
  }
  
  private void dibujarConector(Graphics2D g2)
  {
    LinkedList<clsEnlace> conector = objds.getConector();
    int dim = conector.size();
    for (int i = 0; i < dim; i++)
    {
      clsEnlace objconector = conector.get(i);
      Point o = objconector.getPuntoO();
      Point d = objconector.getPuntoD();
      
      g2.drawLine(o.x, o.y, d.x, d.y);
      if (o.x < d.x)
      {
        g2.drawLine(d.x, d.y, d.x-5, d.y-6);
        g2.drawLine(d.x, d.y, d.x-5, d.y+6);
      }
      else
      {
        g2.drawLine(d.x, d.y, d.x+5, d.y-6);
        g2.drawLine(d.x, d.y, d.x+5, d.y+6);
      }
      
      clsMetodo objmetodo = objconector.getObjmetodo();
      String met = objmetodo.getNombre();
      LinkedList<clsParametro> parametro = objmetodo.getParametros();
      int s = parametro.size();
      String par = "(";
      for (int j = 0; j < s; j++)
      {
        clsParametro objparametro = parametro.get(j);
        //par += objparametro.getTipo()+" "+objparametro.getNombre()+", ";
        par += objparametro.getNombre()+", ";
      }
      if (par.length() > 1)
        par = par.substring(0, par.length() - 2);
      par += ")";
      /*if (objmetodo.getRetorna().equals("void"))
        met += par;
      else
        met += par + ": "+objmetodo.getRetorna();*/
      met += par;
      String cad = objmetodo.getSecuencia()+". "+met;
      int lon = cad.length()/2;
       //(sup.x*2+sup.width)/2-(lon*5), sup.y+y+h);
      g2.drawString(cad, (o.x*2+d.x-o.x)/2-(lon*5), d.y-5);
      
      
      //if (figura instanceof conector && objconector.getOrigen() == ((conector)figura).getOrigen() && objconector.getDestino() == ((conector)figura).getDestino()) 
      //if (figura instanceof conector && objconector.getId() == ((conector)figura).getId())
      if (this.objconector != null && this.objconector.getId() == objconector.getId()) 
      {
        g2.fillRect(o.x, o.y-2, 5, 5);
        g2.fillRect(d.x, d.y-2, 5, 5);
      }
    }
  }

  public void actualizarGrafica()
  {
    objds       = objcontrol.getObjds();
    objactor    = objcontrol.objactor;
    objclase    = objcontrol.objclase;
    objconector = objcontrol.objconector;
    //figura  = objcontrol.getFigura();
    //System.out.println("Figura => "+figura);
    repaint();
  }
}