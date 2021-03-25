package Admin_Module;

import DropDown_Values.ComboModel;
import DatabaseConnector_TableCreator.Connector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import InterFace_Design.*;
import InterFace_Design.Button;

public class Admin_Add_Court {

    static String court_email="";
    static String court_password="";
    static String court_location="";

    //this function describes the structure of the add court page
    void add_court_display(){
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

        //add advocate heading
        JLabel add_court_heading=new JLabel("REGISTER COURT");
        add_court_heading.setBounds(300,140,200,20);
        f.add(add_court_heading);

        //email display
        JLabel email_display=new JLabel("Email");
        email_display.setBounds(200,180,100,20);
        f.add(email_display);

        //email text field
        JTextField email_get=new JTextField();
        email_get.setBounds(300,180,200,20);
        f.add(email_get);

        //password display
        JLabel password_display=new JLabel("Password");
        password_display.setBounds(200,210,100,20);
        f.add(password_display);

        //password field
        JPasswordField password_get=new JPasswordField();
        password_get.setBounds(300,210,200,20);
        f.add(password_get);

        //re password display
        JLabel re_password_display=new JLabel("Re-Password");
        re_password_display.setBounds(200,240,100,20);
        f.add(re_password_display);

        //re password field
        JPasswordField re_password_get=new JPasswordField();
        re_password_get.setBounds(300,240,200,20);
        f.add(re_password_get);

        //location display
        JLabel location_display=new JLabel("Location");
        location_display.setBounds(200,270,100,20);
        f.add(location_display);

        //locations combo box
        DefaultComboBoxModel city_mode=new ComboModel().city_model();
        JComboBox location_get=new JComboBox(city_mode);
        location_get.setBounds(300,270,200,20);
        f.add(location_get);

        //Register Button
        Button register_button=new Button();
        register_button.setBounds(310,310,100,20);
        f.add(register_button);
        register_button.setText("REGISTER");
        register_button.setRounded(true);
        register_button.setBorder(null);
        register_button.setBackground(Color.WHITE);

        Close_Back back=new Close_Back(2);
        f.add(back);
        back.addMouseListener(new Close_Back(2) {
            @Override
            public void mouseClicked(MouseEvent e) {
                f.dispose();
                new Admin_Home().home_display();
            }
        });



        //action lister for register button
        register_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                court_email=email_get.getText();
                court_password=new String(password_get.getPassword());
                court_location=""+city_mode.getSelectedItem();
                String email_validation="^[a-zA-Z0-9_+&*-]+(?:\\."+
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                        "A-Z]{2,7}$";
                if(!(court_email.matches(email_validation))){
                    JOptionPane.showMessageDialog(f, "Invalid Email ID", "status", JOptionPane.WARNING_MESSAGE);
                    email_get.setText("");
                }
                else if(!(court_password.equals(new String(re_password_get.getPassword())))){
                    JOptionPane.showMessageDialog(f, "Re-Password Does not Match", "status", JOptionPane.WARNING_MESSAGE);
                    re_password_get.setText("");
                }
                else{
                    int a=JOptionPane.showConfirmDialog(f,"Are you sure, Do you want to Add Court?");
                    if(a==JOptionPane.YES_OPTION){
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        new Admin_Add_Court().add_court_database();
                        JOptionPane.showMessageDialog(f," Court record has been added");
                        f.dispose();
                        new Admin_Add_Court().add_court_display();
                    }
                }
            }
        });



        f.getContentPane().setBackground(Color.WHITE);
        ImageIcon img=new ImageIcon("I:\\Bootcamp\\Bootathon\\E-Court\\ProjectFile\\3.png");
        JLabel background=new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,740,550);
        f.add(background);



        ImageIcon rimg=new ImageIcon("I:\\Bootcamp\\Bootathon\\E-Court\\ProjectFile\\Chennai_High_Court.png");
        JLabel rbackground=new JLabel("",rimg,JLabel.CENTER);
        rbackground.setBounds(0,0,740,900);
        f.add(rbackground);


        f.setVisible(true);
    }

    //this function establishes the connection to the database and updates the database
    void add_court_database(){

        Connection con=new Connector().establish_connection();
        try{
            String query="INSERT INTO courts(court_location,court_email,court_password) VALUES(?,?,?)";
            PreparedStatement smt=con.prepareStatement(query);
            smt.setString(1,court_location);
            smt.setString(2,court_email);
            smt.setString(3,court_password);
            smt.executeUpdate();
            smt.close();
            con.close();
            System.out.println("Court Record Insertion Successful");
        }
        catch (Exception e){
            System.out.println("Court record insertion failed");
        }

    }

}
