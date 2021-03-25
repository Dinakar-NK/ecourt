package User_Module;

import DatabaseConnector_TableCreator.Connector;
import DropDown_Values.ComboModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User_Advocate_Sort {

    static String type="";
    static String location="";
    static int n;
    void user_advocate_sort_display(){
        JFrame j=new JFrame();
        JLabel l1=new JLabel("Advocate Type");
        JLabel l2=new JLabel("Location");
        JButton b1=new JButton("Find");
        JButton b2=new JButton("Back");




        //advocate type combo box
        DefaultComboBoxModel type_mode=new ComboModel().type_model();
        JComboBox type_get=new JComboBox(type_mode);
        j.add(type_get);


        //locations combo box
        DefaultComboBoxModel city_mode=new ComboModel().city_model();
        JComboBox location_get=new JComboBox(city_mode);

        j.add(l1);
        j.add(l2);
        j.add(b1);
        j.add(b2);
        j.add(location_get);
        j.add(location_get);

        b1.addActionListener(new ActionListener(){          //Find Button
            @Override
            public void actionPerformed(ActionEvent e) {
                location=""+location_get.getSelectedItem();
                type=""+type_get.getSelectedItem();
                new User_Advocate_Sort().user_advocate_database();
                if(n==1){
                    j.dispose();
                    new User_Advocates(location,type).user_advocates_display();
                }
                else{
                    JOptionPane.showMessageDialog(j,"No Records to Display","Alert",JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        b2.addActionListener(new ActionListener(){      //Back Button
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                new User_Home().user_home_display();
            }
        });

        l1.setBounds(225,150,200,25);
        type_get.setBounds(325,150,200,25);
        l2.setBounds(225,200,200,25);
        location_get.setBounds(325,200,200,25);
        b1.setBounds(285,250,100,25);
        b2.setBounds(10,10,75,20);

        j.setBounds(420,170,740,520);
        j.setLayout(null);
        j.setVisible(true);
        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    void user_advocate_database(){
        Connection con=new Connector().establish_connection();
        try{
            String query="SELECT * FROM advocates WHERE( advocate_type='"+type+"' AND advocate_location='"+location+"' )";
            Statement smt=con.createStatement();
            ResultSet advocates=smt.executeQuery(query);
            if(advocates.next()){
                n=1;
            }
            else{
                n=0;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
