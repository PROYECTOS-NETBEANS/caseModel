package Presentacion;

import static Comun.Constantes.PUNTERO;
import static Comun.Constantes.RELACION;
import static Comun.Constantes.TABLA;
import Control.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class barraToolsCase extends JPanel implements MouseListener, MouseMotionListener
{
  private final String diagrama[] = {PUNTERO, RELACION, TABLA};
  private final String imagen[] = {"cursor.jpg", "conector.jpg", "tabla.jpg"};
  private final control objcontrol;
  
  public barraToolsCase(control objcontrol)
  {
    this.objcontrol = objcontrol;
    
    this.setLayout(new GridLayout(6, 1, 0, 5));
    this.setBounds(20, 5, 520, 35);
    cargarComponente();
  }
  
  public void cargarComponente()
  {
    JPanel p[] =  new JPanel[3];
    //String []f = new String()[];
    for (int i = 0; i < diagrama.length; i++)
    {
      //p[i] = new JPanel(new FlowLayout());
      p[i] = new JPanel(new GridLayout(1, 1));
      JLabel lp = new JLabel(new ImageIcon("imagenes/diagrama/"+imagen[i]));
      //lp.setSize(120, 20);
      //lp.setBorder(BorderFactory.createEmptyBorder());
      //lp.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
      p[i].setName(diagrama[i]);
      p[i].add(lp);
      //p[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
      p[i].addMouseListener(this);
      p[i].addMouseMotionListener(this);
      p[i].setPreferredSize(new Dimension(80, 80));
      //p[i].setSize(80, 20);
      //p[i].setBorder(BorderFactory.createEmptyBorder());
      //p[i].setBorder(BorderFactory.createLineBorder(Color.red));
      this.add(p[i]);
    }
  }
  
  public void actionPerformed(ActionEvent e)
  {
    /*JButton o = (JButton) e.getSource();
    System.out.println("PRESIONO: "+o.getName());
    objcontrol.setModo(o.getName());*/
    //if (o.getName().equals("0"))
  }
  
  /**
  * Invoked when the mouse button has been clicked (pressed
  * and released) on a component.
  */
  public void mouseClicked(MouseEvent e)
  {
  }
  
  /**
  * Invoked when a mouse button has been pressed on a component.
     * @param e
  */
  @Override
  public void mousePressed(MouseEvent e)
  {
     JPanel o = (JPanel) e.getSource();
     System.out.println("PRESIONO mouse pressed : "+o.getName());
     objcontrol.setModo(o.getName());     
  }
  
  /**
  * Invoked when a mouse button has been released on a component.
  */
  public void mouseReleased(MouseEvent e)
  {
  }
  
  /**
  * Invoked when the mouse enters a component.
  */
  public void mouseEntered(MouseEvent e)
  {
  }
  
  /**
  * Invoked when the mouse exits a component.
  */
  public void mouseExited(MouseEvent e)
  {
    JPanel obj = (JPanel) e.getSource();
    //obj.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    obj.setBorder(null);
  }
  
  /**
   * ENVENTOS DEL MouseMotionListener
   */
  public void mouseMoved(MouseEvent e)
  {
    //JLabel obj = (JLabel) e.getSource();
    JPanel obj = (JPanel) e.getSource();
    obj.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
  }
  
  public void mouseDragged(MouseEvent e){}
}
