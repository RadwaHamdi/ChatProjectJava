/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Alaa
 */
public interface CleintModelInterface extends Remote {

    public String printHellofromcleint() throws RemoteException;

    public void openChatWindow(ArrayList<String> chatMembers, int chat_ID) throws RemoteException;

    public void receiveMessageFromServer(String message, int chat_id) throws RemoteException;

    public void tellCleintServerIsOff() throws RemoteException;

    public void tellCleintServerIsOn() throws RemoteException;

    public void setServerState() throws RemoteException;

    public void receiveAdvertise(byte[] b) throws RemoteException;

    public int downloadFileClientSide(File file, byte[] b, int frameId) throws RemoteException;

    public void handleDownloadFileClientSide(int flag) throws RemoteException;
    
    public void popUpOnlineCleintsClientSide(user onlineFriend) throws RemoteException;
    
}
