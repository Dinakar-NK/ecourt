package Court_Module;

import InterFace_Design.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Court_Case_Copy {
    static ResultSet rs;
    static ResultSet case_details;
    static int n;

    Court_Case_Copy(ResultSet rs,ResultSet case_details,int n){
        Court_Case_Copy.rs=rs;
        Court_Case_Copy.case_details=case_details;
        Court_Case_Copy.n=n;
    }

    void court_case_copy_display(){
        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel heading=new JLabel("CASE DETAILS");
        heading.setBounds(300,60,100,20);
        f.add(heading);

        JLabel cnrno=new JLabel("CNR No.");
        cnrno.setBounds(20,100,120,20);
        f.add(cnrno);

        JTextField disp_cnr=new JTextField();
        disp_cnr.setBounds(75,100,150,20);
        disp_cnr.setEditable(false);
        f.add(disp_cnr);


        JLabel casetype=new JLabel("Case Type");
        casetype.setBounds(240,100,120,20);
        f.add(casetype);

        JTextField disp_casetype=new JTextField();
        disp_casetype.setBounds(310,100,150,20);
        disp_casetype.setEditable(false);
        f.add(disp_casetype);

        JLabel courtid=new JLabel("Court ID");
        courtid.setBounds(470,100,120,20);
        f.add(courtid);

        JTextField disp_courtid=new JTextField();
        disp_courtid.setBounds(540,100,150,20);
        disp_courtid.setEditable(false);
        f.add(disp_courtid);


        JLabel partyname=new JLabel("Party Name");
        partyname.setBounds(220,140,120,20);
        f.add(partyname);

        JTextField disp_partyname=new JTextField();
        disp_partyname.setBounds(330,140,200,20);
        disp_partyname.setEditable(false);
        f.add(disp_partyname);

        JLabel partyphone=new JLabel("Party Phone");
        partyphone.setBounds(220,180,120,20);
        f.add(partyphone);

        JTextField disp_partyphone=new JTextField();
        disp_partyphone.setBounds(330,180,200,20);
        disp_partyphone.setEditable(false);
        f.add(disp_partyphone);

        JLabel adv_id=new JLabel("Advocate ID");
        adv_id.setBounds(220,220,120,20);
        f.add(adv_id);

        JTextField disp_adv_id=new JTextField();
        disp_adv_id.setBounds(330,220,200,20);
        disp_adv_id.setEditable(false);
        f.add(disp_adv_id);

        JLabel adv_name=new JLabel("Advocate Name");
        adv_name.setBounds(220,260,120,20);
        f.add(adv_name);

        JTextField disp_adv_name=new JTextField();
        disp_adv_name.setBounds(330,260,200,20);
        disp_adv_name.setEditable(false);
        f.add(disp_adv_name);

        JLabel hear_date=new JLabel("Hearing Date");
        hear_date.setBounds(220,300,120,20);
        f.add(hear_date);

        JTextField disp_hear_date=new JTextField();
        disp_hear_date.setBounds(330,300,200,20);
        disp_hear_date.setEditable(false);
        f.add(disp_hear_date);

        Button done =new Button("DONE");
        done.setBounds(250,340,200,20);
        f.add(done);

        f.getContentPane().setBackground(Color.WHITE);
        ImageIcon img=new ImageIcon("I:\\Bootcamp\\Bootathon\\E-Court\\ProjectFile\\Chennai_High_Court.png");
        JLabel background=new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,740,850);
        f.add(background);

        f.setVisible(true);

        try{
            disp_cnr.setText("CNR"+case_details.getInt("case_id"));
            disp_casetype.setText(case_details.getString("case_type"));
            disp_courtid.setText(rs.getString("court_id"));
            disp_hear_date.setText(case_details.getString("next_hearing"));
            if(n==1) {
                disp_partyname.setText(case_details.getString("party_name"));
                disp_partyphone.setText(case_details.getString("party_phone"));
                disp_adv_id.setText(Integer.toString(case_details.getInt("advocate_id")));
                disp_adv_name.setText(case_details.getString("advocate_name"));
            }
            else{
                disp_partyname.setText(case_details.getString("opp_party_name"));
                disp_partyphone.setText(case_details.getString("opp_party_phone"));
                disp_adv_id.setText(Integer.toString(case_details.getInt("opp_advocate_id")));
                disp_adv_name.setText(case_details.getString("opp_advocate_name"));
            }

        }
        catch (Exception e){
            //do nothing
        }

        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Court_Home(rs).court_home_display();
            }
        });
    }
}
