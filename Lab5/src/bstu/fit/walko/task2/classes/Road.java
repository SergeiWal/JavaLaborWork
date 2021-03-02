package bstu.fit.walko.task2.classes;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Road {

    private ArrayList<Car> queue;
    private volatile  Route currentRoute = Route.North;
    private AtomicInteger count = new AtomicInteger(0);

    private final ReentrantLock lock = new ReentrantLock(true);
    private Condition  condition = lock.newCondition();

    public Road() {
        this.queue = new ArrayList<>();
    }

    public void Process(Car newCar)
    {
        try {

            System.out.println(newCar.getNumber() + " подъехал, его направление движения: " +
                    getRoute(newCar.getRoute()) + " сейчас в очереди: " + queue.size() + "машин");
            queue.add(newCar);
            lock.lock();
            Thread.sleep(1000);
            if(queue.size() == 1)
            {
                queue.remove(newCar);
                System.out.println(newCar.getNumber() + " проехал ремонтируемый участок( сейчас в очереди " +
                        queue.size() + " машин)");
                condition.signalAll();
            }
            else
            {
                if(newCar.getRoute() == currentRoute && count.get() < 3 )
                {
                    count.incrementAndGet();
                    Thread.sleep(2);
                    queue.remove(newCar);
                    System.out.println(newCar.getNumber() + " проехал ремонтируемый участок( сейчас в очереди " +
                            queue.size() + " машин)");
                    if(count.get() >= 3)
                    {
                        count.set(0);
                        currentRoute = currentRoute == Route.North?Route.South:Route.North;
                    }
                    condition.signalAll();
                }
                else
                {
                    System.out.println(newCar.getNumber() + " ожидает своей очереди( сейчас в очереди " +
                            queue.size() + " машин)");
                    while (newCar.getRoute() != currentRoute)
                        condition.await();
                    count.incrementAndGet();
                    Thread.sleep(2);
                    queue.remove(newCar);
                    System.out.println(newCar.getNumber() + " проехал ремонтируемый участок( сейчас в очереди " +
                            queue.size() + " машин)");
                    condition.signalAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private String getRoute(Route route)
    {
        switch (route)
        {
            case North:
                return "прямо";
            case South:
                return  "назад";
            default :
                break;
        }
        return null;
    }

}
