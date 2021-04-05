package bstu.fit.walko;

import bstu.fit.walko.entity.Latter;
import bstu.fit.walko.entity.Person;
import bstu.fit.walko.manager.PostManager;
import bstu.fit.walko.service.LatterService;
import bstu.fit.walko.service.PersonService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.Console;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static{
        new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());
    }
    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("Start work---------------");
	// write your code here
        List<Latter> latters = new ArrayList<>();
        LatterService latterService = new LatterService();

        latters = latterService.findAll();
        for (var c:
             latters) {

            System.out.println(c);
        }

        System.out.println("--------------------------");

        System.out.println(latterService.getById(2));
        //latterService.update(latterService.getById(2));

        System.out.println("------------Найти пользователя, длина писем которого наименьшая--------------");
        System.out.println(PostManager.findUserWithMinLattersLen());
        System.out.println("----------Вывести информацию о пользователях, а также количестве " +
                "полученных и отправленных ими письмах.-------------");
        PostManager.printUsersInfo();
        System.out.println("------------Вывести информацию о пользователях, которые получили хотя бы " +
                "одно сообщение с заданной темой--------------");
        PostManager.printUsersInfoByTopic("Собачки");
        System.out.println("------------Вывести информацию о пользователях, которые не получали " +
                "сообщения с заданной темой--------------");
        PostManager.printUsersInfoByNotTopic("Собачки");
        System.out.println("------------Направить письмо заданного человека с заданной темой всем " +
                "адресатам--------------");
        PersonService personService = new PersonService();
        PostManager.sendLatterAllUsers(personService.getById(1) ,"Учёба","Я сдал jdbc!!!", new Date(03052002));
        latters = latterService.findAll();
        for (var c:
                latters) {

            System.out.println(c);
        }
        for(int i=6;i<9;++i)
        {
            Latter latter = new Latter();
            latter.setLattersID(i);
            latterService.remove(latter);
        }
        System.out.println("------------Транзакция--------------");
        PostManager.Transaction();
        Latter latter = new Latter();
        latter.setLattersID(10);
        latterService.remove(latter);
        LOG.info("End work---------------");
    }
}
