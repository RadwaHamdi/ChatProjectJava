/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Alaa
 */
public interface CleintModelInterface extends Remote {
    public String printHellofromcleint() throws RemoteException;
    public void openChatWindow(ArrayList<String> chatMembers,int chat_ID) throws RemoteException;

}
