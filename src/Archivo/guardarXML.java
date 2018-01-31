package Archivo;

import Negocio.*;

import java.awt.Point;
import java.awt.Rectangle;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;


public class guardarXML
{
  //private Grafo diagrama;
  static PrintWriter outputFile;
  
  private clsDiagramaSecuencia objds;
  
  public guardarXML(clsDiagramaSecuencia objds)
  {
    this.objds = objds;
  }
  
  //public void guardarxml( String s )
  public void guardarxml( File a )
  {
    LinkedList<clsActor> actor = objds.getActor();
    LinkedList<clsClase> clase = objds.getClase();
    LinkedList<clsEnlace> conector = objds.getConector();
    Vector ids = objds.getIds();
    
    try 
    { outputFile = new PrintWriter(new FileWriter(a));  }
    catch( Exception e )
    { System.out.println("ERROR");  }
    outputFile.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    outputFile.println("<diagramaSecuencia>");
    
    int dim = actor.size();
    for (int i = 0; i < dim; i++)
    {
      clsActor objactor = actor.get(i);
      guardarActor(objactor);      
    }
    
    dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsClase objclase = clase.get(i);
      if (objclase.getTipo() == 2)
        guardarClase(objclase);
    }
    
    dim = conector.size();
    for (int i = 0; i < dim; i++)
    {
      clsEnlace objconector = conector.get(i);
      guardarConector(objconector);      
    }
    
    dim = ids.size();
    for (int i = 0; i < dim; i++)
      outputFile.println("\t<ids id=\""+ ids.get(i)+"\"/>");
    
    outputFile.println("</diagramaSecuencia>");
    try 
    { outputFile.close(); }
    catch( Exception e)
    { System.out.println("ERROR2"); }
  }
  
  private void guardarActor(clsActor objactor)
  {
    Rectangle fs = objactor.getSuperior();
    Rectangle fi = objactor.getInferior();
    Point o = objactor.getPuntoO();
    Point d = objactor.getPuntoD();
    
    outputFile.println("\t<actor id=\"" + objactor.getId() + "\" nombre=\"" + objactor.getNombre() + 
                       "\" sx=\"" + fs.x + "\" sy=\"" + fs.y + "\" sw=\"" + fs.width + "\" sh=\"" + fs.height + 
                       "\" ix=\"" + fi.x + "\" iy=\"" + fi.y + "\" iw=\"" + fi.width + "\" ih=\"" + fi.height + 
                       "\" ox=\"" + o.x + "\" oy=\"" + o.y + "\" dx=\"" + d.x + "\" dy=\"" + d.y + "\">");
    outputFile.println("\t</actor>");
  }
  
  private void guardarClase(clsClase objclase)
  {
    Rectangle fs = objclase.getSuperior();
    Rectangle fi = objclase.getInferior();
    
    Point o = objclase.getPuntoO();
    Point d = objclase.getPuntoD();
    
    outputFile.println("\t<clase id=\"" + objclase.getId() + "\" classinterface=\"" + objclase.getClassinterface() + 
                        "\" nombre=\"" + objclase.getNombre() + "\" acceso=\"" + objclase.getAcceso() + 
                        "\" sx=\"" + fs.x + "\" sy=\"" + fs.y + "\" sw=\"" + fs.width + "\" sh=\"" + fs.height + 
                        "\" ix=\"" + fi.x + "\" iy=\"" + fi.y + "\" iw=\"" + fi.width + "\" ih=\"" + fi.height + 
                        "\" ox=\"" + o.x + "\" oy=\"" + o.y + "\" dx=\"" + d.x + "\" dy=\"" + d.y + "\">");
    LinkedList<clsAtributo> atributo = objclase.getAtributos();
    int dim = atributo.size();
    for (int i = 0; i < dim; i++)
    {
      clsAtributo objatributo = atributo.get(i);
      guardarAtributo(objatributo);
    }
    
    LinkedList metodo = objclase.getMetodos();
    dim = metodo.size();
    for (int i = 0; i < dim; i++)
    {
      clsMetodo objmetodo = (clsMetodo)metodo.get(i);
      guardarMetodo(objmetodo);
    }
    outputFile.println("\t</clase>");
  }
  
  private void guardarAtributo(clsAtributo objatributo)
  {
    outputFile.println("\t\t<atributo nombre=\""+ objatributo.getNombre()+ "\" tipo=\""+objatributo.getTipo()+ "\" acceso=\""+objatributo.getAcceso()+"\"/>");
  }
  
  private void guardarMetodo(clsMetodo objmetodo)
  {
    outputFile.println("\t\t<metodo secuencia=\""+objmetodo.getSecuencia()+"\" nombre=\""+ objmetodo.getNombre()+ "\" retorno=\""+objmetodo.getRetorna()+ "\" acceso=\""+objmetodo.getAcceso()+"\">");
    LinkedList<clsParametro> parametro = objmetodo.getParametros();
    int dim = parametro.size();
    for (int i = 0; i < dim; i++)
    {
      clsParametro objparametro = parametro.get(i);
      guardarParametro(objparametro);
    }
    outputFile.println("\t\t</metodo>");
  }
  
  private void guardarParametro(clsParametro objparametro)
  {
    outputFile.println("\t\t\t<parametro nombre=\""+ objparametro.getNombre()+ "\" tipo=\""+objparametro.getTipo()+"\"/>");
  }
  
  private void guardarConector(clsEnlace objconector)
  {
    Rectangle fig = objconector.getEnlace();
    Point o = objconector.getPuntoO();
    Point d = objconector.getPuntoD();
    
    outputFile.println("\t<conector id=\"" + objconector.getId() + "\" origen=\"" + objconector.getOrigen() + "\" destino=\"" + objconector.getDestino() +
                       "\" x=\"" + fig.x + "\" y=\"" + fig.y + "\" w=\"" + fig.width + "\" h=\"" + fig.height + 
                       "\" ox=\"" + o.x + "\" oy=\"" + o.y + "\" dx=\"" + d.x + "\" dy=\"" + d.y + "\">");
    clsMetodo objmetodo = objconector.getObjmetodo();
    guardarMetodoC(objmetodo);
    outputFile.println("\t</conector>");
  }
  
  private void guardarMetodoC(clsMetodo objmetodo)
  {
    outputFile.println("\t\t<metodoc secuencia=\""+objmetodo.getSecuencia()+"\" nombre=\""+ objmetodo.getNombre()+ "\" retorno=\""+objmetodo.getRetorna()+ "\" acceso=\""+objmetodo.getAcceso()+"\">");
    LinkedList<clsParametro> parametro = objmetodo.getParametros();
    int dim = parametro.size();
    for (int i = 0; i < dim; i++)
    {
      clsParametro objparametro = parametro.get(i);
      guardarParametroC(objparametro);
    }
    outputFile.println("\t\t</metodoc>");
  }
  
  private void guardarParametroC(clsParametro objparametro)
  {
    outputFile.println("\t\t\t<parametroc nombre=\""+ objparametro.getNombre()+ "\" tipo=\""+objparametro.getTipo()+"\"/>");
  }
}