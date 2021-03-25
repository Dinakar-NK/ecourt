package Court_Module;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Court_Verdict_Next {

    static ResultSet rs;
    static ResultSet case_details;

    Court_Verdict_Next(ResultSet rs,ResultSet case_details){
        Court_Verdict_Next.rs=rs;
        Court_Verdict_Next.case_details=case_details;
        //Court_Verdict_Next.n=n;
    }

    void court_verdict_next_display(){

        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton verdict =new JButton("VERDICT");
        verdict.setBounds(260,180,180,20);
        f.add(verdict);

        JButton nextdate =new JButton("NEXT DATE");
        nextdate.setBounds(260,230,180,20);
        f.add(nextdate);

        verdict.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Court_Verdict(rs,case_details).court_verdict_display();
            }
        });

        nextdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Court_Next_Hearing(rs,case_details).court_next_hearing_display();
            }
        });

        f.setVisible(true);

    }
}
