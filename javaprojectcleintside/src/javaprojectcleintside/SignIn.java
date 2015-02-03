/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprojectcleintside;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ewess
 */
public class SignIn extends javax.swing.JFrame {

    /**
     * Creates new form SignIn
     */
    SignUp signUpView;

Controller controller ; 

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(JTextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public JPasswordField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(JPasswordField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }
    public SignIn(Controller c) {
        initComponents();
        
        this.controller = c; 
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        emailTextField = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JPasswordField();
        signUpButton = new javax.swing.JButton();
        singInButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connect");
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(290, 720));
        setMinimumSize(new java.awt.Dimension(290, 720));
        setPreferredSize(new java.awt.Dimension(290, 720));

        mainPanel.setMaximumSize(new java.awt.Dimension(290, 720));
        mainPanel.setMinimumSize(new java.awt.Dimension(257, 567));
        mainPanel.setPreferredSize(new java.awt.Dimension(290, 720));
        mainPanel.setLayout(null);

        emailTextField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emailTextField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        emailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextFieldActionPerformed(evt);
            }
        });
        mainPanel.add(emailTextField);
        emailTextField.setBounds(40, 210, 200, 30);

        passwordTextField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mainPanel.add(passwordTextField);
        passwordTextField.setBounds(40, 270, 200, 30);

        signUpButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        signUpButton.setForeground(new java.awt.Color(51, 51, 255));
        signUpButton.setText("Sign Up");
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });
        mainPanel.add(signUpButton);
        signUpButton.setBounds(80, 340, 110, 23);

        singInButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        singInButton.setForeground(new java.awt.Color(51, 51, 255));
        singInButton.setText("Sign in");
        singInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singInButtonActionPerformed(evt);
            }
        });
        mainPanel.add(singInButton);
        singInButton.setBounds(80, 310, 110, 23);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 0));
        jLabel2.setText("email");
        mainPanel.add(jLabel2);
        jLabel2.setBounds(40, 190, 35, 17);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 0));
        jLabel3.setText("password");
        mainPanel.add(jLabel3);
        jLabel3.setBounds(40, 250, 67, 17);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/back.png"))); // NOI18N
        jLabel1.setText("Email");
        mainPanel.add(jLabel1);
        jLabel1.setBounds(0, -40, 290, 760);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextFieldActionPerformed

    private void singInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singInButtonActionPerformed
        // TODO add your handling code here:
       // new MainApp().setVisible(true);
        controller.signInClientSide();
    }//GEN-LAST:event_singInButtonActionPerformed

    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
        signUpView = new SignUp(controller);
        
    }//GEN-LAST:event_signUpButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                

}
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SignIn.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 

catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignIn.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 

catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignIn.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 

catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignIn.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            //    new SignIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JButton signUpButton;
    private javax.swing.JButton singInButton;
    // End of variables declaration//GEN-END:variables

public void dialog(String dlabel){
            final JDialog dialog = new JDialog();
            dialog.setTitle("Warning");
            JLabel label = new JLabel(dlabel,JLabel.CENTER);
            dialog.add(label, BorderLayout.CENTER);
            label.setSize(300, 100);
            label.setVerticalTextPosition(label.TOP);
            dialog.setLayout(null);
            dialog.setPreferredSize(new Dimension(300, 150));
            dialog.pack();
            dialog.setVisible(true);
            dialog.addWindowListener(new WindowAdapter() {

                public void WindowClosing(WindowEvent e) {
                    dialog.dispose();
                }
            });
            JButton button = new JButton("ok");
            dialog.add(button);
            button.setSize(50, 40);
            button.setLocation(120, 70);
            button.setVisible(true);
            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });
         
     }

}