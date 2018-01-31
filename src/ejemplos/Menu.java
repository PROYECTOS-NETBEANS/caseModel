package ejemplos;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;

import javax.swing.*;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

class Menu extends JFrame {
 //Toolbar
 JToolBar TBarra=new JToolBar(); 
  JButton BNuevo=new JButton("N");
  JButton BAbrir=new JButton("A");
  JButton BCopiar=new JButton("C");
  JButton BCortar=new JButton("C");
  JButton BPegar=new JButton("P"); 
  JButton BGuardar=new JButton("G");


  public Menu() {

  //ToolBar
  TBarra.setLayout(new FlowLayout());
  TBarra.setBackground(new Color(148, 181, 255));
    TBarra.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
  TBarra.add(BNuevo);
  TBarra.add(BAbrir);
  TBarra.add(BGuardar);
  TBarra.addSeparator();
  TBarra.add(BCopiar);
  TBarra.add(BCortar);
  TBarra.add(BPegar); 
  
  BGuardar.setToolTipText ("Guardar");
  BNuevo.setToolTipText ("Nuevo");
  BAbrir.setToolTipText ("Abrir");
  BCopiar.setToolTipText ("Copiar");
  BCortar.setToolTipText ("BCortar");
  BPegar.setToolTipText ("Pegar");

  
  add(TBarra,"North");
  
   //add(TBarra, "South");
   //   add(TBarra,"East");

  TBarra.setFloatable(false);  
  setTitle("Ejemplos JPopUpMenu"); 
     setSize(800,600);
  setVisible(true);
  setLayout(null);
    try
    {
      jbInit();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    
  }
    
 public static void main (String []args){
  new Menu();
 }

  private void jbInit() throws Exception
  {
    //this.getContentPane().setLayout(null);
    
    //objb.repaint();
  }
}
