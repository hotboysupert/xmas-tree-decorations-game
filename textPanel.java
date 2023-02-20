package menu;

import game.gameMenu;
import game.treePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class textPanel extends JPanel implements ActionListener, MouseMotionListener {
    Timer timer = new Timer(500,this);//set delay for appearance of text
    private int count = 0;//for rhythm
    public static String title = "";//text in loading and also use for setTitle of this JFrame

    String text1 = "";//text in line 1
    String text2 = "";//text in line 2

    Random r = new Random();
    String[] textChoice = {"Silent Night","Joy to the world","We wish you a merry christmas","Jingle bell"};//4 songs!

    textPanel(){
        timer.start();
        title = textChoice[r.nextInt(4)];//random the song and title of this JFrame

    }
    protected void paintComponent(Graphics g){
        g.setColor(Color.BLACK);//set background to black
        g.fillRect(0,0,600,200);

        //set Text and position of text
        g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,32));
        g.setColor(Color.WHITE);
        g.drawString(text1,20,50);
        g.drawString(text2,20,100);

        //when count = 14 or 7 second, mean loading game is successful
        if(count==14){
            addMouseMotionListener(this);//change color when mouse touch it
            addMouseListener(new clickInArea());//able to click button, the button is made from paintComponent instead of JButton
            timer.stop();//no need to continue to count, the game is about to start!
            g.setColor(Color.WHITE);//just making button from paintComponent
            g.fillRect(550,0,50,200);

            g.setColor(Color.RED);//button symbol
            int x[] = {560,560,580};
            int y[] = {50,90,70};
            g.fillPolygon(x,y,3);
        }
    }
    public void actionPerformed(ActionEvent e){
        Graphics g = getGraphics();
        count+=1;
        if(title.equals("Silent Night")){//set delay for song lyric of Silent Night
            if(count == 1){text1+="Si";}
            else if(count == 3){text1+="lent";}
            else if(count == 4){text1+=" Night.";}
            else if(count == 8){text2+="Ho";}
            else if(count == 10){text2+="ly";}
            else if(count == 11){text2+=" Night.";}
        }
        else if(title.equals("Joy to the world")){//set delay for song lyric
            if(count==1){text1+="Joy ";}
            else if(count==3){text1+="to ";}
            else if(count==5){text1+="the ";}
            else if(count==6){text1+="world ";}
            else if(count==8){text2+="the ";}
            else if(count==10){text2+="lord ";}
            else if(count==12){text2+="is ";}
            else if(count==14){text2+="come.";}
        }
        else if(title.equals("We wish you a merry christmas")){//set delay for song lyric
            if(count==1){text1+="We";}
            else if(count==3){text1+=" wish";}
            else if(count==5){text1+=" you";}
            else if(count==6){text1+=" a";}
            else if(count==7){text1+=" merry";}
            else if(count==8){text1+=" christmas";}
            else if(count==10){text2+="and a";}
            else if(count==11){text2+=" happy";}
            else if(count==12){text2+=" new year";}
        }
        else{//only one song left, Jingle bell
            if(count==1){text1+="Jin";}
            else if(count==2){text1+="gle";}
            else if(count==3){text1+=" Bell";}
            else if(count==5){text1+=" Jin";}
            else if(count==6){text1+="gle";}
            else if(count==7){text1+=" Bell";}
            else if(count==9){text2+="Jin";}
            else if(count==10){text2+="gle";}
            else if(count==11){text2+=" all";}
            else if(count==13){text2+=" the";}
            else if(count==14){text2+=" way.";}
        }
        repaint();

    }

    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {
        Graphics g = getGraphics();
        if(e.getX()>=550 ){//when mouse move to here change button to yellow color
            g.setColor(Color.YELLOW);
            g.fillRect(550,0,50,200);

            g.setColor(Color.RED);
            int x[] = {560,560,580};
            int y[] = {50,90,70};
            g.fillPolygon(x,y,3);
        }
        else{//when mouse is away, change back to white
            g.setColor(Color.WHITE);
            g.fillRect(550,0,50,200);

            g.setColor(Color.RED);
            int x[] = {560,560,580};
            int y[] = {50,90,70};
            g.fillPolygon(x,y,3);
        }
    }
}
class clickInArea implements MouseListener{ //sub class to check if your mouse in rectangle button area

    public void mouseClicked(MouseEvent e) {
        if(e.getX()>=550){//the width of rectangle is 550, and it cover all y-axis, just mention only x.
            new gameMenu(); //start gameMenu show decorate menu
            new treePanel();//start treePanel show your tree
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}

