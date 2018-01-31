/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

/**
 *
 * @author Nancy
 */
import Control.control;
import Comun.interfaceServidor;
import Negocio.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class DynamicTree extends JPanel
{
  protected DefaultMutableTreeNode rootNode;
  public DefaultTreeModel treeModel;
  public JTree tree;
  private Toolkit toolkit = Toolkit.getDefaultToolkit();
  
  private DefaultTreeCellRenderer demo = new DefaultTreeCellRenderer();
  private control objcontrol;
   
  public DynamicTree()
  {
    /*this.setFocusable(true);
    this.setPreferredSize(new Dimension(1630, 1630));
    //this.setBounds(new Rectangle(0, 0, 1630, 1630));
    this.setBackground(new Color(0, 132, 0));
    this.setDoubleBuffered(true);*/
    this.setLayout(new BorderLayout());   //  aumentado
    this.setPreferredSize(new Dimension(180, 150));
    
    rootNode = new DefaultMutableTreeNode("Diagrama de Secuencia");
    
    //rootNode.
    //this.addMouseListener(new eventoMouse());
    treeModel = new DefaultTreeModel(rootNode);
    
    //treeModel.setRoot(null);
    demo.setOpenIcon(new ImageIcon("imagenes/clase.png"));
    demo.setLeafIcon(new ImageIcon("imagenes/metodo.png"));
    
    //demo.setOpenIcon(new ImageIcon("img/clase.gif"));
    //demo.setClosedIcon(new ImageIcon("img/diagrama.gif"));
    //demo.setIcon(new ImageIcon("img/diagrama.gif"));
    
    tree = new JTree(treeModel);
    tree.setCellRenderer(demo);
    tree.setEditable(false);
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    tree.setShowsRootHandles(true);
    
    JScrollPane scrollPane = new JScrollPane(tree);
    //setLayout(new GridLayout(1,0));
    add(scrollPane, BorderLayout.CENTER);
    // System.out.println( rootNode.getParent().toString());
  }

  public DefaultMutableTreeNode getRootNode()
  {
    return rootNode;
  }
  
  /** Remove all nodes except the root node. */
  public void clear()
  {
    rootNode.removeAllChildren();
    treeModel.reload();
  }

  /** Remove the currently selected node. */
  public void removeCurrentNode()
  {
    TreePath currentSelection = tree.getSelectionPath();
    if (currentSelection != null)
    {
      DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
      MutableTreeNode parent = (MutableTreeNode)(currentNode.getParent());
      if (parent != null)
      {
        treeModel.removeNodeFromParent(currentNode);
        return;
      }
    }
    // Either there was no selection, or the root was selected.
    toolkit.beep();
  }

  //Add child to the currently selected node. */
  public DefaultMutableTreeNode addObject(Object child)
  {
    DefaultMutableTreeNode parentNode = null;
    TreePath parentPath = tree.getSelectionPath();
    //tree.setSelectionPath();
    if (parentPath == null)
    {
      parentNode = rootNode;
    }
    else
    {
      parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
    }
    
    return addObject(parentNode, child, true);
  }

  public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child)
  {
    return addObject(parent, child, false);
  }

  public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child,  boolean shouldBeVisible)
  {
    DefaultMutableTreeNode childNode =  new DefaultMutableTreeNode(child);
    
    if (parent == null)
    {
      parent = rootNode;
    }
    
    treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
    
    // Make sure the user can see the lovely new node.
    if (shouldBeVisible)
    {
      tree.expandPath(new TreePath(parent.getPath()));
      tree.scrollPathToVisible(new TreePath(childNode.getPath()));
    }
    
    /*System.out.println("**************** ARBOL ******************");
    DefaultMutableTreeNode arb = rootNode;
    //DefaultMutableTreeNode arb = parent;
    int dim = arb.getChildCount();
    for (int i = 0; i < dim; i++)
    {
      clase obj = (clase)((DefaultMutableTreeNode)(MutableTreeNode)arb.getChildAt(i)).getUserObject();
      System.out.println("d => "+obj.getId());
      //MutableTreeNode o = (MutableTreeNode)arb.getChildAt(i);
      //System.out.println("HOLA "+((clase)o).getId());
      //System.out.println("Child => "+arb.getChildAt(i)+" - clase => "+((clase)o).getId());
    }*/
    
    return childNode;
  }
  
  public void actualizarArbolClase(clsClase objclase)
  {
    System.out.println("**************** ARBOL ******************");
    /*DefaultMutableTreeNode arb = rootNode;
    int dim = arb.getChildCount();
    for (int i = 0; i < dim; i++)
    {
      clase obj = (clase)((DefaultMutableTreeNode)(MutableTreeNode)arb.getChildAt(i)).getUserObject();
      System.out.println("d => "+obj.getId());
      
    }*/
    
    int dim = rootNode.getChildCount();
    DefaultMutableTreeNode child = null;
    for (int i = 0; i < dim; i++)
    {
      child = (DefaultMutableTreeNode)(MutableTreeNode)rootNode.getChildAt(i);//.getUserObject();
      clsClase obj = (clsClase)child.getUserObject();
      System.out.println("IDS => "+objclase.getId() +"=="+ obj.getId());
      if (objclase.getId() == obj.getId())
      {
        tree.remove(i);
        rootNode.remove(i);
        addObject(null, obj, true);
        return;
        //tree.setSelectionModel();
        //removeCurrentNode();
      }
    }
    treeModel.reload();
  }

/* public DefaultMutableTreeNode addObject(ForoCompuesto f) {
DefaultMutableTreeNode parentNode = null;

TreePath parentPath = tree.getSelectionPath();
//  System.out.println(parentPath.);

if (parentPath == null) {
parentNode = rootNode;
} else {
parentNode = (DefaultMutableTreeNode)
(parentPath.getLastPathComponent());
// System.out.println(((ForoAbstracta)parentNode.getUserObject()).getAsunto());
}

return addObject(parentNode, f, true);
}

public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
ForoCompuesto f) {
return addObject(parent, f, false);
}

public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, ForoCompuesto f,  boolean shouldBeVisible) {
DefaultMutableTreeNode childNode =  new DefaultMutableTreeNode(f.getAsunto());

childNode.setUserObject(f);
// childNode.



// System.out.println( );

if (parent == null) {
parent = rootNode;
}

treeModel.insertNodeInto(childNode, parent,parent.getChildCount());

// Make sure the user can see the lovely new node.
if (shouldBeVisible) {
tree.expandPath(new TreePath(parent.getPath()));
tree.scrollPathToVisible(new TreePath(childNode.getPath()));

}
return childNode;
}

// public ForoCompuesto getUserObject() {
//  return userObject;
// }*/

/*
 * 
 *     arbol.addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            arbol_mousePressed(e);
          }
        });
 */
  public control getObjcontrol()
  {
    return objcontrol;
  }

  public void setObjcontrol(control objcontrol)
  {
    this.objcontrol = objcontrol;
  }
  
  /*class eventoMouse extends MouseAdapter
  {
    public void mousePressed(MouseEvent e)
    {
      System.out.println("dfdf => "+e.getSource());
      
    }
  }*/
}
