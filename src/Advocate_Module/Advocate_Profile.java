package Advocate_Module;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Advocate_Profile {

    static ResultSet rs;
    Advocate_Profile(ResultSet rs){
        Advocate_Profile.rs=rs;
    }

    //displays the complete profile of Advocate
    void advocate_profile_display(){

        //window
        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        //label
        JLabel advocate_profile=new JLabel("ADVOCATE PROFILE");
        advocate_profile.setBounds(300,40,150,20);
        f.add(advocate_profile);

        JLabel advocate_id=new JLabel("Advocate ID");
        advocate_id.setBounds(200,80,100,20);
        f.add(advocate_id);

        JLabel  advocate_name=new JLabel("Name");
        advocate_name.setBounds(200,110,150,20);
        f.add(advocate_name);

        JLabel advocate_email=new JLabel("Email");
        advocate_email.setBounds(200,140,150,20);
        f.add(advocate_email);

        JLabel advocate_phone=new JLabel("Phone Number");
        advocate_phone.setBounds(200,170,150,20);
        f.add(advocate_phone);

        JLabel advocate_type=new JLabel("Lawyer Type");
        advocate_type.setBounds(200,200,150,20);
        f.add(advocate_type);

        JLabel advocate_location=new JLabel("Location");
        advocate_location.setBounds(200,230,150,20);
        f.add(advocate_location);

        JLabel advocate_totalCases=new JLabel("Total Cases");
        advocate_totalCases.setBounds(200,260,150,20);
        f.add(advocate_totalCases);


        JLabel advocate_winning_case=new JLabel("Winning Cases");
        advocate_winning_case.setBounds(200,290,150,20);
        f.add(advocate_winning_case);



        //textField

        JTextField Advo_ID=new JTextField();
        Advo_ID.setBounds(300,80,200,20);
        f.add(Advo_ID);
        Advo_ID.setEditable(false);


        JTextField Advo_Name=new JTextField();
        Advo_Name.setBounds(300,110,200,20);
        f.add(Advo_Name);
        Advo_Name.setEditable(false);

        JTextField Advo_Email=new JTextField();
        Advo_Email.setBounds(300,140,200,20);
        f.add(Advo_Email);
        Advo_Email.setEditable(false);

        JTextField Advo_Phone=new JTextField();
        Advo_Phone.setBounds(300,170,200,20);
        f.add(Advo_Phone);
        Advo_Phone.setEditable(false);

        JTextField Advo_Type=new JTextField();
        Advo_Type.setBounds(300,200,200,20);
        f.add(Advo_Type);
        Advo_Type.setEditable(false);

        JTextField Advo_Location=new JTextField();
        Advo_Location.setBounds(300,230,200,20);
        f.add(Advo_Location);
        Advo_Location.setEditable(false);

        JTextField Advo_Total_Cases=new JTextField();
        Advo_Total_Cases.setBounds(300,260,200,20);
        f.add(Advo_Total_Cases);
        Advo_Total_Cases.setEditable(false);

        JTextField Advo_Winning_Cases=new JTextField();
        Advo_Winning_Cases.setBounds(300,290,200,20);
        f.add(Advo_Winning_Cases);
        Advo_Winning_Cases.setEditable(false);

        try{
            Advo_ID.setText(Integer.toString(rs.getInt("advocate_id")));
            Advo_Name.setText(rs.getString("advocate_name"));
            Advo_Email.setText(rs.getString("advocate_email"));
            Advo_Phone.setText(rs.getString("advocate_phone"));
            Advo_Type.setText(rs.getString("advocate_type"));
            Advo_Location.setText(rs.getString("advocate_location"));
            Advo_Total_Cases.setText(Integer.toString(rs.getInt("advocate_total_cases")));
            Advo_Winning_Cases.setText(Integer.toString(rs.getInt("advocate_cases_won")));
        }
        catch (Exception e){
            //do nothing
        }




        //button

        JButton update_profile=new JButton("UPDATE PROFILE");
        update_profile.setBounds(270,340,200,20);
        f.add(update_profile);

        //back button
        JButton back_button=new JButton("BACK");
        back_button.setBounds(5,5,80,20);
        f.add(back_button);

        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Advocate_Home(rs).advocate_home_display();
            }
        });

        update_profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Advocate_Update(rs).advocate_update_display();
            }
        });

        f.setVisible(true);
    }

}
