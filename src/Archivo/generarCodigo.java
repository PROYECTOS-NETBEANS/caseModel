package Archivo;

import Negocio.clsAtributo;
import Negocio.clsClase;
import Negocio.clsMetodo;
import Negocio.clsParametro;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.LinkedList;

public class generarCodigo
{
  private clsClase objclase = null;
  private LinkedList<clsAtributo> atributo = new LinkedList<clsAtributo>();
  private LinkedList<clsMetodo> metodo = new LinkedList<clsMetodo>();
  private String toString = "";
  private String sl = "\n";
  
  public generarCodigo()
  {}
  
  public clsClase getClase()
  {
    return objclase;
  }

  public void setClase(clsClase objclase)
  {
    this.objclase = objclase;
  }
  
  public void GeneraCodigo(clsClase objclase)
  {
    toString = "";
    this.objclase = objclase;
    atributo= objclase.getAtributos();
    metodo  = objclase.getMetodos();
    generarClase();
  }
  
  private void generarClase()
  {
    toString = objclase.getAcceso()+" class "+objclase.getNombre()+sl;
    toString += "{"+sl;
    toString += "  // ATRIBUTOS DE LA CLASE" + sl;
    generarAtributo();
    toString += "  // CONTRUCTOR(ES) DE LA CLASE" + sl;
    generarConstructor();
    toString += "  // SELECTORES DE LA CLASE" + sl;
    generarSelector();
    toString += "  // METODO(S) DE LA CLASE" + sl;
    generarMetodo();
    toString+= "}";
  }
  
  private void generarAtributo()
  {
    int dim = atributo.size();
    for (int i = 0; i < dim; i++)
    {
      clsAtributo objatributo = atributo.get(i);
      toString += "  "+generarAtributo(objatributo)+sl;
    }
    toString += sl;
  }
  
  public String generarAtributo(clsAtributo objatributo)
  {
    return objatributo.getAcceso()+" "+objatributo.getTipo()+" "+objatributo.getNombre()+";";
  }
  
  private void generarConstructor()
  {
    //  CONTRUCTO POR DEFAULT
    toString += "  "+objclase.getAcceso()+" "+objclase.getNombre()+"()"+sl;
    toString += "  {"+sl;
    int dim = atributo.size();
    String par = "";
    for (int i = 0; i < dim; i++)
    {
      clsAtributo objatributo = atributo.get(i);
      par += asignacionVariables(objatributo, "", false);
    }
    toString += par;
    toString += "  }"+sl+sl;
    
    if (dim > 0)
    {
      //  OTRO CONSTRUCTOR
      toString += "  "+objclase.getAcceso()+" "+objclase.getNombre()+"(";
      par = "";
      for (int i = 0; i < dim; i++)
      {
        clsAtributo objatributo = atributo.get(i);
        clsParametro objparametro = new clsParametro(objatributo.getNombre(), objatributo.getTipo());
        par += generarParametro(objparametro)+", ";
      }
      if (par.length() > 3)
        par = par.substring(0, par.length() - 2);
      toString += par;
      toString += ")"+sl;
      toString += "  {"+sl;
      
      for (int i = 0; i < dim; i++)
      {
        clsAtributo objatributo = atributo.get(i);
        toString += asignacionVariables(objatributo, "this.", true);
      }
      toString += "  }"+sl+sl;
    }
  }
  
  private String asignacionVariables(clsAtributo objatributo, String ref, boolean sw)
  {
    String asignacion = "";
    if (objatributo.getTipo().equals("int"))
      asignacion += "    "+ref+objatributo.getNombre()+" = 0;"+sl;
    if (objatributo.getTipo().equals("boolean"))
      asignacion += "    "+ref+objatributo.getNombre()+" = false;"+sl;
    if (objatributo.getTipo().equals("float"))
      asignacion += "    "+ref+objatributo.getNombre()+" = 0;"+sl;
    if (objatributo.getTipo().equals("double"))
      asignacion += "    "+ref+objatributo.getNombre()+" = 0;"+sl;
    if (objatributo.getTipo().equals("String"))
      asignacion += "    "+ref+objatributo.getNombre()+" = \"\";"+sl;
    if (objatributo.getTipo().equals("char"))
      asignacion += "    "+ref+objatributo.getNombre()+" = \"\";"+sl;
    System.out.println("tipo atribu => "+objatributo.getTipo());
    if (sw)
    {
      asignacion = asignacion.substring(0, asignacion.indexOf("="));
      asignacion+= " = "+objatributo.getNombre()+"; "+sl;
    }
    return asignacion;
  }
  
