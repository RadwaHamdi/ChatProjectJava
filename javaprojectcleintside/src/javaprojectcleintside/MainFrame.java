/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on Jan 18, 2015, 1:39:28 PM
 */
package javaprojectcleintside;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.PopupMenu;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.EventObject;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.MenuEvent;
import javax.swing.plaf.ToolBarUI;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuListener;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import common.CleintVeiwInterface;
import common.user;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.event.MouseInputListener;
/**
 *
 * @author Alaa
 */
public class MainFrame extends javax.swing.JFrame implements CleintVeiwInterface{
    Controller controller;
    Vector<user> contactlist;
    user cleint;
    public MainFrame(Controller c,Vector<user> contact_list,user current_user)  {
        
        try{
            
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        SwingUtilities.updateComponentTreeUI(this);
        }catch(Exception e){
                e.printStackTrace();
        }
        initComponents();
        controller=c;
        contactlist=contact_list;
        cleint=current_user;
        setLayout(new BorderLayout());
        setSize(300, 600);
        setResizable(false);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        //here remeber to set minimum size
        try {
          
            JPanel centerpanel = new JPanel();
            JPanel north_panel=new JPanel();
            JPanel combobox_panel=new JPanel(new BorderLayout());
            north_panel.setLayout(new BoxLayout(north_panel,BoxLayout.Y_AXIS));

           
            

            JToolBar toolBar = new JToolBar();
            toolBar.setBackground(new Color(0,107,172));
            JPanel toolbar_panel=new JPanel(new BorderLayout());
            Image add_user_Icon = ImageIO.read(getClass().getResource("add.png"));
            Image contacts_Icon = ImageIO.read(getClass().getResource("images.jpg"));
            Image chat_Icon = ImageIO.read(getClass().getResource("chat.jpg"));
            Image remove_user_Icon = ImageIO.read(getClass().getResource("remove contact.jpg"));
            Image sign_out_Icon = ImageIO.read(getClass().getResource("sign out.jpg"));
            Image search_user_Icon = ImageIO.read(getClass().getResource("search.png"));
            


            JButton add_contact_Button = new JButton(new ImageIcon(add_user_Icon));
            JButton contacts_Button = new JButton(new ImageIcon(contacts_Icon));
            JButton chat_Button = new JButton(new ImageIcon(chat_Icon));
            JButton remove_user_Button = new JButton(new ImageIcon(remove_user_Icon));
            JButton sign_out_Button = new JButton(new ImageIcon(sign_out_Icon));
            JButton search_user_Button = new JButton(new ImageIcon(search_user_Icon));

             add_contact_Button.setBorder(emptyBorder);
             contacts_Button.setBorder(emptyBorder);
             chat_Button.setBorder(emptyBorder);
             remove_user_Button.setBorder(emptyBorder);
             sign_out_Button.setBorder(emptyBorder);
             search_user_Button.setBorder(emptyBorder);


            
            //setBackground(new Color(97,121,172));
            toolBar.add(add_contact_Button);
            //toolBar.add(contacts_Button);
            toolBar.add(chat_Button);
            toolBar.add(contacts_Button); 
            toolBar.add(remove_user_Button);                
            toolBar.add(search_user_Button);
            toolBar.add(sign_out_Button);
            
            toolbar_panel.add("North",toolBar);
            north_panel.add(toolbar_panel);
            
            final JComboBox states_combobox=new JComboBox();
            states_combobox.addItem("Online");
            states_combobox.addItem("Dissconected");
            states_combobox.addItem("Available");
            states_combobox.addItem("Away"); 
            
            states_combobox.setRenderer(new customcombobox());
           

            
                JLabel user_name=new JLabel(cleint.getUserName()+"   ");
                user_name.setFont(new Font("Aharoni", Font.BOLD, 18));
              
                combobox_panel.add( "Center",user_name);
                combobox_panel.add("East",states_combobox);
                
                JPanel name=new JPanel();
                
                name.setLayout(new FlowLayout(FlowLayout.LEFT));
                Image my_pic= ImageIO.read(getClass().getResource("friend.jpg"));
                JButton mypic=new JButton(new ImageIcon(my_pic));
                
                mypic.setBorder(emptyBorder);
                combobox_panel.setBackground(new Color(0,107,172));
                name.add(mypic);             
                name.add(combobox_panel);
                
                north_panel.add(name);
           
                
                



            Vector contacts=new Vector();
            for(int i=0;i<contactlist.size();i++){
                contacts.add(contactlist.get(i).getUserName());
            }
            
            

            JList names=new JList(contacts);       
            names.setCellRenderer(new customlist(contactlist));
            JScrollPane contact_list_scrollpane=new JScrollPane(names);
            contact_list_scrollpane.setSize(300, 100);
            name.setBackground(new Color(0,107,172));
            names.setBackground(new Color(244,244,244));
            names.setBorder(BorderFactory.createTitledBorder("Contacts"));

           
             add("North",north_panel);            
             add("Center",contact_list_scrollpane);
             JPanel west_panel=new JPanel();
             west_panel.setBackground(new Color(0,107,172));
             add("West",west_panel);
             JPanel east_panel=new JPanel();
             east_panel.setBackground(new Color(0,107,172));
             add("East",east_panel);
             JPanel south_panel=new JPanel();
             south_panel.setBackground(new Color(0,107,172));//radwa
             south_panel.add(new JLabel("here there will new advertisment"));
             add("South",south_panel);

             names.addMouseListener(new MouseInputListener() {

                public void mouseClicked(MouseEvent e) {
                }

                public void mousePressed(MouseEvent e) {
                  
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }

                public void mouseDragged(MouseEvent e) {
                }

                public void mouseMoved(MouseEvent e) {
                }
            });

              add_contact_Button.addActionListener(new ActionListener() {//remove
                public void actionPerformed(ActionEvent e) {
                    addFriend newfreind=new addFriend();
                }
            });

            contacts_Button.addActionListener(new ActionListener() {//remove
                public void actionPerformed(ActionEvent e) {
                    StartChatGroup newgroup=new StartChatGroup();
                }
            });

             chat_Button.addActionListener(new ActionListener() {//remove
                public void actionPerformed(ActionEvent e) {
                    StartChat newchat=new StartChat(cleint,controller);
                    newchat.setVisible(true);
                    newchat.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                   //Chat newchat=new Chat();
                }
            });



             remove_user_Button.addActionListener(new ActionListener() {//remove
                public void actionPerformed(ActionEvent e) {
                   RemoveFriend freind=new RemoveFriend();
                }
            });


            sign_out_Button.addActionListener(new ActionListener() {//remove
                public void actionPerformed(ActionEvent e) {
                   controller.signOutCleintSide();
                }
            });
            search_user_Button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    System.out.println("search users");
                }
            });
            
            states_combobox.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    
                   String new_status=states_combobox.getSelectedItem().toString();
                   if(new_status.equals("Online")){
                       setstatus(cleint.getEmail(), 0);
                       System.out.println(cleint.getEmail()+"0");
                   }
                   else if(new_status.equals("Dissconected")){
                       setstatus(cleint.getEmail(), 1);
                       System.out.println(cleint.getEmail()+"1");
                   }
                   else if(new_status.equals("Away")){
                       setstatus(cleint.getEmail(), 2);
                       System.out.println(cleint.getEmail()+"2");
                   }
                   else if(new_status.equals("Available")){
                       setstatus(cleint.getEmail(), 3);
                       System.out.println(cleint.getEmail()+"3");
                   }
                   
                   
                }
            });
           



        } catch (Exception e) {
            e.printStackTrace();
        }


        setVisible(true);
        addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
                controller.unRegisterCleint();
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }
        });
        

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar2 = new javax.swing.JToolBar();
        jMenuBar1 = new javax.swing.JMenuBar();

        jToolBar2.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {


            public void run() {
               // new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables
    public void setstatus(String user_email,int status){
        controller.setStatus(user_email, status);
    }
    public void TellServerIsOff(){
    JOptionPane.showMessageDialog(null, "Server is Off");
    }
    
   
}
