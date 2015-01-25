/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectserverside;
import common.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector ;
import common.*;
import java.io.Serializable;

/**
 *
 * @author Alaa
 */
public class ServicesImplementation extends UnicastRemoteObject implements ServicesInterface,Serializable{
     private  DatabaseConnection connection;
     Vector<CleintModelInterface> cleints=new Vector<CleintModelInterface>();
    //in this constructor we have aconnection so we just use it to connect database
    public ServicesImplementation() throws RemoteException{
       
    }

    public String printHellofromserver()  {
       return "Hello From server";
    }
    public void registerCleint(CleintModelInterface newcleint){
        cleints.add(newcleint);
        try{
            System.out.println("hello there is anew cleint registerded");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    
    }

    public Vector<user> retreiveContactList(user u) {
        DatabaseConnection con=new DatabaseConnection();
        return con.retreiveContactList(u);
    }
    public int setStatusServer(String Email,int status){
        DatabaseConnection con=new DatabaseConnection();
        return con.changeuserstatus(Email, status);
        
    
    }

    

}
