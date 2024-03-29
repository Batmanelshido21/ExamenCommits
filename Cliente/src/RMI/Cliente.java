/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package RMI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Implementa la interfaz del cliente y se conecta al servidor Java RMI
 * 
 * @author Omen
 */
public class Cliente {

  private static final int PORT = 3333;
  private static final String NAMESERVICE = "MiServidor";
  private static final String HOSTNAMESERVER = "localhost";

  public static JFrame frame;
  public static JDesktopPane desktopPane;
  public static IServer server;
  public static CallBackClient callBackClient;

  public Cliente() throws RemoteException {
    gui();
    rmi();
  }

  private void rmi() {
    try {

      callBackClient = new CallBackClient();
      Registry registro = LocateRegistry.getRegistry(HOSTNAMESERVER, PORT);
      server = (IServer) registro.lookup(NAMESERVICE);
      server.registrarForCallBack(callBackClient);
      server.processImages();
    } catch (Exception ex) {
      System.out.println("Error en " + ex.getMessage());
    }
  }

  private void gui() {
    frame = new JFrame("Render Client");
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    desktopPane = new JDesktopPane();

    frame.setContentPane(desktopPane);
    frame.setVisible(true);

    frame.addWindowListener(new WindowAdapter() {

      public void windowClosing(WindowEvent e) {
        int i = JOptionPane.showConfirmDialog(desktopPane, "¿Salir de la aplicación?");
        if (i == 0) {
          try {
            server.desregistrarForCallBack(callBackClient);
          } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }

    });


  }

  public static void main(String[] arg) throws RemoteException {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try {
          new Cliente();
          final List<IClient> clientes = null;
        } catch (RemoteException ex) {
          System.out.println("Error en " + ex.getMessage());
        }
      }

    });
  }

}
