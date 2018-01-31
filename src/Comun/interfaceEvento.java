package Comun;

import java.util.EventListener;

public interface interfaceEvento extends EventListener
{
  public void transferirMensajeChat(transferirEvento objevento);
          
  public void actualizarListaUsuario(transferirEvento objevento);
  
  public void transferirDiagramaSecuencia(transferirEvento objevento);
  
  public void transferirActor(transferirEvento objevento);
  
  public void transferirClase(transferirEvento objevento);
  
  public void transferirConector(transferirEvento objevento);
   
  public void transferirActualizarActor(transferirEvento objevento);
  
  public void transferirActualizarClase(transferirEvento objevento);
  
  public void transferirActualizarConector(transferirEvento objevento);
  
  public void transferirMensajeError(transferirEvento objevento);
  
  
  
  public void transferirAtributo(transferirEvento objeventol);
  
  public void transferirMetodo(transferirEvento objevento);
}