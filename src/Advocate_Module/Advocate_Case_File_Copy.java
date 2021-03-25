package Advocate_Module;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Advocate_Case_File_Copy {
    static int n;
    static ResultSet rs;
    static ResultSet case_details;
    static String data="";
    Advocate_Case_File_Copy(ResultSet rs,ResultSet case_details,int n){
        Advocate_Case_File_Copy.rs=rs;
        Advocate_Case_File_Copy.case_details=case_details;
        Advocate_Case_File_Copy.n=n;
    }
    void case_file_copy_display(){
        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        new Advocate_Case_File_Copy(rs,case_details,n).Map_Text();

        JLabel heading=new JLabel("FILED CASE COPY");
        heading.setBounds(300,10,200,20);
        f.add(heading);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JTextArea area=new JTextArea();
        area.setBounds(40,50,500,350);
        scrollPane.setBounds(40,50,650,420);
        scrollPane.getViewport().add(area);
        f.add(scrollPane);
        area.setText(data);
        area.setEditable(false);


        //back button
        JButton back_button=new JButton("BACK");
        back_button.setBounds(5,5,80,20);
        f.add(back_button);
        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                try {
                    new Advocate_Case_History(rs,"CNR"+case_details.getInt("case_id"),n).advocate_case_history_display();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        f.setVisible(true);
    }

    void Map_Text(){
        try {
            data="";
            String location="I:\\Bootcamp\\Bootathon\\E-Court\\ProjectFile\\cases"+"\\"+case_details.getInt("court_id")+
                    "\\CNR"+case_details.getInt("case_id")+"\\case_copy.txt";
            File read=new File(location);
            Scanner reader=new Scanner(read);
            while(reader.hasNextLine()){
                data = data + reader.nextLine() +"\n";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
