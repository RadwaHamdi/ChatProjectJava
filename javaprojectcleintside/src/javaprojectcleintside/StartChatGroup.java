/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaprojectcleintside;

import javax.swing.JFrame;

/**
 *
 * @author ewess
 */
public class StartChatGroup extends javax.swing.JFrame {

    /**
     * Creates new form StartChatGroup
     */
    public StartChatGroup() {
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        addMemberTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        newGroupMembersTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        addNewMemberButton = new javax.swing.JButton();
        cancelNewMemberButton = new javax.swing.JButton();
        removeNewMemberButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New Chat Group");
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(600, 600));
        setMinimumSize(new java.awt.Dimension(490, 350));
        setName("Start New Group Chat"); // NOI18N
        setPreferredSize(new java.awt.Dimension(490, 350));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel2.setText("Start New Group ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(130, 10, 209, 30);

        jLabel1.setText("add member");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 70, 59, 14);
        getContentPane().add(addMemberTextField);
        addMemberTextField.setBounds(150, 60, 300, 28);

        newGroupMembersTextArea.setColumns(20);
        newGroupMembersTextArea.setRows(5);
        jScrollPane1.setViewportView(newGroupMembersTextArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(150, 100, 300, 175);

        jLabel3.setText("group members");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 100, 74, 14);

        addNewMemberButton.setText("Add");
        getContentPane().add(addNewMemberButton);
        addNewMemberButton.setBounds(20, 150, 90, 23);

        cancelNewMemberButton.setText("cancel");
        getContentPane().add(cancelNewMemberButton);
        cancelNewMemberButton.setBounds(20, 210, 90, 23);

        removeNewMemberButton.setText("remove");
        getContentPane().add(removeNewMemberButton);
        removeNewMemberButton.setBounds(20, 180, 90, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(StartChatGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartChatGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartChatGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartChatGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartChatGroup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addMemberTextField;
    private javax.swing.JButton addNewMemberButton;
    private javax.swing.JButton cancelNewMemberButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea newGroupMembersTextArea;
    private javax.swing.JButton removeNewMemberButton;
    // End of variables declaration//GEN-END:variables
}