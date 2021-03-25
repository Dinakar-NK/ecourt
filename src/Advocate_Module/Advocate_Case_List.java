package Advocate_Module;

import Court_Module.Court_Get_CNR;
import DatabaseConnector_TableCreator.Connector;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Advocate_Case_List {

    static ResultSet cases;
    static String table_cnr;
    static int n;
    static String[] table_head={"CNR","COURT","PARTY NAME","STATUS","NEXT HEARING"};
    static ResultSet rs;

    Advocate_Case_List(ResultSet rs,int n) {
        Advocate_Case_List.rs=rs;
        Advocate_Case_List.n=n;
    }

    void advocate_case_list_display(){
        //window
        JFrame f=new JFrame("Advocate Cases");
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




        //label
        JLabel case_heading=new JLabel("CASE LIST");
        case_heading.setBounds(300,40,150,20);
        f.add(case_heading);

        //TODO: case list table to be created with database
        DefaultTableModel model=new DefaultTableModel(table_head,0);
        new Advocate_Case_List(rs,n).advocate_case_list_database();
        try {
            while(cases.next()){
                String cnr="CNR"+cases.getInt("case_id");
                String party_name="";
                if(rs.getInt("advocate_id")==(cases.getInt("advocate_id"))){
                    party_name=cases.getString("party_name");
                }
                else{
                    party_name=cases.getString("opp_party_name");
                }
                String status="";
                if(cases.getString("result").equals("ON PROGRESS")){
                    status="ON PROGRESS";
                }
                else{
                    if(cases.getString("result").equals(Integer.toString(rs.getInt("advocate_id")))){
                        status="WON";
                    }
                    else{
                        status="LOST";
                    }
                }
                String next_hearing="";
                if(cases.getString("result").equals("ON PROGRESS")){
                    next_hearing=cases.getString("next_hearing");
                }
                else{
                    next_hearing="CASE OVER";
                }
                int court=cases.getInt("court_id");
                Object[] values={cnr,court,party_name,status,next_hearing};
                model.addRow(values);
            }


        }
        catch (Exception e){
            System.out.println(e);
        }


        JTable table=new JTable(model);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10,70,700,360);
        scrollPane.getViewport().add(table);
        f.add(scrollPane);

        table.setCellSelectionEnabled(true);
        ListSelectionModel select=table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                table_cnr="";
                int[] row = table.getSelectedRows();
                for(int i=0;i<row.length;++i){
                    table_cnr=(String) table.getValueAt(row[i],0);
                    f.dispose();
                    System.out.println(table_cnr);
                    new Advocate_Case_History(rs,table_cnr,n).advocate_case_history_display();
                }

            }
        });




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

        f.setVisible(true);



    }

    void advocate_case_list_database(){
        //TODO: DATABASE CONNECTIVITY AND GET VALUES FOR TABLE
        Connection con=new Connector().establish_connection();
        try{
            String query="SELECT * FROM cases WHERE (advocate_id="+rs.getInt("advocate_id")+" OR opp_advocate_id="+
                    rs.getInt("advocate_id")+")";
            Statement smt=con.createStatement();
            cases=smt.executeQuery(query);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
