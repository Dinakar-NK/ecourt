package Court_Module;

import InterFace_Design.*;
import InterFace_Design.Button;
import User_Application.Court_Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class Court_Home {
    static ResultSet rs;

    public Court_Home(ResultSet rs){
        Court_Home.rs=rs;
    }

    public void court_home_display(){

        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Welcome text label
        try{

            JPanel intro=new JPanel();
            intro.setBounds(0,60,740,40);
            intro.setBackground(Color.white);
            intro.setLayout(new FlowLayout());
            JLabel court_intro=new JLabel(rs.getString("court_location")+" Court Management");
            court_intro.setFont(new Font("Arial",Font.BOLD,20));
            intro.add(court_intro);
            f.add(intro);

        }
        catch (Exception e){
            //do nothing
        }


        JButton addcase=new JButton("FILE CASE");
        addcase.setBounds(280,100+30,180,20);
        f.add(addcase);


        JButton updatecase =new JButton("UPDATE CASE");
        updatecase.setBounds(280,150+30,180,20);
        f.add(updatecase);

        JButton viewcase =new JButton("VIEW CASE");
        viewcase.setBounds(280,250+30,180,20);
        f.add(viewcase);

        JButton oppupdate =new JButton("OPPOSITION UPDATE");
        oppupdate.setBounds(280,200+30,180,20);
        f.add(oppupdate);

        JButton signout =new JButton("SIGN OUT");
        signout.setBounds(280,300+30,180,20);
        f.add(signout);

        f.setVisible(true);

        signout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Court_Login().court_login_display();
            }
        });

        addcase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Court_File_Case(rs).court_file_case_display();
            }
        });

        updatecase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Court_Get_CNR(rs,1).court_get_cnr_display();
            }
        });

        oppupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Court_Get_CNR(rs,2).court_get_cnr_display();
            }
        });

        viewcase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Court_Get_CNR(rs,3).court_get_cnr_display();
            }
        });
    }

}
