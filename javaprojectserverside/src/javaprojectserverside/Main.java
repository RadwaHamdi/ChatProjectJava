/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectserverside;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;

/**
 *
 * @author Alaa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        test();
    
    }
    
    public static void test(){
        try {
            
            JFrame f=new JFrame();
            f.setSize(200,200);
            final JWindow NotificationWindow=new JWindow();
            JPanel message=new JPanel();
            message.setBackground(new Color(188, 255, 184));
            JLabel OnlineFriend=new JLabel("Alaa Hussein"+" is online");
            
            BufferedImage img=ImageIO.read(new File("C:\\Users\\Alaa\\Desktop\\Notify.png"));
            JLabel image=new  JLabel(new ImageIcon(img));
            message.add(image);
            message.add(OnlineFriend);
            NotificationWindow.add(message);
            NotificationWindow.setSize(200, 50);
            f.setLocation(100, 100);
            NotificationWindow.setLocation(f.getX(), f.getY()+f.getHeight());
            NotificationWindow.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    if(e.getClickCount()==1){
                        NotificationWindow.dispose();
                        
                    }
                }
            });
            
            f.setVisible(true);
            NotificationWindow.setVisible(true);
        }catch(Exception e){
        
            e.printStackTrace();
        }
                
    
    }

}
