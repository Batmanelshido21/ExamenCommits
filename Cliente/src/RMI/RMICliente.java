/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;


import com.sun.security.ntlm.Client;
import interfacegame.IGame;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author texch
 */
public class RMICliente {

  public static void main(String[] args) {
    
    
    
    IGame server;
    String nombre = "GameServer";
    String serverName = "localhost";
    int serverPORT = 3232;
    
    try{
      
      
      Registry registro = LocateRegistry.getRegistry(serverName, serverPORT);
      server = (IGame) registro.lookup(nombre);
      server.moveLeft();
      
      
    } catch (RemoteException ex) {
      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NotBoundException ex) {
      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
