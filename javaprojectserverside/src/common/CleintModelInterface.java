/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Alaa
 */
public interface CleintModelInterface extends Remote {
    public String printHellofromcleint() throws RemoteException;

}
