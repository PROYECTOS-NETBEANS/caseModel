package Archivo;

import Negocio.clsActor;
import Negocio.clsAtributo;
import Negocio.clsClase;
import Negocio.clsEnlace;
import Negocio.clsDiagramaSecuencia;
import Negocio.clsMetodo;

import Negocio.clsParametro;

import com.sun.org.apache.xerces.internal.parsers.SAXParser;

import java.awt.Point;
import java.awt.Rectangle;

import java.util.LinkedList;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.*;

public class leerXMI  extends DefaultHandler
{
  private clsDiagramaSecuencia objds = null;
  
  //private LinkedList<actor> actor       = new LinkedList<actor>();
  private LinkedList<clsClase> clase       = new LinkedList<clsClase>();
  private LinkedList<clsEnlace> conector = new LinkedList<clsEnlace>();
  private Vector<String> ids            = new Vector<String>();
  
  //private LinkedList<atributos> atributo = new LinkedList<atributos>();
  private clsClase objclase;
  private clsMetodo objmetodo;
  private clsMetodo objmetodoc;
  private clsEnlace objconector;
  //private
  
  private String inputFileName;

  public leerXMI()
  {
    objds = new clsDiagramaSecuencia();
  }

  public void startElement( String uri, String localName, String rawName, Attributes attributes ) 
  {
    
    if ( localName.equals( "Class" ) )
    {
      objclase = new clsClase();
      //System.out.println("*********** CLASE ************");
      //objclase.setId(Integer.parseInt(attributes.getValue("id")));
      objclase.setTipo(1);
      objclase.setClassinterface(attributes.getValue("classinterface"));
      objclase.setNombre(attributes.getValue("name"));
      objclase.setAcceso(attributes.getValue("visibility"));
    }
    
    if (localName.equals( "Attribute" ))
    {
      //System.out.println("********* ATRIBUTO ***********");
      String tipo = attributes.getValue("type");
      //System.out.println("AAA => "+attributes.getValue("type"));
      if (tipo == null || tipo == "")
        tipo = "int";
      clsAtributo objatributo = new clsAtributo(attributes.getValue("name"), tipo, attributes.getValue("visibility"));
      objclase.addAtributo(objatributo);
    }
    
    if (localName.equals( "Operation" ))
    {
      String retorno = attributes.getValue("type");
      if (retorno == null || retorno == "")
        retorno = "void";
      objmetodo = new clsMetodo();
      objmetodo.setNombre(attributes.getValue("name"));
      objmetodo.setAcceso(attributes.getValue("visibility"));
      objmetodo.setRetorna(retorno);
      objclase.addMetodo(objmetodo);
    }
  }
  
  public void endElement( String uri, String localName, String rawName) 
  {
    if ( localName.equals( "Class" ) ) 
    {
      //System.out.println(" *** ");
      //objclase.addMetodo(objmetodo);
      objds.addClase(objclase);
    }
    
    /*if (localName.equals("metodo"))
    {
      objclase.addMetodo(objmetodo);
    }*/
  }
  
  public void load( String args )
  {
    inputFileName = args;
    try
    {
      SAXParser parser = new SAXParser();
      //System.out.println(this.toString());
      parser.setContentHandler( this );
      parser.setErrorHandler( this );
      parser.parse( inputFileName );      
    }
    catch( Exception e )
    { e.printStackTrace( System.err );  }
    //System.out.println("Archivo cargado");
    //objds.setIds(ids);
    return;
  }

  public clsDiagramaSecuencia getObjds()
  {
    return objds;
  }

  /*public void setObjds(diagramaSecuencia objds)
  {
    this.objds = objds;
  }*/
  
  public static void main(String[] args)
  {
    /*String url = "C:/DEMOS/nuevaClase.xml";
    leerXMI objleer = new leerXMI();
    //objleer.setObjds();
    objleer.load(url);
    diagramaSecuencia imp = objleer.getObjds();
    LinkedList<clase> clase = imp.getClase();
    System.out.println("OBJDS => "+imp);
    System.out.println("SIZE => "+clase.size());
    for(int i = 0; i < clase.size(); i++)
    {
      clase objclase = clase.get(i);
      //System.out.println("******* TIPO="+objclase.getTipo());
      LinkedList<atributos> atributo = objclase.getAtributos();
      System.out.println("CLASE => "+objclase);
      for (int j = 0; j < atributo.size(); j++)
        System.out.println("   ATRIBUTO => "+atributo.get(j));
      
      LinkedList<metodos> metodo = objclase.getMetodos();
      for (int j = 0; j < metodo.size(); j++)
        System.out.println("   METODO => "+metodo.get(j));
    }*/
  }
}