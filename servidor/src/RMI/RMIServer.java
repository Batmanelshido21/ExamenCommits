/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package RMI;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import interfacegame.IGame;

/**
 *
 * @author texch
 */
public class RMIServer extends UnicastRemoteObject implements IGame {

  // static public List<Player> players;


  private static final long serialVersionUID = 9090898209349823403L;
  private final int PORT = 3232;
  private ArrayList<ICliente> clientes;

  public RMIServer() throws RemoteException {

    super();
    /*
     * Lienzo lienzo = new Lienzo(); players = new ArrayList<>();
     */
    clientes = new ArrayList<>();
    JFrame frame = new JFrame();
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    // frame.add(lienzo);

    frame.setResizable(false);
    frame.setVisible(true);

    // new Thread(new LienzoRefresh(lienzo)).start();
  }

  public static void main(String[] args) throws RemoteException {
    (new RMIServer()).iniciarServer();
  }

  public void iniciarServer() {
    try {

      String direccion = (InetAddress.getLocalHost().toString());
      System.out.println("Iniciando servidor en " + direccion + ":" + PORT);
      Registry registro = LocateRegistry.createRegistry(PORT);
      registro.bind("GameServer", (IGame) this);
      System.out.println("Servidor iniciado");

    } catch (UnknownHostException ex) {
      Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
    } catch (RemoteException ex) {
      Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
    } catch (AlreadyBoundException ex) {
      Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void moveLeft() throws RemoteException {
    System.out.println("Move Left");
  }

  @Override
  public void moveRight() throws RemoteException {
    System.out.println("Move Right");
  }

  @Override
  public void moveUp() throws RemoteException {
    System.out.println("Move Up");
  }

  @Override
  public void moveDown() throws RemoteException {
    System.out.println("Move Down");
  }

  @Override
  public void login(String nombre) throws RemoteException {
    /*
     * int[] pos = Lienzo.getRandomFreePosition(); players.add(new Player(Color.yellow, nombre,
     * pos[0], pos[1], PORT, "localhost"));
     */
  }

  @Override
  public void registerForCallBack(ICliente cliente) throws RemoteException {
    if (!clientes.contains(cliente)) {
      clientes.add(cliente);
    }
    doCallBack(" cliente ha entrado");
  }

  @Override
  public void unregisterForCallBack(ICliente cliente) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  @Override
  public void recibirMensaje(String mensaje) throws RemoteException {
    System.out.println(mensaje);
  }

  private synchronized void doCallBack(String mensaje) throws RemoteException {
    for (ICliente cliente : clientes) {
      cliente.notifyMe(mensaje);
    }
  }
}
