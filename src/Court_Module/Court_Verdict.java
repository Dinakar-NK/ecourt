package Court_Module;

import DatabaseConnector_TableCreator.Connector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Court_Verdict {
    static ResultSet rs;
    static ResultSet case_details;
    static String id="";
    //static int n;
    Court_Verdict(ResultSet rs,ResultSet case_details){
        Court_Verdict.rs=rs;
        Court_Verdict.case_details=case_details;
        //Court_Verdict.n=n;
    }

    void court_verdict_display(){

        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel win=new JLabel("WINNING ADVOCATE ID");
        win.setBounds(300,160,200,20);
        f.add(win);

        String advo_id="";
        String opp_advo_id="";
        try {
            advo_id=case_details.getString("advocate_id");
            opp_advo_id=case_details.getString("opp_advocate_id");
        }
        catch (Exception e){
            System.out.println(e);
        }

        String get_advid[] = {advo_id,opp_advo_id};
        JComboBox get_win = new JComboBox(get_advid);
        get_win.setBounds(270,200,200,20);
        f.add(get_win);

        JButton declare=new JButton("DECLARE");
        declare.setBounds(315,240,100,20);
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
                id=get_win.getSelectedItem()+"";
                new Court_Verdict(rs,case_details).court_verdict_database();
                f.dispose();
                new Court_Get_CNR(rs,1).court_get_cnr_display();
            }
        });
    }

    void court_verdict_database(){
        Connection con=new Connector().establish_connection();
        try{
            String query="UPDATE cases SET result='"+id+"' WHERE case_id="+case_details.getInt("case_id");
            Statement smt=con.createStatement();
            int n=smt.executeUpdate(query);
            query="SELECT * FROM advocates WHERE advocate_id="+Integer.parseInt(id);
            ResultSet adv=smt.executeQuery(query);
            adv.next();
            int won=adv.getInt("advocate_cases_won")+1;
            query="UPDATE advocates SET advocate_cases_won="+won+" WHERE advocate_id="+Integer.parseInt(id);
            n=smt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
