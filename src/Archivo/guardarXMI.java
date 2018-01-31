package Archivo;

import Negocio.*;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.LinkedList;

public class guardarXMI
{
  static PrintWriter outputFile;
  private clsDiagramaSecuencia objds;
  
  private LinkedList<clsClase> clase = new LinkedList<clsClase>();
  private LinkedList<clsEnlace> conector = new LinkedList<clsEnlace>();
  
  public guardarXMI(clsDiagramaSecuencia objds)
  {
    this.objds = objds;
  }
  
  public void exportarXMI(File a)
  {
    try
    { outputFile = new PrintWriter(new FileWriter(a));  }
    catch( Exception e )
    { System.out.println("ERROR1");  }
    cabecera();
    outputFile.println("<XMI.content>");
    outputFile.println("<UML:Model xmi.id=\"UMLProyect.1\">");
    outputFile.println("\t<UML:Namespace.ownedElement>");
    outputFile.println("\t\t<UML:Model xmi.id =\"UMLModel.2\" name=\"Secuencia\" visibility=\"public\" isSpecification=\"false\" namespace=\"UMLProject.1\" isRoot=\"false\" isLeaf=\"false\" isAbstract=\"false\">");
    outputFile.println("\t\t\t<UML:Namespace.ownedElement>");
    
    outputFile.println("\t\t\t\t<UML:Collaboration xmi.id=\"UMLCollaborationInstanceSet.3\" name=\"CollaborationInstanceSet1\" visibility=\"public\" isSpecification=\"false\">");
    //***************** declaracion de enlaces ************
    declaracionEnlace();
    //************** fin de declaracion de encalessss ********
    
    //********** definiendo clases ************************************
    declaracionClase();
    //*************************** fin clases  ***********************
    outputFile.println("\t\t\t\t</UML:Collaboration>");
    //************** declaracion de los stereotipos (interfaz, control, entidad) => (boundary, control, entity)
    declaracionEstereotipoClase();
    
    outputFile.println("\t\t\t</UML:Namespace.ownedElement>");
    outputFile.println("\t\t</UML:Model>");
    outputFile.println("\t</UML:Namespace.ownedElement>");
    outputFile.println("</UML:Model>");
    
    //********************** define los valores del enlace  ********************
    definirValoresEnlace();
    
    outputFile.println("</XMI.content>");
    outputFile.println("</XMI>");
    
    try
    { outputFile.close(); }
    catch( Exception e)
    { System.out.println("ERROR2"); }
  }
  
  private void cabecera()
  {
    outputFile.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    outputFile.println("<XMI xmi.version = \"1.1\" xmlns:UML=\"href://org.omg/UML/1.3\">");
    outputFile.println("<XMI.header>");
    outputFile.println("\t<XMI.documentation>");
    outputFile.println("\t\t<XMI.owner></XMI.owner>");
    outputFile.println("\t\t<XMI.contact></XMI.contact>");
    outputFile.println("\t\t<XMI.exporter>StarUML.XMI-Addin</XMI.exporter>");
    outputFile.println("\t\t<XMI.exporterVersion>1.0</XMI.exporterVersion>");
    outputFile.println("\t\t<XMI.notice></XMI.notice>");
    outputFile.println("\t</XMI.documentation>");
    outputFile.println("\t<XMI.metamodel xmi.name = \"UML\" xmi.version = \"1.3\"/>");
    outputFile.println("</XMI.header>");
  }
  
  public void declaracionEnlace()
  {
    /*
     * <UML:Collaboration.interaction>
            <UML:Interaction xmi.id="UMLInteractionInstanceSet.4" name="InteractionInstanceSet1" visibility="public" isSpecification="false" context="UMLCollaborationInstanceSet.3"/>
          </UML:Collaboration.interaction>
     */
    outputFile.println("\t\t\t\t\t<UML:Collaboration.interaction>");
    conector = objds.getConector();
    //int dim = conector.size();
    int dim = 0;
    if (dim > 0)
    {
      outputFile.println("\t\t\t\t\t\t<UML:Interaction xmi.id=\"UMLInteractionInstanceSet.4\" name=\"InteractionInstanceSet1\" visibility=\"public\" isSpecification=\"false\" context=\"UMLCollaborationInstanceSet.3\">");
      //  SE CREAN TODOS LOS ENLACE
      outputFile.println("\t\t\t\t\t</UML:Interaction.message>");
    }
    else
      outputFile.println("\t\t\t\t\t\t<UML:Interaction xmi.id=\"UMLInteractionInstanceSet.4\" name=\"InteractionInstanceSet1\" visibility=\"public\" isSpecification=\"false\" context=\"UMLCollaborationInstanceSet.3\"/>");
    outputFile.println("\t\t\t\t\t</UML:Collaboration.interaction>");
  }
  
  public void declaracionClase()
  {
    clase = objds.getClase();
    int dim = clase.size();
    if (dim > 0)    //  SI ES MAYOR SE DECLARAN TODAS LAS CLASES EXISTENTE
    {
      outputFile.println("\t\t\t\t\t<UML:Namespace.ownedElement>");
      for (int i = 0; i < dim; i++)
      {
        clsClase objclase = clase.get(i);
        if (objclase.getTipo() == 2)
          addClase(clase.get(i));
      }
      outputFile.println("\t\t\t\t\t</UML:Namespace.ownedElement>");
    }
    //outputFile.println("");
  }
  
