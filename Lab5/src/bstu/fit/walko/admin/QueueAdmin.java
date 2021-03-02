package bstu.fit.walko.admin;

import  java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import  bstu.fit.walko.threads.*;

public class QueueAdmin {

    private ArrayList<Human> queue;

    public QueueAdmin(ArrayList<Human> queue) {
        this.queue = queue;
        this.queue.sort((l,r)->{
            if(l.getShowerHours() > r.getShowerHours())
                return 1;
            else  if(l.getShowerHours() < r.getShowerHours())
                return  -1;
            else return 0;
        });
    }

    public void start() throws InterruptedException {
        if(!queue.isEmpty())
        {
            for(int i =0;i < queue.size();i++)
            {
                queue.get(i).start();
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }
}
