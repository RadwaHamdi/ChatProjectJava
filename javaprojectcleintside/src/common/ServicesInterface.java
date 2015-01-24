/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Alaa
 */
public interface ServicesInterface extends Remote{
//this method just for test
    public String printHellofromserver() throws RemoteException;
    public void registerCleint(CleintModelInterface newcleint)throws RemoteException;
    public Vector retreiveContactList(user u)throws RemoteException;
}
