/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectserverside;

import common.CleintModelInterface;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alaa
 */
public class ServerController  {
    public static HashMap<Integer, ArrayList<String>> sessions_ids;
    public static int counter;
    ServerView view;
    ServicesImplementation obj;
    Registry reg;
    boolean serverRunning=true;
    boolean IssFirstTimeToRunServer=true;
    Vector<CleintModelInterface> onlineCleints;
    CleintModelInterface first;
    public  ServerController(){
        view=new ServerView(this);
        view.addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
                 onlineCleints= obj.getOnlineCleints();
                System.out.println(onlineCleints.size());
               
            for(int i=0;i<onlineCleints.size();i++){
                     try {
                        // if(onlineCleints.get(i)!=null)
                         onlineCleints.get(i).tellCleintServerIsOff();
                         System.out.println("send to cleint "+i+" to  him serverstopped");
                     } catch (RemoteException ex) {
                      
                     }
            }
                
             JOptionPane.showMessageDialog(view,"Server is stopped");
            serverRunning=false;
                
            }
          

            public void windowClosed(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        try{
        sessions_ids=new HashMap<Integer, ArrayList<String>>();
        counter=0;
        obj=new ServicesImplementation(this);
        reg=LocateRegistry.createRegistry(5005);
        reg.rebind("chatservice", (Remote) obj);
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Server is already runing");
            view.dispose();
        }
        
    }
    
  
    public static void main(String[] args) {
        new  ServerController();
    }
    
    public  void startServer(){
        

		try{
         //here we  open connection to database so we should take object from class database connection
                        if(!serverRunning){                       
                        
                        if(IssFirstTimeToRunServer){  
                        
                        IssFirstTimeToRunServer=false;                        
                        }
                        
                        onlineCleints= obj.getOnlineCleints();  
                        System.out.println(onlineCleints.size()+"sssssssssss");
                      for(int i=0;i<onlineCleints.size();i++){
                      onlineCleints.get(i).tellCleintServerIsOn();
                      onlineCleints.get(i).setServerState();
                      System.out.println("send to cleint "+i+" to  him server started");
                       }
                      
                        serverRunning=true;
                        
                        JOptionPane.showMessageDialog(view,"Server is started");
                        }
                        else{
                        JOptionPane.showMessageDialog(view,"Server is already running");
                        }
     
		}catch(RemoteException ex){
			ex.printStackTrace();
			}
                
                
                 
                 
                         

    
    }
    public  void  stopServer(){
        try {
            if(serverRunning){
            //reg.unbind("chatservice");
            //UnicastRemoteObject.unexportObject((Remote)obj, true);
            
                        onlineCleints= obj.getOnlineCleints();
                System.out.println(onlineCleints.size());
            for(int i=0;i<onlineCleints.size();i++){
                onlineCleints.get(i).tellCleintServerIsOff();
                 System.out.println("send to cleint "+i+" to  him serverstopped");
            }
                    
             JOptionPane.showMessageDialog(view,"Server is stopped");
            serverRunning=false;
            }
            else{
                JOptionPane.showMessageDialog(view, "Server is already not running");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    

}
