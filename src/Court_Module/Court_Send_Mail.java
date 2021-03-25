package Court_Module;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static javax.mail.Transport.*;

public class Court_Send_Mail {

    public static void sendMail(String recipient,String data){
        System.out.println("Preparing to Send email");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String MyEmail="lokeshvarsakthi@gmail.com";
        String MyPassword="lokeshanirudh";

        Session session= Session.getInstance(properties,new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(MyEmail,MyPassword);
            }
        });

        Message message= prepareMessage(session, MyEmail,recipient,data);
        try {
            send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Message Sent Successfully");
    }

    private static Message prepareMessage(Session session, String MyEmail, String recipient,String data) {
        Message message=new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(MyEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
            message.setSubject("Your Case Has Been Filed");
            message.setText(data);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    void get_mail(String[] mails,String data){
        for(int i=0;i<4;++i){
            if(mails[i]!=null){
                new Court_Send_Mail().sendMail(mails[i],data);
                System.out.println("counter"+i);
            }
        }
    }
}
