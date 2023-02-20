package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class treeMenuPanel extends JPanel implements ActionListener {
    Random r = new Random();//for random snowball position
    Timer timer = new Timer(500,this);//for repaint of position of snowball

    treeMenuPanel(){
        timer.start();
        setBackground(Color.BLACK);
        setBounds(0,0,600,400);//set shape of this panel rectangle scale (x,y,width,height)
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,48));
        g.drawString("Merry Christmas !",96,48);//draw merry christmas

        g.setColor(new Color(0,176,80));//draw christmas tree leaf
        int x[] = {300,280,290,260,270,240,250,220,380,350,360,330,340,310,320};
        int y[] = {60,120,120,180,180,240,240,300,300,240,240,180,180,120,120};
        g.fillPolygon(x,y,x.length);

        g.setColor(new Color(127,96,0));//draw for trunk
        g.fillRect(280,300,30,80);

        for(int i=0; i<=300; i++){//random snow ball 300 balls !
            g.setColor(Color.WHITE);
            g.fillOval(r.nextInt(600),r.nextInt(600),5,5);//random only x y position, the size is the same.
        }

    }
    public void actionPerformed(ActionEvent e) {
        repaint();
    }//repaint every 0.5 second, change position of snow
}
