package bstu.fit.walko.task2;

import bstu.fit.walko.task2.classes.Car;
import bstu.fit.walko.task2.classes.Road;
import bstu.fit.walko.task2.classes.Route;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        try
        {
            Road road = new Road();

            ArrayList<Thread> cars = new ArrayList<>();
            Thread t1 = new Car(1111111, Route.North,road);
            Thread t2 =new Car(2222222, Route.North,road);
            Thread t3 =new Car(3333333, Route.North,road);
            Thread t4 =new Car(4444444, Route.North,road);
            Thread t5 =new Car(5555555, Route.North,road);
            Thread t6 =new Car(6666666, Route.North,road);
            Thread t7 =new Car(7777777, Route.North,road);
            Thread t8 =new Car(8888888, Route.North,road);
            Thread t9 =new Car(9999999, Route.North,road);
            Thread t10 =new Car(1212111, Route.North,road);
            Thread t11 =new Car(3232323, Route.North,road);
            Thread t12 =new Car(4454544, Route.North,road);
            Thread t13 =new Car(6567564, Route.North,road);
            Thread t14 =new Car(9999312, Route.North,road);
            Thread t15 =new Car(3543002, Route.North,road);
            Thread t16 =new Car(3243282, Route.North,road);

            cars.add(t1);
            cars.add(t2);
            cars.add(t3);
            cars.add(t4);
            cars.add(t5);
            cars.add(t6);
            cars.add(t7);
            cars.add(t8);
            cars.add(t9);
            cars.add(t10);
            cars.add(t11);
            cars.add(t12);
            cars.add(t13);
            cars.add(t14);
            cars.add(t15);
            cars.add(t16);

            for (var c:
                 cars) {
                c.start();
            }


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
