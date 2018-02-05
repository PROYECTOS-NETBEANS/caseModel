 package Presentacion;

import Comun.*;
import java.rmi.RemoteException;

public class formServidor
{
  public formServidor()
  {
  }
  public void iniciarServidor()
  {
    try 
    {
      implementacionServidor objservidor = new implementacionServidor();
      objservidor.iniciarServidor();
    }
    catch (RemoteException e) {
        e.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
      formServidor frm = new formServidor();
      frm.iniciarServidor();
  }
}
