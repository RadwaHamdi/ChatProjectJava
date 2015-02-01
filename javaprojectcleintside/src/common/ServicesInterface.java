/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;


import java.io.File;
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
    public void sendMessagetoserver(String message,int chat_id) throws RemoteException;
    public void closechatsession(int chat_id)throws RemoteException;
    public int insertUserData(user u) throws RemoteException;
    public user signInServerSide(String email, String password)throws RemoteException;
    public void unRegisterCleintServerSide(user oldUser,CleintModelInterface userRef)throws RemoteException;
    public boolean checkServerState()throws RemoteException;
    public void downloadFileServerSide(File file, byte[] b, int frameId, String email)throws RemoteException;
    int addFriendServerSide(String userEmail, String receiverEmail) throws RemoteException;
    int removeFriendServerSide(String userEmail, String receiverEmail) throws RemoteException;
}
