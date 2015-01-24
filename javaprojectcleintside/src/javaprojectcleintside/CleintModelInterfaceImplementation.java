/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectcleintside;
import common.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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

}
