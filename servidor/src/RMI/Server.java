/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package RMI;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Implementación del servidor RMI y su interfaz
 *
 * @author Omen
 */
public class Server extends UnicastRemoteObject implements IServer {

  private final List<IClient> clientes;
  private final List<Image> imagenes;

  public Server() throws RemoteException {
    super();

    JFrame frame = new JFrame();
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    JButton loginButton = new JButton("iniciar");
    loginButton.setBounds(10, 80, 80, 25);
    frame.add(loginButton);
    frame.setResizable(false);
    frame.setVisible(true);


    clientes = new ArrayList<>();
    imagenes = new ArrayList<>();
    imagenes.add(new Image("Imagen1",
        "https://img.peru21.pe/files/article_content_ec_fotos/uploads/2017/08/11/598d4b8825b21.jpeg"));
    imagenes.add(new Image("Imagen2",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d6/Vladimir_Putin_in_KGB_uniform.jpg/140px-Vladimir_Putin_in_KGB_uniform.jpg"));
    imagenes.add(new Image("Imagen3", "https://www.uv.mx/universo/532/infgral/images/002.gif"));
    imagenes.add(new Image("Imagen4",
        "https://img.peru21.pe/files/article_content_ec_fotos/uploads/2017/08/11/598d4b8825b21.jpeg"));
    imagenes.add(
        new Image("Imagen5", "https://www.abc.es/Media/201410/22/enola-gay-avion--644x425.jpg"));
    imagenes.add(new Image("Imagen6", "https://i.ytimg.com/vi/asxohslP35Y/maxresdefault.jpg"));
    imagenes.add(new Image("Imagen7", "https://i.ytimg.com/vi/asxohslP35Y/maxresdefault.jpg"));
    imagenes.add(new Image("Imagen8",
        "https://cdn1.thr.com/sites/default/files/imagecache/landscape_928x523/2017/03/gettyimages-519745414-h_2017.jpg"));
    imagenes.add(new Image("Imagen9",
        "https://upload.wikimedia.org/wikipedia/commons/e/eb/Stephen_Hawking.StarChild.jpg"));
    imagenes.add(
        new Image("Imagen10", "https://www.abc.es/Media/201410/22/enola-gay-avion--644x425.jpg"));

  }

  private void init() throws RemoteException {
    try {
      String direccion = (InetAddress.getLocalHost()).toString();
      int puerto = 3333;
      System.out.println("Iniciando servidor en " + direccion + ":" + puerto);

      Registry registro = LocateRegistry.createRegistry(puerto);
      registro.bind("MiServidor", (IServer) this);

    } catch (Exception ex) {
      System.out.println("Error en" + ex.getMessage());
    }
  }

  @Override
  public void registrarForCallBack(IClient cliente) throws RemoteException {
    if (!clientes.contains(cliente)) {
      clientes.add(cliente);
      mandarProcesamiento();
    }
  }

  @Override
  public void desregistrarForCallBack(IClient cliente) throws RemoteException {
    clientes.remove(cliente);
  }

  private void mandarProcesamiento() throws RemoteException {
    for (IClient cliente : clientes) {
      cliente.processImages(imagenes);
    }
  }

  public static void main(String[] args) throws RemoteException {
    (new Server()).init();
  }

  @Override
  public void processImages() throws RemoteException {
    for (IClient cliente : clientes) {
      cliente.processImages(imagenes);
    }
  }

}
