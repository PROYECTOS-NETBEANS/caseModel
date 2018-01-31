package Presentacion;

import Control.control;

import Negocio.*;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.LinkedList;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class frmEnlace extends JDialog
{
  private JLabel jLabel1 = new JLabel();
  private JComboBox jComboBox1 = new JComboBox();
  private JButton baceptar = new JButton();
  private JButton jButton2 = new JButton();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  
  private control objcontrol;
  private clsEnlace objconector;
  private clsClase objclase;
  private LinkedList<clsMetodo> metodo = new LinkedList<clsMetodo>();
  private clsMetodo objmetodo;
  private JButton jButton3 = new JButton();

  //private Vector nombre = new Vector();

  public frmEnlace()
  {
    this(null, "", false);
  }

  public frmEnlace(Frame parent, String title, boolean modal)
  {
    super(parent, title, modal);
  }

  private void jbInit() throws Exception
  {
    this.setSize(new Dimension(268, 179));
    this.getContentPane().setLayout( null );
    jLabel1.setText("Nombre:");
    jLabel1.setBounds(new Rectangle(20, 35, 55, 15));
    jComboBox1.setBounds(new Rectangle(85, 30, 165, 20));
    baceptar.setText("Aceptar");
    baceptar.setBounds(new Rectangle(30, 105, 90, 25));
    baceptar.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            baceptar_actionPerformed(e);
          }
        });
    jButton2.setText("Cancelar");
    jButton2.setBounds(new Rectangle(145, 105, 90, 25));
    jButton2.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            bcancelar_actionPerformed(e);
          }
        });
    jLabel2.setText("Secuencia:");
    jLabel2.setBounds(new Rectangle(20, 70, 65, 15));
    jTextField1.setBounds(new Rectangle(85, 65, 55, 20));
    this.getContentPane().add(jButton3, null);
    this.getContentPane().add(jTextField1, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(baceptar, null);
    this.getContentPane().add(jComboBox1, null);
    this.getContentPane().add(jLabel1, null);
    
    cargarMetodoCombo();
    jButton3.setText("Add Metodo");
    jButton3.setBounds(new Rectangle(145, 65, 105, 20));
    jButton3.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            jButton3_actionPerformed(e);
          }
        });
    /*
     *     for (int i = 0; i < dim; i++)
    {
      metodos objmetodo = metodo.get(i);
      String met = objmetodo.getNombre();
      LinkedList<parametros> parametro = objmetodo.getParametros();
      int s = parametro.size();
      String par = "(";
      for (int j = 0; j < s; j++)
      {
        parametros objparametro = parametro.get(i);
        par += objparametro.getTipo()+" "+objparametro.getNombre()+", ";
      }
      if (par.length() > 3)
        par = par.substring(0, par.length() - 2);
      par += ")";
      met += par+": "+objmetodo.getRetorna();
      jComboBox1.addItem(met);
      nombre.add(objmetodo.getNombre());
    }
     */
  }
  
  private void cargarMetodoCombo()
  {
    metodo = objclase.getMetodos();
    int dim = metodo.size();
    int index = 0;
    jComboBox1.removeAllItems();
    for (int i = 0; i < dim; i++)
    {
      clsMetodo aux = metodo.get(i);
      if (aux.getNombre().equals(objmetodo.getNombre()))
        index = i;
      jComboBox1.addItem(aux.getNombre());
    }
    //this.setTitle(objmetodo.getNombre()+" - Propiedades de un Conector");
    this.setTitle("Propiedades de un Conector");
    if (dim > 0)
      jComboBox1.setSelectedIndex(index);
    jTextField1.setText(""+objmetodo.getSecuencia());
  }
  
  public void cargarPropiedad(control objcontrol, clsClase objclase, clsEnlace objconector)
  {
    try
    {
      this.objcontrol = objcontrol;
      this.objclase   = objclase;
      //System.out.println("CONECTOR => "+(conector)objcontrol.getFigura());
      this.objconector = objconector;
      objmetodo = objconector.getObjmetodo();
      System.out.println("CONECTOR => "+objconector);
      jbInit();
    }
    catch (Exception e)
    { e.printStackTrace();  }
  }


  /*private void jButton1_actionPerformed(ActionEvent e)
  {
    
  }*/

  private void jButton3_actionPerformed(ActionEvent e)
  {
    frmClase formClase = new frmClase(null, "?", true);
    formClase.cargarPropiedad(objcontrol, objclase);
    formClase.setVisible(true);
    
    cargarMetodoCombo();
    /*objconector = (conector)objcontrol.getFigura();
    objmetodo = objconector.getObjmetodo();*/
  }

  private void baceptar_actionPerformed(ActionEvent e)
  {
    System.out.println("index combo => "+jComboBox1.getSelectedIndex());
    int index = jComboBox1.getSelectedIndex();
    if (index >= 0)
    {
      objmetodo = metodo.get(jComboBox1.getSelectedIndex());
      objmetodo.setSecuencia(Integer.parseInt(jTextField1.getText()));
      objconector.setObjmetodo(objmetodo);
      objcontrol.enviarActualizarConector(objconector);
    }
    this.setVisible(false);
  }

  private void bcancelar_actionPerformed(ActionEvent e)
  {
    this.setVisible(false);
  }
}
