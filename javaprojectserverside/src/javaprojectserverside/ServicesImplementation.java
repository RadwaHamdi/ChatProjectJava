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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author Alaa
 */
public class ServicesImplementation extends UnicastRemoteObject implements ServicesInterface,Serializable{
     private  DatabaseConnection connection;
     Vector<CleintModelInterface> cleints=new Vector<CleintModelInterface>();
     ArrayList<String> online_users=new ArrayList<String>();
     HashMap<String,CleintModelInterface> cleints_with_email=new HashMap<String, CleintModelInterface>();
    //in this constructor we have aconnection so we just use it to connect database
    public ServicesImplementation() throws RemoteException{
       
    }

    public String printHellofromserver()  {
       return "Hello From server";
    }
    public void registerCleint(CleintModelInterface newcleint,String user_email){
        cleints.add(newcleint);
        online_users.add(user_email);
        cleints_with_email.put(user_email, newcleint);
       
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

    public int startNewChatSessionstr(String user_email, ArrayList<String> receiverEmails) throws RemoteException {
       
        receiverEmails.add(user_email);
        Collections.sort(receiverEmails);
        ArrayList<String> temp;
        boolean check=false;
        //ArrayList<String> sessions_memebers = new ArrayList<String>();
        for (int i = 0; i < receiverEmails.size(); i++) {
            for (int j = 0; j < online_users.size(); j++) {
                if(online_users.get(j).equals(receiverEmails.get(i))){
                    check=true;
                    break;
                }
            }
            if(check==false){
                return  -1;
            }
        }
         for(int i=0;i<Server.sessions_ids.size();i++){
             temp=Server.sessions_ids.get(i+1);
             if(temp.equals(receiverEmails)){
                 return  -1;
                 
             }
        }
        for(int i=0;i<receiverEmails.size();i++){
            int j=0;
            for (j = 0; j <online_users.size(); j++) {
                if(online_users.get(j).equals(receiverEmails.get(i))){
                    cleints_with_email.get(receiverEmails.get(i)).openChatWindow(receiverEmails, Server.counter);
                }
            }
        }
        return Server.counter++;
    }

    

}
