package DatabaseConnector_TableCreator;

import java.sql.Connection;
import java.sql.Statement;

public class Cases {
    public static void main(String[] args) {
        Connection con=new Connector().establish_connection();
        try{
            String name="Cases";
            String query="CREATE TABLE "+name+
                    "(case_id INTEGER AUTO_INCREMENT,"+
                    "court_id INTEGER not NULL,"+
                    "case_type VARCHAR(50) not NULL,"+
                    "party_name VARCHAR(30) not NULL,"+
                    "party_phone VARCHAR(10) not NULL,"+
                    "party_email VARCHAR(30),"+
                    "advocate_name VARCHAR(30) not NULL,"+
                    "advocate_id INTEGER not NULL,"+
                    "opp_party_name VARCHAR(30),"+
                    "opp_party_phone VARCHAR(30),"+
                    "opp_party_email VARCHAR(30),"+
                    "opp_advocate_name VARCHAR(30),"+
                    "opp_advocate_id INTEGER,"+
                    "next_hearing VARCHAR(10) not NULL,"+
                    "case_password VARCHAR(20) not NULL,"+
                    "result VARCHAR(20) not NULL,"+
                    "dates VARCHAR(150),"+
                    "documents VARCHAR(300),"+
                    "case_file_date VARCHAR(10),"+
                    "PRIMARY KEY(case_id))";

            Statement smt=con.createStatement();
            smt.execute(query);
            System.out.println("table created successfully");
        }
        catch (Exception e){
            System.out.println("Unable to Create Table Cases");
        }
    }
}
