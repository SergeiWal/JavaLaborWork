package bstu.fit.walko.threads;

import bstu.fit.walko.resources.Shower;

import java.util.concurrent.TimeUnit;

public class Human extends Thread {
    private String humanName;
    private Gender gender;
    private Shower shower;
    private double showerHours;

    public String getHumanName() {
        return humanName;
    }

    public void setHumanName(String humanName) {
        this.humanName = humanName;
    }

    public Gender getGender() {
        return gender;
    }

    public Shower getShower() {
        return shower;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Human(String nHumanName, Gender nGender,double nShowerHours, Shower nShower)
    {
        super();
        humanName = nHumanName;
        gender = nGender;
        shower = nShower;
        showerHours = nShowerHours;
    }

    public double getShowerHours() {
        return showerHours;
    }

    @Override
    public  void run()
    {
        try {
            shower.comeInShower(this);
            TimeUnit.SECONDS.sleep(2);
            shower.exitUser(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
