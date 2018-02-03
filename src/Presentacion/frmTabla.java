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

public class frmTabla extends JDialog
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
  
  private clsTabla objclase;
 // private conector objconector;
  
  private LinkedList<clsColumna> atributo;
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

  public frmTabla(Frame parent, String title, boolean modal)
  {
    super(parent, title, modal);
  }
  
  public void cargarPropiedad(control objcontrol, clsTabla objclase)
  {
    try
    {
      System.out.println("************* CARGAR PROPIEDAD => tipo=");
      this.objcontrol = objcontrol;
      this.objclase = objclase;
      atributo = objclase.getColumnas();
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
              System.out.println("no implementas");
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
  }
  
  private void cargarGeneral()
  {
    this.setTitle(objclase.getNombreTabla()+" - Propiedades de Tabla");
    gtfnombre.setText(objclase.getNombreTabla());
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
      clsColumna objatributo = atributo.get(i);
      items.add(objatributo.getAcceso()+" "+objatributo.getNombre()+": "+objatributo.getTipo());
    }
    alatributos.setListData(items);
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


    clsColumna objatributo = new clsColumna(atfnombre.getText(), acbtipo.getSelectedItem().toString(), acceso);
    objcontrol.addColumna(objclase.getId(), objatributo);
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
        clsColumna objatributo = atributo.get(i);
        String cad = objatributo.getAcceso()+" "+objatributo.getNombre()+": "+objatributo.getTipo();
        if (cad.equals(aux))
        {
          objcontrol.delColumna(objclase.getId(), objatributo.getNombre());
          break;
        }
      }
      //cargarAtributo();
    }
  }
  
  public void actualizarAtributo(clsTabla objclase)
  {
    this.objclase = objclase;
    atributo = this.objclase.getColumnas();
    cargarAtributo();
  }

  private void mbadicionar_mousePressed(MouseEvent e)
  {
      System.out.println("frmTabla.mbadicionar_mousePressed no implemnetato! x limbert");
      /*
    String acceso = "";
    if (mrbprivate.isSelected())
      acceso = mrbprivate.getText();
    if (mrbprotected.isSelected())
      acceso = mrbprotected.getText();
    if (mrbpublic.isSelected())
      acceso = mrbpublic.getText();
    
    clsMetodo objmetodo = new clsMetodo(mtfnombre.getText(), mcbretorno.getSelectedItem().toString(), acceso);
    objmetodo.setParametros(parametro);
    //System.out.println("+++++++++++++++++++++++ PASO INICIAL => tipo="+objclase.getTipo());
    objcontrol.addMetodo(objclase.getId(), objmetodo, true);
    */
  }

  private void mbeliminar_mousePressed(MouseEvent e)
  {
      System.out.println("frmTabla.mbeliminar_mousePressed no implemnet limbert");
    /*
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
    */
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
    }
  }
  
  private void baceptar_mousePressed(MouseEvent e)
  {
    objclase.setNombreTabla(gtfnombre.getText());
    objcontrol.enviarActualizarTabla(objclase, true);
    this.setVisible(false);
  }
  
  private void bcancelar_mousePressed(MouseEvent e)
  {
    this.setVisible(false);
  }
}