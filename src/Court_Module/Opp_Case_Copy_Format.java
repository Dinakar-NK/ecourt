package Court_Module;

import DatabaseConnector_TableCreator.Connector;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Opp_Case_Copy_Format {
    static ResultSet case_details;
    static ResultSet rs;
    static String description="";
    static String email="";

    Opp_Case_Copy_Format(ResultSet case_details,ResultSet rs,String description,String email){
        Opp_Case_Copy_Format.case_details=case_details;
        Opp_Case_Copy_Format.rs=rs;
        Opp_Case_Copy_Format.description=description;
        Opp_Case_Copy_Format.email=email;
    }

    void Opp_Case_Format() {
        try {
            System.out.println("Before database");
            new Opp_Case_Copy_Format(case_details,rs,description,email).opp_case_copy_format();
            String data="";
            case_details.next();
            String location = "I:\\Bootcamp\\Bootathon\\E-Court\\ProjectFile\\cases\\" + rs.getInt("court_id")+
                    "\\CNR"+case_details.getInt("case_id")+"\\case_copy.txt";
            File read=new File(location);
            Scanner reader=new Scanner(read);
            while(reader.hasNextLine()){
                data = data + reader.nextLine() +"\n";
            }
            System.out.println("after database");
            data=data+"\n\t\t\tOpposite Party Details\n"+
                    "************************************************************************\n"+
                    "Party Name: "+case_details.getString("opp_party_name")+"\n\n"+
                    "Party Phone: "+case_details.getString("opp_party_phone")+"\n\n"+
                    "Advocate ID: "+case_details.getInt("opp_advocate_id")+"\n\n"+
                    "Advocate Name: "+case_details.getString("opp_advocate_name")+"\n\n"+
                    "Case Description\n"+description+"\n\n"+
                    "************************************************************************\n";

            FileWriter case_copy=new FileWriter(location);
            case_copy.write(data);
            case_copy.close();
            new Court_Mail_Collect(rs,case_details).Court_Emails(data);

        } catch (Exception e) {
            System.out.println("Opp case capy foooo"+e);
        }
    }

    void opp_case_copy_format(){
        Connection con=new Connector().establish_connection();
        try {
            String query="UPDATE cases SET opp_party_email='"+email+"' WHERE case_id="+case_details.getInt("case_id");
            Statement smt=con.createStatement();
            int k=smt.executeUpdate(query);
            query="SELECT * FROM cases WHERE case_id="+case_details.getInt("case_id");
            smt=con.createStatement();
            case_details=smt.executeQuery(query);
        } catch (Exception e) {
            System.out.println("OPP case copy format"+e);
        }
    }
}