  private void declaracionEstereotipoClase()
  {
    clase = objds.getClase();
    int dim = clase.size();
    for (int i = 0; i < dim; i++)
    {
      clsClase objclase = clase.get(i);
      String id = "UMLClassifierRole."+objclase.getId();
      //  (interfaz, control, entidad) => (boundary, control, entity)
      String estereotipo = "";
      System.out.println("INGLES => "+objclase.getClassinterface());
      if (objclase.getTipo() == 2)
      {
      if (objclase.getClassinterface().equals("Interfaz"))
        estereotipo = "boundary";
      if (objclase.getClassinterface().equals("Proceso"))
        estereotipo = "control";
      if (objclase.getClassinterface().equals("Entidad"))
        estereotipo = "entity";
      }
      outputFile.println("\t\t\t\t<UML:Stereotype xmi.id=\"X.56\" name=\""+estereotipo+"\" extendedElement=\""+id+"\"/>");
      
    }
  }
  
  private void addClase(clsClase objclase)
  {
    String id = "UMLClassifierRole."+objclase.getId();
    outputFile.println("\t\t\t\t\t\t<UML:ClassifierRole xmi.id=\""+id+"\" name=\""+objclase.getNombre()+"\" visibility=\""+objclase.getAcceso()+"\" isSpecification=\"false\" isRoot=\"false\" isLeaf=\"false\" isAbstract=\"false\">");
    outputFile.println("\t\t\t\t\t\t\t<UML:Classifier.feature>");
    addAtributo(objclase);
    addMetodo(objclase);
    outputFile.println("\t\t\t\t\t\t\t</UML:Classifier.feature>");
    outputFile.println("\t\t\t\t\t\t</UML:ClassifierRole>");
  }
  
  private void addAtributo(clsClase objclase)
  {
    LinkedList<clsAtributo> atributo = objclase.getAtributos();
    int dim = atributo.size();
    for (int i = 0; i < dim; i++)
    {
      clsAtributo objatributo = atributo.get(i);
      String id = "UMLClassifierRole."+objclase.getId();
      outputFile.println("\t\t\t\t\t\t\t<UML:Attribute xmi.id=\"UMLAttribute.16\" name=\""+objatributo.getNombre()+"\" visibility=\""+objatributo.getAcceso()+"\" isSpecification=\"false\" ownerScope=\"instance\" changeability=\"changeable\" targetScope=\"instance\" type=\""+objatributo.getTipo()+"\" owner=\""+id+"\"/>");
    }
  }
  
  private void addMetodo(clsClase objclase)
  {
    LinkedList<clsMetodo> metodo = objclase.getMetodos();
    int dim = metodo.size();
    for (int i = 0; i < dim; i++)
    {
      clsMetodo objmetodo = metodo.get(i);
      String id = "UMLClassifierRole."+objclase.getId();
      String idm= "UMLOperation."+objclase.getId();
      if (objmetodo.getParametros().size() > 0)
      {
        outputFile.println("\t\t\t\t\t\t\t<UML:Operation xmi.id=\""+idm+"\" name=\""+objmetodo.getNombre()+"\" visibility=\""+objmetodo.getAcceso()+"\" isSpecification=\"false\" ownerScope=\"instance\" isQuery=\"false\" concurrency=\"sequential\" isRoot=\"false\" isLeaf=\"false\" isAbstract=\"false\" specification=\""+objmetodo.getRetorna()+"\" owner=\""+id+"\">");
        addParametro(idm, objmetodo);
        outputFile.println("\t\t\t\t\t\t\t</UML:Operation>");
      }
      else
        outputFile.println("\t\t\t\t\t\t\t<UML:Operation xmi.id=\""+idm+"\" name=\""+objmetodo.getNombre()+"\" visibility=\""+objmetodo.getAcceso()+"\" isSpecification=\"false\" ownerScope=\"instance\" isQuery=\"false\" concurrency=\"sequential\" isRoot=\"false\" isLeaf=\"false\" isAbstract=\"false\" specification=\""+objmetodo.getRetorna()+"\" owner=\""+id+"\"/>");
    }
  }
  
  private void addParametro(String idm, clsMetodo objmetodo)
  {
    LinkedList<clsParametro> parametro = objmetodo.getParametros();
    int s = parametro.size();
    outputFile.println("\t\t\t\t\t\t\t\t<UML:BehavioralFeature.parameter>");
    for (int j = 0; j < s; j++)
    {
      clsParametro objparametro = parametro.get(j);
      outputFile.println("\t\t\t\t\t\t\t\t\t<UML:Parameter xmi.id=\"UMLParameter.20\" name=\""+objparametro.getNombre()+"\" visibility=\"public\" isSpecification=\"false\" kind=\"in\" behavioralFeature=\""+idm+"\" type=\""+objparametro.getTipo()+"\"/>");
    }
    outputFile.println("\t\t\t\t\t\t\t\t</UML:BehavioralFeature.parameter>");
  }
  
  public void definirValoresEnlace()
  {
  }
}
