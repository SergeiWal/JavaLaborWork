package bstu.fit.walko.resources;

import bstu.fit.walko.threads.Gender;
import bstu.fit.walko.threads.Human;
import java.util.concurrent.atomic.AtomicInteger;

public class Shower {

    private AtomicInteger currentUserCount;
    private int maxUserCount;
    private Gender currentGender;
    private double currentTime;

    public int getMaxUserCount() {
        return maxUserCount;
    }

    public void setCurrentUserCount(int currentUserCount) {
        this.currentUserCount.set(currentUserCount);
    }

    public Shower(int maxUserCount) {
        this.currentUserCount = new AtomicInteger(0);
        this.maxUserCount = maxUserCount;
        this.currentTime = 0.0;
    }

    public synchronized void comeInShower(Human person) throws InterruptedException {
        if(currentUserCount.get() == 0)
        {
            currentGender = person.getGender();
            currentTime = person.getShowerHours();
            System.out.println(currentTime + " hours:");
            showerInfo(person.getHumanName());
            notifyAll();
            //this.wait();
        }
        else if(currentUserCount.get() < maxUserCount && person.getGender()==currentGender &&
        person.getShowerHours()==currentTime)
        {
            showerInfo(person.getHumanName());
           notifyAll();
            //this.wait();
        }
        else if(currentUserCount.get() < maxUserCount && person.getGender()==currentGender)
        {
            System.out.println(">" + person.getHumanName() + " ждёт своей очереди");
            while (currentUserCount.get() != maxUserCount)this.wait();
            currentTime = person.getShowerHours();
            System.out.println(currentTime + " hours:");
            showerInfo(person.getHumanName());
            notifyAll();
        }
        else if(person.getGender()==currentGender &&
                person.getShowerHours()==currentTime)
        {
            System.out.println(">" + person.getHumanName() + " ждёт своей очереди");
            while (currentUserCount.get() >= maxUserCount)this.wait();
            showerInfo(person.getHumanName());
            notifyAll();
            //this.wait();
        }
        else
        {
            System.out.println(">" + person.getHumanName() + " ждёт своей очереди");
            while (person.getGender()!=currentGender && currentUserCount.get() != 0)this.wait();
            showerInfo(person.getHumanName());
            currentGender = person.getGender();
            notifyAll();
            //this.wait();
        }
    }

    public synchronized void exitUser(Human person)
    {
        System.out.println(">" + person.getHumanName() + " вышел из душа");
        currentUserCount.decrementAndGet();
        this.notifyAll();
    }

    private void showerInfo(String name) {
        System.out.println(">" + name+ " зашёл в душ");
        currentUserCount.incrementAndGet();
    }
}
