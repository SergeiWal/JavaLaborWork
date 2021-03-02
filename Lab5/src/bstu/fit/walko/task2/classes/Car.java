package bstu.fit.walko.task2.classes;

import java.util.concurrent.TimeUnit;

public class Car extends Thread {

    private int number;
    private Route route;
    private Road road;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }


    public  Car(int number, Route route, Road road)
    {
        super();
        this.number = number;
        this.route = route;
        this.road = road;
    }

    @Override
    public void run() {
     road.Process(this);
    }

}
