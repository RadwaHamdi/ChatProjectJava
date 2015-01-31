/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;


import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Alaa
 */
public class user implements Serializable{
     private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String passward;
    private Date birthdate;
    private int status;

    //here there must be another attribute for user image;
    public void setFirstName(String firstname){
        this.firstname=firstname;
    }
    public void setLastName(String L){
        this.lastname=L;
    }
    public void setUserName(String username){
        this.username=username;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public  void setPassward(String passward){
        this.passward=passward;
    }
    public  void setBirthDate(Date birthdate){
        this.birthdate=birthdate;
    }
    public  void setStatue(int status){
        this.status=status;
    }
    public String getFirstName(){
        return firstname;
    }
    public String getLastName(){
        return lastname;
    }
    public String getUserName(){
        return username;
    }
    public String getEmail(){
        return email;
    }
    public String getPassward(){
        return passward;
    }
    public Date getBirthDate(){
        return birthdate;
    }
    public int getStatus(){
        return status;
    }
}

