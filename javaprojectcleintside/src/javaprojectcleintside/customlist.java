/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectcleintside;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import common.user;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Alaa
 */
public class customlist extends JPanel implements ListCellRenderer {
    Vector<user> friends_statuses=new Vector<user>();
    public customlist(Vector<user> states){
        friends_statuses=states;
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {


          JPanel p=new JPanel(new BorderLayout());
          JPanel p1=new JPanel(new FlowLayout(5));
       

        try {
            //here you should replace contacts.jpg  with user image
              Image person_Icon = ImageIO.read(getClass().getResource("friend.jpg"));
              JLabel l=new  JLabel(new ImageIcon(person_Icon));
              
              
              p1.add(l);
            JLabel  l2=new  JLabel(value.toString());
              l2.setFont(new Font("Aharoni", Font.BOLD, 15));
              if(isSelected){
                  l2.setForeground(new Color(248,248,248));
              }
              
             //l.setBackground(Color.white);
              p1. add("Center",l2);
              
              p.setBackground(new Color(244,244,244));
              p1.setBackground(new Color(244,244,244));
             int status =friends_statuses.get(index).getStatus();
             if(status==0){
                 Image state_Icon = ImageIO.read(getClass().getResource("on.png"));
                 l=new  JLabel(new ImageIcon(state_Icon));
             }
             else if(status==1){
             Image state_Icon = ImageIO.read(getClass().getResource("off_1.png"));
              l=new  JLabel(new ImageIcon(state_Icon));
             }
             else if(status==2){
             Image state_Icon = ImageIO.read(getClass().getResource("dis.png"));
              l=new  JLabel(new ImageIcon(state_Icon));
             }
             else if(status==3){
             Image state_Icon = ImageIO.read(getClass().getResource("avail .png"));
              l=new  JLabel(new ImageIcon(state_Icon));
             }
             else{
             Image state_Icon = ImageIO.read(getClass().getResource("off_1.png"));
              l=new  JLabel(new ImageIcon(state_Icon));
             }
             


           
            
              
             // p.add(s);
              p.add("West",p1);
              p.add("East",l);
              //p.setBackground(Color.white);
            if (isSelected){
             
            p.setBackground(new Color(0,107,172));
             p1.setBackground(new Color(0,107,172));

            }

               
            
        } catch (Exception ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Server is Off,cannot load user data!");
            
            
        }
           //
         
          
            return p;



    }

}
