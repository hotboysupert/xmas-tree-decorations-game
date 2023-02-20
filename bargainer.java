package customer;

import java.util.Random;

public class bargainer extends customer{
    Random r = new Random();
    double[] range = {0.2,0.5,0.7,0.8,0.9};
    public double discount = range[r.nextInt(range.length)]; //bargainer will lower your income by multiply by this factor

    public bargainer(String name){
        super(name,"Thank you for discount");
    }
}
