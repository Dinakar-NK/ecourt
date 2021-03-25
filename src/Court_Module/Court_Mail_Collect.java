package Court_Module;

import DatabaseConnector_TableCreator.Connector;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class Court_Mail_Collect {
    static ResultSet case_details;
    static ResultSet rs;
    static ResultSet email;

    Court_Mail_Collect(ResultSet rs,ResultSet case_details){
        Court_Mail_Collect.rs=rs;
        Court_Mail_Collect.case_details=case_details;
    }
    void Court_Emails(String data){
        new Court_Mail_Collect(rs,case_details).Court_Email_database();
        String[] emails=new String[4];
        try{
            emails[0]=case_details.getString("party_email");
            emails[1]=case_details.getString("opp_party_email");
            emails[2]=email.getString("advocate_email");
            if(email.next()){
                emails[3]=email.getString("advocate_email");
            }
            System.out.println("this are mails"+Arrays.toString(emails));
            new Court_Send_Mail().get_mail(emails,data);
        }
        catch (Exception e){
            System.out.println("court emails");
        }
    }
    void Court_Email_database(){
        Connection con=new Connector().establish_connection();
        try{
           String query="SELECT * FROM advocates WHERE (advocate_id="+case_details.getInt("advocate_id")+" OR "+
                   " advocate_id="+case_details.getInt("opp_advocate_id")+")";
            Statement smt=con.createStatement();
            email=smt.executeQuery(query);
            email.next();
        }
        catch (Exception e){
            System.out.println("court email database");
        }
    }
}
