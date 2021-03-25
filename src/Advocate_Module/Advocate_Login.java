package Advocate_Module;

import Admin_Module.Admin_Home;
import DatabaseConnector_TableCreator.Connector;
import User_Application.User_Advocate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Advocate_Login {

    static String advocate_email="";
    static String advocate_password="";
    static int n;
    static ResultSet rs;

    public void advocate_login_display(){

        //window
        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Login: Heading
        JLabel login_heading=new JLabel("ADVOCATE LOGIN");
        login_heading.setBounds(300,130,160,20);
        f.add(login_heading);

        //Email: Label
        JLabel email_display=new JLabel("Email ");
        email_display.setBounds(200,170,100,20);
        f.add(email_display);

        //Email: Text Field
        JTextField email_get=new JTextField();
        email_get.setBounds(320,170,200,20);
        f.add(email_get);

        //Password: Label
        JLabel password_display=new JLabel("Password ");
        password_display.setBounds(200,210,100,20);
        f.add(password_display);

        //Password: Password Field
        JPasswordField password_get=new JPasswordField();
        password_get.setBounds(320,210,200,20);
        f.add(password_get);

        //login: Button
        JButton login_button=new JButton("LOGIN");
        login_button.setBounds(300,250,100,20);
        f.add(login_button);

        //back button
        JButton back_button=new JButton("BACK");
        back_button.setBounds(5,5,80,20);
        f.add(back_button);

        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new User_Advocate().User_Advocate_display();
            }
        });

        f.setVisible(true);

        //login button action listener
        login_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                advocate_email=email_get.getText();
                advocate_password=new String(password_get.getPassword());
                new Advocate_Login().advocate_login_database();
                if(n==1){
                    f.dispose();
                    new Advocate_Home(rs).advocate_home_display();
                }
                else {
                    JOptionPane.showMessageDialog(f,"Incorrect Email or Password.","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

    }

    void advocate_login_database(){

        Connection con=new Connector().establish_connection();
        try{
            String query="SELECT * FROM advocates WHERE (advocate_email=? AND advocate_password=?)";
            PreparedStatement smt=con.prepareStatement(query);
            smt.setString(1,advocate_email);
            smt.setString(2,advocate_password);
            rs=smt.executeQuery();
            if(rs.next()){
                n=1;
            }
            else{
                n=0;
            }
        }
        catch (Exception e){
            System.out.println("Unable to retrieve records"+e);
        }

    }

}
