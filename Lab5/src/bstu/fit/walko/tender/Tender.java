package bstu.fit.walko.tender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CyclicBarrier;

public class Tender {
    private ArrayList<Bid> collection;
    private CyclicBarrier barrier;
    private final int BID_COUNT = 3;

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public void add(Bid newBid) {
        collection.add(newBid);
    }

    private Bid defineWinnerBid(){
        return Collections.min(collection, (o1, o2) -> o1.getPrice() - o2.getPrice());
    }

    public Tender()
    {
        this.collection = new ArrayList<>();
        this.barrier = new CyclicBarrier(BID_COUNT, new Runnable() {
            @Override
            public void run() {
                Bid winner = Tender.this.defineWinnerBid();
                System.out.println("Заявка №" + winner.getBidId() +" цена: "
                        + winner.getPrice() +" победила!");
                collection.remove(winner);
            }
        });
    }
}
