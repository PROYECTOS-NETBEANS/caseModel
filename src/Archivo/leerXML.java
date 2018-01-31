package Archivo;

import Negocio.*;

import com.sun.org.apache.xerces.internal.parsers.SAXParser;

import java.awt.Point;
import java.awt.Rectangle;

import java.util.LinkedList;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class leerXML extends DefaultHandler
{
  private clsDiagramaSecuencia objds = null;
  
  private LinkedList<clsActor> actor       = new LinkedList<clsActor>();
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

  public leerXML()
  {
    objds = new clsDiagramaSecuencia();
    //System.out.println("CREADO => "+objds);
  }
  
  public void startElement( String uri, String localName, String rawName, Attributes attributes ) 
  {
    clsActor objactor = new clsActor();
    if ( localName.equals( "actor" ) )
    {
      //System.out.println("*********** ACTOR ************");
      objactor.setId(Integer.parseInt(attributes.getValue("id")));
      objactor.setNombre(attributes.getValue("nombre"));
      Rectangle fs = new Rectangle(Integer.parseInt(attributes.getValue("sx")), Integer.parseInt(attributes.getValue("sy")), Integer.parseInt(attributes.getValue("sw")), Integer.parseInt(attributes.getValue("sh")));
      objactor.setSuperior(fs);
      Rectangle fi = new Rectangle(Integer.parseInt(attributes.getValue("ix")), Integer.parseInt(attributes.getValue("iy")), Integer.parseInt(attributes.getValue("iw")), Integer.parseInt(attributes.getValue("ih")));
      objactor.setInferior(fi);
      objactor.setPuntoO(new Point(Integer.parseInt(attributes.getValue("ox")), Integer.parseInt(attributes.getValue("oy"))));
      objactor.setPuntoD(new Point(Integer.parseInt(attributes.getValue("dx")), Integer.parseInt(attributes.getValue("dy"))));
      objds.addActor(objactor);
    }
    
    if ( localName.equals( "clase" ) )
    {
      objclase = new clsClase();
      //System.out.println("*********** CLASE ************");
      objclase.setTipo(2);
      objclase.setId(Integer.parseInt(attributes.getValue("id")));
      objclase.setClassinterface(attributes.getValue("classinterface"));
      objclase.setNombre(attributes.getValue("nombre"));
      objclase.setAcceso(attributes.getValue("acceso"));
      Rectangle fs = new Rectangle(Integer.parseInt(attributes.getValue("sx")), Integer.parseInt(attributes.getValue("sy")), Integer.parseInt(attributes.getValue("sw")), Integer.parseInt(attributes.getValue("sh")));
      objclase.setSuperior(fs);
      Rectangle fi = new Rectangle(Integer.parseInt(attributes.getValue("ix")), Integer.parseInt(attributes.getValue("iy")), Integer.parseInt(attributes.getValue("iw")), Integer.parseInt(attributes.getValue("ih")));
      objclase.setInferior(fi);
      objclase.setPuntoO(new Point(Integer.parseInt(attributes.getValue("ox")), Integer.parseInt(attributes.getValue("oy"))));
      objclase.setPuntoD(new Point(Integer.parseInt(attributes.getValue("dx")), Integer.parseInt(attributes.getValue("dy"))));
      //objds.addClase(objclase);
    }
    
    if (localName.equals( "atributo" ))
    {
      //System.out.println("********* ATRIBUTO ***********");
      clsAtributo objatributo = new clsAtributo(attributes.getValue("nombre"), attributes.getValue("tipo"), attributes.getValue("acceso"));
      /*objatributo.setAdd(false);
      objatributo.setDel(false);*/
      objclase.addAtributo(objatributo);
    }
    
    if (localName.equals( "metodo" ))
    {
      objmetodo = new clsMetodo();
      //System.out.println("********* METODO ***********");
      objmetodo.setSecuencia(Integer.parseInt(attributes.getValue("secuencia")));
      objmetodo.setNombre(attributes.getValue("nombre"));
      objmetodo.setRetorna(attributes.getValue("retorno"));
      objmetodo.setAcceso(attributes.getValue("acceso"));
    }
    
    if (localName.equals( "parametro" ))
    {
      //System.out.println("********* PARAMETRO ***********");
      clsParametro objparametro = new clsParametro(attributes.getValue("nombre"), attributes.getValue("tipo"));
      objmetodo.addParametro(objparametro);
    }
    
    if (localName.equals( "conector" ))
    {
      //System.out.println("********* CONECTOR ***********");
      objconector = new clsEnlace();
      objconector.setId(Integer.parseInt(attributes.getValue("id")));
      objconector.setOrigen(Integer.parseInt(attributes.getValue("origen")));
      objconector.setDestino(Integer.parseInt(attributes.getValue("destino")));
      Rectangle fig = new Rectangle(Integer.parseInt(attributes.getValue("x")), Integer.parseInt(attributes.getValue("y")), Integer.parseInt(attributes.getValue("w")), Integer.parseInt(attributes.getValue("h")));
      objconector.setEnlace(fig);
      objconector.setPuntoO(new Point(Integer.parseInt(attributes.getValue("ox")), Integer.parseInt(attributes.getValue("oy"))));
      objconector.setPuntoD(new Point(Integer.parseInt(attributes.getValue("dx")), Integer.parseInt(attributes.getValue("dy"))));
    }
    
    if (localName.equals( "metodoc" ))
    {
      objmetodoc = new clsMetodo();
      //System.out.println("********* METODO C ***********");
      objmetodoc.setSecuencia(Integer.parseInt(attributes.getValue("secuencia")));
      objmetodoc.setNombre(attributes.getValue("nombre"));
      objmetodoc.setRetorna(attributes.getValue("retorno"));
      objmetodoc.setAcceso(attributes.getValue("acceso"));
    }
    
    if (localName.equals( "parametroc" ))
    {
      //System.out.println("********* PARAMETRO ***********");
      clsParametro objparametro = new clsParametro(attributes.getValue("nombre"), attributes.getValue("tipo"));
      objmetodoc.addParametro(objparametro);
    }
    
    if (localName.equals("ids"))
      ids.add(attributes.getValue("id"));
    //objclase.addMetodo(objmetodo);
    //objds.addClase(objclase);
  }
  
  public void endElement( String uri, String localName, String rawName) 
  {
    if ( localName.equals( "clase" ) ) 
    {
      objds.addClase(objclase);
      /*clsClase aux = new clsClase(objclase.getId(), 2, objclase.getClassinterface(), objclase.getNombre(), objclase.getAcceso(), objclase.getSuperior(), objclase.getInferior());
      objds.addClase(aux);*/
    }
    
    if (localName.equals("metodo"))
    {
      objclase.addMetodo(objmetodo);
    }
    
    if (localName.equals("conector"))
    {
      objconector.setObjmetodo(objmetodoc);
      objds.addConector(objconector);
    }
  }
  
  public void load( String args )
  {
    inputFileName = args;
    try
    {
      SAXParser parser = new SAXParser();
      parser.setContentHandler( this );
      parser.setErrorHandler( this );
      parser.parse( inputFileName );      
    }
    catch( Exception e )
    { e.printStackTrace( System.err );  }
    //System.out.println("Archivo cargado");
    objds.setIds(ids);
    
    /*actor = objds.getActor();
    System.out.println("*********************** ACTORES ********************");
    for (int i = 0; i < actor.size(); i++)
    {
      actor objactor = actor.get(i);
      System.out.println(objactor.getId()+" - "+objactor.getNombre()+" - "+objactor.getSuperior()+" - "+objactor.getInferior());
    }
    
    clase = objds.getClase();
    System.out.println("*********************** CLASES ******************** " +clase.size());
    for (int i = 0; i < clase.size(); i++)
    {
      clase objclase = clase.get(i);
      System.out.println(objclase.getId()+" - "+objclase.getClassinterface()+" - "+objclase.getNombre()+" - "+objclase.getAcceso()+" - "+objclase.getSuperior()+" - "+objclase.getInferior());
      
      LinkedList<atributos> atributo = objclase.getAtributos();
      for (int j = 0; j < atributo.size(); j++)
      {
        atributos objatributo = atributo.get(j);
        System.out.println("Atributo => "+objatributo.getNombre()+" - "+objatributo.getTipo()+" - "+objatributo.getAcceso());
      }
      
      LinkedList<metodos> metodo = objclase.getMetodos();
      for (int j = 0; j < metodo.size(); j++)
      {
        metodos objmetodo = metodo.get(j);
        System.out.println("Metodo => "+objmetodo.getNombre()+" - "+objmetodo.getRetorna()+" - "+objmetodo.getAcceso());
        LinkedList<parametros> parametro = objmetodo.getParametros();
        for (int k = 0; k < parametro.size(); k++)
        {
          parametros objparametro = parametro.get(k);
          System.out.println("Parametros => "+objparametro.getNombre()+" - "+objparametro.getTipo());
        }
      }
    }*/
     /*conector = objds.getConector();
     System.out.println("*********************** CONECTOR ******************** " +conector.size());
     for (int i = 0; i < conector.size(); i++)
     {
       conector objconector = conector.get(i);
       System.out.println("conector => "+objconector.toString());
       //System.out.println(objclase.getId()+" - "+objclase.getClassinterface()+" - "+objclase.getNombre()+" - "+objclase.getAcceso()+" - "+objclase.getSuperior()+" - "+objclase.getInferior());
       metodos objmetodo = objconector.getObjmetodo();
       System.out.println("Metodo => "+objmetodo.toString());
       LinkedList<parametros> parametro = objmetodo.getParametros();
       for (int k = 0; k < parametro.size(); k++)
       {
         parametros objparametro = parametro.get(k);
         System.out.println("Parametro => "+objparametro.toString());
       }
     }*/
    return;
  }
  
  public static void main(String[] args)
  {
    /*String url = "E:/SEM 2 - 2009/MATERIA - SWI/2do Parcial/DiagramaSecuencia/demo.xml";
    leerXML objleer = new leerXML();
    objleer.load(url);*/
  }

  public clsDiagramaSecuencia getObjds()
  {
    return objds;
  }

  /*public void setObjds(diagramaSecuencia objds)
  {
    this.objds = objds;
  }*/
}
