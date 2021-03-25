package Court_Module;

import Advocate_Module.Advocate_Case_History;
import DatabaseConnector_TableCreator.Connector;
import DatabaseConnector_TableCreator.Court;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Court_Update_Case {
    static ResultSet rs;
    static int n;
    static ResultSet case_details;
    static String title="";
    static String document="";
    static String location="";

    public Court_Update_Case(ResultSet rs,ResultSet case_details,int n) {
        Court_Update_Case.rs=rs;
        Court_Update_Case.case_details=case_details;
        Court_Update_Case.n=n;
    }

    public void court_update_case_display(String document_input,String Title_input,String Date_input){
        //window
        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //label
        JLabel details=new JLabel("Document");
        details.setBounds(40,95,150,30);
        f.add(details);

        JTextField title_get=new JTextField("TITLE");
        title_get.setFont(new Font("Arial",Font.BOLD,16));
        title_get.setBounds(40,70,500,30);
        f.add(title_get);

        JLabel cnr_display=new JLabel("CNR");
        cnr_display.setBounds(200,20,200,20);
        f.add(cnr_display);


        JTextField cnr_text=new JTextField();
        cnr_text.setBounds(200,40,150,20);
        f.add(cnr_text);
        cnr_text.setEditable(false);

        JLabel hearing=new JLabel("Hearing Date");
        hearing.setBounds(390,20,200,20);
        f.add(hearing);


        JTextField Next_Hearing_Date=new JTextField();
        Next_Hearing_Date.setBounds(390,40,150,20);
        f.add(Next_Hearing_Date);
        Next_Hearing_Date.setEditable(false);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JTextArea area=new JTextArea();
        area.setBounds(40,50,500,350);
        scrollPane.setBounds(40,120,500,350);
        scrollPane.getViewport().add(area);
        f.add(scrollPane);

        JLabel party_name=new JLabel("Party Name");
        party_name.setBounds(550,20,100,20);
        f.add(party_name);

        JTextField party_name_display=new JTextField();
        party_name_display.setBounds(550,40,160,20);
        party_name_display.setEditable(false);
        f.add(party_name_display);

        JLabel advocate_name=new JLabel("Advocate Name");
        advocate_name.setBounds(550,70,100,20);
        f.add(advocate_name);

        JTextField advocate_name_display=new JTextField();
        advocate_name_display.setBounds(550,90,160,20);
        advocate_name_display.setEditable(false);
        f.add(advocate_name_display);

        JLabel opp_party_name=new JLabel("Opposite Party Name");
        opp_party_name.setBounds(550,120,150,20);
        f.add(opp_party_name);

        JTextField opp_party_name_display=new JTextField();
        opp_party_name_display.setBounds(550,140,160,20);
        opp_party_name_display.setEditable(false);
        f.add(opp_party_name_display);

        JLabel opp_advocate_name=new JLabel("Opposite Advocate Name");
        opp_advocate_name.setBounds(550,170,150,20);
        f.add(opp_advocate_name);

        JTextField opp_advocate_name_display=new JTextField();
        opp_advocate_name_display.setBounds(550,190,160,20);
        opp_advocate_name_display.setEditable(false);
        f.add(opp_advocate_name_display);

        JLabel case_type=new JLabel("Case Type");
        case_type.setBounds(550,220,150,20);
        f.add(case_type);

        JTextField case_type_display=new JTextField();
        case_type_display.setBounds(550,240,160,20);
        case_type_display.setEditable(false);
        f.add(case_type_display);


        //update button
        JButton update=new JButton("UPDATE");
        update.setBounds(615,449,100,20);
        f.add(update);

        //back button
        JButton back_button=new JButton("BACK");
        back_button.setBounds(5,5,80,20);
        f.add(back_button);



        try{
            Next_Hearing_Date.setText(case_details.getString("next_hearing"));
            cnr_text.setText("CNR"+case_details.getInt("case_id"));
            party_name_display.setText(case_details.getString("party_name"));
            advocate_name_display.setText(case_details.getString("advocate_name"));
            opp_party_name_display.setText(case_details.getString("opp_party_name"));
            opp_advocate_name_display.setText(case_details.getString("opp_advocate_name"));
            case_type_display.setText(case_details.getString("case_type"));
            if(!case_details.getString("result").equals("ON PROGRESS")){
                area.setEditable(false);
                area.setText("COMPLETED CASE CANNOT BE UPDATED");
                update.setEnabled(false);
                title_get.setEditable(false);
                title_get.setText("COMPLETED CASE CANNOT BE UPDATED");
                JLabel status=new JLabel("Won Advocate ID");
                status.setBounds(550,270,150,20);
                f.add(status);

                JTextField status_display=new JTextField(case_details.getString("result"));
                status_display.setBounds(550,290,160,20);
                status_display.setEditable(false);
                f.add(status_display);
            }
            else{
                area.setEditable(true);
                update.setEnabled(true);
                title_get.setEditable(true);
                JLabel status=new JLabel("Status");
                status.setBounds(550,270,150,20);
                f.add(status);

                JTextField status_display=new JTextField("ON PROGRESS");
                status_display.setBounds(550,290,160,20);
                status_display.setEditable(false);
                f.add(status_display);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        if((n==4)||(n==5)||(n==3)){
            String[] arr=document_input.split("\n", 2);
            title_get.setText(Title_input);
            title_get.setEditable(false);
            area.setText(arr[1]);
            area.setEditable(false);
            update.setVisible(false);
            Next_Hearing_Date.setText(Date_input);
        }

        f.setVisible(true);

        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    f.dispose();
                    if (n == 1) {
                        new Court_Get_CNR(rs,n).court_get_cnr_display();
                    }
                    else if(n==3){
                        new Advocate_Case_History(rs,cnr_text.getText(),n).advocate_case_history_display();
                    }
                    else {
                        new Advocate_Case_History(rs, "CNR" + case_details.getInt("case_id"),n).advocate_case_history_display();
                    }
                }
                catch (Exception ex){
                    System.out.println(e);
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(f,"Are you sure, Do you want to Update Case?");
                if(a==JOptionPane.YES_OPTION) {
                    title=title_get.getText();
                    document=title+"\n"+area.getText();
                    location=new Court_Create_Text_Case(rs,case_details).court_create_text_creation(document)+",";
                    new Court_Update_Case(rs,case_details,n).court_update_case_database();
                    f.dispose();
                    new Court_Verdict_Next(rs,case_details).court_verdict_next_display();
                }
            }
        });
    }

    void court_update_case_database(){
        Connection con=new Connector().establish_connection();
        try{
            String dates=case_details.getString("dates");
            String locations=case_details.getString("documents");
            if(dates==null){
                Statement smt=con.createStatement();
                String query="UPDATE cases SET dates='"+case_details.getString("next_hearing")+
                        ",' WHERE case_id="+case_details.getInt("case_id");
                int n=smt.executeUpdate(query);
                query="UPDATE cases SET documents='"+location+"' WHERE case_id="+case_details.getInt("case_id");
                n=smt.executeUpdate(query);
                System.out.println(dates+"these are dates");
                System.out.println(locations+"these are locations");
            }
            else{
                dates=dates+case_details.getString("next_hearing")+",";
                String query="UPDATE cases SET dates='"+dates+"' WHERE case_id="+case_details.getInt("case_id");
                Statement smt=con.createStatement();
                int n=smt.executeUpdate(query);
                System.out.println(dates+"these are dates");
                locations=case_details.getString("documents")+location;
                query="UPDATE cases SET documents='"+locations+"' WHERE case_id="+case_details.getInt("case_id");
                n=smt.executeUpdate(query);
                System.out.println(locations+"these are locations");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
