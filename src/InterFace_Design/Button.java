package InterFace_Design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Button extends JButton implements MouseListener{
    private boolean rounded;
    private boolean background;
    private boolean line;
    private boolean entered;
    private boolean pressed;

    private Color entered_color;
    private Color pressed_color;
    private Color gradient_background;
    private Color gradient_line;
    private Color line_color;

    public Button(){
        super();
        addMouseListener(this);
        rounded=false;
        background=true;
        line=true;
        entered=false;
        pressed=false;

        entered_color= getBackground().brighter();
        pressed_color= getBackground().darker();
        line_color=Color.BLACK;

        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    public Button(String text){
        super();
        addMouseListener(this);
        rounded=false;
        background=true;
        line=true;
        entered=false;
        pressed=false;

        entered_color= getBackground().brighter();
        pressed_color= getBackground().darker();
        line_color=Color.BLACK;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setText(text);
        setRounded(true);
        setBorder(null);
        setBackground(Color.WHITE);

    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        entered_color= bg.brighter();
        pressed_color= bg.darker();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2= (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        Shape s= (rounded) ? new RoundRectangle2D.Float(1,1,getWidth()-2,getHeight()-2,getHeight()-2,getHeight()-2)
                : new Rectangle2D.Float(1,1,getWidth()-2,getHeight()-2);

        if(background || (pressed && !background)){
            if(gradient_background==null){
                g2.setColor(color());
            }
            else {
                GradientPaint paint=new GradientPaint(0,0,getBackground(),getWidth(),getHeight(),gradient_background);
                g2.setPaint(paint);
            }
            g2.fill(s);
        }

        if(line){
            if(gradient_line==null){
                g2.setColor(isEnabled() ? line_color : new Color(204,204,204));
            }
            else{
                GradientPaint paint=new GradientPaint(0,0,line_color,getWidth(),getHeight(),gradient_line);
                g2.setPaint(paint);
            }
            g2.draw(s);
        }
        super.paintComponent(g);
    }

    private Color color(){
        if(!isEnabled()){
            return new Color(204,204,204);
        }
        Color temp= getBackground();
        if(pressed) return pressed_color;
        if(entered) return entered_color;
        return temp;
    }

    public void setRounded(boolean rounded) {
        this.rounded = rounded;
    }

    public void setBackground(boolean background) { this.background = background; }

    public void setLine(boolean line) {
        this.line = line;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public void setEntered_color(Color entered_color) {
        this.entered_color = entered_color;
    }

    public void setGradient_background(Color gradient_background) {
        this.gradient_background = gradient_background;
    }

    public void setGradient_line(Color gradient_line) {
        this.gradient_line = gradient_line;
    }

    public void setLine_color(Color line_color) {
        this.line_color = line_color;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        setBackground(new Color(223, 229, 240));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setBackground(Color.WHITE);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(new Color(223, 229, 240));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(Color.WHITE);
    }
}
