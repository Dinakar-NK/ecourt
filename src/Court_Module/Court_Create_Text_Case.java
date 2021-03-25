package Court_Module;

import java.io.FileWriter;
import java.sql.ResultSet;

public class Court_Create_Text_Case {
    static ResultSet rs;
    static ResultSet case_details;

    Court_Create_Text_Case(ResultSet rs,ResultSet case_details){
        Court_Create_Text_Case.rs=rs;
        Court_Create_Text_Case.case_details=case_details;
    }

    String court_create_text_creation(String document){
        try{
            String location="I:\\Bootcamp\\Bootathon\\E-Court\\ProjectFile\\cases"+"\\"+case_details.getInt("court_id")+
                    "\\CNR"+case_details.getInt("case_id")+"\\";
            //System.out.println(location);
            String date=case_details.getString("next_hearing");
            String[] split=date.split("/");
            String text_name=split[0]+split[1]+split[2]+".txt";
            location=location+text_name;
            //System.out.println("before writer");
            FileWriter writer=new FileWriter(location);
            writer.write(document);
            writer.close();
            String base="";
            location=case_details.getInt("court_id")+"?CNR"+case_details.getInt("case_id")+"?"+text_name;
            System.out.println(location);

            return location;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

}
