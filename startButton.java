package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startButton extends JButton implements ActionListener {//it is button therefore must inherit from JButton

    Timer timer = new Timer(500,this);//for control blink
    int count = 0;//even = green, odd = red

    startButton(String text){//1 argument change only text
        this(text,Color.WHITE,new Font(Font.SANS_SERIF,Font.BOLD,36));}

    startButton(String text, Color color){this(text,color,new Font(Font.SANS_SERIF,Font.BOLD,36));}
    //2 argument change only text and color

    startButton(String text, Color color, Font font){//3 argument for change text and color and font
        setBackground(Color.GREEN);
        setText(text);
        setForeground(Color.WHITE);
        setFont(font);
        timer.start();

    }
    public void actionPerformed(ActionEvent e) {
        count+=1;
        if(count%2 == 0){this.setBackground(Color.GREEN);}//even change background of JButton to green
        else{this.setBackground(Color.RED);}//odd change background of JButton to red
    }
}
