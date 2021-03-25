package User_Module;

import DatabaseConnector_TableCreator.Connector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User_Advocates {
    static ResultSet advocates;
    static String location="";
    static String type="";
    static int n;
    static String[] table_head={"Advocate ID","Advocate Name","Advocate Type","Phone Number","Total Cases","Total Won"};

    User_Advocates(String location,String type){
        User_Advocates.location=location;
        User_Advocates.type=type;
    }

    void user_advocates_display(){
        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel heading=new JLabel("Advocates");
        heading.setBounds(300,20,150,20);
        f.add(heading);

        new User_Advocates(location,type).user_advocates_database();

        if(n==1) {
            DefaultTableModel model = new DefaultTableModel(table_head, 0);
            try {

                while (advocates.next()) {
                    Object[] values = {advocates.getInt("advocate_id"), advocates.getString("advocate_name"),
                            advocates.getString("advocate_type"),
                            advocates.getString("advocate_phone"), advocates.getInt("advocate_total_cases"),
                            advocates.getInt("advocate_cases_won")};
                    model.addRow(values);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setBounds(10, 50, 700, 400);
            scrollPane.getViewport().add(table);
            f.add(scrollPane);

            table.setCellSelectionEnabled(true);
            //back button
            JButton back_button=new JButton("BACK");
            back_button.setBounds(5,5,80,20);
            f.add(back_button);

            f.setVisible(true);

            back_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new User_Advocate_Sort().user_advocate_sort_display();
                }
            });
        }
        else {
            JOptionPane.showMessageDialog(f,"No Records to Display","Alert",JOptionPane.WARNING_MESSAGE);
            f.dispose();
            new User_Advocate_Sort().user_advocate_sort_display();
        }




    }

    void user_advocates_database(){
        Connection con=new Connector().establish_connection();
        try{
            String query="SELECT * FROM advocates WHERE( advocate_type='"+type+"' AND advocate_location='"+location+"' )";
            Statement smt=con.createStatement();
            advocates=smt.executeQuery(query);
            n=1;
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
