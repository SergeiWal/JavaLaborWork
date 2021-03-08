package bstu.fit.walko.shower;

import java.util.ArrayList;

public class ShowerTaskStart {
    public static void main(String[] args) {

        Shower shower = new Shower(3);

        ArrayList<Thread> queue = new ArrayList<>();
        queue.add(new Person("Сергей", GenderType.MAN, 8, shower));
        queue.add(new Person("Анна", GenderType.WOMAN,8, shower));
        queue.add(new Person("Иван", GenderType.MAN,8,shower));
        queue.add(new Person("Гриша", GenderType.MAN,8, shower));
        queue.add(new Person("Казимир", GenderType.MAN,9,shower));
        queue.add(new Person("Игорь", GenderType.MAN,9,shower));
        queue.add(new Person("Лера", GenderType.WOMAN,9,shower));
        queue.add(new Person("Катя", GenderType.WOMAN,8,shower));
        queue.add(new Person("Настя", GenderType.WOMAN,9,shower));
        queue.add(new Person("Олег", GenderType.MAN,9,shower));
        queue.add(new Person("Ефим", GenderType.MAN,10,shower));
        queue.add(new Person("Вика", GenderType.WOMAN,10,shower));

        queue.sort((l,r)->{
            if(((Person)l).getHour()>((Person)r).getHour())
                return 1;
            else if(((Person)l).getHour()<((Person)r).getHour())
                return -1;
            else
                return 0;
        });

        for (var c:
             queue) {
            c.start();
        }

    }
}
