
package ejemplos;

import java.io.Serializable;

import javax.swing.*;

public class principal //implements Serializable 
{
  //private JPanel principal  = null;
  private JComponent panel   = null;
  
  public principal(JPanel panel)
  { this.panel = panel; }
  
  public void setPanel(JPanel panel)
  { this.panel = panel;  }
  
  public JPanel getPanel()
  { return (JPanel)panel; }
  
  public void addComponente(JComponent comp)
  {
    panel.add(comp, null);
  }
  
  public void delComponente(JComponent comp)
  {
    panel.remove(comp);
  }
  
  public void modComponente(JComponent comp)
  {
    delComponente(comp);
    addComponente(comp);
  }
  
  public void guardar()
  {}
  
  public void abrir()
  {}
}
