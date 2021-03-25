package Advocate_Module;

import Court_Module.Court_Get_CNR;
import Court_Module.Court_Home;
import Court_Module.Court_Update_Case;
import DatabaseConnector_TableCreator.Advocate;
import DatabaseConnector_TableCreator.Connector;
import User_Module.User_Home;

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

import static java.awt.Font.BOLD;

public class Advocate_Case_History {
    static String[] dates;
    static String[] location;
    static String[] Title;
    static ResultSet rs;
    static ResultSet case_details;
    static String cnr="";
    static String[] table_head={"TITLE","DATE"};
    static int n;
    public Advocate_Case_History(ResultSet rs,String cnr,int n){
        Advocate_Case_History.rs=rs;
        Advocate_Case_History.cnr=cnr;
        Advocate_Case_History.n=n;
    }


    //this defines the structure of Case history page
    public void advocate_case_history_display(){
        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




        JLabel case_filed_by=new JLabel("Case Filled By");
        case_filed_by.setBounds(40,60,150,20);
        f.add(case_filed_by);

        JLabel opposition=new JLabel("Opposition");
        opposition.setBounds(40,100,150,20);
        f.add(opposition);

        JLabel party_names=new JLabel("Party Name");
        party_names.setBounds(190,20,150,20);
        f.add(party_names);

        JLabel advocate_name=new JLabel("Advocate Name");
        advocate_name.setBounds(360,20,150,20);
        f.add(advocate_name);

        JLabel hearing=new JLabel("NEXT HEARING DATE");
        hearing.setBounds(540,20,200,20);
        f.add(hearing);

//textField

        JTextField Party_Name=new JTextField();
        Party_Name.setBounds(150,60,150,20);
        f.add(Party_Name);
        Party_Name.setEditable(false);

        JTextField Advo_Name=new JTextField();
        Advo_Name.setBounds(330,60,150,20);
        f.add(Advo_Name);
        Advo_Name.setEditable(false);

        JTextField Opp_Party_Name=new JTextField();
        Opp_Party_Name.setBounds(150,100,150,20);
        f.add(Opp_Party_Name);
        Opp_Party_Name.setEditable(false);

        JTextField Opp_Advo_Name=new JTextField();
        Opp_Advo_Name.setBounds(330,100,150,20);
        f.add(Opp_Advo_Name);
        Opp_Advo_Name.setEditable(false);

        JTextField Next_Hearing_Date=new JTextField();
        Next_Hearing_Date.setBounds(500,60,200,20);
        f.add(Next_Hearing_Date);
        Next_Hearing_Date.setEditable(false);

        JButton case_file=new JButton("CASE FILE COPY");
        case_file.setBounds(500,100,200,20);
        f.add(case_file);

        case_file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Advocate_Case_File_Copy(rs,case_details,n).case_file_copy_display();
            }
        });

        //back button
        JButton back_button=new JButton("BACK");
        back_button.setBounds(5,5,80,20);
        f.add(back_button);



        new Advocate_Case_History(rs,cnr,n).advocate_case_history_database();
        DefaultTableModel model=new DefaultTableModel(table_head,0);
        try{
            Party_Name.setText(case_details.getString("party_name"));
            Advo_Name.setText(case_details.getString("advocate_name"));
            Opp_Party_Name.setText(case_details.getString("opp_party_name"));
            Opp_Advo_Name.setText(case_details.getString("opp_advocate_name"));
            if(case_details.getString("result").equals("ON PROGRESS")){
                Next_Hearing_Date.setText(case_details.getString("next_hearing"));
            }
            else{
                Next_Hearing_Date.setText("CASE OVER");
            }

            dates=case_details.getString("dates").split(",");
            location=case_details.getString("documents").split(",");
            System.out.println(case_details.getString("documents"));
            Title=new String[location.length];
            System.out.println(location.length);
            for(int i=0;i< location.length;++i){
                System.out.println(location[i]);
                Title[i]=new Advocate_Map_Text_File().get_title(location[i]);
                System.out.println(Title[i]);
                Object[] value={Title[i],dates[i]};
                //System.out.println(Title[i]+" "+dates[i]);
                model.addRow(value);
            }
        }
        catch (Exception e){
            //do nothing
        }
        JTable table=new JTable(model);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10,150,700,300);
        scrollPane.getViewport().add(table);
        f.add(scrollPane);

        table.setCellSelectionEnabled(true);
        ListSelectionModel select=table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index=-1;
                String table_cnr="";
                String date_send="";
                int[] row = table.getSelectedRows();
                for(int i=0;i<row.length;++i){
                    table_cnr=(String) table.getValueAt(row[i],1);
                    for(int j=0;j< dates.length;++j){
                        if(dates[j].equals(table_cnr)){
                            date_send=dates[j];
                           index=j;
                           break;
                        }
                    }
                    if(index!=-1){
                        break;
                    }
                }
                f.dispose();
                String document=new Advocate_Map_Text_File().get_document(location[index]);
                new Court_Update_Case(rs,case_details,n).court_update_case_display(document,Title[index],date_send);
            }
        });

        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                if(n==4) {
                    new Advocate_Case_List(rs, n).advocate_case_list_display();
                }
                else if(n==3){
                    new Court_Get_CNR(rs,n).court_get_cnr_display();
                }
                else if(n==5){
                    new User_Home().user_home_display();
                }
            }
        });
        f.setVisible(true);
    }

    //this function gets the value from the database
    void advocate_case_history_database(){
        //TODO: DATABASE CONNECTIVITY FOR GETTING PARTICULAR CASE HISTORY
        Connection con=new Connector().establish_connection();
        try {
            int id=Integer.parseInt(cnr.substring(3));
            String query="SELECT * FROM cases WHERE case_id="+id;
            Statement smt=con.createStatement();
            case_details=smt.executeQuery(query);
            case_details.next();
            System.out.println(case_details.getString("party_name"));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
