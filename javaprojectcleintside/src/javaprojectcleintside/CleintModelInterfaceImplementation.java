/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectcleintside;
import common.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Alaa
 */
public class CleintModelInterfaceImplementation extends UnicastRemoteObject implements  CleintModelInterface{
    Controller controller;
    public CleintModelInterfaceImplementation(Controller c)throws RemoteException{
        controller=c;
    }
    public String printHellofromcleint(){
        return  "Hello,there is a cleint connect server";
    }

    public void openChatWindow(ArrayList<String> chatMembers, int chat_ID)  {
        controller.openchatwindows_controller(chatMembers, chat_ID);
        
    }

    public void receiveMessageFromServer(String message, int chat_id){
        controller.displaymessage(message, chat_id);
    }

}
