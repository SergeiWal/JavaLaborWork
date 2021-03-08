package bstu.fit.walko.tender;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Bid extends Thread{
    private int bidId;
    private int price;
    private CyclicBarrier barrier;


    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Bid(int id, int price, CyclicBarrier barrier)
    {
        super();
        this.bidId = id;
        this.price = price;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Клиент №" + this.bidId + " определил цену " + this.price);
            Thread.sleep(new Random().nextInt(3000));
            int delta = new Random().nextInt(20);
            this.price-=delta;
            System.out.println("Ставка " + this.bidId + " : " + this.price);
            this.barrier.await();
            System.out.println("Please, wait ...");

        } catch (InterruptedException | BrokenBarrierException exception) {
            exception.printStackTrace();
        }

    }
}
