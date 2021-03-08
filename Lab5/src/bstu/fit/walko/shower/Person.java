package bstu.fit.walko.shower;

import java.util.concurrent.TimeUnit;

public class Person extends Thread {
    private String personName;
    private GenderType gender;
    private int hour;
    private Shower shower;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public Shower getShower() {
        return shower;
    }

    public void setShower(Shower shower) {
        this.shower = shower;
    }

    public Person(String personName, GenderType genderType, int hour, Shower shower)
    {
        super();
        this.personName = personName;
        this.gender = genderType;
        this.shower =shower;
        this.hour = hour;
    }

    @Override
    public void run() {
        try
        {
            shower.goToShower(this);
            TimeUnit.MILLISECONDS.sleep(100);
            shower.goOutShower(this);
        }
        catch (InterruptedException exception)
        {}
    }
}
