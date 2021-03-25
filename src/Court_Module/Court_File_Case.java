package Court_Module;

import DatabaseConnector_TableCreator.Connector;
import DropDown_Values.ComboModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Court_File_Case {
    static ResultSet rs;
    static ResultSet case_details;
    static String case_type="";
    static String party_name="";
    static String party_phone="";
    static String party_email_send="";
    static String advocate_id="";
    static String advocate_name="";
    static String case_password="";
    static String hearing_date="";
    static String case_description="";
    static String file_date="";
    static int n;

    Court_File_Case(ResultSet rs){
        Court_File_Case.rs=rs;
    }

    void court_file_case_display(){

        JFrame f=new JFrame();
        f.setLayout(null);
        f.setResizable(false);
        f.setBounds(420,170,740,520);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JLabel disp=new JLabel("FILE CASE");
        disp.setBounds(300+30,20,80,20);
        f.add(disp);

        JLabel casetype=new JLabel("CASE TYPE");
        casetype.setBounds(220-200,80+5,120,20);
        f.add(casetype);

        //advocate type combo box
        DefaultComboBoxModel type_mode=new ComboModel().case_type_model();
        JComboBox type_get=new JComboBox(type_mode);
        type_get.setBounds(330-200,80+5,200,20);
        f.add(type_get);

        JLabel partyname=new JLabel("PARTY NAME");
        partyname.setBounds(220-200,115+5,120,20);
        f.add(partyname);

        JTextField get_partyname=new JTextField();
        get_partyname.setBounds(330-200,115+5,200,20);
        f.add(get_partyname);

        JLabel partyphone=new JLabel("PARTY PHONE");
        partyphone.setBounds(220-200,150+5,120,20);
        f.add(partyphone);

        JTextField get_partyphone=new JTextField();
        get_partyphone.setBounds(330-200,150+5,200,20);
        f.add(get_partyphone);

        JLabel party_email=new JLabel("PARTY EMAIL");
        party_email.setBounds(220-200,185+5,120,20);
        f.add(party_email);

        JTextField get_party_email=new JTextField();
        get_party_email.setBounds(330-200,185+5,200,20);
        f.add(get_party_email);

        JLabel adv_id=new JLabel("ADVOCATE ID");
        adv_id.setBounds(220-200,220+5,120,20);
        f.add(adv_id);

        JTextField get_adv_id=new JTextField();
        get_adv_id.setBounds(330-200,220+5,200,20);
        f.add(get_adv_id);

        JLabel adv_name=new JLabel("ADVOCATE NAME");
        adv_name.setBounds(220-200,255+5,120,20);
        f.add(adv_name);

        JTextField get_adv_name=new JTextField();
        get_adv_name.setBounds(330-200,255+5,200,20);
        get_adv_name.setEditable(false);
        f.add(get_adv_name);

        JLabel password=new JLabel("PASSWORD");
        password.setBounds(220-200,290+5,120,20);
        f.add(password);

        JPasswordField get_password=new JPasswordField();
        get_password.setBounds(330-200,290+5,200,20);
        f.add(get_password);


        JLabel re_password=new JLabel("RE-PASSWORD");
        re_password.setBounds(220-200,325+5,120,20);
        f.add(re_password);

        JPasswordField get_re_password=new JPasswordField();
        get_re_password.setBounds(330-200,325+5,200,20);
        f.add(get_re_password);

        JLabel hear_date=new JLabel("Hearing Date");
        hear_date.setBounds(220-200,360+5,120,20);
        f.add(hear_date);

        JTextField get_hear_date=new JTextField();
        get_hear_date.setBounds(330-200,360+5,200,20);
        f.add(get_hear_date);

        JLabel filed_date=new JLabel("Filing Date");
        filed_date.setBounds(220-200,395+5,120,20);
        f.add(filed_date);

        JTextField get_file_date=new JTextField();
        get_file_date.setBounds(330-200,395+5,200,20);
        f.add(get_file_date);

//        JLabel DOB_hint=new JLabel("(DD/MM/YYYY)");
//        DOB_hint.setBounds(530-200,310,100,20);
//        DOB_hint.setForeground(Color.gray);
//        DOB_hint.setFont(new Font("Verdana", Font.PLAIN, 10));
//        f.add(DOB_hint);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JTextArea area=new JTextArea("Case Description");
        area.setBounds(40,50,500,350);
        scrollPane.setBounds(340,80,370,350);
        scrollPane.getViewport().add(area);
        f.add(scrollPane);


        JButton add_case =new JButton("FILE CASE");
        add_case.setBounds(280,440,200,20);
        f.add(add_case);

        JButton back_button=new JButton("BACK");
        back_button.setBounds(5,5,80,20);
        f.add(back_button);
        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Court_Home(rs).court_home_display();
            }
        });

        get_adv_id.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                get_adv_name.setText(new Court_Get_Advocate().get_advocate(get_adv_id.getText()));
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });



        f.setVisible(true);

        add_case.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                case_type=""+type_get.getSelectedItem();
                party_name=get_partyname.getText();
                party_phone=get_partyphone.getText();
                party_email_send=get_party_email.getText();
                advocate_id=get_adv_id.getText();
                advocate_name=get_adv_name.getText();
                case_password=new String(get_password.getPassword());
                case_description=area.getText();
                file_date=get_file_date.getText();
                hearing_date=get_hear_date.getText();
                String email_validation="^[a-zA-Z0-9_+&*-]+(?:\\."+
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                        "A-Z]{2,7}$";
                String date_validation="^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
                if(party_name.equals("")){
                    JOptionPane.showMessageDialog(f, "Party Name cannot be Empty", "status", JOptionPane.WARNING_MESSAGE);
                }
                else if(!(party_email_send.matches(email_validation))){
                    JOptionPane.showMessageDialog(f, "Invalid Email ID", "status", JOptionPane.WARNING_MESSAGE);
                }
                else if(advocate_id.equals("")){
                    JOptionPane.showMessageDialog(f, "Advocate Name cannot be Empty", "status", JOptionPane.WARNING_MESSAGE);
                }
                else if(!(party_phone.length()==10)){
                    JOptionPane.showMessageDialog(f, "Invalid Phone", "status", JOptionPane.WARNING_MESSAGE);
                }
                else if(!(party_phone.matches("^[6-9]\\d{9}$"))){
                    JOptionPane.showMessageDialog(f, "Invalid Phone", "status", JOptionPane.WARNING_MESSAGE);
                }
                else if((case_password.equals(""))){
                    JOptionPane.showMessageDialog(f, "Password cannot be empty", "status", JOptionPane.WARNING_MESSAGE);
                }
                else if(!(case_password.equals(new String(get_re_password.getPassword())))){
                    JOptionPane.showMessageDialog(f, "Re-Password Does not Match", "status", JOptionPane.WARNING_MESSAGE);
                    get_re_password.setText("");
                }
                else if(!(hearing_date.matches(date_validation))){
                    JOptionPane.showMessageDialog(f, "Invalid Date Format\n Date Format DD/MM/YYYY", "status", JOptionPane.WARNING_MESSAGE);
                }
                else if(!(file_date.matches(date_validation))){
                    JOptionPane.showMessageDialog(f, "Invalid Date Format", "status", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    int a=JOptionPane.showConfirmDialog(f,"Are you sure, Do you want to File Case?");
                    if(a==JOptionPane.YES_OPTION){
                        new Court_File_Case(rs).court_file_case_database();
                        JOptionPane.showMessageDialog(f,"Case has been filed");
                        f.dispose();
                        new Court_Cases_Folder(rs,case_details).court_case_folder();
                        new Case_Copy_Format(rs,case_details,case_description,file_date,party_email_send).format_copy();
                        new Court_Case_Copy(rs,case_details,1).court_case_copy_display();
                    }
                }
            }
        });



    }

    void court_file_case_database(){

        Connection con=new Connector().establish_connection();
        try{
            String query = "INSERT INTO cases(court_id,case_type,party_name,party_phone,advocate_name,advocate_id,next_hearing,case_password,result) " +
                    "VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement smt=con.prepareStatement(query);
            smt.setInt(1,rs.getInt("court_id"));
            smt.setString(2,case_type);
            smt.setString(3,party_name);
            smt.setString(4,party_phone);
            smt.setString(5,advocate_name);
            smt.setInt(6,Integer.parseInt(advocate_id));
            smt.setString(7,hearing_date);
            smt.setString(8,case_password);
            smt.setString(9,"ON PROGRESS");
            n=smt.executeUpdate();
            query="SELECT * FROM cases WHERE case_id=(SELECT max(case_id) FROM cases)";
            Statement smt1=con.createStatement();
            case_details=smt1.executeQuery(query);
            case_details.next();
            System.out.println(case_details.getInt("case_id"));
            query="SELECT * FROM advocates WHERE advocate_id="+Integer.parseInt(advocate_id);
            ResultSet adv=smt.executeQuery(query);
            adv.next();
            int tot=adv.getInt("advocate_total_cases")+1;
            query="UPDATE advocates SET advocate_total_cases="+tot+" WHERE advocate_id="+Integer.parseInt(advocate_id);
            n=smt.executeUpdate(query);
            System.out.println(n);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
