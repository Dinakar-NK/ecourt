package DatabaseConnector_TableCreator;

import java.sql.Connection;
import java.sql.Statement;

public class Court {
    public static void main(String[] args) {
        Connection con=new Connector().establish_connection();
        try{
            String query="CREATE TABLE courts("+
                    "court_id INTEGER AUTO_INCREMENT,"+
                    "court_location VARCHAR(30) not NULL,"+
                    "court_email VARCHAR(50) not NULL,"+
                    "court_password VARCHAR(50) not NULL,"+
                    "PRIMARY KEY(court_id))";
            Statement smt=con.createStatement();
            smt.execute(query);
            System.out.println("Court Table Creation Successful");
        }
        catch (Exception e){
            System.out.println("Court Table Not Created");
        }
    }
}
