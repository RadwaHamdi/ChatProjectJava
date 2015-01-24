package javaprojectcleintside;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ComboBoxItemIcon {
    String state="away.jpg";

    public static void main(String[] args) {
        new ComboBoxItemIcon  ();
    }

    public ComboBoxItemIcon () {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                final JComboBox cb = new JComboBox();
                cb.setRenderer(new customcombobox());
                cb.addItem("Online");
                 cb.addItem("Dissconected");
                 cb.addItem("Available");
                cb.addItem("Away");
               
                cb.setEditable(true);
                 MyComboBoxEditor x=new MyComboBoxEditor();
                                 cb.setEditor(x);
                

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new GridBagLayout());
                frame.add(cb);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                cb.addItemListener(new ItemListener() {

                    public void itemStateChanged(ItemEvent e) {
                        System.out.println(cb.getSelectedItem().toString());
                        if(cb.getSelectedItem().toString().equals("Online"))
                        {
                                state="Online.jpg";
                                 MyComboBoxEditor x=new MyComboBoxEditor();
                                 cb.setEditor(x);
                        }
                    }
                });

            }
        });
    }

    public class MyComboBoxEditor implements ComboBoxEditor {

        private EditorPane editorPane;


        public MyComboBoxEditor() {
            editorPane = new EditorPane();
            editorPane.setBackground(Color.white);

        }

        @Override
        public Component getEditorComponent() {
            return editorPane;
        }

        @Override
        public void setItem(Object anObject) {
            editorPane.setText(anObject == null ? null : anObject.toString());
        }

        @Override
        public Object getItem() {
            return editorPane.getText();
        }

        @Override
        public void selectAll() {
            editorPane.selectAll();
        }

        @Override
        public void addActionListener(ActionListener l) {
            editorPane.addActionListener(l);
            
           
        }

        @Override
        public void removeActionListener(ActionListener l) {
            editorPane.removeActionListener(l);
        }

    }

    public class EditorPane extends JPanel {

        private JLabel field;
        private JButton button;

        public EditorPane() {
            field=new JLabel();
            field.setBackground(Color.white);
            
            try{
            Image state_Icon = ImageIO.read(getClass().getResource(state));
            button = new JButton(new ImageIcon(state_Icon));
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setBackground(Color.white);
            }catch(Exception e){
            e.printStackTrace();
            }

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
           gbc.gridx = 0;
            add(field, gbc);
            
            gbc.weightx = 0;
           gbc.fill = GridBagConstraints.NONE;
            gbc.gridx++;
             add(button, gbc);
           

        }

        @Override
        public void addNotify() {
            super.addNotify();
            field.requestFocusInWindow();
        }

        public void selectAll() {
           
        }

        public void setText(String text) {
            field.setText(text);
        }

        public String getText() {
            return field.getText();
        }

        public void addActionListener(ActionListener listener) {
           
            
        }

        public void removeActionListener(ActionListener listener) {
           
        }

    }

}