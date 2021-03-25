package User_Application;

import Advocate_Module.Advocate_Login;
import InterFace_Design.Introduction;
import User_Module.User_Home;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class User_Advocate {

    public void intro(){
        new Introduction().introduction_display();
    }
    public  void User_Advocate_display(){



        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JButton advocate=new JButton("Enter as Advocate");
        advocate.setBounds(260,200,200,20);
        f.add(advocate);

        JButton user=new JButton("Enter as User");
        user.setBounds(260,240,200,20);
        f.add(user);



        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new User_Home().user_home_display();
            }
        });

        advocate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Advocate_Login().advocate_login_display();
            }
        });


        f.setVisible(true);
    }

    public static void main(String[] args) {
        new User_Advocate().intro();
        new User_Advocate().User_Advocate_display();
    }
}
