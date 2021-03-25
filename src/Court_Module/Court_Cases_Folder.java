package Court_Module;

import java.io.File;
import java.sql.ResultSet;

public class Court_Cases_Folder {
    static ResultSet rs;
    static ResultSet case_details;

    Court_Cases_Folder(ResultSet rs,ResultSet case_details){
        Court_Cases_Folder.rs=rs;
        Court_Cases_Folder.case_details=case_details;
    }

    void court_case_folder(){
        try {
            String location = "I:\\Bootcamp\\Bootathon\\E-Court\\ProjectFile\\cases";
            location = location + "\\" + rs.getInt("court_id");
            File Court=new File(location);
            Court.mkdir();
            location=location+"\\CNR"+case_details.getInt("case_id");
            File case_folder=new File(location);
            case_folder.mkdir();
            System.out.println("Folder created");
        }
        catch (Exception e){
            System.out.println("Folder generation"+e);
        }
    }

}
