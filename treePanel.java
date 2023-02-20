package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class treePanel extends JPanel implements ActionListener, MouseListener {
    static Color treeColor = Color.GREEN;//static because the value is changed by gameMenu class
    static Color ballColor = Color.WHITE;//static because the value is changed by gameMenu class
    static int starStatus = 0;//static because the value is changed by gameMenu class
    static int resetStatus=0;//static because the value is changed by gameMenu class
    static int forAddingListener=0;//static because the value is changed by gameMenu class
    static int ballCount = 0;//static because the value is changed by gameMenu class
    static String caption = "My Christmas Tree";//static because the value is changed by gameMenu class

    Timer first_timer = new Timer(250,this);//use for repaint when color or text is changed
    Timer second_timer = new Timer(250,this);//use for repaint when reset ball decoration, cannot use only first_timer
    //because it will clear your current decoration in step1

    public treePanel(){
        first_timer.start();//for repaint
        JFrame f = new JFrame("Tree");
        f.setSize(200,400);
        f.add(this);
        f.setDefaultCloseOperation(3);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    protected void paintComponent(Graphics g){
        g.setColor(Color.BLACK);//background of this panel
        g.fillRect(0,0,200,400);
        g.setColor(treeColor);

        int x[] = {100,80,90,60,70,40,50,20,180,150,160,130,140,110,120};
        int y[] = {60,120,120,180,180,240,240,300,300,240,240,180,180,120,120};
        g.fillPolygon(x,y,x.length);//draw christmas tree leaf

        g.setColor(new Color(127,96,0));
        g.fillRect(80,300,30,80);//draw trunk

        //prevent overlapping of caption by replacing old text with black rectangle
        g.setColor(Color.BLACK);
        g.fillRect(0,0,200,20);
        g.setColor(Color.WHITE);
        g.drawString(caption,20,20);

        //put star
        if(starStatus == 1){
            g.setColor(Color.YELLOW);
            int xStar[] = {100,110,130,115,120,100,80,85,70,90};
            int yStar[] = {20,30,30,50,70,60,70,50,30,30};
            g.fillPolygon(xStar,yStar,xStar.length);
        }
        //if starStatus is not 1 it will not draw anything.

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == first_timer){
            repaint(); //to repaint when something is changed such as tree color, text, ..
            if(forAddingListener == 5){//just for addingListener, when the finish step 1 button is pressed.
                addMouseListener(this);//if adding mouselistener before finishing step 1,it will be very hard to control reset status.
            }
        }
        if(e.getSource() == second_timer){
            if(resetStatus == 1){
                repaint();//repaint again
                resetStatus=0;//set the value back to 0. Recycle it!
            }
        }

    }

    public void mouseClicked(MouseEvent e) {//draw the ball with radius 3 unit
        Graphics g = getGraphics();
        g.setColor(ballColor);//color is from combobox in gameMenu
        g.fillOval(e.getX()-3,e.getY()-3,6,6);
        ballCount+=1;
        first_timer.stop();//stop this
        second_timer.start();//start this timer to control for ball decoration
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}
