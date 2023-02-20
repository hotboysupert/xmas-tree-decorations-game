package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameMenu extends JFrame implements ActionListener {
    static int cost = 0; //static because will be changed the value in sellingMenu class
    static int total = 0;//static because will be changed the value in sellingMenu class

    JRadioButton greenRadio = new JRadioButton("Green"); //change treeColor in treePanel to Color.GREEN
    JRadioButton whiteRadio = new JRadioButton("White"); //change treeColor in treePanel to Color.WHITE

    JCheckBox starCheckbox = new JCheckBox("Star");//change value in treePanel.starStatus

    String[] color = {"Red","Green","Blue","White","Pink","Yellow"}; //for ComboBox
    JComboBox ballColorComboBox = new JComboBox(color);//for selecting color for ball

    JTextField captionTextField = new JTextField(20);//to insert caption in tree

    JButton finish = new JButton("Finish step 1 without any change");//finish step 1
    JButton reset = new JButton("Reset");//reset ball decoration but step 1 decoration still be same
    JButton done = new JButton("Done");//sell your tree

    JPanel allPanel = new JPanel(new GridLayout(6,1));//whole panel
    JPanel treeColorPanel = new JPanel(new GridLayout(1,3));//label + radio + radio
    JPanel starTextPanel = new JPanel(new GridLayout(1,3));//star + label + captionTextField
    JPanel ballPanel = new JPanel(new GridLayout(1,2));//label + combobox
    JPanel donePanel = new JPanel(new GridLayout(1,2));//reset + done

    public gameMenu(){
        setTitle("Merry Chirstmas!");
        setSize(600,400);
        setLocationRelativeTo(null);

        greenRadio.setSelected(true);
        greenRadio.addActionListener(this);
        whiteRadio.addActionListener(this);
        starCheckbox.addActionListener(this);
        captionTextField.addActionListener(this);
        finish.addActionListener(this);
        ballColorComboBox.addActionListener(this);
        reset.addActionListener(this);
        done.addActionListener(this); //just add listener to all of it


        reset.setEnabled(false);//prevent user accidentally reset before finishing step 1
        done.setEnabled(false);//prevent user accidentally press done before finishing step 1
        ballColorComboBox.setEnabled(false);//prevent user accidentally use ball combobox before finishing step 1

        treeColorPanel.add(new JLabel("Color of tree",SwingConstants.CENTER));//set text as center of label
        treeColorPanel.add(greenRadio);
        treeColorPanel.add(whiteRadio);

        starTextPanel.add(starCheckbox);
        starTextPanel.add(new JLabel("Your caption"));
        starTextPanel.add(captionTextField);

        ballPanel.add(new JLabel("Ball color",SwingConstants.CENTER));
        ballPanel.add(ballColorComboBox);

        donePanel.add(reset);
        donePanel.add(done);

        allPanel.add(new JLabel("Make the tree for selling! ",SwingConstants.CENTER));
        allPanel.add(treeColorPanel);
        allPanel.add(starTextPanel);
        allPanel.add(finish);
        allPanel.add(ballPanel);
        allPanel.add(donePanel);

        setDefaultCloseOperation(3);//like JFrame.EXIT_ON_CLOSE but short version
        setLayout(new BorderLayout());
        add(allPanel,BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == greenRadio){
            treePanel.treeColor = Color.GREEN;
            whiteRadio.setSelected(false);//deselect white
        }
        else if(e.getSource() == whiteRadio){
            treePanel.treeColor = Color.WHITE;
            greenRadio.setSelected(false);//deselect green
        }
        if(starCheckbox.isSelected()){
            treePanel.starStatus=1;//status 1 mean adding star
        }
        else if(!starCheckbox.isSelected()){
            treePanel.starStatus=0;//status 0 mean not adding star see the code in treePanel
        }
        if(e.getSource() == captionTextField){
            treePanel.caption = captionTextField.getText();//press enter to put your text
            captionTextField.setText("");//then clear the text
        }
        if(e.getSource() == finish){
            treePanel.forAddingListener=5;//to notify treePanel that mouseMotionListener will be started to work

            finish.setEnabled(false);
            greenRadio.setEnabled(false);
            whiteRadio.setEnabled(false);
            captionTextField.setEnabled(false);
            starCheckbox.setEnabled(false); //disable all of step 1

            reset.setEnabled(true);//enable step 2
            done.setEnabled(true);//enable step 2
            ballColorComboBox.setEnabled(true);//enable step 2
        }
        //String[] color = {"Red","Green","Blue","White","Pink","Yellow"};
        if(ballColorComboBox.getSelectedItem().equals("Red")){
            treePanel.ballColor = Color.RED;
        }
        else if(ballColorComboBox.getSelectedItem().equals("Green")){

            treePanel.ballColor = (new Color(0,176,80));//Color.GREEN is not appropriate
        }
        else if(ballColorComboBox.getSelectedItem().equals("Blue")){
            treePanel.ballColor = Color.CYAN;
        }
        else if(ballColorComboBox.getSelectedItem().equals("White")){
            treePanel.ballColor = Color.LIGHT_GRAY;
        }
        else if(ballColorComboBox.getSelectedItem().equals("Pink")){
            treePanel.ballColor = Color.PINK;
        }
        else if(ballColorComboBox.getSelectedItem().equals("Yellow")){
            treePanel.ballColor = Color.YELLOW;
        }
        if(e.getSource() == reset){
            treePanel.ballCount=0; //set ball count to zero
            treePanel.resetStatus=1;//when resetStatus=1 treePanel will clear all ball
        }
        if(e.getSource() == done){ //finish, ready to sell
            if(greenRadio.isSelected()){
                cost+=500; //green color = 500 dollars
            }
            else if(whiteRadio.isSelected()){
                cost+=1000;// white color = 1000 dollars
            }
            if(starCheckbox.isSelected()){
                cost+=1000;// star for 1000 dollars
            }
            cost+=Math.ceil(treePanel.caption.length()*100.1); //each of character worth 100.1 dollars
            cost+=Math.ceil(treePanel.ballCount*250.5);// each of ball worth 250.5 dollars
            this.dispose();//close this menu
            new sellingMenu();//start sellingMenu
        }

    }
}
