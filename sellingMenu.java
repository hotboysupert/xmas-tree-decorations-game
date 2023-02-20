package game;

import javax.swing.*;
import customer.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class sellingMenu extends JFrame implements ActionListener {
    customer[] list = {new bargainer("Fent"),new bargainer("Panya"),new customer("Blue"),
    new customer("AJ"),new customer("Bank"),new customer("Seasar"),new santa(),new thief("The Oaker"),
    new thief("The burgler"),new customer("Aya")};//build some set of customer with diversity
    //explicit casting

    Timer countDown = new Timer(1000,this);//to know the time to close the shop
    int time = 10;//10 second before close the shop
    int sell = 0;//total cost that make this tree
    int bonus=0;//the bonus or profit for sell price 5%,10%,20%, or no profit.
    int willBuy = 0;//Check random status if 1 mean customer buy it 0 mean no one buy it.

    //bonus button
    JButton ten = new JButton("10%");
    JButton five = new JButton("5%");
    JButton zero = new JButton("No bonus");
    JButton twenty = new JButton("20%");

    JPanel bonusPanel = new JPanel(new GridLayout(1,3));

    //Label show cost of the tree
    JLabel rawCost = new JLabel("The cost is "+String.valueOf(gameMenu.cost)+" Dollars. Add some profit!",SwingConstants.CENTER);
    JLabel sellCost = new JLabel();//Label show the sell price after adding profit.
    JLabel timeLabel = new JLabel();//Label show how much time is left before closing the shop.
    Random r = new Random();//use for random
    sellingMenu(){

        setTitle("Selling!");
        setLayout(new GridLayout(4,1));
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);

        sellCost.setText("Selling in: "+sell+" Dollars."+" Current earn: "+gameMenu.total);//selling price and total earning
        bonusPanel.add(zero);
        bonusPanel.add(five);
        bonusPanel.add(ten);
        bonusPanel.add(twenty);

        //add ActionListener of bonus
        zero.addActionListener(this);
        five.addActionListener(this);
        ten.addActionListener(this);
        twenty.addActionListener(this);

        add(rawCost);
        add(bonusPanel);
        add(sellCost);
        add(timeLabel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == countDown){
            time-=1;//time will decrease in every 1 second
            timeLabel.setText("Shop will close in "+String.valueOf(time));//change text of timeLabel 10 -> 9 -> 8 -> ...

            if(bonus == 0){
                int[] buy = {1,1,1,1,1,1,1,1,1,0}; //90% will buy
                willBuy = buy[r.nextInt(10)];//random 1 or 0 1 mean buy
            }
            else if(bonus ==5){
                int[] buy = {1,1,1,1,1,1,0,0,0,0}; //60% will buy
                willBuy = buy[r.nextInt(10)];//random 1 or 0 1 mean buy
            }
            else if(bonus == 10){
                int[] buy = {1,1,1,1,0,0,0,0,0,0}; //40% will buy
                willBuy = buy[r.nextInt(10)];//random 1 or 0 1 mean buy
            }
            else if(bonus == 20){
                int[] buy = {0,0,0,1,0,0,0,0,0,0}; //10% will buy
                willBuy = buy[r.nextInt(10)];
            }
            if(willBuy == 1){
                customer c = list[r.nextInt(list.length)];//random customer in list
                JOptionPane.showMessageDialog(null,"I am "+c.name+"\n"+c.say);//show some popup
                if(c instanceof bargainer){//check for instance
                    bargainer d = (bargainer)c;//implicit casting
                    JOptionPane.showMessageDialog(null,"You have been bargained!");//bargainer!
                    gameMenu.total+=sell*d.discount;}//add up your earning
                else if(c instanceof santa){//check for instance
                    santa d =(santa)c;//implicit casting
                    JOptionPane.showMessageDialog(null,"Santa clause is coming to town!");//santa !
                    gameMenu.total+=sell*(d.bonus);//add up your earning
                }
                else if(c instanceof thief){//check for instance
                    thief d = (thief)c;//implicit casting
                    JOptionPane.showMessageDialog(null,"It is a thief!");//no....
                    gameMenu.total-=(sell+d.steal);//lose your earning
                }
                else{
                    gameMenu.total+=gameMenu.cost;//add up your earning
                }
                gameMenu.cost = 0;//reset the cost when shopping is ended
                sellCost.setText("Selling in: "+sell+" Dollars."+" Current earn: "+gameMenu.total);
                countDown.stop();//stop
                time=10;//set time back to 10
                bonus=0;//set bonus back to 0
                dispose();//close this menu
                new gameMenu();//start to make new tree again
            }

            if(time == 0){//in case that no one buy your tree
                countDown.stop();
                JOptionPane.showMessageDialog(null,"Close!");//time up! close!
                gameMenu.total-=sell;//you could not sell anything! waste your resources!         
                bonus=0;//set bonus back to 0
                dispose();//close this menu
                new gameMenu();

            }

        }
        if(e.getSource() == zero){//sell at the cost price
            bonus=0;
            sell = gameMenu.cost;
            sellCost.setText("Selling in: "+sell+" Dollars."+" Current earn: "+gameMenu.total);
            countDown.start();
            zero.setEnabled(false);//prevent user that accidentally press profit button again.
            five.setEnabled(false);
            twenty.setEnabled(false);
            ten.setEnabled(false);
        }
        else if(e.getSource() == five){//5% profit
            bonus=5;
            sell = (int)Math.floor(gameMenu.cost*105/100);
            sellCost.setText("Selling in: "+sell+" Dollars."+" Current earn: "+gameMenu.total);
            countDown.start();//start to count for closing time
            zero.setEnabled(false);//prevent user that accidentally press profit button again.
            five.setEnabled(false);
            ten.setEnabled(false);
            twenty.setEnabled(false);

        }
        else if(e.getSource() == ten){
            bonus=10;
            sell = (int)Math.floor(gameMenu.cost*110/100);
            sellCost.setText("Selling in: "+sell+" Dollars."+" Current earn: "+gameMenu.total);
            countDown.start();//start to count for closing time
            zero.setEnabled(false);//prevent user that accidentally press profit button again.
            five.setEnabled(false);
            ten.setEnabled(false);
            twenty.setEnabled(false);
        }
        else if(e.getSource() == twenty){
            bonus=20;
            sell = (int)Math.floor(gameMenu.cost*120/100);
            sellCost.setText("Selling in: "+sell+" Dollars."+" Current earn: "+gameMenu.total);
            countDown.start();//start to count for closing time
            zero.setEnabled(false);//prevent user that accidentally press profit button again.
            five.setEnabled(false);
            ten.setEnabled(false);
            twenty.setEnabled(false);
        }
    }
}
