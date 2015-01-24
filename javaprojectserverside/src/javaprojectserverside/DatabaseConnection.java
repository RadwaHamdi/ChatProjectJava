/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectserverside;


import common.user;
import java.io.Serializable;
import java.sql.*;
import javax.swing.*;
import java.util.Date;
import java.util.Vector;


/**
 *
 * @author Alaa
 */
public class DatabaseConnection  {



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        user user=new user();
        user.setEmail("Radwa@gmail.com");
        Vector<user> x=retreiveContactList(user);
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
        

    }
   


    public static Vector<user> retreiveContactList(user myuser){
        Vector contact_list=new Vector();
     try{
         
            //note the data base name should change according to the used database also username and passward
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost/firstdb","root","1234");
          Statement stmt=con.createStatement();
          String querystring=new String("select friend_email from  contacts where user_email='"+myuser.getEmail()+"'");
          ResultSet friends=stmt.executeQuery(querystring);
          ResultSet friend_contact;
          user user;


          while(friends.next()){
              querystring=new String("select * from user where email='"+friends.getString(1)+"'");
              Statement stmt2=con.createStatement();
              friend_contact=stmt2.executeQuery(querystring);
              friend_contact.next();
              user=new user();
              user.setFirstName(friend_contact.getString(1));
              user.setLastName(friend_contact.getString(2));
              user.setUserName(friend_contact.getString(3));
              user.setEmail(friend_contact.getString(4));
              user.setPassward(friend_contact.getString(5));
              user.setBirthDate(friend_contact.getDate(6));
              user.setStatue(friend_contact.getInt(7));
              contact_list.add(user);
             
          }
          con.close();

        }catch(Exception e){
        e.printStackTrace();
        }
        return  contact_list;


    }
}
