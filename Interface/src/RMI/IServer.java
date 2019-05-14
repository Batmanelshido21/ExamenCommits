/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz RMI para el servidor Declara dos métodos para registrar y quitar el registro del
 * cliente.
 * 
 * @author Omen
 */
public interface IServer extends Remote {

  /**
   * Registra el cliente remoto para su posterior llamada
   * 
   * @throws RemoteException
   */
  public void registrarForCallBack(IClient cliente) throws RemoteException;

  /**
   * Quita el cliente remoto
   * 
   * @throws RemoteException
   */
  public void desregistrarForCallBack(IClient cliente) throws RemoteException;

  public void processImages() throws RemoteException;

  // public void porcentaje(int porciento) throws RemoteException;
}
