package Advocate_Module;

import DatabaseConnector_TableCreator.Connector;
import DropDown_Values.ComboModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Advocate_Update {
    static ResultSet rs;
    static String advocate_phone_number="";
    static String advocate_type_lawyer="";
    static String advocate_location_city="";
    static int n;

    Advocate_Update(ResultSet rs){
        Advocate_Update.rs=rs;
    }

    //structure of update display
    void advocate_update_display(){

        //window
        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //label
        JLabel advocate_profile_update=new JLabel("ADVOCATE PROFILE UPDATE");
        advocate_profile_update.setBounds(250,110,200,20);
        f.add(advocate_profile_update);

        //label
        JLabel advocate_phone=new JLabel("Phone Number");
        advocate_phone.setBounds(200,150,150,20);
        f.add(advocate_phone);

        JLabel advocate_type=new JLabel("Lawyer Type");
        advocate_type.setBounds(200,190,150,20);
        f.add(advocate_type);

        JLabel advocate_location=new JLabel("Location");
        advocate_location.setBounds(200,230,150,20);
        f.add(advocate_location);


        //locations combo box
        DefaultComboBoxModel city_mode=new ComboModel().city_model();
        JComboBox location_get=new JComboBox(city_mode);
        location_get.setBounds(300,230,200,20);
        f.add(location_get);

        //textField
        JTextField Advo_Phone=new JTextField();
        Advo_Phone.setBounds(300,150,200,20);
        f.add(Advo_Phone);


        //advocate type combo box
        DefaultComboBoxModel type_mode=new ComboModel().type_model();
        JComboBox type_get=new JComboBox(type_mode);
        type_get.setBounds(300,190,200,20);
        f.add(type_get);


        //button
        JButton update_button=new JButton ("UPDATE");
        update_button.setBounds(270,270,150,20);
        f.add(update_button);

        //back button
        JButton back_button=new JButton("BACK");
        back_button.setBounds(5,5,80,20);
        f.add(back_button);

        try{
            Advo_Phone.setText(rs.getString("advocate_phone"));
            type_mode.setSelectedItem(rs.getString("advocate_type"));
            city_mode.setSelectedItem(rs.getString("advocate_location"));
        }
        catch (Exception e){
            //do nothing
        }

        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Advocate_Profile(rs).advocate_profile_display();
            }
        });

        update_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                advocate_phone_number=Advo_Phone.getText();
                advocate_location_city=""+city_mode.getSelectedItem();
                advocate_type_lawyer=""+type_mode.getSelectedItem();
                if(!(advocate_phone_number.length()==10)){
                    JOptionPane.showMessageDialog(f, "Invalid Phone Number", "status", JOptionPane.WARNING_MESSAGE);
                    Advo_Phone.setText("");
                }
                else{
                    int a=JOptionPane.showConfirmDialog(f,"Are you sure, Do you want to Add Advocate?");
                    if(a==JOptionPane.YES_OPTION){
                        new Advocate_Update(rs).advocate_update_database();
                        JOptionPane.showMessageDialog(f," Advocate record has been Updated");
                        f.dispose();
                        new Advocate_Profile(rs).advocate_profile_display();
                    }
                }
            }
        });

        f.setVisible(true);

    }


    //database connectivity of update display, it updates the database
    void advocate_update_database(){
        Connection con=new Connector().establish_connection();
        try{
            String query="UPDATE advocates SET "+
                    "advocate_phone='"+advocate_phone_number+"',"+
                    "advocate_type='"+advocate_type_lawyer+"',"+
                    "advocate_location='"+advocate_location_city+"' "+
                    "WHERE advocate_id="+(rs.getInt("advocate_id"))+"";
            Statement smt=con.createStatement();
            int n=smt.executeUpdate(query);
            String query1="SELECT * FROM advocates WHERE advocate_id=?";
            PreparedStatement p_smt=con.prepareStatement(query1);
            p_smt.setInt(1,rs.getInt("advocate_id"));
            rs=p_smt.executeQuery();
            rs.next();
        }
        catch (Exception e){
            System.out.println("Profile not Update "+e);
        }
    }

}
