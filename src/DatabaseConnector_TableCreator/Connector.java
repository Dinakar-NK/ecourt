package DatabaseConnector_TableCreator;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

    public Connection establish_connection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/e_court";
            String user="root";
            String pass="riseup08chillbro";
            Connection con= DriverManager.getConnection(url,user,pass);
            System.out.println("Connection Successful");
            return con;
        }
        catch (Exception e){
            System.out.println("Connection to the Database Failed");
            return null;
        }
    }

    public static void main(String[] args) {
        Connection con=new Connector().establish_connection();
    }
}
