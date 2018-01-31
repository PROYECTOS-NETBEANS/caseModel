 package Presentacion;

import Comun.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.JButton;

public class formServidor extends JFrame
{
  private JScrollPane spLista = new JScrollPane();
  private JList listUsuario = new JList();
  private JButton bOn = new JButton();
  private JButton bDesconectar = new JButton();
  private JButton bOff = new JButton();

  public formServidor()
  {
    try
    {
      jbInit();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    iniciarServidor();
  }

  private void jbInit() throws Exception
  {
    this.getContentPane().setLayout( null );
    this.setSize(new Dimension(248, 369));
    this.setTitle( "SERVIDOR" );
    this.addWindowListener(new WindowAdapter()
        {
          public void windowClosing(WindowEvent e)
          {
            this_windowClosing(e);
          }
        });
    spLista.setBounds(new Rectangle(10, 55, 225, 245));
    spLista.setBorder(BorderFactory.createTitledBorder("Usuarios Conectados"));
    bOn.setText("ON");
    bOn.setBounds(new Rectangle(10, 15, 95, 25));
    bOn.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            bOn_actionPerformed(e);
          }
        });
    bDesconectar.setText("Desconectar");
    bDesconectar.setBounds(new Rectangle(70, 305, 110, 25));
    bDesconectar.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            bDesconectar_actionPerformed(e);
          }
        });
    bOff.setText("OFF");
    bOff.setBounds(new Rectangle(130, 15, 100, 25));
    bOff.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            bOff_actionPerformed(e);
          }
        });
    spLista.getViewport().add(listUsuario, null);
    this.getContentPane().add(bOff, null);
    this.getContentPane().add(bDesconectar, null);
    this.getContentPane().add(bOn, null);
    this.getContentPane().add(spLista, null);
  }
  
  public void iniciarServidor()
  {
    try 
    {
      implementacionServidor objservidor = new implementacionServidor();
      objservidor.setJList(listUsuario);
      objservidor.iniciarServidor();
    }
    catch (RemoteException e) {
               e.printStackTrace();
            }
  }

  public static void main(String[] args)
  {
    JFrame.setDefaultLookAndFeelDecorated(true);
    formServidor objservidor = new formServidor();
    objservidor.setVisible(true);
    //objservidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void this_windowClosing(WindowEvent e)
  {
    System.exit(1);
  }

  private void bDesconectar_actionPerformed(ActionEvent e)
  {
  }

  private void bOn_actionPerformed(ActionEvent e)
  {
  }

  private void bOff_actionPerformed(ActionEvent e)
  {
  }
}
