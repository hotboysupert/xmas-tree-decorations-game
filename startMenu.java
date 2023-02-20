package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startMenu extends JFrame implements ActionListener {
    startButton start = new startButton("Start !");
    JPanel startGrid = new JPanel(new GridLayout(3,3));

    startMenu(){
        setTitle("Merry Chirstmas!");
        setSize(600,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        start.addActionListener(this);//only this component that added listener

        startGrid.setBackground(Color.BLACK);
        startGrid.setBounds(0,400,600,200);
        startGrid.add(new JLabel());
        startGrid.add(new JLabel());
        startGrid.add(new JLabel());
        startGrid.add(new JLabel());
        startGrid.add(start);
        startGrid.add(new JLabel());
        startGrid.add(new JLabel());
        startGrid.add(new JLabel());
        startGrid.add(new JLabel()); //adding blank Label to set position of start button to the center.

        add(startGrid,BorderLayout.SOUTH);
        add(new treeMenuPanel(), BorderLayout.CENTER);
        setDefaultCloseOperation(3);
        setVisible(true);
    }
    public static void main(String[] args){
        new startMenu();
    }//run program in this method!

    public void actionPerformed(ActionEvent e) {//when press start button
        dispose();//close this menu
        new loadFrame();//start loading menu JFrame
    }
}
