package User_Module;

import Advocate_Module.Advocate_Case_History;
import Advocate_Module.Advocate_Home;
import Advocate_Module.Advocate_Login;
import DatabaseConnector_TableCreator.Connector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User_Case_Login {
    static String cnr="";
    static String password="";
    static int n;
    static ResultSet case_details;

    void user_case_login_display(){
        //window
        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JLabel case_view=new JLabel("Case View");
        case_view.setBounds(300,130,160,20);
        f.add(case_view);


        JLabel cnr_display=new JLabel("CNR number ");
        cnr_display.setBounds(200,170,100,20);
        f.add(cnr_display);


        JTextField cnr_get=new JTextField();
        cnr_get.setBounds(320,170,200,20);
        f.add(cnr_get);

        //Password: Label
        JLabel password_display=new JLabel("Password ");
        password_display.setBounds(200,210,100,20);
        f.add(password_display);

        //Password: Password Field
        JPasswordField password_get=new JPasswordField();
        password_get.setBounds(320,210,200,20);
        f.add(password_get);

        //enter: Button
        JButton enter=new JButton("ENTER");
        enter.setBounds(300,250,100,20);
        f.add(enter);

        //back button
        JButton back_button=new JButton("BACK");
        back_button.setBounds(5,5,80,20);
        f.add(back_button);

        f.setVisible(true);

       enter.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   System.out.println("previous");
                   cnr = cnr_get.getText();
                   System.out.println("next");
                   password = new String(password_get.getPassword());
                   new User_Case_Login().user_case_login_database();
                   if (n == 1) {
                       f.dispose();
                       new Advocate_Case_History(null,cnr,5).advocate_case_history_display();
                   } else {
                       JOptionPane.showMessageDialog(f, "Check your CNR Number and Password", "Alert", JOptionPane.WARNING_MESSAGE);
                   }
               }
               catch (Exception ex){
                   System.out.println(ex);
                   JOptionPane.showMessageDialog(f, "Check your CNR Number and Password", "Alert", JOptionPane.WARNING_MESSAGE);
               }

           }
       });

       back_button.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               f.dispose();
               new User_Home().user_home_display();
           }
       });
    }

    void user_case_login_database(){
        Connection con=new Connector().establish_connection();
        try{
            int id=Integer.parseInt(cnr.substring(3));
            String query="SELECT * FROM cases WHERE (case_id="+id+" AND case_password='"+password+"')";
            Statement smt=con.createStatement();
            case_details=smt.executeQuery(query);
            if(case_details.next()){
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
