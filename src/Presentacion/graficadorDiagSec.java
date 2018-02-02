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
  private clsDiagrama objds = null;
  private interfaceServidor objservidor;
  private Object figura = null;      //  figura => si el objecto es selecionado(actor, clase, conector)*/
  
  private clsTabla objclase = null;
  private clsActor objactor = null;
  private clsRelacion objconector = null;
  
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
    
    dibujarTabla(g2);
    dibujarRelacion(g2);

  }  
  
  private void dibujarTabla(Graphics2D g2)
  {
    LinkedList<clsTabla> tablas = objds.getTablas();
    int dim = tablas.size();
    for (int i = 0; i < dim; i++)
    {
        clsTabla objclase = tablas.get(i);        
        //System.out.println("OBJ - CLASE => "+objclase.getSuperior());
        Rectangle sup = objclase.getSuperior();
        //Rectangle inf = objclase.getInferior();
        Point o = objclase.getPuntoO();
        Point d = objclase.getPuntoD();

        int x, y, w, h;
        x = sup.width*30/100;
        y = sup.height*20/100;
        w = sup.width*40/100;
        h = sup.height*60/100;
        g2.drawOval(sup.x+x, sup.y+y, w, h*67/100);
        g2.drawLine(sup.x+x+w, sup.y+y+(h*67/100)/2, sup.x+x+(w*80/100), sup.y+y+(h*67/100)*70/100);
        g2.drawLine(sup.x+x+w, sup.y+y+(h*67/100)/2, sup.x+x+w+(w*20/100), sup.y+y+(h*67/100)*70/100);
        int lon = objclase.getNombreTabla().length()/2;
        g2.drawString(objclase.getNombreTabla(), (sup.x*2+sup.width)/2-(lon*5), sup.y+y+h);
        //g2.drawLine(inf.x+(inf.width/2), inf.y, inf.x+(inf.width/2), inf.y+inf.height);
        g2.drawLine(o.x, o.y, d.x, d.y);
              
       
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
  
  private void dibujarRelacion(Graphics2D g2)
  {
    /*
    LinkedList<clsRelacion> conector = objds.getRelacion();
    int dim = conector.size();
    for (int i = 0; i < dim; i++)
    {
      clsRelacion objconector = conector.get(i);
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
    */
  }

  public void actualizarGrafica()
  {
    objds       = objcontrol.getObjds();
    objclase    = objcontrol.objTabla;
    objconector = objcontrol.objconector;
    repaint();
  }
}