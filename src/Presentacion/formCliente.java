package Presentacion;

import Archivo.generarCodigo;

import Archivo.guardarXMI;
import Archivo.guardarXML;
import Archivo.leerXMI;
import Archivo.leerXML;

import Negocio.*;

import Comun.*;


import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.event.ActionEvent;

import java.awt.event.MouseEvent;

import java.io.File;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.rmi.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class formCliente extends JFrame implements interfaceEvento
{
  private JScrollPane spchat  = new JScrollPane();
  private JTextArea tachat    = new JTextArea();
  private JTextField tfchat   = new JTextField();
  private JButton benviar     = new JButton();
  
  public Registry registro;
  private int puerto  = 3333;
  private String ip   = "127.0.0.1";
  private String name = "DIAGRAMA_SECUENCIA";
  private String usuario                    = "";
  private interfaceServidor objservidor     = null;
  private implementacionCliente objcliente  = null;
  
  //private JToolBar jToolBar1 = new JToolBar();
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu mArchivo      = new JMenu();
  private JMenuItem miNuevo     = new JMenuItem();
  private JMenuItem miAbrir     = new JMenuItem();
  private JMenuItem miGuardar   = new JMenuItem();
  private JMenuItem miExportar  = new JMenuItem();
  private JMenuItem miSalir     = new JMenuItem();
  
  private JFileChooser selectfile;
  private fileFilter objfilter = null;
  private String url = "C:/";
  private JMenu mTools = new JMenu();
  private JMenuItem miGenCod = new JMenuItem();
  private JMenuItem miImportar = new JMenuItem();
  
  private DynamicTree arbol = new DynamicTree();
  private JScrollPane spgraficador = new JScrollPane();
  private graficadorDiagSec objgraficador;
  private barraDiagramaSecuencia objdiagrama;
  private barraMenu objmenu;
  //private JPanel jPanel1 = new JPanel(new BorderLayout());
  
  //************************ PANELES ***********************
  //private JPanel pnorte = new JPanel(new FlowLayout());
  private JPanel pnorte = new JPanel(new BorderLayout());
  private JPanel psur = new JPanel(new BorderLayout()); 
  private JPanel pcentro = new JPanel(new BorderLayout()); 
  private JPanel peste = new JPanel(new BorderLayout()); 
  private JPanel poeste = new JPanel(new BorderLayout());
  private JMenuItem jMenuItem1 = new JMenuItem();
  private JMenu mEdicion = new JMenu();
  private JMenuItem jMenuItem2 = new JMenuItem();
  private JMenuItem jMenuItem3 = new JMenuItem();
  private JMenuItem jMenuItem4 = new JMenuItem();
  private JMenuItem jMenuItem5 = new JMenuItem();
  private JMenuItem jMenuItem6 = new JMenuItem();
  private JMenu Ayuda = new JMenu();
  private JMenuItem jMenuItem7 = new JMenuItem();
  private JMenuItem jMenuItem8 = new JMenuItem();

  public formCliente()
  {
    try
    { jbInit(); }
    catch (Exception e)
    { e.printStackTrace();  }
    conectar();
  }

  private void jbInit() throws Exception
  {
    //this.getContentPane().setLayout( null );
    this.getContentPane().setLayout(new BorderLayout(0, 0));
    this.setSize(new Dimension(650, 450));
    this.setTitle( "CLIENTE" );
    this.setBounds(new Rectangle(10, 10, 650, 450));
    this.setJMenuBar(jMenuBar1);
    this.addWindowListener(new WindowAdapter()
        {
          public void windowClosing(WindowEvent e)
          { this_windowClosing(e);  }
        });
    //spchat.setBounds(new Rectangle(10, 200, 200, 110));
    //tfchat.setBounds(new Rectangle(10, 320, 110, 25));
    benviar.setText("ENVIAR");
    benviar.setBounds(new Rectangle(125, 320, 85, 25));
    tachat.setEditable(false);
    pcentro.add(spgraficador, BorderLayout.CENTER);  //  CENTRO
    spchat.getViewport().add(tachat, BorderLayout.CENTER);
    spchat.setPreferredSize(new Dimension(400, 100));
    JPanel pchat = new JPanel(new BorderLayout());
    /*psur.add(spchat, );     //  SUR
    psur.add(benviar);
    psur.add(tfchat);*/
    pchat.add(spchat, BorderLayout.CENTER);
    JPanel penviar = new JPanel(new BorderLayout());
    penviar.add(tfchat, BorderLayout.CENTER);
    penviar.add(benviar, BorderLayout.EAST);
    pchat.add(penviar, BorderLayout.SOUTH);
    mArchivo.add(miNuevo);
    mArchivo.add(miAbrir);
    mArchivo.addSeparator();
    mArchivo.add(miGuardar);
    mArchivo.add(jMenuItem1);
    mArchivo.addSeparator();
    mArchivo.add(miImportar);
    mArchivo.add(miExportar);
    mArchivo.addSeparator();
    mArchivo.add(miSalir);
    jMenuBar1.add(mArchivo);
    mEdicion.add(jMenuItem2);
    mEdicion.add(jMenuItem3);
    mEdicion.add(jMenuItem4);
    mEdicion.addSeparator();
    mEdicion.add(jMenuItem5);
    mEdicion.add(jMenuItem6);
    jMenuBar1.add(mEdicion);
    mTools.add(miGenCod);
    jMenuBar1.add(mTools);
    Ayuda.add(jMenuItem7);
    Ayuda.addSeparator();
    Ayuda.add(jMenuItem8);
    jMenuBar1.add(Ayuda);
    tfchat.addKeyListener(new eventoKey());
    benviar.addActionListener(new eventoAction());
    mArchivo.setText("Archivo");
    miNuevo.setText("Nuevo");
    miNuevo.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            miNuevo_actionPerformed(e);
          }
        });
    miAbrir.setText("Abrir");
    miAbrir.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            miAbrir_actionPerformed(e);
          }
        });
    miGuardar.setText("Guardar");
    miGuardar.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            miGuardar_actionPerformed(e);
          }
        });
    miExportar.setText("Exportar");
    miExportar.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            miExportar_actionPerformed(e);
          }
        });
    miSalir.setText("Salir");
    miSalir.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            miSalir_actionPerformed(e);
          }
        });
    mTools.setText("Tools");
    miGenCod.setText("Generar Codigo");
    miGenCod.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            miGenCod_actionPerformed(e);
          }
        });
    /*spgraficador.setBounds(new Rectangle(220, 45, 200, 400));
    spgraficador.setSize(200, 400)*/
    miImportar.setText("Importar Clase");
    miImportar.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            miImportar_actionPerformed(e);
          }
        });

    jMenuItem1.setText("Guardar Como...");
    mEdicion.setText("Edición");
    jMenuItem2.setText("Copiar");
    jMenuItem3.setText("Pegar");
    jMenuItem4.setText("Eliminar");
    jMenuItem5.setText("Fuente");
    jMenuItem6.setText("Color");
    Ayuda.setText("Ayuda");
    jMenuItem7.setText("Ayuda");
    jMenuItem8.setText("Acerca de...");
    arbol.tree.addTreeSelectionListener(new TreeSelectionListener()
        {
          public void valueChanged(TreeSelectionEvent e)
          {
            DefaultMutableTreeNode node = 
              (DefaultMutableTreeNode) (e.getPath().getLastPathComponent());
            // Object nodeInfo = node.getUserObject();
            System.out.println("UNICO => " + e.getSource());
            if (!node.isRoot())
            {
              if (node.getUserObject() instanceof clsClase)
              {
                objgraficador.getObjcontrol().setArbolsw(true);
                objgraficador.getObjcontrol().setModo("Interfaz");
                objgraficador.getObjcontrol().setArbolobj((clsClase) node.getUserObject());
              }
            }
          }
        });

    peste.add(arbol, BorderLayout.CENTER);
    psur.add(pchat);
    this.getContentPane().add(pnorte, BorderLayout.NORTH);
    this.getContentPane().add(psur, BorderLayout.SOUTH);
    this.getContentPane().add(peste, BorderLayout.EAST);
    this.getContentPane().add(poeste, BorderLayout.WEST);
    this.getContentPane().add(pcentro, BorderLayout.CENTER);
  }
  
  private void conectar() 
  { //  OK
    try
    {
      objcliente = new implementacionCliente();
      objcliente.adicionarEscuchador(this);
      registro = LocateRegistry.getRegistry(ip, puerto);
      //LocateRegistry.createRegistry(puerto);
      objservidor = (interfaceServidor)registro.lookup(name); //no es el nombre de la clase sino como se registro
      objgraficador = new graficadorDiagSec(objservidor);
      
      usuario = JOptionPane.showInputDialog(this, "INTRODUCIR NOMBRE O NICK: ", "Usuario");
      String mensajeconexion = objservidor.conectar(usuario, objcliente);
      while(!mensajeconexion.equals(Mensaje.OK))
      {
        JOptionPane.showMessageDialog(this, Mensaje.NOT);
        usuario = JOptionPane.showInputDialog(this, "INTRODUCIR NOMBRE O NICK: ", "Usuario");
        mensajeconexion = objservidor.conectar(usuario, objcliente);
      }
      this.setTitle("USUARIO: "+usuario);
      objgraficador.getObjcontrol().setUsuario(usuario);
      objgraficador.getObjcontrol().setFormCliente(this);
    }
    catch (RemoteException e)
    { e.printStackTrace();  }
    catch (NotBoundException e)
    { e.printStackTrace(); }  
    catch (Exception e) 
    { e.printStackTrace();  }
    objdiagrama   = new barraDiagramaSecuencia(objgraficador.getObjcontrol());
    objmenu = new barraMenu(this, objgraficador.getObjcontrol());
    spgraficador.getViewport().add(objgraficador);
    pnorte.add(objmenu);
    poeste.add(objdiagrama, BorderLayout.NORTH);
  }
  
  public void desconectar()
  {
    try 
    {
      objservidor.desconectar(usuario);
      usuario = "";
    }
    catch (Exception e)
    { e.printStackTrace();  }
    tachat  = new JTextArea();
    spchat.getViewport().add(tachat);
  }
  
  public void transferirMensajeChat(transferirEvento objevento)
  { //  OK
    tachat.append(objevento.getMensajeChat() + "\n");
    int len = tachat.getText().length();
    tachat.select(len, len);
    
    /*this.toFront();
    this.setVisible(true);
    this.requestFocus();*/
  }
           
  public void actualizarListaUsuario(transferirEvento objevento)
  { //  OK
    Vector users = objevento.getListaUsuario();
    int dim = users.size();
    System.out.println("********************************");
    for (int i = 0; i < dim; i++)
    {
      System.out.println("USER i = "+i+" => "+users.get(i));
    }
  }

  public void transferirDiagramaSecuencia(transferirEvento objevento)
  {
    /*objgraficador.getObjcontrol().objactor = objevento.getObjactor();
    objgraficador.getObjcontrol().objclase = objevento.getObjclase();
    objgraficador.getObjcontrol().objconector = objevento.getObjconector();*/
    objgraficador.getObjcontrol().setObjds(objevento.getObjds());
    objgraficador.actualizarGrafica();
    addDiagramaSecuenciaArbol(objevento.getUpdatearbol());
  }
  
  public void transferirActor(transferirEvento objevento)
  {
    objgraficador.getObjcontrol().setObjds(objevento.getObjds());
    objgraficador.getObjcontrol().objactor    = objevento.getObjactor();
    objgraficador.getObjcontrol().objclase    = null;
    objgraficador.getObjcontrol().objconector = null;
    objgraficador.actualizarGrafica();
  }
  
  public void transferirClase(transferirEvento objevento)
  {
    objgraficador.getObjcontrol().setObjds(objevento.getObjds());
    objgraficador.getObjcontrol().objactor    = null;
    objgraficador.getObjcontrol().objclase    = objevento.getObjclase();
    objgraficador.getObjcontrol().objconector = null;
    objgraficador.actualizarGrafica();
    addDiagramaSecuenciaArbol(objevento.getUpdatearbol());
  }
  
  public void transferirConector(transferirEvento objevento)
  {
    objgraficador.getObjcontrol().setObjds(objevento.getObjds());
    objgraficador.getObjcontrol().objactor    = null;
    objgraficador.getObjcontrol().objclase    = null;
    objgraficador.getObjcontrol().objconector = objevento.getObjconector();
    objgraficador.actualizarGrafica();
  }
  
  public void transferirActualizarActor(transferirEvento objevento)
  {
    objgraficador.getObjcontrol().actualizarActor(objevento.getObjactor());
    objgraficador.actualizarGrafica();
  }
  
  public void transferirActualizarClase(transferirEvento objevento)
  {
    objgraficador.getObjcontrol().actualizarClase(objevento.getObjclase());
    objgraficador.actualizarGrafica();
    addDiagramaSecuenciaArbol(objevento.getUpdatearbol());
  }
  
  public void transferirActualizarConector(transferirEvento objevento)
  {
    objgraficador.getObjcontrol().actualizarConector(objevento.getObjconector());
    objgraficador.actualizarGrafica();
  }
  
  public void transferirMensajeError(transferirEvento objevento)
  {
    JOptionPane.showMessageDialog(this, objevento.getMensajeerror());
  }

  private void this_windowClosing(WindowEvent e)
  {
    desconectar();
    System.exit(1);
  }

  private void miGuardar_actionPerformed(ActionEvent e)
  {
    guardarFile();
  }

  public void guardarFile()
  {
    JFileChooser selectfile = new JFileChooser();
    cargarFilter("Archivos XML", "xml");
    selectfile.setFileFilter(objfilter);
    selectfile.setCurrentDirectory(new File(url)); //  especifico el directorio la primera vez
    int result = selectfile.showSaveDialog(this);
    if(result == JFileChooser.APPROVE_OPTION)
    {
      guardarXML objguardar = new guardarXML(objgraficador.getObjcontrol().getObjds());
      File a = new File(selectfile.getSelectedFile().getPath()+".xml");
      objguardar.guardarxml(a);
    }
  }
  
  private void miAbrir_actionPerformed(ActionEvent e)
  { abrirFile();  }
  
  public void abrirFile()
  {
    selectfile = new JFileChooser();
    cargarFilter("Archivos XML", "xml");
    selectfile.setFileFilter(objfilter);
    selectfile.setCurrentDirectory(new File(url));
    int returnVal = selectfile.showOpenDialog(this);
    if(returnVal == JFileChooser.APPROVE_OPTION) 
    {
      File file = selectfile.getSelectedFile(); // obtener archivo seleccionado
      if ( file == null || file.getName().equals(""))    // mostrar error si es inválido
         JOptionPane.showMessageDialog( this, "Nombre de archivo inválido", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE );
      else
      { // abrir archivo
        leerXML objleer = new leerXML();
        objleer.load(file.getPath());
        try
        {
          objservidor.abrirDiagramaSecuencia(usuario, objleer.getObjds());
        } // procesar excepciones que pueden ocurrir al abrir el archivo
        catch (RemoteException ex) 
        { ex.printStackTrace(); } 
        catch ( Exception excepcionES ) 
        { JOptionPane.showMessageDialog( this, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE ); }
      } // fin de instrucción else
    } 
  }

  private void miSalir_actionPerformed(ActionEvent e)
  {
    desconectar();
    System.exit(1);
  }

  private void miGenCod_actionPerformed(ActionEvent e)
  {
    generarCodigoFuente();
  }
  
  public void generarCodigoFuente()
  {
    JFileChooser selectfile = new JFileChooser();
    cargarFilter("Archivos JAVA", "java");
    selectfile.setFileFilter(objfilter);
    selectfile.setDialogTitle("Generando Archivo \".java\"...");
    selectfile.setCurrentDirectory(new File(url)); //  especifico el directorio la primera vez
    int result = selectfile.showDialog(this, "Generar");
    if(result == JFileChooser.APPROVE_OPTION)
    {
      try 
      {
        LinkedList<clsClase> clase = objservidor.getObjds().getClase();
        int dim = clase.size();
        generarCodigo objgeneracodigo = new generarCodigo();
        for (int i = 0; i < dim; i++)
        {
          clsClase objclase = clase.get(i);
          if (objclase.getTipo() == 2)
          {
            objgeneracodigo.GeneraCodigo(objclase);
            File a = new File(selectfile.getCurrentDirectory()+"\\"+objclase.getNombre()+".java");
            objgeneracodigo.guardarFile(a);
          }
        }
      }
      catch (Exception ex) 
      { ex.printStackTrace(); }
    }
  }

  private void miNuevo_actionPerformed(ActionEvent e)
  {
    nuevo();
  }
  
  public void nuevo()
  {
    try 
    {
      //objservidor.nuevoDiagramaSecuencia(objgraficador.getObjcontrol().getUsuario());
      objservidor.nuevoDiagramaSecuencia(usuario);
    }
    catch (Exception ex) 
    { ex.printStackTrace(); }
  }

  private void miExportar_actionPerformed(ActionEvent e)
  {
    exportarXMI();
  }
  
  public void exportarXMI()
  {
    JFileChooser selectfile = new JFileChooser();
    cargarFilter("Archivos XML", "xml");
    selectfile.setFileFilter(objfilter);
    selectfile.setDialogTitle("Exportar XMI...");
    selectfile.setCurrentDirectory(new File(url)); //  especifico el directorio la primera vez
    int result = selectfile.showSaveDialog(this);
    if(result == JFileChooser.APPROVE_OPTION)
    {
      guardarXMI objguardar = new guardarXMI(objgraficador.getObjcontrol().getObjds());
      File a = new File(selectfile.getSelectedFile().getPath()+".xml");
      objguardar.exportarXMI(a);
    }
  }

  private void miImportar_actionPerformed(ActionEvent e)
  {
    importarXMI();
  }
  
  public void importarXMI()
  {
    selectfile = new JFileChooser();
    cargarFilter("Archivos XML", "xml");
    selectfile.setFileFilter(objfilter);
    selectfile.setDialogTitle("Importar XMI...");
    selectfile.setCurrentDirectory(new File(url));
    int returnVal = selectfile.showOpenDialog(this);
    if(returnVal == JFileChooser.APPROVE_OPTION) 
    {
      File file = selectfile.getSelectedFile(); // obtener archivo seleccionado
      if ( file == null || file.getName().equals(""))    // mostrar error si es inválido
         JOptionPane.showMessageDialog( this, "Nombre de archivo inválido", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE );
      else
      { // abrir archivo
        leerXMI objleer = new leerXMI();
        objleer.load(file.getPath());
        try
        {
          objservidor.importarDiagramaSecuencia(usuario, objleer.getObjds());
        } // procesar excepciones que pueden ocurrir al abrir el archivo
        catch (RemoteException ex) 
        { ex.printStackTrace(); } 
        catch ( Exception excepcionES ) 
        { JOptionPane.showMessageDialog( this, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE ); }
      } // fin de instrucción else
    } 
  }

  public void addDiagramaSecuenciaArbol(boolean updatearbol)
  {
    if (updatearbol)
    {
    arbol.clear();
    LinkedList<clsClase> clase = objgraficador.getObjcontrol().getObjds().getClase();
    int dim = clase.size();
    //System.out.println("TOTAL EN EL ARBOL => "+clase.size());
    for (int i = 0; i < dim; i++)
    {
      clsClase objclase = clase.get(i);
      if (objclase.getTipo() == 1)
      {
        //System.out.println("SOY ARBOL CON id = "+objclase.getId());
        DefaultMutableTreeNode p1 = arbol.addObject(null, objclase);
        LinkedList<clsAtributo> atributo = objclase.getAtributos();
        int dima = atributo.size();
        for (int j = 0; j < dima; j++)
          arbol.addObject(p1, atributo.get(j), true);
        
        LinkedList<clsMetodo> metodo = objclase.getMetodos();
        dima = metodo.size();
        for (int j = 0; j < dima; j++)
          arbol.addObject(p1, metodo.get(j), true);
      }
    }
    arbol.setVisible(true);
    //arbol.treeModel.reload();
    }
  }
  
  
  public void transferirAtributo(transferirEvento objevento)
  {
    objgraficador.getObjcontrol().setObjds(objevento.getObjds());
    //objgraficador.getObjcontrol().objclase = objevento.getObjclase();
    objgraficador.getObjcontrol().actualizarFormClaseAtributo(objevento.getObjclase());
    addDiagramaSecuenciaArbol(objevento.getUpdatearbol());
  }
  
  public void transferirMetodo(transferirEvento objevento)
  {
    objgraficador.getObjcontrol().setObjds(objevento.getObjds());
    //objgraficador.getObjcontrol().objclase = objevento.getObjclase();
    objgraficador.getObjcontrol().actualizarFormClaseMetodo(objevento.getObjclase());
    addDiagramaSecuenciaArbol(objevento.getUpdatearbol());
  }
  
  //********************** EVENTO DEL MOUSE Y TECLADO **************************
  class eventoKey extends KeyAdapter
  {
    public void keyPressed(KeyEvent e)
    {
      int keyCode = e.getKeyCode();
      if (keyCode == KeyEvent.VK_ENTER)
      {
        enviarMensajeChat();
      }
    }
  }
   
  class eventoAction implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() instanceof JButton)
      {
        //System.out.println("boton: "+e.getSource());
        enviarMensajeChat();
      }
    }
  }
  
  private void enviarMensajeChat()
  {
    String mensajechat = tfchat.getText().trim();
    if (!mensajechat.equals("")) 
    {
      try
      {
        objservidor.enviarMensajeChat(usuario, mensajechat);
      }
      catch (Exception e)
      { e.printStackTrace();  }
      tfchat.setText("");
    }
  }
  
  
  private void cargarFilter(String descripcion, String extension)
  {
    objfilter = new fileFilter();
    objfilter.addExtension(extension);
    objfilter.setDescription(descripcion);
  }
  
  public static void main(String[] args)
  {
    JFrame.setDefaultLookAndFeelDecorated(true);
    formCliente objcliente = new formCliente();
    objcliente.setVisible(true);
    //objcliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}