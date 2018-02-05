package Presentacion;

import Comun.Constantes;
import Control.control;

import Negocio.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class frmTabla extends JDialog
{
  private JTabbedPane tbpropiedad = new JTabbedPane();
  private JPanel pgeneral = new JPanel(null);
  private JPanel patributos = new JPanel(null);
  
  private JLabel glnombre = new JLabel();
  private JTextField gtfnombre = new JTextField();
  
  private JLabel alnombre = new JLabel();
  private JTextField atfnombre = new JTextField();
  private JLabel altipo = new JLabel();
  
  private JComboBox acbtipo = new JComboBox();
  
  private final JLabel altamaño = new JLabel();
  private final JTextField attamaño = new JTextField();
  
  private final JLabel alscala = new JLabel();
  private final JTextField atscala = new JTextField();  
  
  private JCheckBox achprimarykey = new JCheckBox();
  
  private JScrollPane aspatributo = new JScrollPane();
  
  private DefaultListModel modelo = new DefaultListModel();
  private JList alcolumnas = new JList(modelo);
  
  
  private JButton abadicionar = new JButton();
  private JButton abeliminar = new JButton();  
  
  private control objcontrol;
  
  private clsTabla objclase;

  private final JButton btnAceptar = new JButton();
  private final JButton btnCancelar = new JButton();
  
  private LinkedList<clsColumna> columnas;

    
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
      columnas = objclase.getColumnas();
      jbInit();
    }
    catch (Exception e)
    { e.printStackTrace();  }
  }

  private void jbInit() throws Exception
  {
    btnAceptar.setText("Aceptar");
    btnAceptar.setBounds(new Rectangle(260, 145, 95, 25));
    btnAceptar.addMouseListener(new MouseAdapter()
        {
          @Override
          public void mousePressed(MouseEvent e)
          {
            btnaceptar_mousePressed(e);
          }
    });

    btnCancelar.setText("Cancelar");
    btnCancelar.setBounds(new Rectangle(50, 145, 95, 25));
    btnCancelar.addMouseListener(new MouseAdapter()
        {
          @Override
          public void mousePressed(MouseEvent e)
          {
            btncancelar_mousePressed(e);
          }
    });    
    
    this.setSize( new Dimension( 400, 300 ) );
    this.getContentPane().setLayout( null );
    tbpropiedad.setBounds(new Rectangle(10, 10, 375, 255));
    
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
    
    pgeneral.add(glnombre, null);
    pgeneral.add(gtfnombre, null);
    pgeneral.add(btnAceptar, null);
    pgeneral.add(btnCancelar, null);
    
    alnombre.setText("Nombre:");
    alnombre.setBounds(new Rectangle(10, 15, 65, 15));
    atfnombre.setBounds(new Rectangle(80, 15, 170, 20));
    
    
    acbtipo.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                deshabilitarControles(e.getItem().toString());
            }            
        }
    });
    altipo.setText("Tipo:");
    altipo.setBounds(new Rectangle(10, 40, 70, 15));
    acbtipo.setBounds(new Rectangle(80, 40, 170, 20));
    
    altamaño.setText("Tamaño:");
    altamaño.setBounds(new Rectangle(10, 65, 70, 15));
    attamaño.setBounds(new Rectangle(80, 65, 50, 20));

    alscala.setText("Escala:");
    alscala.setBounds(new Rectangle(10, 90, 70, 15));
    atscala.setBounds(new Rectangle(80, 90, 50, 20));
    
    achprimarykey.setText("Llave primaria");
    achprimarykey.setBounds(new Rectangle(80, 115, 170, 15));
        
    aspatributo.setBounds(new Rectangle(15, 140, 230, 75));
    aspatributo.getViewport().add(alcolumnas, null);
    abadicionar.setText("Adicionar");
    abadicionar.setBounds(new Rectangle(260, 145, 95, 25));
    abadicionar.addMouseListener(new MouseAdapter()
        {
          @Override
          public void mousePressed(MouseEvent e)
          {
            abadicionar_mousePressed(e);
          }
        });
    abeliminar.setText("Eliminar");
    abeliminar.setBounds(new Rectangle(260, 185, 95, 25));
    abeliminar.addMouseListener(new MouseAdapter()
        {
          @Override
          public void mousePressed(MouseEvent e)
          {
            abeliminar_mousePressed(e);
          }
        });
    patributos.add(alnombre, null);
    patributos.add(atfnombre, null);
    
    patributos.add(acbtipo, null);
    patributos.add(altipo, null);
    
    patributos.add(altamaño, null);
    patributos.add(attamaño, null);    
    
    patributos.add(alscala, null);
    patributos.add(atscala, null);
    
    patributos.add(aspatributo, null);
    patributos.add(abeliminar, null);
    patributos.add(abadicionar, null);
    
    patributos.add(achprimarykey, null);
    
    tbpropiedad.addTab("General", pgeneral);
    tbpropiedad.addTab("Columnas", patributos);
    this.getContentPane().add(tbpropiedad, null);
    
    cargarGeneral();
    cargarColumnas();
  }
  
  private void cargarGeneral()
  {
    this.setTitle(objclase.getNombreTabla()+" - Columnas de Tabla");
    gtfnombre.setText(objclase.getNombreTabla());
  }
  
  private void cargarColumnas()
  {
    atfnombre.setText("");
    
    acbtipo.removeAllItems();    
    acbtipo.addItem(Constantes.ENTERO);
    acbtipo.addItem(Constantes.DECIMAL);
    acbtipo.addItem(Constantes.CADENA);
    acbtipo.addItem(Constantes.FECHA);
    
    acbtipo.setSelectedIndex(0);
    achprimarykey.setSelected(false);
    int dim = columnas.size();
    modelo.removeAllElements();
    for (int i = 0; i < dim; i++)
    {
      clsColumna objatributo = columnas.get(i);
      modelo.addElement(objatributo.getNombre()+": "+objatributo.getTipo());
    }
    alcolumnas.setModel(modelo);
  }
        
  private void gtfnombre_keyReleased(KeyEvent e)
  {
    this.setTitle(gtfnombre.getText()+" - Columnas de una Tabla");
  }

  private void abadicionar_mousePressed(MouseEvent e)
  { try{
        validar();
        clsColumna objcolumna = new clsColumna(atfnombre.getText(), acbtipo.getSelectedItem().toString(), attamaño.getText(), atscala.getText(), achprimarykey.isSelected());        
        modelo.addElement(objcolumna.getNombre()+": "+objcolumna.getTipo());
        columnas.add(objcolumna);
        objcontrol.addColumna(objclase.getId(), objcolumna);     
        
    }catch(Exception ex ){
       JOptionPane.showMessageDialog(this, ex.getMessage());
       System.out.println("frmTabla.abadicionar_mousePressed");
    }
  }
  private void validar() throws Exception
  {
        String m = "";       
        if(atfnombre.getText().trim().length() <=0 )
            m += "ingrese un nombre de columna \n";
        
        if(atfnombre.getText().indexOf(" ") > 0)
            m += "ingrese un nombre de columna sin ESPACIOS \n";
        
        switch (acbtipo.getSelectedItem().toString()) {
            case Constantes.CADENA:
                if(attamaño.getText().trim().length() <= 0)
                    m += "ingrese tamaño de cadena \n";
                break;
            case Constantes.DECIMAL:
                if(atscala.getText().trim().length() <= 0)
                    m += "ingrese nro de decimales \n";
                if(attamaño.getText().trim().length() <= 0)
                    m += "ingrese tamaño de decimal \n";                
                break;
            default:
                 System.out.println("no se requiere validacion");
        }
       
        if(m.length() > 0)
            throw new Exception(m);
  }  
    private void abeliminar_mousePressed(MouseEvent e)
    { 
      if (alcolumnas.getSelectedIndex() >= 0)
      {
      
        String aux = alcolumnas.getSelectedValue().toString();
        int dim = columnas.size();
        for (int i = 0; i < dim; i++)
        {
          clsColumna objatributo = columnas.get(i);
          String cad = objatributo.getNombre()+": "+objatributo.getTipo();
          if (cad.equals(aux))
          {
            objcontrol.delColumna(objclase.getId(), objatributo.getNombre());
            modelo.removeElement(cad);
            columnas.remove(objatributo);
            atfnombre.setText("");
            break;
          }
        }
      }
    }

    public void actualizarAtributo(clsTabla objclase)
    {
      this.objclase = objclase;
      columnas = this.objclase.getColumnas();
      cargarColumnas();
    }

    public void btnaceptar_mousePressed(MouseEvent e){
        if(gtfnombre.getText().indexOf(" ") > 0){
           JOptionPane.showInternalMessageDialog(this, "ingrese un nombre de tabla sin ESPACIOS", "Error", JOptionPane.ERROR_MESSAGE); 
        }else{
            objclase.setNombreTabla(gtfnombre.getText());        
            objcontrol.enviarActualizarTabla(objclase);
            this.setVisible(false);            
        }
    }
    public void btncancelar_mousePressed(MouseEvent e){
        this.setVisible(false);
    } 
    public void deshabilitarControles(String tipo){
        switch (tipo) {
            case Constantes.CADENA:
                atscala.setEnabled(false);
                attamaño.setEnabled(true);
                break;
            case Constantes.DECIMAL: 
                atscala.setEnabled(true);
                attamaño.setEnabled(true);                
                break;
            default:
                atscala.setEnabled(false);
                attamaño.setEnabled(false);
        }
    }
}