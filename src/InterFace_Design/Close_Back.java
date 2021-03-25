package InterFace_Design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Close_Back extends JLabel implements MouseListener {

    static int n;
    public Close_Back(int n){
        super();
        Close_Back.n =n;
        if(n==1){
            setText("x");
            setFont(new Font("Calibri",Font.PLAIN,20));
            setBounds(720,5,20,20);
            setForeground(Color.lightGray);
        }
        else{
            setText("BACK");
            setBounds(5,5,80,20);
            setForeground(Color.gray);
        }
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { setForeground(Color.BLACK); }

    @Override
    public void mouseReleased(MouseEvent e) { setForeground(Color.lightGray); }

    @Override
    public void mouseEntered(MouseEvent e) { setForeground(Color.BLACK); }

    @Override
    public void mouseExited(MouseEvent e) { setForeground(Color.lightGray); }
}
