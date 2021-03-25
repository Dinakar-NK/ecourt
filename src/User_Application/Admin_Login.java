package User_Application;

import Admin_Module.Admin_Home;
import InterFace_Design.Button;
import InterFace_Design.Close_Back;
import InterFace_Design.Introduction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class Admin_Login {

    static String admin_email="lokeshvar@gmail.com";
    static String admin_password="Lokeshvar08";

    //this function structures the login page
    public void login_display(){

        JFrame f=new JFrame();
        f.setBounds(420,170,740,520);
        f.setUndecorated(true);
        f.setLayout(null);
        f.setResizable(false);

        Close_Back close=new Close_Back(1);
        f.add(close);
        close.addMouseListener(new Close_Back(1){
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });



        //Login: Heading
        JLabel login_heading=new JLabel("ADMINISTRATOR LOGIN");
        login_heading.setBounds(180,170,250,20);
        login_heading.setFont(new Font("Arial",1,15));
        f.add(login_heading);

        //Email: Label
        JLabel email_display=new JLabel("Email ");
        email_display.setBounds(120,220,100,20);
        email_display.setFont(new Font("Arial",0,13));
        f.add(email_display);

        //Email: Text Field
        JTextField email_get=new JTextField();
        email_get.setBounds(240,220,200,20);

        f.add(email_get);

        //Password: Label
        JLabel password_display=new JLabel("Password ");
        password_display.setBounds(120,260,100,20);
        password_display.setFont(new Font("Arial",0,13));
        f.add(password_display);

        //Password: Password Field
        JPasswordField password_get=new JPasswordField(60);
        password_get.setBounds(240,260,200,20);
        f.add(password_get);

        //login: Button
        Button login_button=new Button();
        login_button.setBounds(220,310,100,20);
        f.add(login_button);
        login_button.setText("LOGIN");
        login_button.setRounded(true);
        login_button.setBorder(null);
        login_button.setBackground(Color.WHITE);
        login_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( (email_get.getText().equals(admin_email)) && (admin_password.equals(new String(password_get.getPassword())))){
                    //Intent to Admin Home Page
                    f.dispose();
                    new Admin_Home().home_display();
                }
                else {
                    JOptionPane.showMessageDialog(f,"Incorrect Email or Password.","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        ImageIcon img=new ImageIcon("I:\\Bootcamp\\Bootathon\\E-Court\\ProjectFile\\Chennai_High_Court.png");
        JLabel background=new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,740,850);
        f.add(background);


        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }


    public static void main(String[] args) {

        new Introduction().introduction_display();
        new Admin_Login().login_display();
    }
}
