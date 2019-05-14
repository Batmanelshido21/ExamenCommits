/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;
import java.rmi.*;
import java.util.List;

/**
 * Interfaz RMI para ser implementada por el cliente
 * @author Omen
 */
public interface IClient extends Remote {
    
    /**
     * Cancela el procesamiento de las imágenes. Esta función puede ser llamada por el Servidor
     * @throws RemoteException 
     */
    public void cancel() throws RemoteException;
    
    /**
     * Procesa las imágenes. Esta función puede ser llamada por el servidor
     * @param images
     * @throws RemoteException 
     */
    public void processImages(List<Image> images) throws RemoteException;
    
}
