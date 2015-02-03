/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectcleintside;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Alaa
 */
public class customcombobox extends JLabel implements ListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
     JLabel label=new JLabel();
     label.setBackground(Color.white);
     label.setOpaque(true);
     label.setText(value.toString());
        


     if(index==0){
         try{
         Image online_Icon = ImageIO.read(getClass().getResource("online.jpg"));
         label.setIcon(new ImageIcon(online_Icon));
             }catch(Exception e){

             }
         }
     if(index==1){
        // if(value.equals("Offline")){
         try{
         Image disconnected_Icon = ImageIO.read(getClass().getResource("away.jpg"));
         label.setIcon(new ImageIcon(disconnected_Icon));
             }catch(Exception e){

             }


         }
         if(index==2){
        // if(value.equals("Offline")){
         try{
         Image available_Icon = ImageIO.read(getClass().getResource("available.jpg"));
         label.setIcon(new ImageIcon(available_Icon));
             }catch(Exception e){

             }



         }
      if(index==3){
        // if(value.equals("Offline")){
         try{
         Image away_Icon = ImageIO.read(getClass().getResource("disconnected.jpg"));
         label.setIcon(new ImageIcon(away_Icon));
             }catch(Exception e){

             }



         }




        
      return label;
    }

}
