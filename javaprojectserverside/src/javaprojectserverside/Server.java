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
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alaa
 */
public class Server  {
    public static HashMap<Integer, ArrayList<String>> sessions_ids;
    public static int counter;
    
  

    public Server() {
        sessions_ids=new HashMap<Integer, ArrayList<String>>();
        counter=0;

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
