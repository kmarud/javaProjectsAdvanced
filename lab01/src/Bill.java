import java.io.Serializable;

/**
 * Created by kamil on 08.10.16.
 */
public class Bill implements Serializable{

    private int id;
    private double amount;

    public Bill(int id, double amount){
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }
}
