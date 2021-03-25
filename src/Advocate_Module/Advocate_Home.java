package Advocate_Module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Advocate_Home {

    static ResultSet rs;

    Advocate_Home(ResultSet rs) {
        Advocate_Home.rs = rs;
    }

    void advocate_home_display() {

        //window
        JFrame f = new JFrame();
        f.setBounds(420, 170, 740, 520);
        f.setLayout(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Welcome text label
        try {

            JPanel intro = new JPanel();
            intro.setBounds(0, 100, 740, 20);
            intro.setBackground(Color.white);
            intro.setLayout(new FlowLayout());
            JLabel advocate_intro = new JLabel("Welcome, " + rs.getString("advocate_name"));
            intro.add(advocate_intro);
            f.add(intro);

        } catch (Exception e) {
            //do nothing
        }


        //buttons
        JButton Profile_button = new JButton("PROFILE");
        Profile_button.setBounds(270, 150, 200, 20);
        f.add(Profile_button);

        JButton Case_button = new JButton("CASES");
        Case_button.setBounds(270, 200, 200, 20);
        f.add(Case_button);

        JButton logout_button = new JButton("LOGOUT");
        logout_button.setBounds(270, 250, 200, 20);
        f.add(logout_button);

        f.setVisible(true);

        Profile_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Advocate_Profile(rs).advocate_profile_display();
            }
        });

        Case_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Advocate_Case_List(rs,4).advocate_case_list_display();
            }
        });

        logout_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Advocate_Login().advocate_login_display();
            }
        });

    }
}
