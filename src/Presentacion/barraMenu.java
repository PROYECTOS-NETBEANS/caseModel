package Presentacion;

import Negocio.*;
import Control.control;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class barraMenu extends JPanel implements MouseListener, MouseMotionListener
{
  //JToolBar
  private formCliente form;
  private control objcontrol;
  private clsTabla objTabla;
  private clsRelacion objenlace;
  
  JToolBar barra1   = new JToolBar(); //  imagenes\archivo
  JButton bnuevo    = new JButton(new ImageIcon("imagenes/archivo/nuevo.png"));
  JButton babrir    = new JButton(new ImageIcon("imagenes/archivo/abrir.png"));
  JButton bguardar  = new JButton(new ImageIcon("imagenes/archivo/guardar.png"));
  JButton bguardarc = new JButton(new ImageIcon("imagenes/archivo/guardarcomo.png"));
  JButton bexportar = new JButton(new ImageIcon("imagenes/archivo/exportar.png"));
  JButton bimportar = new JButton(new ImageIcon("imagenes/archivo/importar.png"));
  
  JToolBar barra2   = new JToolBar();
  JButton bcopiar   = new JButton(new ImageIcon("imagenes/edicion/copiar.png"));
  JButton bpegar    = new JButton(new ImageIcon("imagenes/edicion/pegar.png"));
  JButton beliminar = new JButton(new ImageIcon("imagenes/edicion/eliminar.png"));
  JButton bfuente   = new JButton(new ImageIcon("imagenes/edicion/fuente.png"));
  JButton bcolor    = new JButton(new ImageIcon("imagenes/edicion/color.png"));
  
  public barraMenu(formCliente form, control objcontrol)
  {
    this.form = form;
    this.objcontrol = objcontrol;
    this.setLayout(new GridLayout(1, 2, 0, 10));
    //this.setBackground(new Color(165, 165, 165));
    cargarArchivo();
    cargarEdicion();
    this.add(barra1);
    this.add(barra2);
    this.setBorder(BorderFactory.createEmptyBorder());
  }
  
  public void cargarArchivo()
  {
    barra1.setLayout(new GridLayout(1, 7, 0, 0));
    barra1.setLayout(new FlowLayout());
    
    barra1.add(bnuevo);
    barra1.add(babrir);
    barra1.add(bguardar);
    barra1.add(bguardarc);
    barra1.addSeparator(new Dimension(2, 0));
    barra1.add(bexportar);
    barra1.add(bimportar);
    
    bnuevo.setToolTipText ("Nuevo");
    bnuevo.setName("Nuevo");
    bnuevo.addMouseListener(this);
    bnuevo.setBorder(BorderFactory.createEmptyBorder());
    //bnuevo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    bnuevo.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
    babrir.setToolTipText ("Abrir");
    babrir.setName("Abrir");
    babrir.addMouseListener(this);
    babrir.addMouseMotionListener(this);
    babrir.setBorder(BorderFactory.createEmptyBorder());
    babrir.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
    //babrir.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    bguardar.setToolTipText ("Guardar");
    bguardar.setName("Guardar");
    bguardar.addMouseListener(this);
    bguardar.addMouseMotionListener(this);
    bguardar.setBorder(BorderFactory.createEmptyBorder());
    bguardar.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
    //bguardar.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    bguardarc.setToolTipText("Guardar como...");
    bguardarc.setName("GuardarC");
    bguardarc.addMouseListener(this);
    bguardarc.addMouseMotionListener(this);
    bguardarc.setBorder(BorderFactory.createEmptyBorder());
    bguardarc.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
    //bguardarc.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    bexportar.setToolTipText ("Exportar XMI");
    bexportar.setName("Exportar");
    bexportar.addMouseListener(this);
    bexportar.addMouseMotionListener(this);
    bexportar.setBorder(BorderFactory.createEmptyBorder());
    bexportar.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
    //bexportar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    bimportar.setToolTipText("Importar XMI");
    bimportar.setName("Importar");
    bimportar.addMouseListener(this);
    bimportar.addMouseMotionListener(this);
    bimportar.setBorder(BorderFactory.createEmptyBorder());
    bimportar.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
    //bimportar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    barra1.setFloatable(false);
    barra1.setBorder(BorderFactory.createEmptyBorder());
  }
  
  public void cargarEdicion()
  {
    barra2.setLayout(new GridLayout(1, 7, 0, 0));
    barra2.setLayout(new FlowLayout());
    
    barra2.add(bcopiar);
    barra2.add(bpegar);
    barra2.add(beliminar);
    barra2.addSeparator(new Dimension(2, 10));
    barra2.add(bfuente);
    barra2.add(bcolor);
    
    bcopiar.setToolTipText ("Copiar");
    bcopiar.setName("Copiar");
    bcopiar.addMouseListener(this);
    bcopiar.setBorder(BorderFactory.createEmptyBorder());
    bcopiar.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
    //bnuevo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    bpegar.setToolTipText ("Pegar");
    bpegar.setName("Pegar");
    bpegar.addMouseListener(this);
    bpegar.addMouseMotionListener(this);
    bpegar.setBorder(BorderFactory.createEmptyBorder());
    bpegar.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
    //babrir.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    beliminar.setToolTipText ("Eliminar");
    beliminar.setName("Eliminar");
    beliminar.addMouseListener(this);
    beliminar.addMouseMotionListener(this);
    beliminar.setBorder(BorderFactory.createEmptyBorder());
    beliminar.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
    //bguardar.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    bfuente.setToolTipText ("Fuente");
    bfuente.setName("Fuente");
    bfuente.addMouseListener(this);
    bfuente.addMouseMotionListener(this);
    bfuente.setBorder(BorderFactory.createEmptyBorder());
    bfuente.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
    //bexportar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    bcolor.setToolTipText("Color");
    bcolor.setName("Color");
    bcolor.addMouseListener(this);
    bcolor.addMouseMotionListener(this);
    bcolor.setBorder(BorderFactory.createEmptyBorder());
    bcolor.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
    //bimportar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    barra2.setFloatable(false);
    barra2.setBorder(BorderFactory.createEmptyBorder());
  }
  

  public void mouseClicked(MouseEvent e)
  {
  }
  
  /**
  * Invoked when a mouse button has been pressed on a component.
  */
  public void mousePressed(MouseEvent e)
  {
    //JButton obj = (JButton) e.getSource();
    //System.out.println("PRESIONO: "+obj.getName());
    if (e.getSource() instanceof JButton)
    {
      JButton obj = (JButton) e.getSource();
      if (obj.getName().equals("Nuevo"))
        form.nuevo();
      if (obj.getName().equals("Abrir"))
        form.abrirFile();
      if (obj.getName().equals("Guardar"))
        form.guardarFile();
      if (obj.getName().equals("GuardarC"))
        form.guardarFile();
      if (obj.getName().equals("Exportar"))
        form.exportarXMI();
      if (obj.getText().equals("Importar"))
        form.importarXMI();
      //*************************************
      if (obj.getName().equals("Copiar"))
      {
        objTabla = objcontrol.objTabla;
      }
      if (obj.getName().equals("Pegar"))
      {
        if (objTabla != null)
        {
          Rectangle s = objTabla.getSuperior();
          s.setLocation(0, 0);
          Rectangle i = objTabla.getInferior();
          clsTabla aux = new clsTabla(0, "", objTabla.getAcceso(), s, new Rectangle(s.x+(s.width/2)-5, s.y+s.height, 10, i.height));
          aux.setColumnas(objTabla.getColumnas());
          objcontrol.enviarTabla(aux);
        }
      }
      if (obj.getName().equals("Eliminar"))
      {
        objTabla = objcontrol.objTabla;
        objenlace= objcontrol.objconector;
        if (objTabla != null)
          objcontrol.eliminarTabla(objTabla.getId());
        if (objenlace != null)
          objcontrol.eliminarEnlace(objTabla.getId());
      }
      /*if (obj.getName().equals("Fuente"))
        form.guardarFile();
      if (obj.getName().equals("Color"))
        form.exportarXMI();*/
    }
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
    JButton obj = (JButton) e.getSource();
    //obj.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    //obj.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
    //obj.setBorder(null);
  }
  
  /**
  * ENVENTOS DEL MouseMotionListener
  */
  public void mouseMoved(MouseEvent e)
  {
    //JLabel obj = (JLabel) e.getSource();
    JButton obj = (JButton) e.getSource();
    //obj.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    //obj.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165)));
  }
  
  public void mouseDragged(MouseEvent e){}
}
