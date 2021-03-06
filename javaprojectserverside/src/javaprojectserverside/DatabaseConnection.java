/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprojectserverside;

import common.user;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alaa
 */
public class DatabaseConnection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        insertContact("Maram@gmail.com", "alaarady93@gmail.com");

    }

    public static Vector<user> retreiveContactList(user myuser) {
        Vector contact_list = new Vector();
        try {

            //note the data base name should change according to the used database also username and passward
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/firstdb", "root", "1234");
            Statement stmt = con.createStatement();
            String querystring = new String("select friend_email from  contacts where user_email='" + myuser.getEmail() + "'");
            ResultSet friends = stmt.executeQuery(querystring);
            ResultSet friend_contact;
            user user;

            while (friends.next()) {
                querystring = new String("select * from user where email='" + friends.getString(1) + "'");
                Statement stmt2 = con.createStatement();
                friend_contact = stmt2.executeQuery(querystring);
                friend_contact.next();
                user = new user();
                user.setFirstName(friend_contact.getString(1));
                user.setLastName(friend_contact.getString(2));
                user.setUserName(friend_contact.getString(3));
                user.setEmail(friend_contact.getString(4));
                user.setPassward(friend_contact.getString(5));
                user.setStatue(friend_contact.getInt(6));
                contact_list.add(user);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return contact_list;

    }

    public static int changeuserstatus(String Email, int status) {
        int inserted = 0;
        try {

            //note the data base name should change according to the used database also username and passward
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/firstdb", "root", "1234");
            Statement stmt = con.createStatement();
            String querystring = new String("update user set status=" + status + " where email='" + Email + "'");

            stmt.executeUpdate(querystring);
            con.close();
            inserted = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return inserted;

    }

    public static int InsertInUserTable(String first_name, String last_name, String user_name, String email, String password, int status) {
        int check = -1;
        try {

            String queryString = new String("insert into user values ('" + first_name + "','" + last_name + "','" + user_name + "','" + email + "','" + password + "'," + status + ")");
            System.out.println(queryString);
            System.out.println(queryString);
            //please change this to your database congiguration
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/firstdb", "root", "1234");
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(queryString);
            conn.close();
            check = 0;
            return check;

        } catch (Exception ex) {
            //ex.printStackTrace();
            return check;
        }

    }

    public user checkSignIn(String email, String password) {
        int flag = 1;
        user u = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/firstdb", "root", "1234");
            java.sql.PreparedStatement statement = con.prepareStatement("select email, passward from user where email = ?");
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("email: " + rs.getString("email") + "\t" + "password: " + rs.getString("passward"));
                if (rs.getString("email").equals(email) && rs.getString("passward").equals(password)) {
                    flag = 0;
                    System.out.println("login successfully ");
                    u = new user();
                    Statement stmt = con.createStatement();
                    ResultSet rst = stmt.executeQuery("select * from user where email ='" + rs.getString("email") + "'");
                    rst.next();
                    u.setEmail(email);
                    u.setFirstName(rst.getString("first_name"));
                    u.setLastName(rst.getString("last_name"));
                    u.setUserName(rst.getString("user_name"));
                    break;
                } else {
                    flag = 1;
                    System.out.println("login failed, try again ");
                }
            }
        } catch (SQLException e) {
            flag = 2;
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            flag = 2;
            e.printStackTrace();
        }
        return u;
    }

    public static int insertFriendRequests(String userEmail, String receiverEmail) {
        /**
         *
         * This method may return one of three values 0 => means it succeeded to
         * send request 1=> means that the user entered a wrong email 2=> means
         * that the user has already sent a request to the receiver
         */
        int flag = 0;
        Connection cn = null;
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost/firstdb", "root", "1234");

            ResultSet rs = cn.createStatement().executeQuery("select * from friend_requests where email = '" + receiverEmail + "' and request_sender = '" + userEmail + "'");
            rs.next();
            rs.getString(1);
            rs.getString(2);
            flag = 2;

        } catch (SQLException ex) {

            try {

                ResultSet rs = cn.createStatement().executeQuery("select * from contacts where user_email = '" + receiverEmail + "' and friend_email = '" + userEmail + "'");
                rs.next();
                rs.getString(1);
                rs.getString(2);
                ResultSet rs2 = cn.createStatement().executeQuery("select * from contacts where user_email = '" + userEmail + "' and friend_email = '" + receiverEmail + "'");
                rs2.next();
                rs2.getString(1);
                rs2.getString(2);
                flag = 3;
            } catch (SQLDataException e) {
                try {
                    ResultSet rs = cn.createStatement().executeQuery("select * from user where email = '" + receiverEmail + "'");

                    rs.next();
                    rs.getString(1);
                    rs.getString(2);
                    rs.getString(3);
                    rs.getString(4);

                    PreparedStatement insertStmt = cn.prepareStatement("insert into friend_requests (email, request_sender) values(?,?)");
                    insertStmt.setString(1, receiverEmail);
                    insertStmt.setString(2, userEmail);
                    insertStmt.execute();
                    flag = 0;
                    cn.close();

                } catch (SQLException e1) {

                    flag = 1;
                }
            } catch (SQLException ex1) {
                try {
                    ResultSet rs = cn.createStatement().executeQuery("select * from user where email = '" + receiverEmail + "'");

                    rs.next();
                    rs.getString(1);
                    rs.getString(2);
                    rs.getString(3);
                    rs.getString(4);

                    PreparedStatement insertStmt = cn.prepareStatement("insert into friend_requests (email, request_sender) values(?,?)");
                    insertStmt.setString(1, receiverEmail);
                    insertStmt.setString(2, userEmail);
                    insertStmt.execute();
                    flag = 0;
                    cn.close();

                } catch (SQLException e1) {

                    flag = 1;
                }
            }
        }

        return flag;

    }

    public static int deleteFromContact(String userEmail, String receiverEmail) {
        int flag = 2;
        Connection cn = null;
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost/firstdb", "root", "1234");
            Statement stmt=cn.createStatement();
             String query=new String("delete from contacts where user_email='"+userEmail+"' and friend_email='"+receiverEmail+"'");
             System.out.println(query);
             stmt.executeUpdate(query);
             Statement stmt1=cn.createStatement();
             String query1=new String("delete from contacts  where friend_email='"+userEmail+"' and user_email='"+receiverEmail+"'");
             stmt1.executeUpdate(query1);
             System.out.println(query1);
           /* PreparedStatement deleteStmt = cn.prepareStatement("delete from contacts (user_email, friend_email) values(?,?)");
            deleteStmt.setString(1, userEmail);
            deleteStmt.setString(2, receiverEmail);
            deleteStmt.execute();
            PreparedStatement deleteStmt2 = cn.prepareStatement("delete from contacts (user_email, friend_email) values(?,?)");
            deleteStmt2.setString(1, receiverEmail);
            deleteStmt2.setString(2, userEmail);

            deleteStmt2.execute();
            */
            flag = 0;
            cn.close();
        } catch (SQLException ex) {

            flag = 1;
        }

        return flag;
    }

    public static ArrayList<String> getFriendsOfCleint(user cleint) {
        ArrayList<String> friends = new ArrayList<String>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/firstdb", "root", "1234");
            Statement stmt = con.createStatement();

            String querystring = new String("select user_email from  contacts where friend_email='" + cleint.getEmail() + "'");
            ResultSet friendsEmails = stmt.executeQuery(querystring);
            while (friendsEmails.next()) {
                friends.add(friendsEmails.getString(1));
            }
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return friends;

    }

    public static user getUserDate(String user_email) {
        user Client = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/firstdb", "root", "1234");
            Statement stmt = con.createStatement();
            String querystring = new String("select * from  user where email='" + user_email + "'");
            ResultSet userData = stmt.executeQuery(querystring);
            userData.next();
            Client = new user();
            Client.setEmail(user_email);
            Client.setUserName(userData.getString("user_name"));
            Client.setFirstName(userData.getString("first_name"));
            Client.setLastName(userData.getString("last_name"));
            Client.setStatue(userData.getInt("status"));

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return Client;

    }

    public static Vector<user> getFriendRequestsList(user myuser) {
        Vector friend_list = new Vector();
        try {

            //note the data base name should change according to the used database also username and passward
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/firstdb", "root", "1234");
            Statement stmt = con.createStatement();
            String querystring = new String("select request_sender from  friend_requests where email='" + myuser.getEmail() + "'");

            ResultSet friends = stmt.executeQuery(querystring);
            ResultSet friend_contact;
            user user;

            while (friends.next()) {
                querystring = new String("select * from user where email='" + friends.getString(1) + "'");
                Statement stmt2 = con.createStatement();
                friend_contact = stmt2.executeQuery(querystring);
                friend_contact.next();
                user = new user();
                user.setFirstName(friend_contact.getString(1));
                user.setLastName(friend_contact.getString(2));
                user.setUserName(friend_contact.getString(3));
                user.setEmail(friend_contact.getString(4));
                user.setPassward(friend_contact.getString(5));
                user.setStatue(friend_contact.getInt(6));
                friend_list.add(user);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return friend_list;
    }

    public static int insertContact(String userEmail, String receiverEmail) {
        int flag = 3;
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/firstdb", "root", "1234");
            PreparedStatement insertStmt = cn.prepareStatement("insert into contacts (user_email, friend_email) values(?,?)");
            insertStmt.setString(1, receiverEmail);
            insertStmt.setString(2, userEmail);
            insertStmt.execute();

            insertStmt = cn.prepareStatement("insert into contacts (user_email, friend_email) values(?,?)");
            insertStmt.setString(2, receiverEmail);
            insertStmt.setString(1, userEmail);
            insertStmt.execute();

            /*PreparedStatement deleteStmt;
             deleteStmt = cn.prepareStatement("delete from friend_requests (email, request_sender) values(?,?)");
             deleteStmt.setString(1, userEmail);
             deleteStmt.setString(2, receiverEmail);
             deleteStmt.execute();
             */
            Statement stmt = cn.createStatement();
            String query = "delete from friend_requests where request_sender='" + receiverEmail + "' and email ='" + userEmail + "'";
            stmt.executeUpdate(query);
            flag = 0;
            cn.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class
                    .getName()).log(Level.SEVERE, null, ex);
            flag = 1;
        }
        return flag;
    }

    public static int deleteFriendRequest(String userEmail, String receiverEmail) {
        /**
         * returns => 0 removed him from friend requests successfully returns =>
         * 1 failed //database error
         */
        int flag = 3;
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/firstdb", "root", "1234");
            /*PreparedStatement deleteStmt;
             deleteStmt = cn.prepareStatement("delete from friend_requests (email, request_sender) values(?,?)");
             deleteStmt.setString(1, receiverEmail);
             deleteStmt.setString(2, userEmail);
             deleteStmt.execute();*/
            Statement stmt = cn.createStatement();
            String query = "delete from friend_requests where request_sender='" + receiverEmail + "' and email ='" + userEmail + "'";
            stmt.executeUpdate(query);
            flag = 0;
            cn.close();
        } catch (SQLException ex) {
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            flag = 1;
        }
        return flag;
    }
}
