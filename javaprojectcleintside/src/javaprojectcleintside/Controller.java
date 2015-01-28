/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectcleintside;
import common.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.omg.CORBA.MARSHAL;

/**
 *
 * @author Alaa
 */
public class Controller implements Serializable {
    CleintModelInterface model;
    CleintVeiwInterface veiw;
    Vector<user> contactlist;
    Vector<Chat> chats=new Vector<Chat>();
    ServicesInterface  obj;
    user newcleint;
    
    
    HashMap<Integer, ArrayList<String>> sessions;
 
    public Controller (user newuser)throws RemoteException{
        
        model=new CleintModelInterfaceImplementation(this);
        sessions=new HashMap<Integer, ArrayList<String>>();
         try {
             
             
             Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5005);
              obj = (ServicesInterface) reg.lookup("chatservice");
              newcleint=newuser;
              
              newcleint.setEmail(newuser.getEmail());
              newcleint.setUserName(newuser.getUserName());
              System.out.println(newuser.getEmail());
              System.out.println(newuser.getUserName());
               
              obj.registerCleint(new CleintModelInterfaceImplementation(this),newcleint.getEmail());
              obj.printHellofromserver();
              
              //note the info of the user should be set from the log in method 
              //or we should here select by the user_email which user entered in login and select all info about this user from
              //database and set all info of this user here using setters
              
              contactlist=obj.retreiveContactList(newcleint);
              veiw=new MainFrame(this, contactlist,newcleint);
            //this should be removed this is just for testing logic
          /*   for(int i=0;i<contactlist.size();i++){
            System.out.print(contactlist.get(i).getFirstName()+":");
            System.out.print(contactlist.get(i).getLastName()+":");
            System.out.print(contactlist.get(i).getUserName()+":");
            System.out.print(contactlist.get(i).getEmail()+":");
            System.out.print(contactlist.get(i).getBirthDate()+":");
            System.out.print(contactlist.get(i).getStatus()+":");
            System.out.print(contactlist.get(i).getPassward()+":");
            System.out.println("");
             }
             //
              
            */
             


        
        } catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "Server is off");
               System.exit(0);
        }
    }
    /*public static void main(String[] args) {
        
        try{
        //Controller c=new Controller();
    
        
        
        
        }catch(Exception e){
            e.printStackTrace();
        }

    }*/
    public int setStatus(String email,int status){
        int inserted=0;
        try{
               inserted=obj.setStatusServer(email, status);
        }catch(Exception e){
            e.printStackTrace();
        }
        return inserted;
    }
    public  void openchatwindows_controller(ArrayList<String> chatMembers, int chat_ID) {
        sessions.put(chat_ID, chatMembers);
        //remeber to add chatmembers in GUI of chat frame :)
        Chat newchatwindow=new Chat(this);
        newchatwindow.setFrameID(chat_ID);
        chats.add(newchatwindow);
        
    }
    public void start_chat_session(ArrayList<String> members){
        try{
    int check=obj.startNewChatSessionstr(newcleint.getEmail(), members);
    if(check==-1){
        JOptionPane.showMessageDialog(null, "Sorry,you canot start this chat  session");
    }
    
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void sendMessageToServerSide(String message,int chat_id){
        try{
        obj.sendMessagetoserver(newcleint.getUserName()+" : "+message, chat_id);
        
       }catch(Exception ex){
            ex.printStackTrace();
       }
        
    }
    public void displaymessage(String message,int chat_id){
        for(int i=0;i<chats.size();i++){
            if(chat_id==chats.get(i).getFrameID()){
                chats.get(i).displaymessageontextarea(message);
            }
        }
    }
    public void closechatsession(int chat_id){
        for(int i=0;i<chats.size();i++){
            if(chats.get(i).getFrameID()==chat_id){
                chats.remove(i);
                break;
            }
        }
        try{
        obj.closechatsession(chat_id);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
