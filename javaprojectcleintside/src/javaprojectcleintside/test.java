/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectcleintside;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Alaa
 */
public class test {
    JFrame f=new JFrame();
    public test(){
    f.setSize(200,200);
    Vector contacts=new Vector();
    contacts.add("alaa");
    contacts.add("hussein");
    contacts.add("ali");
    JList names=new JList(contacts);
        
  
   
    names.setCellRenderer(new customlist());
    f.add(names);
    f.setVisible(true);
    f.addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent e) {

            }

            public void windowClosing(WindowEvent e) {
                System.exit(0);

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
    public static void main(String[] args) {
        new test();
    }

}
