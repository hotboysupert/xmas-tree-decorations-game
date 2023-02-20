package customer;

import java.util.Random;

public class thief extends customer{
    Random r = new Random();
    double[] range = {100,500,1000,250,700};
    public double steal = range[r.nextInt(range.length)]; //you will lose extra money when encounter thief.
    public thief(String name){
        super(name,"HAHAHAHAAHAH");
    }
}
