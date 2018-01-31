package Presentacion;

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

public class barraDiagramaSecuencia extends JPanel implements MouseListener, MouseMotionListener
{
  //private String diagrama[] = {"Puntero", "Conector", "Actor", "Interfaz", "Proceso", "Entidad", "6", "7"};
  private String diagrama[] = {"Puntero", "Conector", "Actor", "Interfaz", "Proceso", "Entidad"};
  private String imagen[] = {"Puntero.gif", "Enlace.gif", "Actor.gif", "Interfaz.gif", "Proceso.gif", "Entidad.gif"};
  private control objcontrol;
  
  public barraDiagramaSecuencia(control objcontrol)
  {
    this.objcontrol = objcontrol;
    
    //this.setLayout(new FlowLayout());
    this.setLayout(new GridLayout(6, 1, 0, 5));
    this.setBounds(20, 5, 520, 35);
    //this.setBackground(new Color(165, 165, 165));
    //this.setPreferredSize(new Dimension(120, ));
    //this.setDoubleBuffered(true);
    //this.addMouseListener(objcontrol);
    //this.addMouseMotionListener(objcontrol);
    cargarComponente();
  }
  
  public void cargarComponente()
  {
    JPanel p[] =  new JPanel[6];
    //String []f = new String()[];
    for (int i = 0; i < diagrama.length; i++)
    {
      //p[i] = new JPanel(new FlowLayout());
      p[i] = new JPanel(new GridLayout(1, 2));
      JLabel lp = new JLabel(new ImageIcon("imagenes/diagrama/"+imagen[i]));
      //lp.setSize(120, 20);
      //lp.setBorder(BorderFactory.createEmptyBorder());
      //lp.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
      JLabel ln = new JLabel(diagrama[i]);
      //ln.setSize(80, 20);
      //ln.setBorder(BorderFactory.createEmptyBorder());
      //ln.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
      p[i].setName(diagrama[i]);
      p[i].add(lp);
      p[i].add(ln);
      //p[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
      p[i].addMouseListener(this);
      p[i].addMouseMotionListener(this);
      p[i].setPreferredSize(new Dimension(120, 20));
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
  */
  public void mousePressed(MouseEvent e)
  {
     JPanel o = (JPanel) e.getSource();
     System.out.println("PRESIONO: "+o.getName());
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
