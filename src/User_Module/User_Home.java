package User_Module;

import User_Application.User_Advocate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User_Home {

    public void user_home_display(){
        JFrame j=new JFrame();
        JButton b1=new JButton("View Case");
        JButton b2=new JButton("View Lawyer");
        j.add(b1);j.add(b2);

        b1.addActionListener(new ActionListener(){              //View Case Button
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                new User_Case_Login().user_case_login_display();

            }
        });

        b2.addActionListener(new ActionListener(){              //View Lawyer Button
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                new User_Advocate_Sort().user_advocate_sort_display();

            }
        });

        //back button
        JButton back_button=new JButton("BACK");
        back_button.setBounds(5,5,80,20);
        j.add(back_button);

        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                new User_Advocate().User_Advocate_display();
            }
        });

        b1.setBounds(275,180,150,25);                   //Bounds
        b2.setBounds(275,220,150,25);
        j.setLayout(null);
        j.setVisible(true);
        j.setBounds(420,170,740,520);
        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