  private void generarSelector()
  {
    int dim = atributo.size();
    String met = "";
    for (int i = 0; i < dim; i++)
    {
      clsAtributo objatributo = atributo.get(i);
      clsMetodo objmetodo = new clsMetodo("set"+objatributo.getNombre(), "void", "public");
      objmetodo.addParametro(new clsParametro(objatributo.getNombre(), objatributo.getTipo()));
      met += generarMetodoSelector(objmetodo) + sl;
      objmetodo = new clsMetodo("get"+objatributo.getNombre(), objatributo.getTipo(), "public");
      objmetodo.addParametro(new clsParametro(objatributo.getNombre(), objatributo.getTipo()));
      met += generarMetodoSelector(objmetodo) + sl;
    }
    toString += met;
  }
  
  private String generarMetodoSelector(clsMetodo objmetodo)
  {
    String met = "  "+objmetodo.getAcceso()+" "+objmetodo.getRetorna()+" "+objmetodo.getNombre();
    if (objmetodo.getRetorna().equals("void"))
    {
      met += "("+generarParametro(objmetodo.getParametros())+")" + sl;
      met += "  {" + sl;
      clsParametro objparametro = objmetodo.getParametros().get(0);
      clsAtributo objatributo = new clsAtributo(objparametro.getNombre(), objparametro.getTipo(), "");
      met += asignacionVariables(objatributo, "this.", true);
    }
    else
    {
      met += "()" + sl;
      met += "  {" + sl;
      clsParametro objparametro = objmetodo.getParametros().get(0);
      met += "    return " + objparametro.getNombre() + ";" + sl;
    }
    met += "  }" + sl;
    return met;
  }
  
  private void generarMetodo()
  {
    int dim = metodo.size();
    for (int i = 0; i < dim; i++)
      toString += generarMetodo(metodo.get(i)) + sl;
  }
  
  public String generarMetodo(clsMetodo objmetodo)
  {
    String met = "  "+objmetodo.getAcceso()+" "+objmetodo.getRetorna()+" "+objmetodo.getNombre();
    met += "("+generarParametro(objmetodo.getParametros())+")" + sl;
    met += "  {" + sl;    
    met += "  }" + sl;
    return met;
  }
  
  private String generarParametro(LinkedList<clsParametro> parametro)
  {
    String par = "";
    int dim = parametro.size();
    for (int i = 0; i < dim; i++)
    {
      par += generarParametro(parametro.get(i)) + ", ";
    }
    if (par.length() > 3)
      return par.substring(0, par.length() - 2);
    return par;
  }
  
  private String generarParametro(clsParametro objpparametro)
  {
    return objpparametro.getTipo()+" "+objpparametro.getNombre();
  }
  
  public String toString()
  {
    return toString;
  }
  
  public void guardarFile(File a)
  {
    PrintWriter outputFile;
    System.out.println("generar => "+a.getPath());
    try 
    {
      outputFile = new PrintWriter(new FileWriter(a));
      outputFile.println(toString());
      outputFile.close();
    }
    catch( Exception e )
    { 
      e.printStackTrace();
      System.out.println("ERROR => "+e.toString()); 
    }
  }
  
  public static void main(String[] args)
  {
    /*String a = "hola";
    System.out.println(a);
    String b = "  hola\tcomo estas de\tnuevo";
    System.out.println(b);
    String c = "hola\ncomo";
    System.out.println(c);*/
  }
  
}