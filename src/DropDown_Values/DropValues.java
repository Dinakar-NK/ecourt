package DropDown_Values;
import DatabaseConnector_TableCreator.Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DropValues {

    //Fetches the cities from the cities table and send to the combo model class
    ArrayList<String> cities(){
        Connection con=new Connector().establish_connection();
        try{
            String query="SELECT * FROM cities";
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(query);
            ArrayList<String> arr=new ArrayList<String>();
            while(rs.next()){
                arr.add(rs.getString("city"));
            }
            return arr;
        }
        catch (Exception e){
            System.out.println("Unable to fetch Values");
            return null;
        }
    }

    //Fetches the Types of Lawyers from the types table and send to the combo model class
    ArrayList<String> types(){
        Connection con=new Connector().establish_connection();
        try{
            String query="SELECT * FROM types";
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(query);
            ArrayList<String> arr=new ArrayList<String>();
            while (rs.next()){
                arr.add(rs.getString("type"));
            }
            return arr;
        }
        catch (Exception e){
            System.out.println("Unable to Fetch Values");
            return null;
        }
    }

    //Case types getting from the table
    ArrayList<String> case_types(){
        Connection con=new Connector().establish_connection();
        try{
            String query="SELECT * FROM types";
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(query);
            ArrayList<String> arr=new ArrayList<String>();
            while (rs.next()){
                arr.add((rs.getString("type")).substring(0,(rs.getString("type")).length()-6));
            }
            return arr;
        }
        catch (Exception e){
            System.out.println("Unable to Fetch Values");
            return null;
        }
    }

}
