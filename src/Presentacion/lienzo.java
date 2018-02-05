package Presentacion;

import Comun.*;
import Negocio.*;
import Control.control;
import java.awt.*;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class lienzo extends JPanel
{
  private control objcontrol;
  private clsDiagrama objds = null;
  private interfaceServidor objservidor;
  
  public lienzo(interfaceServidor objservidor)
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

  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setStroke(new BasicStroke(1.5f));
    
    g2.setFont(new Font("Dialog", 0, 10));
    
    dibujarRelacion(g2);
    dibujarTabla(g2);    
  }  
  
  private void dibujarTabla(Graphics2D g2)
  {
    LinkedList<clsTabla> tablas = objds.getTablas();
    tablas.forEach((tabla) -> {
        
        Rectangle sup = tabla.getSuperior();
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(sup.x, sup.y, sup.width, sup.height);
        g2.setColor(Color.BLACK);
        
        // cuadro y nombre de la tabla
        g2.drawRect(sup.x, sup.y, sup.width, Constantes.TAMAÑO_LETRA);
        g2.drawString(tabla.getNombreTabla(), sup.x + 6, sup.y + 12);
                
        g2.drawRect(sup.x, sup.y,sup.width, sup.height);
        this.dibujarColumnas(g2, sup.getLocation(), tabla.getColumnas());
      });
  }
  private void dibujarColumnas(Graphics2D g2, Point inicio, LinkedList<clsColumna> cols){
      
      int y = inicio.y + (Constantes.TAMAÑO_LETRA * 2);
      
      for (clsColumna col : cols) {
          if(col.isPrimaryKey())
            g2.drawString("Pk " + col.getNombre() + " :" + col.getTipo(), inicio.x + 6, y);
          else
            g2.drawString(col.getNombre() + " :" + col.getTipo(), inicio.x + 6, y);
          y += Constantes.TAMAÑO_LETRA;
      }
  }
  private void dibujarRelacion(Graphics2D g2){
      LinkedList<clsRelacion> relaciones = objds.getRelacion();
      relaciones.forEach((rel) -> {
          g2.drawLine(rel.getPuntoO().x, rel.getPuntoO().y, rel.getPuntoD().x, rel.getPuntoD().y);
      });
  }
  public void actualizarGrafica()
  {
    objds       = objcontrol.getObjds();
    repaint();
  }
}