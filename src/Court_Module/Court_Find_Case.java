package Court_Module;

import DatabaseConnector_TableCreator.Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Court_Find_Case {

    ResultSet find_case(int n,int id){
        Connection con=new Connector().establish_connection();
        try{
            String query="SELECT * FROM cases WHERE case_id="+n+" AND court_id="+id;
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(query);
            return rs;
        }
        catch (Exception e){
            return null;
        }
    }

}
