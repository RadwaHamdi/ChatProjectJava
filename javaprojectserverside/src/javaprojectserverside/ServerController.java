/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectserverside;

import common.CleintModelInterface;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Alaa
 */
public class ServerController  {
    public static HashMap<Integer, ArrayList<String>> sessions_ids;
    public static int counter;
    ServerView view;
    ServerGUI serverView ;
    ServicesImplementation obj;
    Registry reg;
    boolean serverRunning=true;
    boolean IssFirstTimeToRunServer=true;
    Vector<CleintModelInterface> onlineCleints;
    CleintModelInterface first;
    public  ServerController(){
        view=new ServerView(this);
        
       // serverView = new ServerGUI(this);
        
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
        System.setProperty("java.rmi.server.hostname", "10.145.208.119");
        obj=new ServicesImplementation(this);
        reg=LocateRegistry.createRegistry(5005);
        reg.rebind("chatservice", (Remote) obj);
        
        }catch(Exception e){
          //  e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Server is already runing");
            System.exit(0);
           
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
			 JOptionPane.showMessageDialog(view,"Server is already running");
                        //serverView.dispose();;
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
            JOptionPane.showMessageDialog(view, "Server is already not running");
        }
    }
    
    
    
     public void sendAdvertise() throws RemoteException{
        try {
            byte[] b;
            System.out.println("send advertisment ");
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                FileInputStream fis = new FileInputStream(fc.getSelectedFile().getPath());
               // int fileSize = fis.available();
           // FileInputStream fis = new FileInputStream(new File("C:\\Users\\Alaa\\Desktop\\icons\\friend.jpg"));
            int size=(int)(new File(fc.getSelectedFile().getPath()).length());
            b=new byte[size];
            fis.read(b);
            
            System.out.println("size="+size);    
              
                    System.out.println("before call...");
                    onlineCleints=obj.getOnlineCleints();
                    for (int i = 0; i <onlineCleints.size(); i++) {
                        
                        onlineCleints.get(i).receiveAdvertise(b);
            }
                    
                    System.out.println("call recieveAdv...");
         
            System.out.println("after loop..."); 
            //System.out.println("FileReaderStream >> file size ");
            fis.close();
            }    
        }catch(Exception e){
            e.printStackTrace();
        }
}
    
    
    

}
