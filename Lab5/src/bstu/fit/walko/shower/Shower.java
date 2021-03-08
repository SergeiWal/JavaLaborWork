package bstu.fit.walko.shower;

public class Shower {
    private int maxCapacity;
    private int currentCapacity = 0;
    private GenderType currentGender;
    private int currentTime = 0;

    public Shower(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void goToShower(Person person)
    {

        while ((currentCapacity>=maxCapacity || currentGender!=person.getGender() || currentTime<person.getHour())
                && currentCapacity!=0 && currentTime!=0)
        {
            try
            {
                wait();
                System.out.println(person.getPersonName() + " ожидает своей очереди(" + person.getHour() + " часов, в душе "
                + currentCapacity + " человек)");
            }
            catch (InterruptedException e){}
        }

        if(currentCapacity == 0){
            currentGender = person.getGender();
            currentTime = person.getHour();
        }
        currentCapacity++;
        System.out.println(person.getPersonName() + " зашёл(а) в душ(" + person.getHour() + " часов, в душе "
                + currentCapacity + " человек)");
        notify();
    }

    public  synchronized void  goOutShower(Person person)
    {
        currentCapacity--;
        System.out.println(person.getPersonName() + " вышел(а) из душа(" + person.getHour() + " часов, в душе "
                + currentCapacity + " человек)");
        notify();
    }

}
