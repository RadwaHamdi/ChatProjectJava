/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprojectserverside;

import common.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import common.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaprojectserverside.DatabaseConnection.InsertInUserTable;
import javax.swing.JOptionPane;

/**
 *
 * @author Alaa
 */
public class ServicesImplementation extends UnicastRemoteObject implements ServicesInterface, Serializable {

    private DatabaseConnection connection;
    Vector<CleintModelInterface> cleints = new Vector<CleintModelInterface>();
    ArrayList<String> online_users = new ArrayList<String>();
    HashMap<String, CleintModelInterface> cleints_with_email = new HashMap<String, CleintModelInterface>();
    ServerController controller;
    //in this constructor we have aconnection so we just use it to connect database

    public ServicesImplementation(ServerController c) throws RemoteException {
        controller=c;
    }

    public String printHellofromserver() {
        return "Hello From server";
    }

    public void registerCleint(CleintModelInterface newcleint, String user_email) {
        if(controller.serverRunning==true){
        boolean check=false;  
        for(int i=0;i<online_users.size();i++){
            if(online_users.get(i).equals(user_email)){
                check=true;
                break;
            }
        }
        if(check==false){
        cleints.add(newcleint);
        online_users.add(user_email);
        cleints_with_email.put(user_email, newcleint);
        System.out.println(online_users+"from register method");
        System.out.println(cleints.size());
        }
        try {
            System.out.println("hello there is anew cleint registerded");
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        else{
            try{
            newcleint.tellCleintServerIsOff();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void unRegisterCleintServerSide(user oldUser,CleintModelInterface userRef){
        
        if(controller.serverRunning==true){
        online_users.remove(oldUser.getEmail());
        cleints.remove(userRef);
        cleints_with_email.remove(oldUser.getEmail());
        System.out.println(online_users+"gfrom inregister method");
        
        }else{
            try{
            userRef.tellCleintServerIsOff();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public Vector<user> retreiveContactList(user u) {
        
        DatabaseConnection con = new DatabaseConnection();
        return con.retreiveContactList(u);
        
    }

    public int setStatusServer(String Email, int status) {
        DatabaseConnection con = new DatabaseConnection();
        return con.changeuserstatus(Email, status);

    }

    public int startNewChatSessionstr(String user_email, ArrayList<String> receiverEmails) throws RemoteException {
        if(controller.serverRunning==true){
        receiverEmails.add(user_email);
        Collections.sort(receiverEmails);
        ArrayList<String> temp = new ArrayList<String>();
        boolean check = false;
        System.out.println(receiverEmails);
        System.out.println(temp);

        //ArrayList<String> sessions_memebers = new ArrayList<String>();
        for (int i = 0; i < receiverEmails.size(); i++) {
            for (int j = 0; j < online_users.size(); j++) {
                if (online_users.get(j).equals(receiverEmails.get(i))) {
                    check = true;
                    //break;
                }
            }
            if (check == false) {
                return -1;
            }
        }
        for (int i = 0; i < ServerController.sessions_ids.size(); i++) {
            temp = ServerController.sessions_ids.get(i);
            System.out.println("temp is:" + temp);
            if (temp.equals(receiverEmails)) {
                return -1;

            }
        }
        for (int i = 0; i < receiverEmails.size(); i++) {
            int j;
            for (j = 0; j < online_users.size(); j++) {
                if (online_users.get(j).equals(receiverEmails.get(i))) {
                    System.out.println("openchatwindow");
                    cleints_with_email.get(receiverEmails.get(i)).openChatWindow(receiverEmails, ServerController.counter);
                    System.out.println(receiverEmails.get(i));
                }
            }
        }

        ServerController.sessions_ids.put(ServerController.counter, receiverEmails);
        System.out.println(ServerController.sessions_ids.toString());
        System.out.println("counter is:" + ServerController.counter);
        return ServerController.counter++;
        }
        else{
        return -1;
        }
    }

    public void sendMessagetoserver(String message, int chat_id) throws RemoteException {
        if(controller.serverRunning==true){
        ArrayList<String> message_receivers = ServerController.sessions_ids.get(chat_id);
        for (int i = 0; i < message_receivers.size(); i++) {
            CleintModelInterface receiver = cleints_with_email.get(message_receivers.get(i));
            receiver.receiveMessageFromServer(message, chat_id);
        }
        }
    }

    public void closechatsession(int chat_id) {
        ServerController.sessions_ids.replace(chat_id, new ArrayList<String>());
    }

    public int insertUserData(user u) {
        if(controller.serverRunning==true){
        String first_name = u.getFirstName();
        String last_name = u.getLastName();
        String user_name = u.getUserName();
        String email = u.getEmail();
        String password = u.getPassward();
        int status = u.getStatus();
        int check=InsertInUserTable(first_name, last_name, user_name, email, password, status);
        return check;
        }
        else{
        return -1;
        }
        
        
    }

    public user signInServerSide(String email, String password) throws RemoteException {
        if(controller.serverRunning==true){
        String userEmail = email;
        String userPassword = password;
        user u = new DatabaseConnection().checkSignIn(email, password);
        return u; 
    }
        else return new user();
    }
    public  Vector<CleintModelInterface> getOnlineCleints(){
        return cleints;
    }

    public boolean checkServerState() throws RemoteException {
       return controller.serverRunning;
    }
    }


