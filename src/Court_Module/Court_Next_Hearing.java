package Court_Module;

import DatabaseConnector_TableCreator.Connector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Court_Next_Hearing {
    static ResultSet rs;
    static ResultSet case_details;
    static String date="";
    //static int n;
    Court_Next_Hearing(ResultSet rs,ResultSet case_details){
        Court_Next_Hearing.rs=rs;
        Court_Next_Hearing.case_details=case_details;
        //Court_Next_Hearing.n=n;
    }

    void court_next_hearing_display(){
        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel next=new JLabel("Next Hearing date");
        next.setBounds(310,160,200,20);
        f.add(next);

        JTextField next_date=new JTextField();
        next_date.setBounds(270,200,200,20);
        f.add(next_date);

        JButton declare=new JButton("Next Hearing");
        declare.setBounds(270,240,200,20);
        f.add(declare);

        JButton back=new JButton("BACK");
        back.setBounds(20,20,70,20);
        f.add(back);

        f.setVisible(true);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Court_Verdict_Next(rs,case_details).court_verdict_next_display();
            }
        });

        declare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                date=next_date.getText();
                String date_validation="^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
                if(!(date.matches(date_validation))){
                    JOptionPane.showMessageDialog(f, "Invalid Date Format", "status", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    new Court_Next_Hearing(rs,case_details).court_next_hearing_database();
                    f.dispose();
                    new Court_Get_CNR(rs,1).court_get_cnr_display();
                }
            }
        });
    }

    void court_next_hearing_database(){

        Connection con=new Connector().establish_connection();
        try {
            String query="UPDATE cases SET next_hearing='"+date+"' WHERE case_id="+case_details.getInt("case_id");
            Statement smt=con.createStatement();
            int n=smt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

}
