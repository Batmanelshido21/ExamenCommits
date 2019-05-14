/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import RMI.ICliente;
import interfacegame.IGame;

/**
 *
 * @author javie
 */
public class Cliente extends UnicastRemoteObject implements ICliente {

  /**
   * @param args the command line arguments
   */
  public Cliente() throws RemoteException {
    super();
  }

  public static void main(String[] args) throws RemoteException, NotBoundException {
    Scanner lector = new Scanner(System.in);
    IGame server;
    String nombre = "GameServer";
    String serverName = "localhost";
    int ServerPORT = 3232;

    try {
      Cliente cliente = new Cliente();
      Registry registro = LocateRegistry.getRegistry(serverName, ServerPORT);
      server = (IGame) registro.lookup(nombre);
      server.registerForCallBack(cliente);
      server.moveLeft();
      String mensaje = "Hola vato loco";
      server.recibirMensaje(mensaje);

    } catch (IOException e) {

    }
  }

  @Override
  public void notifyMe(String message) throws RemoteException {
    System.out.println("mensaje recibido" + message);
  }

}
