package Court_Module;

import DatabaseConnector_TableCreator.Connector;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Case_Copy_Format {
    static String description="";
    static ResultSet rs;
    static ResultSet case_details;
    static String filed_date;
    static String location="";
    static String party_email="";
    Case_Copy_Format(ResultSet rs,ResultSet case_details,String description,String filed_date,String party_email){
        Case_Copy_Format.rs=rs;
        Case_Copy_Format.case_details=case_details;
        Case_Copy_Format.description=description;
        Case_Copy_Format.filed_date=filed_date;
        Case_Copy_Format.party_email=party_email;
    }

    void format_copy(){
        try {
            location="I:\\Bootcamp\\Bootathon\\E-Court\\ProjectFile\\cases"+"\\"+case_details.getInt("court_id")+
                    "\\CNR"+case_details.getInt("case_id")+"\\case_copy.txt";
            FileWriter case_copy=new FileWriter(location);
            String details="************************************************************************\n"+
                    "Filed On:"+filed_date+"\t\tCase Copy\t\tCNR: CNR"+case_details.getInt("case_id")+"\n"+
                    "************************************************************************\n\n"+
                    "Court ID: "+rs.getInt("court_id")+"\n\n"+
                    "Case Filed In: "+rs.getString("court_location")+" Court\n\n"+
                    "Case Type: "+case_details.getString("case_type")+"\n\n"+
                    "Party Name: "+case_details.getString("party_name")+"\n\n"+
                    "Party Phone: "+case_details.getString("party_phone")+"\n\n"+
                    "Advocate ID: "+case_details.getInt("advocate_id")+"\n\n"+
                    "Advocate Name: "+case_details.getString("advocate_name")+"\n\n"+
                    "First Hearing Date: "+case_details.getString("next_hearing")+"\n\n"+
                    "Case Description\n\n"+description+"\n\n"+
                    "************************************************************************\n";
            case_copy.write(details);
            case_copy.close();
            new Case_Copy_Format(rs,case_details,description,filed_date,party_email).format_copy_database();
            case_details.next();
            new Court_Mail_Collect(rs,case_details).Court_Emails(details);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void format_copy_database(){
        Connection con=new Connector().establish_connection();
        try {
            String query="UPDATE cases SET case_file_date='"+filed_date+
                    "', party_email='"+party_email+"' WHERE case_id="+case_details.getInt("case_id");

            Statement smt=con.createStatement();
            int f=smt.executeUpdate(query);
            query="SELECT * FROM cases WHERE case_id="+case_details.getInt("case_id");
            smt=con.createStatement();
            case_details=smt.executeQuery(query);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
