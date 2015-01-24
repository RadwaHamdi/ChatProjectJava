/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectserverside;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Alaa
 */
public class Server  {

    public Server() {

		try{
         //here we  open connection to database so we should take object from class database connection
                        
			ServicesImplementation obj=new ServicesImplementation();
			Registry reg=LocateRegistry.createRegistry(5005);
			reg.rebind("chatservice", (Remote) obj);
     
		}catch(RemoteException ex){
			ex.printStackTrace();
			}
	}
    public static void main(String[] args){
		new Server();

	}

}
