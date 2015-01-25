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
    ServicesInterface  obj;

    public Controller ()throws RemoteException{
        model=new CleintModelInterfaceImplementation(this);
         try {
             
             
             Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5005);
              obj = (ServicesInterface) reg.lookup("chatservice");
              obj.registerCleint(new CleintModelInterfaceImplementation(this));
              obj.printHellofromserver();
              user newcleint=new user();
              newcleint.setEmail("Radwa@gmail.com");
              contactlist=obj.retreiveContactList(newcleint);
              veiw=new MainFrame(this, contactlist);
            //this should be removed this is just for testing logic
             for(int i=0;i<contactlist.size();i++){
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
              
              


        
        } catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "Server is off");
               System.exit(0);
        }
    }
    public static void main(String[] args) {
        
        try{
        Controller c=new Controller();
        
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
