package Court_Module;

import DatabaseConnector_TableCreator.Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Court_Get_Advocate {

    String get_advocate(String id){
        Connection con=new Connector().establish_connection();
        try{
            String name="";
            String query="SELECT advocate_name from advocates WHERE advocate_id="+id;
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(query);
            if(rs.next()) {
                return rs.getString("advocate_name");
            }
            else {
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

}
