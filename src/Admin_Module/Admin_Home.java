package Admin_Module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import InterFace_Design.Button;
import InterFace_Design.Close_Back;
import User_Application.Admin_Login;

public class Admin_Home {

    //this function defines the structure of home display
    public void home_display(){

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


        //add Advocate
        Button add_advocate=new Button();
        add_advocate.setBounds(260,110,200,20);
        f.add(add_advocate);
        add_advocate.setText("REGISTER ADVOCATE");
        add_advocate.setRounded(true);
        add_advocate.setBorder(null);
        add_advocate.setBackground(Color.WHITE);
        add_advocate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Admin_Add_Advocate().add_advocate_display();
            }
        });


        //delete Advocate
        Button delete_advocate=new Button();
        delete_advocate.setBounds(260,170,200,20);
        f.add(delete_advocate);
        delete_advocate.setText("REMOVE ADVOCATE");
        delete_advocate.setRounded(true);
        delete_advocate.setBorder(null);
        delete_advocate.setBackground(Color.WHITE);
        delete_advocate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Admin_Delete_Advocate().delete_advocate_display();
            }
        });


        //add court
        Button add_court=new Button();
        add_court.setBounds(260,230,200,20);
        f.add(add_court);
        add_court.setText("REGISTER COURT");
        add_court.setRounded(true);
        add_court.setBorder(null);
        add_court.setBackground(Color.WHITE);
        add_court.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Admin_Add_Court().add_court_display();
            }
        });


        //delete court
        Button delete_court=new Button();
        delete_court.setBounds(260,290,200,20);
        f.add(delete_court);
        delete_court.setText("REMOVE COURT");
        delete_court.setRounded(true);
        delete_court.setBorder(null);
        delete_court.setBackground(Color.WHITE);
        delete_court.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Admin_Delete_Court().delete_court_display();
            }
        });


        //sign out button
        Button sign_out=new Button();
        sign_out.setBounds(260,350,200,20);
        f.add(sign_out);
        sign_out.setText("SIGN OUT");
        sign_out.setRounded(true);
        sign_out.setBorder(null);
        sign_out.setBackground(Color.WHITE);
        sign_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Admin_Login().login_display();
            }
        });

        ImageIcon img=new ImageIcon("I:\\Bootcamp\\Bootathon\\E-Court\\ProjectFile\\Chennai_High_Court.png");
        JLabel background=new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,740,850);
        f.add(background);
        f.getContentPane().setBackground(Color.WHITE);

        f.setVisible(true);

    }


}
