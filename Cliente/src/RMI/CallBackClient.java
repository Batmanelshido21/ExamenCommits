/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author Omen
 */
public class CallBackClient extends UnicastRemoteObject implements IClient {

    public CallBackClient() throws RemoteException {
        super();
    }
    
    public void cancel() throws RemoteException {
        System.out.println("Se ha cancelado el procesamiento");
    }

    public void processImages(List<Image> images) throws RemoteException {
        int i=1;
        for(Image img : images) {
            new Thread(new FrameImage(img, i++)).start();
        }
    }

   
    
}
