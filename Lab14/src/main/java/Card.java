import java.io.Serializable;

public class Card implements Serializable {

    private int number;
    private String owner;
    private double sum;

    public Card(){}

    public Card(int number, String owner, double sum) {
        this.number = number;
        this.owner = owner;
        this.sum = sum;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
