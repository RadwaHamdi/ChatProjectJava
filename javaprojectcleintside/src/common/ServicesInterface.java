/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Alaa
 */
public interface ServicesInterface extends Remote{
//this method just for test
    public String printHellofromserver() throws RemoteException;
    public void registerCleint(CleintModelInterface newcleint,String user_email)throws RemoteException;
    public Vector<user> retreiveContactList(user u)throws RemoteException;
    public int setStatusServer(String Email,int status) throws RemoteException;
    public int startNewChatSessionstr(String user_email,ArrayList<String> receiverEmails)throws RemoteException;
}
