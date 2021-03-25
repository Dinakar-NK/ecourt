package DatabaseConnector_TableCreator;

import java.sql.Connection;
import java.sql.Statement;

public class Advocate {
    public static void main(String[] args) {
        Connection con=new Connector().establish_connection();
        try{
            String name="Advocates";
            String query="CREATE TABLE "+name+
                    "(advocate_id INTEGER AUTO_INCREMENT,"+
                    "advocate_name VARCHAR(50) not NULL,"+
                    "advocate_email VARCHAR(50) not NULL,"+
                    "advocate_password VARCHAR(50) not NULL,"+
                    "advocate_phone VARCHAR(10) not NULL,"+
                    "advocate_type VARCHAR(50) not NULL,"+
                    "advocate_location VARCHAR(30) not NULL,"+
                    "advocate_total_cases INTEGER not NULL,"+
                    "advocate_cases_won INTEGER not NULL,"+
                    "PRIMARY KEY(advocate_id))";
            Statement smt=con.createStatement();
            smt.execute(query);
            System.out.println("Advocate Table Creation Successful");
        }
        catch (Exception e){
            System.out.println("Advocate Table Creation Failed "+e);
        }
    }
}
