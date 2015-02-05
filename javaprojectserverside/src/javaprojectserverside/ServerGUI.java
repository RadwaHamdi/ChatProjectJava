package javaprojectserverside;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javax.swing.*;

public class ServerGUI extends JFrame{

    static PieChart pie;
    static ObservableList<PieChart.Data> pieChartData;

    public static Container initialize() {

        final JFXPanel fxPanel = new JFXPanel();
        JPanel jp = new JPanel();
        jp.add(fxPanel);
        jp.setVisible(true);
        // Really shouldn't do this, so commented it out
        jp.setPreferredSize(new Dimension(250, 250));
        //jp.setBackground(Color.);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
        });

        return jp;
    }

    private static void initFX(JFXPanel fxPanel) {
        Scene scene = initScene();
        fxPanel.setScene(scene);
    }

    private static Scene initScene() {
        Scene scene = null;

        Group root = new Group();
        scene = new Scene(root, javafx.scene.paint.Color.WHITE);
        Text text = new Text();
        text.setX(120);
        text.setY(25);
        text.setFont(new Font(25));
        text.setText("Connect App Server");

        pie = new PieChart();
        pie.setLabelLineLength(10);
        pie.setLegendSide(Side.LEFT);
        updateChart();
        root.getChildren().addAll(text, pie);
        return (scene);
    }

    public static void updateChart() {

        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("online users", getOnlineUsersCount()), new PieChart.Data("offline users", getOfflineUsersCount()));

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                pie.setData(pieChartData);
            }
        });

    }

    public static int getOnlineUsersCount() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/firstdb", "root", "1234");
            Statement stmt = con.createStatement();
            String querystring = new String("select  count(status) as onlines from  user where status < 4");
            ResultSet rst = stmt.executeQuery(querystring);
            rst.next();
            rst.getInt("onlines");
            System.out.println("onlines " + rst.getInt("onlines"));
            return rst.getInt("onlines");
        } catch (SQLException ex) {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public static int getOfflineUsersCount() {
        try {
            System.out.println("in getOfflineUsersCount");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/firstdb", "root", "1234");
            Statement stmt = con.createStatement();
            String querystring = "select count(status) as offlin from  user where status > 4";
            ResultSet rst = stmt.executeQuery(querystring);
            rst.next();

            rst.getInt("offlin");
            System.out.println("offlines " + rst.getInt("offlin"));
            return rst.getInt("offlin");
        } catch (SQLException ex) {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

//    public static void main(String[] args) {
//        new ServerGUI();
//    }

    ServerController controller ; 
    public ServerGUI(ServerController c) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();

        JButton startServiceButton = new JButton("start service");
        JButton stopServiceButton = new JButton("stop service");
        JButton updateStatisticsButton = new JButton("update statistics ");
        //buttonsPanel.add(startServiceButton);
        //buttonsPanel.add(stopServiceButton);
        buttonsPanel.add(updateStatisticsButton);

        updateStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateChart();
            }
        });

        startServiceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startServer();
            }
        });

        stopServiceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stopServer();
            }
        });

//        JLabel label = new JLabel(new ImageIcon("C:\\Users\\ewess\\Documents\\NetBeansProjects\\ServerStatistics\\src\\serverstatistics\\bkg.png"));
//        label.setPreferredSize(new Dimension(500, 500));
//        frame.add(label, "Center");
        
        
        frame.setPreferredSize(new Dimension(500, 500));
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.setBackground(java.awt.Color.BLACK);
        frame.setVisible(true);
        frame.add(ServerGUI.initialize(), BorderLayout.CENTER);
        //frame.add(buttonsPanel, "South");
        frame.pack();
    }
}
