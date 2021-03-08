package bstu.fit.walko.tender;

import java.util.Random;

public class TenderRunner {
    public static void main(String[] args) {

        Tender tender = new Tender();

        for(int i=0;i<10;++i)
        {
            Bid bid = new Bid(i,new Random().nextInt(200),tender.getBarrier());
            tender.add(bid);
            bid.start();
        }

    }
}
