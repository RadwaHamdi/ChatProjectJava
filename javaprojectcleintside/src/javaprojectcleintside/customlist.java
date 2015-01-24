/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectcleintside;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Alaa
 */
public class customlist extends JPanel implements ListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {


          JPanel p=new JPanel(new BorderLayout());
          JPanel p1=new JPanel(new FlowLayout(5));
       

        try {
            //here you should replace contacts.jpg  with user image
              Image person_Icon = ImageIO.read(getClass().getResource("final_icon.png"));
              JLabel l=new  JLabel(new ImageIcon(person_Icon));
             //l.setBackground(Color.white);

              p1.add(l);
              l=new  JLabel(value.toString());
              l.setFont(new Font("Aharoni", Font.BOLD, 12));
             //l.setBackground(Color.white);
              p1. add("Center",l);


             if(index<=2){
               Image state_Icon = ImageIO.read(getClass().getResource("on.png"));
              l=new  JLabel(new ImageIcon(state_Icon));
            }
 else if (index > 2 && index <= 5) {
               Image state_Icon = ImageIO.read(getClass().getResource("off.png"));
              l=new  JLabel(new ImageIcon(state_Icon));
            }

 else if (index > 5 && index <= 7) {
               Image state_Icon = ImageIO.read(getClass().getResource("dis.png"));
              l=new  JLabel(new ImageIcon(state_Icon));
            }
 else if (index > 7 && index <= 10) {
               Image state_Icon = ImageIO.read(getClass().getResource("avail.png"));
              l=new  JLabel(new ImageIcon(state_Icon));
            }
            else{
             Image state_Icon = ImageIO.read(getClass().getResource("on.png"));
              l=new  JLabel(new ImageIcon(state_Icon));
            }

              
             
            
              
               p.setBackground(Color.white);
             p1.setBackground(Color.white);


           
            
              
             // p.add(s);
              p.add("West",p1);
              p.add("East",l);
              //p.setBackground(Color.white);
            if (isSelected){
            p.setBackground(new Color(0,92,110));
             p1.setBackground(new Color(0,92,110));

            }

               
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
           //
         
          
            return p;



    }

}
