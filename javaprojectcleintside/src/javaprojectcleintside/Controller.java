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
import org.omg.CORBA.MARSHAL;

/**
 *
 * @author Alaa
 */
public class Controller implements Serializable {
    CleintModelInterface model;
    CleintVeiwInterface veiw;
    ServicesInterface obj;

    public Controller (CleintVeiwInterface v)throws RemoteException{
        this.veiw=v;
        //here call veiw.setcontroller(this) or in main
        //call 
        model=new CleintModelInterfaceImplementation(this);
         try {
            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5005);
            obj = (ServicesInterface) reg.lookup("chatservice");

             
             obj.registerCleint(new CleintModelInterfaceImplementation(this));
             obj.printHellofromserver();
             user newcleint=new user();
             newcleint.setEmail("Radwa@gmail.com");
             
           
            Vector<user> x=obj.retreiveContactList(newcleint);
            
             for(int i=0;i<x.size();i++){
            System.out.print(x.get(i).getFirstName()+":");
            System.out.print(x.get(i).getLastName()+":");
            System.out.print(x.get(i).getUserName()+":");
            System.out.print(x.get(i).getEmail()+":");
            System.out.print(x.get(i).getBirthDate()+":");
            System.out.print(x.get(i).getStatus()+":");
            System.out.print(x.get(i).getPassward()+":");
            System.out.println("");
             }









        } catch (Exception ex) {
             ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        CleintVeiwInterface v=new MainFrame();
        try{
        new Controller(v);


        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
