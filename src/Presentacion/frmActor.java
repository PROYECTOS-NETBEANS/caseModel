package Presentacion;

import Control.control;

import Negocio.*;

import java.awt.Dimension;
import java.awt.Frame;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class frmActor extends JDialog
{
  private control objcontrol;
  private clsActor objactor;
  private JLabel jLabel1 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();

  public frmActor()
  {
    this(null, "", false);
  }

  public frmActor(Frame parent, String title, boolean modal)
  {
    super(parent, title, modal);
  }

  private void jbInit() throws Exception
  {
    this.setSize(new Dimension(252, 132));
    this.getContentPane().setLayout( null );
    this.setTitle(objactor.getNombre()+" - Propiedades de un Actor");
    jLabel1.setText("Nombre:");
    jLabel1.setBounds(new Rectangle(20, 20, 50, 15));
    jTextField1.setBounds(new Rectangle(80, 15, 150, 20));
    jTextField1.addKeyListener(new KeyAdapter()
        {
          public void keyReleased(KeyEvent e)
          {
            jTextField1_keyReleased(e);
          }
        });
    jButton1.setText("Aceptar");
    jButton1.setBounds(new Rectangle(25, 60, 85, 25));
    jButton1.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            jButton1_actionPerformed(e);
          }
        });
    jButton2.setText("Cancelar");
    jButton2.setBounds(new Rectangle(135, 60, 85, 25));
    jButton2.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            jButton2_actionPerformed(e);
          }
        });
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jTextField1, null);
    this.getContentPane().add(jLabel1, null);
  }
  
  public void cargarPropiedad(control objcontrol, clsActor objactor)
  {
    try
    {
      this.objcontrol = objcontrol;
      this.objactor = objactor;
      jbInit();
    }
    catch (Exception e)
    { e.printStackTrace();  }
  }

  private void jButton1_actionPerformed(ActionEvent e)
  {
    objactor.setNombre(jTextField1.getText());
    objcontrol.enviarActualizarActor(objactor);
    this.setVisible(false);
  }

  private void jButton2_actionPerformed(ActionEvent e)
  {
    this.setVisible(false);
  }

  private void jTextField1_keyReleased(KeyEvent e)
  {
    this.setTitle(jTextField1.getText()+" - Propiedades de un Actor");
  }
}