package User_Application;

import Court_Module.Court_Home;
import DatabaseConnector_TableCreator.Connector;
import InterFace_Design.Button;
import InterFace_Design.Close_Back;
import InterFace_Design.Introduction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Court_Login {

    static ResultSet rs;
    static String court_email="";
    static String court_password="";
    static int n;




    public void court_login_display(){

        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




        //Login: Heading
        JLabel login_heading=new JLabel("COURT LOGIN");
        login_heading.setBounds(230+60,170-40,250,20);
        f.add(login_heading);


        JLabel email=new JLabel("E-mail");
        email.setBounds(120+60,220-40,100,20);
        f.add(email);

        JTextField get_mail=new JTextField();
        get_mail.setBounds(240+60,220-40,200,20);
        f.add(get_mail);

        JLabel password=new JLabel("Password ");
        password.setBounds(120+60,260-40,100,20);
        f.add(password);

        JPasswordField get_password=new JPasswordField();
        get_password.setBounds(240+60,260-40,200,20);
        f.add(get_password);

        JButton login=new JButton("LOGIN");
        login.setBounds(220+60,310-40,100,20);
        f.add(login);

        f.setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                court_email=get_mail.getText();
                court_password=new String(get_password.getPassword());
                new Court_Login().court_login_database();
                if(n==1){
                    f.dispose();
                    new Court_Home(rs).court_home_display();
                }
                else{
                    JOptionPane.showMessageDialog(f,"Incorrect Email or Password.","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

    }

    void court_login_database(){
        Connection con=new Connector().establish_connection();
        try{
            String query="SELECT * FROM courts WHERE ( court_email=? AND court_password=?)";
            PreparedStatement smt=con.prepareStatement(query);
            smt.setString(1,court_email);
            smt.setString(2,court_password);
            rs=smt.executeQuery();
            if(rs.next()){
                n=1;
            }
            else{
                n=0;
            }
        }
        catch (Exception e){
            System.out.println("Execution failed: "+e);
        }
    }

    public static void main(String[] args) {

        new Introduction().introduction_display();
        new Court_Login().court_login_display();
    }

}
