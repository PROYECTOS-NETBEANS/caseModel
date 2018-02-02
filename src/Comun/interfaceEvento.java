package Comun;

import java.util.EventListener;

public interface interfaceEvento extends EventListener
{
  public void transferirMensajeChat(transferirEvento objevento);
          
  public void actualizarListaUsuario(transferirEvento objevento);
  
  public void transferirDiagramaSecuencia(transferirEvento objevento);
              
  public void transferirTabla(transferirEvento objevento);
  
  public void transferirRelacion(transferirEvento objevento);
  
  public void transferirActualizarTabla(transferirEvento objevento);
  
  public void transferirActualizarRelacion(transferirEvento objevento);
  
  public void transferirMensajeError(transferirEvento objevento); 
  
  public void transferirColumna(transferirEvento objeventol);
  
}