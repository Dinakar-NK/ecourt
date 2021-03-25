package Admin_Module;
import DatabaseConnector_TableCreator.Connector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import InterFace_Design.Button;
import java.util.ArrayList;
import java.util.Iterator;

import DropDown_Values.*;
import InterFace_Design.Close_Back;

public class Admin_Add_Advocate {
    static int n=0;
    static String advocate_name="";
    static String advocate_email="";
    static String advocate_phone="";
    static String advocate_password="";
    static String advocate_type="";
    static String advocate_location="";


    //This function defines the structure of the Add Advocate page
    void add_advocate_display(){
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
        JLabel add_advocate_heading=new JLabel("REGISTER ADVOCATE");
        add_advocate_heading.setBounds(300,10+70+20,200,20);
        f.add(add_advocate_heading);

        //name display
        JLabel name_display=new JLabel("Name");
        name_display.setBounds(200,50+70+20,100,20);
        f.add(name_display);

        //name text field
        JTextField name_get=new JTextField();
        name_get.setBounds(300,50+70+20,200,20);
        f.add(name_get);

        //email display
        JLabel email_display=new JLabel("Email");
        email_display.setBounds(200,80+70+20,100,20);
        f.add(email_display);

        //email text field
        JTextField email_get=new JTextField();
        email_get.setBounds(300,80+70+20,200,20);
        f.add(email_get);

        //phone display
        JLabel phone_display=new JLabel("Phone");
        phone_display.setBounds(200,110+70+20,100,20);
        f.add(phone_display);

        //phone text field
        JTextField phone_get=new JTextField();
        phone_get.setBounds(300,110+70+20,200,20);
        f.add(phone_get);

        //password display
        JLabel password_display=new JLabel("Password");
        password_display.setBounds(200,140+70+20,100,20);
        f.add(password_display);

        //password field
        JPasswordField password_get=new JPasswordField();
        password_get.setBounds(300,140+70+20,200,20);
        f.add(password_get);

        //re password display
        JLabel re_password_display=new JLabel("Re-Password");
        re_password_display.setBounds(200,170+70+20,100,20);
        f.add(re_password_display);

        //re password field
        JPasswordField re_password_get=new JPasswordField();
        re_password_get.setBounds(300,170+70+20,200,20);
        f.add(re_password_get);

        //type display
        JLabel type_display=new JLabel("Type");
        type_display.setBounds(200,200+70+20,100,20);
        f.add(type_display);

        //advocate type combo box
        DefaultComboBoxModel type_mode=new ComboModel().type_model();
        JComboBox type_get=new JComboBox(type_mode);
        type_get.setBounds(300,200+70+20,200,20);
        f.add(type_get);

        //location display
        JLabel location_display=new JLabel("Location");
        location_display.setBounds(200,230+70+20,100,20);
        f.add(location_display);

        //locations combo box
        DefaultComboBoxModel city_mode=new ComboModel().city_model();
        JComboBox location_get=new JComboBox(city_mode);
        location_get.setBounds(300,230+70+20,200,20);
        f.add(location_get);

        Close_Back back=new Close_Back(2);
        f.add(back);

        back.addMouseListener(new Close_Back(2) {
            @Override
            public void mouseClicked(MouseEvent e) {
                f.dispose();
                new Admin_Home().home_display();
            }
        });



        //Register Button
        Button register_button=new Button();
        register_button.setBounds(320,270+70+20,100,20);
        f.add(register_button);
        register_button.setText("REGISTER");
        register_button.setRounded(true);
        register_button.setBorder(null);
        register_button.setBackground(Color.WHITE);

        //register button action listener
        register_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                advocate_name=name_get.getText();
                advocate_email=email_get.getText();
                advocate_phone=phone_get.getText();
                advocate_password=new String(password_get.getPassword());
                advocate_type=""+type_get.getSelectedItem();
                advocate_location=""+location_get.getSelectedItem();
                //System.out.println(advocate_name+" "+advocate_email+" "+advocate_phone+" "+advocate_password+" "+advocate_type+" "+advocate_location);
                String email_validation="^[a-zA-Z0-9_+&*-]+(?:\\."+
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                        "A-Z]{2,7}$";
                if(advocate_name.equals("")){
                    JOptionPane.showMessageDialog(f, "Advocate Name cannot be Empty", "status", JOptionPane.WARNING_MESSAGE);
                }
                else if(!(advocate_email.matches(email_validation))){
                    JOptionPane.showMessageDialog(f, "Invalid Email ID", "status", JOptionPane.WARNING_MESSAGE);
                    email_get.setText("");
                }
                else if(!(advocate_phone.length()==10)){
                    JOptionPane.showMessageDialog(f, "Invalid Phone Number", "status", JOptionPane.WARNING_MESSAGE);
                    phone_get.setText("");
                }
                else if(!(advocate_password.equals(new String(re_password_get.getPassword())))){
                    JOptionPane.showMessageDialog(f, "Re-Password Does not Match", "status", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    int a=JOptionPane.showConfirmDialog(f,"Are you sure, Do you want to Add Advocate?");
                    if(a==JOptionPane.YES_OPTION){
                        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        new Admin_Add_Advocate().add_advocate_database();
                        JOptionPane.showMessageDialog(f," Advocate record has been added");
                        f.dispose();
                        new Admin_Add_Advocate().add_advocate_display();;
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


    //This Function Establishes the connection to the advocate database and inserts the value of new records
    void add_advocate_database(){

        Connection con=new Connector().establish_connection();
        try{
            String query="INSERT INTO advocates(advocate_name,advocate_email,advocate_password,advocate_phone,advocate_type,advocate_location,"+
                    "advocate_total_cases,advocate_cases_won) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement smt=con.prepareStatement(query);
            smt.setString(1,advocate_name);
            smt.setString(2,advocate_email);
            smt.setString(3,advocate_password);
            smt.setString(4,advocate_phone);
            smt.setString(5,advocate_type);
            smt.setString(6,advocate_location);
            smt.setInt(7,0);
            smt.setInt(8,0);
            int n=smt.executeUpdate();
            smt.close();
            con.close();
        }
        catch (Exception e){
            System.out.println("Insertion of Advocate Record is Failed");
        }

    }

}
