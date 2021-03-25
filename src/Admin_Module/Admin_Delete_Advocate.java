package Admin_Module;
import DatabaseConnector_TableCreator.Connector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import InterFace_Design.*;
import InterFace_Design.Button;

public class Admin_Delete_Advocate {

    static String advocate_id="";
    static int n=0;

    //This function describes the structure of delete advocate page
    void delete_advocate_display(){
        JFrame f=new JFrame();
        f.setBounds(420,170,740,520);
        f.setUndecorated(true);
        f.setLayout(null);
        f.setResizable(false);

        Close_Back close=new Close_Back(1);
        f.add(close);
        close.addMouseListener(new Close_Back(1){
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        //delete advocate heading
        JLabel delete_advocate_heading=new JLabel("REMOVE ADVOCATE");
        delete_advocate_heading.setBounds(300,150+50,150,20);
        f.add(delete_advocate_heading);

        //advocate id display
        JLabel id_display=new JLabel("Advocate ID");
        id_display.setBounds(200,190+50,100,20);
        f.add(id_display);

        //advocate id text field
        JTextField id_get=new JTextField();
        id_get.setBounds(300,190+50,200,20);
        f.add(id_get);

        //delete button
        Button delete_button=new Button();
        delete_button.setBounds(310,230+50,100,20);
        f.add(delete_button);
        delete_button.setText("REMOVE");
        delete_button.setRounded(true);
        delete_button.setBorder(null);
        delete_button.setBackground(Color.WHITE);
        delete_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                advocate_id=id_get.getText();
                if(advocate_id.equals("")){
                    JOptionPane.showMessageDialog(f, "Advocate ID cannot be Empty", "status", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    int a = JOptionPane.showConfirmDialog(f, "Are you sure, Do you want to Delete?");
                    if (a == JOptionPane.YES_OPTION) {
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        new Admin_Delete_Advocate().delete_advocate_database();
                        if(n==1){
                            JOptionPane.showMessageDialog(f, " Advocate record has been Deleted");
                            f.dispose();
                            new Admin_Delete_Advocate().delete_advocate_display();
                        }
                        else{
                            JOptionPane.showMessageDialog(f, " Advocate record Not Found");
                            f.dispose();
                            new Admin_Delete_Advocate().delete_advocate_display();
                        }
                    }
                }

            }
        });


        Close_Back back=new Close_Back(2);
        f.add(back);
        back.addMouseListener(new Close_Back(2) {
            @Override
            public void mouseClicked(MouseEvent e) {
                f.dispose();
                new Admin_Home().home_display();
            }
        });




        f.getContentPane().setBackground(Color.WHITE);
        ImageIcon img=new ImageIcon("I:\\Bootcamp\\Bootathon\\E-Court\\ProjectFile\\3.png");
        JLabel background=new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,125,740,250);
        f.add(background);

        ImageIcon rimg=new ImageIcon("I:\\Bootcamp\\Bootathon\\E-Court\\ProjectFile\\Chennai_High_Court.png");
        JLabel rbackground=new JLabel("",rimg,JLabel.CENTER);
        rbackground.setBounds(0,0,740,900);
        f.add(rbackground);


        f.setVisible(true);
    }

    //This Function establishes the connection to advocates and deleting the advocate records
    void delete_advocate_database(){
        Connection con=new Connector().establish_connection();
        try{
            String query="DELETE FROM advocates WHERE advocate_id=?";
            PreparedStatement smt=con.prepareStatement(query);
            smt.setInt(1,Integer.parseInt(advocate_id));
            n=smt.executeUpdate();
            smt.close();
            con.close();

        }
        catch (Exception e){
            System.out.println("Deletion of Advocate Failed");
        }
    }
}
