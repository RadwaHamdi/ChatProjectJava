/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectcleintside;
import common.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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

    public void tellCleintServerIsOff() {
        controller.TellCleint_ServerIsOff();
        
        
    }
    public  void tellCleintServerIsOn() {
    controller.TellCleint_ServerIson();
    }

    public void setServerState() throws RemoteException {
        controller.serverstate=true;
    }
     public void receiveAdvertise(byte[] b){
         controller.displayadvertisement(b);
     }
     
       @Override
    public int downloadFileClientSide(File file, byte[] b, int frameId) throws RemoteException {
        int receiveFileFlag=0;
           System.out.println("frame_Id is :"+frameId);
        for(int i=0;i<controller.chats.size();i++){
            if(controller.chats.get(i).getFrameID()==frameId){
                 receiveFileFlag = JOptionPane.showConfirmDialog(controller.chats.get(i), "do you want to accept the file transfer ? ", "Sharing files ", JOptionPane.YES_NO_OPTION);
                System.out.println("chat_id is :"+controller.chats.get(i).getFrameID());
                 break;

        }
        }

        if (receiveFileFlag == JOptionPane.YES_OPTION) {
            try {
                System.out.println("in download file");
                File dir = new File("..\\connect App1");
                dir.mkdir();

                FileOutputStream fos = new FileOutputStream(dir + "\\" + file.getName());
                fos.write(b);
                fos.close();
                System.out.println("downloaded");
                return 0;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CleintModelInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
                return 2  ;
            } catch (IOException ex) {

                Logger.getLogger(CleintModelInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
                return 2; //try again 

            }
        } else {
            return 1; // transer denied 
        }

    }

    @Override
    public void handleDownloadFileClientSide(int flag) throws RemoteException {
        switch(flag){
            case 0 : 
                JOptionPane.showMessageDialog(controller.chats.get(flag), "the file sent successfully ");
                break; 
            case 1:
                JOptionPane.showMessageDialog(controller.chats.get(flag), "your friend doesn't accept your file transferring ");
                break; 
            case 2:
                JOptionPane.showMessageDialog(controller.chats.get(flag), "sending failed, try again ... ");
                break; 
        }
    }
    
}
