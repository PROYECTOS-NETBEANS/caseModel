package Presentacion;

import Control.control;

import Negocio.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;

import java.rmi.RemoteException;
import java.util.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class frmClase extends JDialog
{
  private JTabbedPane tbpropiedad = new JTabbedPane();
  private JPanel pgeneral = new JPanel(null);
  private JPanel patributos = new JPanel(null);
  private JPanel pmetodos = new JPanel(null);
  
  private JLabel glnombre = new JLabel();
  private JTextField gtfnombre = new JTextField();
  private JLabel glestereotipo = new JLabel();
  private JComboBox gcbestereotipo = new JComboBox();
  private JPanel gpacceso = new JPanel(null);
  private JRadioButton grbprivate = new JRadioButton();
  private JRadioButton grbprotected = new JRadioButton();
  private JRadioButton grbpublic = new JRadioButton();
  /*private JButton gbaceptar = new JButton();
  private JButton gbcancelar = new JButton();*/
  
  private JLabel alnombre = new JLabel();
  private JTextField atfnombre = new JTextField();
  private JLabel altipo = new JLabel();
  private JComboBox acbtipo = new JComboBox();
  private JPanel apacceso = new JPanel(null);
  private JRadioButton arbprivate = new JRadioButton();
  private JRadioButton arbprotected = new JRadioButton();
  private JRadioButton arbpublic = new JRadioButton();
  /*private JButton abaceptar = new JButton();
  private JButton abcancelar = new JButton();*/
  private JScrollPane aspatributo = new JScrollPane();
  private JList alatributos = new JList();
  private JButton abadicionar = new JButton();
  private JButton abeliminar = new JButton();
  
  
  private JLabel mlnombre = new JLabel();
  private JTextField mtfnombre = new JTextField();
  private JLabel mlretorno = new JLabel();
  private JComboBox mcbretorno = new JComboBox();
  private JButton mbparam = new JButton();
  private JPanel mpacceso = new JPanel(null);
  private JRadioButton mrbprivate = new JRadioButton();
  private JRadioButton mrbprotected = new JRadioButton();
  private JRadioButton mrbpublic = new JRadioButton();
  /*private JButton mbaceptar = new JButton();
  private JButton mbcancelar = new JButton();*/
  private JScrollPane mspmetodo = new JScrollPane();
  private JList mlmetodos = new JList();
  private JButton mbadicionar = new JButton();
  private JButton mbeliminar = new JButton();
  
  private JButton baceptar; 
  private JButton bcancelar;
  
  private control objcontrol;
  
  private clsClase objclase;
 // private conector objconector;
  
  private LinkedList<clsAtributo> atributo;
  private LinkedList<clsMetodo> metodo;
  private LinkedList<clsParametro> parametro;  
  
  //**************** DIALOGO PARA LOS EL FORMULARIO PARAMETROS *****************
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JTextField jTextField1;
  private JComboBox jComboBox1;
  private JScrollPane jScrollPane1;
  private JList jList1;
  private JButton jButton1;
  private JButton jButton2;
  private JButton jButton3;
  private JButton jButton4;
  private JDialog dparametro;  

  public frmClase(Frame parent, String title, boolean modal)
  {
    super(parent, title, modal);
  }
  
  public void cargarPropiedad(control objcontrol, clsClase objclase)
  {
    try
    {
      System.out.println("************* CARGAR PROPIEDAD => tipo="+objclase.getTipo());
      this.objcontrol = objcontrol;
      this.objclase = objclase;
      atributo = objclase.getAtributos();
      metodo   = objclase.getMetodos();
      jbInit();
    }
    catch (Exception e)
    { e.printStackTrace();  }
  }

  private void jbInit() throws Exception
  {
    this.setSize( new Dimension( 400, 300 ) );
    this.getContentPane().setLayout( null );
    tbpropiedad.setBounds(new Rectangle(10, 10, 375, 255));
    
    baceptar  = new JButton();
    bcancelar = new JButton();
    glnombre.setText("Nombre:");
    glnombre.setBounds(new Rectangle(5, 50, 65, 15));
    gtfnombre.setBounds(new Rectangle(75, 50, 165, 20));
    gtfnombre.addKeyListener(new KeyAdapter()
        {
          public void keyReleased(KeyEvent e)
          {
            gtfnombre_keyReleased(e);
          }
        });
    glestereotipo.setText("Estereotipo:");
    glestereotipo.setBounds(new Rectangle(5, 95, 70, 15));
    gcbestereotipo.setBounds(new Rectangle(75, 90, 165, 20));
    gcbestereotipo.addItem("Interfaz");
    gcbestereotipo.addItem("Proceso");
    gcbestereotipo.addItem("Entidad");
    gpacceso.setBounds(new Rectangle(250, 45, 105, 115));
    gpacceso.setBorder(BorderFactory.createTitledBorder("Acceso"));
    
    grbprivate.setText("private");
    grbprivate.setBounds(new Rectangle(10, 25, 85, 20));
    grbprivate.addMouseListener(new MouseAdapter()
        {
          public void mouseClicked(MouseEvent e)
          {
            grbprivate_mouseClicked(e);
          }
        });
    grbprotected.setText("protected");
    grbprotected.setBounds(new Rectangle(10, 55, 85, 20));
    grbprotected.addMouseListener(new MouseAdapter()
        {
          public void mouseClicked(MouseEvent e)
          {
            grbprotected_mouseClicked(e);
          }
        });
    grbpublic.setText("public");
    grbpublic.setBounds(new Rectangle(10, 85, 85, 20));
    grbpublic.setSelected(true);
    grbpublic.addMouseListener(new MouseAdapter()
        {
          public void mouseClicked(MouseEvent e)
          {
            grbpublic_mouseClicked(e);
          }
        });
    baceptar.setText("Aceptar");
    baceptar.setBounds(new Rectangle(30, 135, 85, 25));
    baceptar.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            baceptar_mousePressed(e);
          }
        });
    bcancelar.setText("Cancelar");
    bcancelar.setBounds(new Rectangle(135, 135, 85, 25));
    bcancelar.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            bcancelar_mousePressed(e);
          }
        });
    pgeneral.add(glnombre, null);
    pgeneral.add(gtfnombre, null);
    pgeneral.add(glestereotipo, null);
    pgeneral.add(gcbestereotipo, null);
    pgeneral.add(gpacceso, null);
    pgeneral.add(bcancelar, null);
    pgeneral.add(baceptar, null);
    gpacceso.add(grbpublic, null);
    gpacceso.add(grbprotected, null);
    gpacceso.add(grbprivate, null);
    
    baceptar  = new JButton();
    bcancelar = new JButton();
    alnombre.setText("Nombre:");
    alnombre.setBounds(new Rectangle(10, 15, 65, 15));
    atfnombre.setBounds(new Rectangle(80, 15, 170, 20));
    altipo.setText("Tipo:");
    altipo.setBounds(new Rectangle(10, 60, 70, 15));
    acbtipo.setBounds(new Rectangle(80, 55, 170, 20));
    apacceso.setBounds(new Rectangle(255, 10, 105, 115));
    apacceso.setBorder(BorderFactory.createTitledBorder("Acceso"));
    arbprivate.setText("private");
    arbprivate.setBounds(new Rectangle(10, 25, 85, 20));
    arbprivate.setSelected(true);
    arbprivate.addMouseListener(new MouseAdapter()
        {
          public void mouseClicked(MouseEvent e)
          {
            arbprivate_mouseClicked(e);
          }
        });
    arbprotected.setText("protected");
    arbprotected.setBounds(new Rectangle(10, 55, 85, 20));
    arbprotected.addMouseListener(new MouseAdapter()
        {
          public void mouseClicked(MouseEvent e)
          {
            arbprotected_mouseClicked(e);
          }
        });
    arbpublic.setText("public");
    arbpublic.setBounds(new Rectangle(10, 85, 85, 20));
    arbpublic.addMouseListener(new MouseAdapter()
        {
          public void mouseClicked(MouseEvent e)
          {
            arbpublic_mouseClicked(e);
          }
        });
    baceptar.setText("Aceptar");
    baceptar.setBounds(new Rectangle(35, 100, 85, 25));
    baceptar.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            baceptar_mousePressed(e);
          }
        });
    bcancelar.setText("Cancelar");
    bcancelar.setBounds(new Rectangle(140, 100, 85, 25));
    bcancelar.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            bcancelar_mousePressed(e);
          }
        });
    aspatributo.setBounds(new Rectangle(15, 140, 230, 75));
    aspatributo.getViewport().add(alatributos, null);
    abadicionar.setText("Adicionar");
    abadicionar.setBounds(new Rectangle(260, 145, 95, 25));
    abadicionar.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            abadicionar_mousePressed(e);
          }
        });
    abeliminar.setText("Eliminar");
    abeliminar.setBounds(new Rectangle(260, 185, 95, 25));
    abeliminar.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            abeliminar_mousePressed(e);
          }
        });
    patributos.add(alnombre, null);
    patributos.add(atfnombre, null);
    patributos.add(acbtipo, null);
    patributos.add(altipo, null);
    apacceso.add(arbpublic, null);
    apacceso.add(arbprotected, null);
    apacceso.add(arbprivate, null);
    patributos.add(apacceso, null);
    patributos.add(baceptar, null);
    patributos.add(bcancelar, null);
    patributos.add(aspatributo, null);
    patributos.add(abeliminar, null);
    patributos.add(abadicionar, null);
    
    baceptar  = new JButton();
    bcancelar = new JButton();
    mbeliminar.setText("Eliminar");
    mbeliminar.setBounds(new Rectangle(260, 185, 95, 25));
    mbeliminar.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            mbeliminar_mousePressed(e);
          }
        });
    mbadicionar.setText("Adicionar");
    mbadicionar.setBounds(new Rectangle(260, 145, 95, 25));
    mbadicionar.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            mbadicionar_mousePressed(e);
          }
        });
    mspmetodo.setBounds(new Rectangle(15, 140, 230, 75));
    mcbretorno.setBounds(new Rectangle(0, 0, 0, 0));
    mpacceso.setBounds(new Rectangle(255, 10, 105, 115));
    mpacceso.setBorder(BorderFactory.createTitledBorder("Acceso"));
    mlnombre.setText("Nombre:");
    mlnombre.setBounds(new Rectangle(10, 15, 50, 15));
    mcbretorno.setBounds(new Rectangle(60, 55, 115, 20));
    mlretorno.setText("Retorno:");
    mlretorno.setBounds(new Rectangle(10, 60, 50, 15));
    baceptar.setText("Aceptar");
    baceptar.setBounds(new Rectangle(35, 100, 85, 25));
    baceptar.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            baceptar_mousePressed(e);
          }
        });
    bcancelar.setText("Cancelar");
    bcancelar.setBounds(new Rectangle(140, 100, 85, 25));
    bcancelar.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            bcancelar_mousePressed(e);
          }
        });
    mtfnombre.setBounds(new Rectangle(60, 15, 190, 20));
    mrbprivate.setText("private");
    mrbprivate.setBounds(new Rectangle(10, 25, 85, 20));
    mrbprivate.addMouseListener(new MouseAdapter()
        {
          public void mouseClicked(MouseEvent e)
          {
            mrbprivate_mouseClicked(e);
          }
        });
    mrbprotected.setText("protected");
    mrbprotected.setBounds(new Rectangle(10, 55, 85, 20));
    mrbprotected.addMouseListener(new MouseAdapter()
        {
          public void mouseClicked(MouseEvent e)
          {
            mrbprotected_mouseClicked(e);
          }
        });
    mrbpublic.setText("public");
    mrbpublic.setBounds(new Rectangle(10, 85, 85, 20));
    mrbpublic.setSelected(true);
    mrbpublic.addMouseListener(new MouseAdapter()
        {
          public void mouseClicked(MouseEvent e)
          {
            mrbpublic_mouseClicked(e);
          }
        });
    mbparam.setText("Param");
    mbparam.setBounds(new Rectangle(180, 55, 70, 20));
    mbparam.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            mbparam_mousePressed(e);
          }
        });
    pmetodos.add(mbparam, null);
    pmetodos.add(mtfnombre, null);
    pmetodos.add(bcancelar, null);
    pmetodos.add(baceptar, null);
    pmetodos.add(mlretorno, null);
    pmetodos.add(mcbretorno, null);
    pmetodos.add(mlnombre, null);
    mpacceso.add(mrbpublic, null);
    mpacceso.add(mrbprotected, null);
    mpacceso.add(mrbprivate, null);
    pmetodos.add(mpacceso, null);
    pmetodos.add(mcbretorno, null);
    mspmetodo.getViewport().add(mlmetodos, null);
    pmetodos.add(mspmetodo, null);
    pmetodos.add(mbadicionar, null);
    pmetodos.add(mbeliminar, null);

    tbpropiedad.addTab("General", pgeneral);
    tbpropiedad.addTab("Atributos", patributos);
    tbpropiedad.addTab("Metodos", pmetodos);
    this.getContentPane().add(tbpropiedad, null);
    
    cargarGeneral();
    cargarAtributo();
    cargarMetodo();
  }
  
  private void cargarGeneral()
  {
    this.setTitle(objclase.getNombre()+" - Propiedades de una Clase");
    gtfnombre.setText(objclase.getNombre());
    gcbestereotipo.setSelectedItem(objclase.getClassinterface());
    if (grbprivate.getText().equals(objclase.getAcceso()))
      cargarGeneralSelect(true, false, false);
    if (grbprotected.getText().equals(objclase.getAcceso()))
      cargarGeneralSelect(false, true, false);
    if (grbpublic.getText().equals(objclase.getAcceso()))
      cargarGeneralSelect(false, false, true);
  }
  
  private void cargarAtributo()
  {
    atfnombre.setText("");
    acbtipo.removeAllItems();
    acbtipo.addItem("int");
    acbtipo.addItem("boolean");
    acbtipo.addItem("float");
    acbtipo.addItem("double");
    acbtipo.addItem("String");
    acbtipo.addItem("char");
    acbtipo.setSelectedIndex(0);
    
    int dim = atributo.size();
    Vector<String> items = new Vector<String>();
    cargarAtributoSelect(true, false, false);
    for (int i = 0; i < dim; i++)
    {
      clsAtributo objatributo = atributo.get(i);
      items.add(objatributo.getAcceso()+" "+objatributo.getNombre()+": "+objatributo.getTipo());
    }
    alatributos.setListData(items);
  }
  
  private void cargarMetodo()
  {
    mtfnombre.setText("");
    mcbretorno.removeAllItems();
    mcbretorno.addItem("void");
    mcbretorno.addItem("int");
    mcbretorno.addItem("boolean");
    mcbretorno.addItem("float");
    mcbretorno.addItem("double");
    mcbretorno.addItem("String");
    mcbretorno.addItem("char");
    mcbretorno.setSelectedIndex(0);
    
    int dim = metodo.size();
    Vector<String> items = new Vector<String>();
    cargarMetodoSelect(true, false, false);
    for (int i = 0; i < dim; i++)
    {
      clsMetodo objmetodo = metodo.get(i);
      parametro = objmetodo.getParametros();
      String pars = "";
      int k = parametro.size();
      for (int j = 0; j < k; j++)
      {
        clsParametro objparametro = parametro.get(j);
        pars = pars + objparametro.getTipo()+" "+objparametro.getNombre()+",";
      }
      if (!pars.equals(""))
        pars = pars.substring(0, pars.length()-1);
      items.add(objmetodo.getAcceso()+" "+objmetodo.getNombre()+"("+pars+"): "+objmetodo.getRetorna());
    }
    mlmetodos.setListData(items);
    parametro = new LinkedList<clsParametro>();
  }
  
  private void cargarGeneralSelect(boolean op1, boolean op2, boolean op3)
  {
    grbprivate.setSelected(op1);
    grbprotected.setSelected(op2);
    grbpublic.setSelected(op3);
  }
  
  private void cargarAtributoSelect(boolean op1, boolean op2, boolean op3)
  {
    arbprivate.setSelected(op1);
    arbprotected.setSelected(op2);
    arbpublic.setSelected(op3);
  }
  
  private void cargarMetodoSelect(boolean op1, boolean op2, boolean op3)
  {
    mrbprivate.setSelected(op1);
    mrbprotected.setSelected(op2);
    mrbpublic.setSelected(op3);
  }
  
  private void gtfnombre_keyReleased(KeyEvent e)
  {
    this.setTitle(gtfnombre.getText()+" - Propiedades de una Clase");
  }

  private void grbprivate_mouseClicked(MouseEvent e)
  {
    cargarGeneralSelect(true, false, false);
  }

  private void grbprotected_mouseClicked(MouseEvent e)
  {
    cargarGeneralSelect(false, true, false);
  }

  private void grbpublic_mouseClicked(MouseEvent e)
  {
    cargarGeneralSelect(false, false, true);
  }

  private void arbprivate_mouseClicked(MouseEvent e)
  {
    cargarAtributoSelect(true, false, false);
  }
  
  private void arbprotected_mouseClicked(MouseEvent e)
  {
    cargarAtributoSelect(false, true, false);
  }

  private void arbpublic_mouseClicked(MouseEvent e)
  {
    cargarAtributoSelect(false, false, true);
  }
  
  private void mrbprivate_mouseClicked(MouseEvent e)
  {
    cargarMetodoSelect(true, false, false);
  }

  private void mrbprotected_mouseClicked(MouseEvent e)
  {
    cargarMetodoSelect(false, true, false);
  }

  private void mrbpublic_mouseClicked(MouseEvent e)
  {
    cargarMetodoSelect(false, false, true);
  }
  
  private void abadicionar_mousePressed(MouseEvent e)
  {
    String acceso = "";
    if (arbprivate.isSelected())
      acceso = arbprivate.getText();
    if (arbprotected.isSelected())
      acceso = arbprotected.getText();
    if (arbpublic.isSelected())
      acceso = arbpublic.getText();


    clsAtributo objatributo = new clsAtributo(atfnombre.getText(), acbtipo.getSelectedItem().toString(), acceso);
    objcontrol.addAtributo(objclase.getId(), objatributo, true);
    /*atributo.add(objatributo);
    cargarAtributo();*/
  }
  
  private void abeliminar_mousePressed(MouseEvent e)
  {
    if (alatributos.getSelectedIndex() >= 0)
    {
      String aux = alatributos.getSelectedValue().toString();
      int dim = atributo.size();
      for (int i = 0; i < dim; i++)
      {
        clsAtributo objatributo = atributo.get(i);
        String cad = objatributo.getAcceso()+" "+objatributo.getNombre()+": "+objatributo.getTipo();
        if (cad.equals(aux))
        {
          objcontrol.delAtributo(objclase.getId(), objatributo.getNombre(), true);
          break;
        }
      }
      //cargarAtributo();
    }
  }
  
  public void actualizarAtributo(clsClase objclase)
  {
    this.objclase = objclase;
    atributo = this.objclase.getAtributos();
    cargarAtributo();
  }

  private void mbadicionar_mousePressed(MouseEvent e)
  {
    String acceso = "";
    if (mrbprivate.isSelected())
      acceso = mrbprivate.getText();
    if (mrbprotected.isSelected())
      acceso = mrbprotected.getText();
    if (mrbpublic.isSelected())
      acceso = mrbpublic.getText();
    clsMetodo objmetodo = new clsMetodo(mtfnombre.getText(), mcbretorno.getSelectedItem().toString(), acceso);
    objmetodo.setParametros(parametro);
    /*metodo.add(objmetodo);
    cargarMetodo();*/
    //System.out.println("+++++++++++++++++++++++ PASO INICIAL => tipo="+objclase.getTipo());
    objcontrol.addMetodo(objclase.getId(), objmetodo, true);
  }

  private void mbeliminar_mousePressed(MouseEvent e)
  {
    if (mlmetodos.getSelectedIndex() >= 0)
    {
      String aux = mlmetodos.getSelectedValue().toString();
      int dim = metodo.size();
      for (int i = 0; i < dim; i++)
      {
        clsMetodo objmetodo = metodo.get(i);
        
        parametro = objmetodo.getParametros();
        String pars = "";
        int k = parametro.size();
        for (int j = 0; j < k; j++)
        {
          clsParametro objparametro = parametro.get(j);
          pars = pars + objparametro.getTipo()+" "+objparametro.getNombre()+",";
        }
        if (!pars.equals(""))
          pars = pars.substring(0, pars.length()-1);
        String cad = objmetodo.getAcceso()+" "+objmetodo.getNombre()+"("+pars+"): "+objmetodo.getRetorna();
        if (cad.equals(aux))
        {
          //metodo.remove(i);
          objcontrol.delMetodo(objclase.getId(), objmetodo, true);
          break;
        }
      }
      //cargarMetodo();
    }
  }
  
  public void actualizarMetodo(clsClase objclase)
  {
    this.objclase = objclase;
    metodo = this.objclase.getMetodos();
    cargarMetodo();
  }
  

  private void mbparam_mousePressed(MouseEvent e)
  {
    cargarDialogoParametros();
    cargarParametros();
    dparametro.setVisible(true);
  }
  
  private void cargarDialogoParametros()
  {
    jLabel1 = new JLabel();
    jLabel2 = new JLabel();
    jTextField1 = new JTextField();
    jComboBox1 = new JComboBox();
    jScrollPane1 = new JScrollPane();
    jList1 = new JList();
    jButton1 = new JButton();
    jButton2 = new JButton();
    jButton3 = new JButton();
    jButton4 = new JButton();
    dparametro = new JDialog(this, mtfnombre.getText(), true);
    
    parametro = new LinkedList<clsParametro>();
    
    dparametro.setSize(new Dimension(279, 172));
    dparametro.getContentPane().setLayout( null );
    jLabel1.setText("Nombre:");
    jLabel1.setBounds(new Rectangle(15, 15, 55, 15));
    jLabel2.setText("Tipo:");
    jLabel2.setBounds(new Rectangle(15, 50, 45, 15));
    jTextField1.setBounds(new Rectangle(75, 15, 105, 20));
    jComboBox1.setBounds(new Rectangle(75, 50, 105, 20));
    jScrollPane1.setBounds(new Rectangle(10, 80, 170, 55));
    jButton1.setText("Aceptar");
    jButton1.setBounds(new Rectangle(190, 15, 75, 25));
    jButton1.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            jButton1_mousePressed(e);
          }
        });
    jButton2.setText("Cancelar");
    jButton2.setBounds(new Rectangle(190, 45, 75, 25));
    jButton2.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            jButton2_mousePressed(e);
          }
        });
    jButton3.setText("Adicionar");
    jButton3.setBounds(new Rectangle(190, 80, 75, 25));
    jButton3.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            jButton3_mousePressed(e);
          }
        });
    jButton4.setText("Eliminar");
    jButton4.setBounds(new Rectangle(190, 110, 75, 25));
    jButton4.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            jButton4_mousePressed(e);
          }
        });
    jScrollPane1.getViewport().add(jList1, null);
    dparametro.getContentPane().add(jButton4, null);
    dparametro.getContentPane().add(jButton3, null);
    dparametro.getContentPane().add(jButton2, null);
    dparametro.getContentPane().add(jButton1, null);
    dparametro.getContentPane().add(jScrollPane1, null);
    dparametro.getContentPane().add(jComboBox1, null);
    dparametro.getContentPane().add(jTextField1, null);
    dparametro.getContentPane().add(jLabel2, null);
    dparametro.getContentPane().add(jLabel1, null);
  }
  
  private void cargarParametros()
  {
    jTextField1.setText("");
    jComboBox1.removeAllItems();
    jComboBox1.addItem("int");
    jComboBox1.addItem("boolean");
    jComboBox1.addItem("float");
    jComboBox1.addItem("double");
    jComboBox1.addItem("String");
    jComboBox1.addItem("char");
    jComboBox1.setSelectedIndex(0);
    
    Vector<String> items = new Vector<String>();
    int dim = parametro.size();
    for (int i = 0; i < dim; i++)
    {
      clsParametro objparametro = parametro.get(i);
      items.add(objparametro.getNombre()+": "+objparametro.getTipo());
    }
    jList1.setListData(items);
  }
  
  private void jButton1_mousePressed(MouseEvent e)
  {
    dparametro.setVisible(false);
  }

  private void jButton2_mousePressed(MouseEvent e)
  {
    parametro = new LinkedList<clsParametro>();
    dparametro.setVisible(false);
  }

  private void jButton3_mousePressed(MouseEvent e)
  {
    clsParametro objparametro = new clsParametro(jTextField1.getText(), jComboBox1.getSelectedItem().toString());
    parametro.add(objparametro);
    cargarParametros();
  }

  private void jButton4_mousePressed(MouseEvent e)
  {
    if (jList1.getSelectedIndex() >= 0)
    {
      String aux = jList1.getSelectedValue().toString();
      int dim = parametro.size();
      for (int i = 0; i < dim; i++)
      {
        clsParametro objparametro = parametro.get(i);
        String cad = objparametro.getNombre()+": "+objparametro.getTipo();
        if (cad.equals(aux))
        {
          parametro.remove(i);
          break;
        }
      }
      cargarParametros();
    }
  }
  
  private void baceptar_mousePressed(MouseEvent e)
  {
    objclase.setNombre(gtfnombre.getText());
    objclase.setClassinterface(gcbestereotipo.getSelectedItem().toString());
    if (grbprivate.isSelected())
      objclase.setAcceso(grbprivate.getText());
    if (grbprotected.isSelected())
      objclase.setAcceso(grbprotected.getText());
    if (grbpublic.isSelected())
      objclase.setAcceso(grbpublic.getText());
    //objclase.setAtributos(atributo);
    objcontrol.enviarActualizarClase(objclase, true);
    this.setVisible(false);
  }
  
  private void bcancelar_mousePressed(MouseEvent e)
  {
    this.setVisible(false);
  }
}