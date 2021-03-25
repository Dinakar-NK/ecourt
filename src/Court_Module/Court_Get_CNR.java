package Court_Module;

import Advocate_Module.Advocate_Case_History;
import DatabaseConnector_TableCreator.Connector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

public class Court_Get_CNR {
    static ResultSet rs;
    static ResultSet case_details;
    static String cnr="";
    static int n;

    public Court_Get_CNR(ResultSet rs,int n){
        Court_Get_CNR.n=n;
        Court_Get_CNR.rs=rs;
    }

    public void court_get_cnr_display(){
        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel disp=new JLabel("ENTER CNR NO");
        disp.setBounds(320,160,120,20);
        f.add(disp);

        JTextField get_cnrno=new JTextField();
        get_cnrno.setBounds(270,200,200,20);
        get_cnrno.setText("");
        f.add(get_cnrno);

        JButton enter=new JButton("Enter");
        enter.setBounds(315,240,100,20);
        f.add(enter);

        //back button
        JButton back_button=new JButton("BACK");
        back_button.setBounds(5,5,80,20);
        f.add(back_button);

        f.setVisible(true);

        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Court_Home(rs).court_home_display();
            }
        });

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cnr=get_cnrno.getText();
                    int id=Integer.parseInt(cnr.substring(3));
                    case_details = new Court_Find_Case().find_case(id, rs.getInt("court_id"));
                        if (case_details.next()) {
                            if(n==1){
                                f.dispose();
                                new Court_Update_Case(rs,case_details,n).court_update_case_display("","Title","");
                            }
                            else if(n==2){
                                f.dispose();
                                new Court_Opposition_Update(rs,case_details,n).court_opposition_update_display();
                            }
                            else if(n==3){
                                f.dispose();
                                new Advocate_Case_History(rs,cnr,n).advocate_case_history_display();
                            }
                        } else {
                            JOptionPane.showMessageDialog(f, "Check your CNR Number", "Alert", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(f, "Check your CNR Number", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

}
