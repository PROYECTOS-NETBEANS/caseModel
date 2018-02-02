package Archivo;

import Negocio.*;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.LinkedList;

public class guardarXMI
{
  
  static PrintWriter outputFile;
  private clsDiagrama objds;
  
  private LinkedList<clsTabla> clase = new LinkedList<clsTabla>();
  private LinkedList<clsRelacion> conector = new LinkedList<clsRelacion>();
  
  public guardarXMI(clsDiagrama objds)
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
    // conector = objds.getConector(); limbert
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
    clase = objds.getTablas();
    int dim = clase.size();
    if (dim > 0)    //  SI ES MAYOR SE DECLARAN TODAS LAS CLASES EXISTENTE
    {
      outputFile.println("\t\t\t\t\t<UML:Namespace.ownedElement>");
      for (int i = 0; i < dim; i++)
      {
        clsTabla objclase = clase.get(i);
        // limbert if (objclase.getTipo() == 2)
          addClase(clase.get(i));
      }
      outputFile.println("\t\t\t\t\t</UML:Namespace.ownedElement>");
    }
    //outputFile.println("");
  }
  
  
  private void addClase(clsTabla objclase)
  {
    String id = "UMLClassifierRole."+objclase.getId();
    outputFile.println("\t\t\t\t\t\t<UML:ClassifierRole xmi.id=\""+id+"\" name=\""+objclase.getNombreTabla()+"\" visibility=\""+objclase.getAcceso()+"\" isSpecification=\"false\" isRoot=\"false\" isLeaf=\"false\" isAbstract=\"false\">");
    outputFile.println("\t\t\t\t\t\t\t<UML:Classifier.feature>");
    addAtributo(objclase);
    outputFile.println("\t\t\t\t\t\t\t</UML:Classifier.feature>");
    outputFile.println("\t\t\t\t\t\t</UML:ClassifierRole>");
  }
  
  private void addAtributo(clsTabla objclase)
  {
    LinkedList<clsColumna> atributo = objclase.getColumnas();
    int dim = atributo.size();
    for (int i = 0; i < dim; i++)
    {
      clsColumna objatributo = atributo.get(i);
      String id = "UMLClassifierRole."+objclase.getId();
      outputFile.println("\t\t\t\t\t\t\t<UML:Attribute xmi.id=\"UMLAttribute.16\" name=\""+objatributo.getNombre()+"\" visibility=\""+objatributo.getAcceso()+"\" isSpecification=\"false\" ownerScope=\"instance\" changeability=\"changeable\" targetScope=\"instance\" type=\""+objatributo.getTipo()+"\" owner=\""+id+"\"/>");
    }
  }
   
  public void definirValoresEnlace()
  {
  }
}
