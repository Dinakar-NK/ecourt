package InterFace_Design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Introduction  {
    static JProgressBar bar;
    static JLabel head;
    static JLabel quote;
    static JPanel line;
    static JFrame introduction=new JFrame();


    public void introduction_display(){

        introduction.setBounds(420,170,740,520);
        introduction.setUndecorated(true);
        introduction.setLayout(null);
        introduction.setResizable(false);

        Close_Back close=new Close_Back(1);
        introduction.add(close);
        close.addMouseListener(new Close_Back(1){
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        JPanel progress=new JPanel();
        progress.setBounds(0,460,740,40);
        progress.setBackground(Color.WHITE);
        progress.setLayout(new FlowLayout());

        head=new JLabel();
        head.setFont(new Font("Arial",Font.BOLD,60));
        head.setBounds(20,200,400,80);
        head.setForeground(new Color(38, 38, 38));
        introduction.add(head);

        line=new JPanel();
        line.setBounds(20,280,400,1);
        line.setBackground(new Color(245, 0, 0));
        introduction.add(line);

        quote=new JLabel("Makes Documentation Easier");
        quote.setFont(new Font("Arial", Font.ITALIC,20));
        quote.setBounds(20,280,800,40);
        introduction.add(quote);

        bar=new JProgressBar(0,100);
        bar.setValue(0);
        bar.setPreferredSize(new Dimension(740,5));
        bar.setBorderPainted(false);
        bar.setBackground(new Color(255, 127, 17));
        bar.setForeground(new Color(255, 27, 28));
        progress.add(bar);
        introduction.add(progress);

        introduction.getContentPane().setBackground(Color.WHITE);
        introduction.setVisible(true);



        JLabel company=new JLabel("A RE-BOOT 33 LIMITED");
        company.setBounds(300,500,200,20);
        company.setFont(new Font("Arial", Font.PLAIN,10));
        company.setForeground(Color.GRAY);
        introduction.add(company);


        new Introduction().fill();
        try{
            introduction.dispose();
            Thread.sleep(100);

        }
        catch (Exception e){

        }

    }

    void fill(){
        int i=1;
        try {
            while (i<=100){
                bar.setValue(i);
                if(i<=33){
                    head.setText("Easy Court");
                    quote.setText("Makes Documentation Easier");
                }
                else if(i>=33 && i<=66){
                    head.setText("Advocates..!");
                    quote.setText("No More Written Files");
                }
                else{
                    head.setText("Users");
                    quote.setText("Find Your Perfect Advocate and Get Complete Case Documents");
                }
                Thread.sleep(60);
                i=i+1;
            }
        }
        catch (Exception e){

        }
    }



}
