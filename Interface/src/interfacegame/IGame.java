/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package interfacegame;

import java.rmi.Remote;
import java.rmi.RemoteException;
import RMI.ICliente;

/**
 * Interfaz Game para el videojuego Hereda de Java.rmi.Remote Debe manejar RemoteException en todos
 * sus metodos
 * 
 * @author texch
 */
public interface IGame extends Remote {

  void moveLeft() throws RemoteException;

  void moveRight() throws RemoteException;

  void moveUp() throws RemoteException;

  void moveDown() throws RemoteException;

  void login(String nombre) throws RemoteException;

  public void registerForCallBack(ICliente cliente) throws RemoteException;

  public void unregisterForCallBack(ICliente cliente) throws RemoteException;

  void recibirMensaje(String mensaje) throws RemoteException;

}
