/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprojectcleintside;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ewess
 */
public class FontFormat extends javax.swing.JDialog {

    public FontFormat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationByPlatform(true);
        final String[] fontsArray = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        
        DefaultComboBoxModel<String> fontsListModel = new DefaultComboBoxModel<String>();
        for (int i = 0; i < fontsArray.length; i++) {
            fontsListModel.addElement(fontsArray[i]);
        }

        fontsCombobox.setModel(fontsListModel);
        
        fontsCombobox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sampleLabel.setFont(new Font(fontsCombobox.getSelectedItem().toString(),Font.PLAIN,sizeSlider.getValue()));
            }
        });

        /// combobox 
        DefaultComboBoxModel<String> comboboxModel = new DefaultComboBoxModel<String>();
        comboboxModel.addElement("Red");
        comboboxModel.addElement("Green");
        comboboxModel.addElement("Blue");

        class MyRender implements ListCellRenderer<String> {

            @Override
            public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel customView = new JLabel();
                customView.setText(value);
                customView.setOpaque(true);
                if (isSelected) {
                    switch (index) {
                        case 0:
                            customView.setBackground(Color.red);
                            break;
                        case 1:
                            customView.setBackground(Color.green);
                            break;
                        case 2:
                            customView.setBackground(Color.blue);
                            break;
                    }
                }
                return customView;
            }
        }
        MyRender render = new MyRender();
        colorCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("I'm in Combobox ActionPerformed ...");
                switch (colorCombobox.getSelectedIndex()) {
                    case 0:
                        sampleLabel.setForeground(Color.red);
                        break;
                    case 1:
                        sampleLabel.setForeground(Color.green);
                        break;
                    case 2:
                        sampleLabel.setForeground(Color.blue);
                        break;
                    default:
                        sampleLabel.setForeground(Color.black);
                        break;
                }
                sampleLabel.setFont(new Font(fontsCombobox.getSelectedItem().toString(), Font.PLAIN,sizeSlider.getValue()));
            }
        });
        colorCombobox.setModel(comboboxModel);
        colorCombobox.setRenderer(render);

        /////////////////////////// size  ////////////////////////
   
        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                sampleLabel.setFont(new Font(fontsCombobox.getSelectedItem().toString(), Font.PLAIN, sizeSlider.getValue()));
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        sampleLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        applyButton = new javax.swing.JButton();
        sizeSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        fontsCombobox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        colorCombobox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 320));
        setModal(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        sampleLabel.setText("Sample");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(sampleLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sampleLabel)
                .addGap(97, 97, 97))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 210, 380, 63);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(187, 326, 70, 0);

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });
        getContentPane().add(applyButton);
        applyButton.setBounds(320, 280, 70, 23);

        sizeSlider.setBackground(new java.awt.Color(102, 102, 102));
        sizeSlider.setForeground(new java.awt.Color(255, 255, 51));
        sizeSlider.setMajorTickSpacing(5);
        sizeSlider.setMaximum(60);
        sizeSlider.setPaintLabels(true);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setToolTipText("");
        sizeSlider.setValue(11);
        getContentPane().add(sizeSlider);
        sizeSlider.setBounds(10, 150, 380, 45);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Font type");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 70, 80, 17);

        fontsCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(fontsCombobox);
        fontsCombobox.setBounds(10, 90, 160, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Font size");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 130, 70, 17);

        colorCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        colorCombobox.setSelectedIndex(-1);
        colorCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorComboboxActionPerformed(evt);
            }
        });
        getContentPane().add(colorCombobox);
        colorCombobox.setBounds(220, 90, 170, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Font color");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 70, 120, 17);

        jLabel3.setFont(new java.awt.Font("Traditional Arabic", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Font Format");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(80, 10, 250, 40);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButton);
        cancelButton.setBounds(245, 280, 70, 23);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 400, 320);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        // TODO add your handling code here:
   
        this.dispose();

    }//GEN-LAST:event_applyButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
   
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void colorComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_colorComboboxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FontFormat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FontFormat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FontFormat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FontFormat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FontFormat dialog = new FontFormat(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox colorCombobox;
    private javax.swing.JComboBox fontsCombobox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel sampleLabel;
    private javax.swing.JSlider sizeSlider;
    // End of variables declaration//GEN-END:variables
}
