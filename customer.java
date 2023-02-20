package customer;

import java.util.Random;

public class customer {
    public String name,say;
    String[] text = {"I will buy this tree","Nice one","Your tree look nice"}; //random speaking sentence
    Random r = new Random();
    customer(String name,String say){
        this.name = name;
        this.say = say;
    }
    public customer(String name){
        this.say = text[r.nextInt(text.length)];
        this.name = name;
    }

}
