package bstu.fit.walko.manager;

import bstu.fit.walko.entity.Latter;
import bstu.fit.walko.entity.Person;
import bstu.fit.walko.service.LatterService;
import bstu.fit.walko.service.PersonService;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostManager {

    public static  final Logger Log = Logger.getLogger(PostManager.class);

    public static Person findUserWithMinLattersLen(){
        List<Latter> latters = new ArrayList<>();
        List<Person> people = new ArrayList<>();

        LatterService latterService = new LatterService();
        PersonService personService = new PersonService();

        latters = latterService.findAll();
        people = personService.findAll();

        Person person = null;
        int minLen = Integer.MAX_VALUE;
        long personIdx = latters.get(0).getSender()-1;

        for(var p: people){
            int lengt=0;
            for(var l: latters){
                if(l.getSender()==p.getPersonId()){
                    lengt += l.getText().length();
                }
            }
            if(lengt<minLen){
                minLen = lengt;
                personIdx = p.getPersonId() - 1;
            }
        }

        person = people.get((int) personIdx);

        Log.info("Найден пользователь, длина писем которого наименьшая");
        return person;
    }

    public static void printUsersInfo()
    {
        System.out.println("--------------Пользователи------------------");

        List<Person> people = new ArrayList<>();
        PersonService personService = new PersonService();
        people = personService.findAll();

        LatterService latterService = new LatterService();
        List<Latter> latters = latterService.findAll();

        for(var c:people){
            System.out.println("------User");
            int sendCount =0;
            int getCount =0;
            long id = c.getPersonId();
            for(var x: latters){
                if(id == x.getSender()){
                    sendCount++;
                }
                if(id == x.getAdressee()){
                    getCount++;
                }
            }
            System.out.println(c.toString() + "\nLatters send count: " + sendCount + "\nLetters get count: "+
                    getCount);
        }

        Log.info("Выведена информация о пользователях, а также количестве " +
                "полученных и отправленных ими письмах.");
    }

    public static void printUsersInfoByTopic(String topic) {
        System.out.println("--------------Тема: " + topic + "------------------");

        List<Person> people = new ArrayList<>();
        PersonService personService = new PersonService();
        people = personService.findAll();

        LatterService latterService = new LatterService();
        List<Latter> latters = latterService.findAll();

        for(var c:latters)
        {
            String currentTopic = c.getTopic();
            if(currentTopic.equals(topic)){
                System.out.println(people.get((int)c.getAdressee()-1));
            }
        }
        Log.info("Выведена информация о пользователях, которые получили хотя бы " +
                "одно сообщение с заданной темой");
    }

    public static void printUsersInfoByNotTopic(String topic){

        System.out.println("--------------Тема: " + topic + "------------------");

        PersonService personService = new PersonService();
        List<Person> people = personService.findAll();

        LatterService latterService = new LatterService();
        List<Latter> latters = latterService.findAll();

        for(var p: people){
            int count =0;
            for(var l: latters){
                if(l.getAdressee() == p.getPersonId()
                        && l.getTopic().equals(topic)){
                    count++;
                }
            }
            if(count==0){
                System.out.println(p);
            }
        }

        Log.info("Выведена информация о пользователях, которые не получали " +
                "сообщения с заданной темой");

    }

    public static void sendLatterAllUsers(Person sender, String topic, String text, Date sendDate)
    {
        LatterService latterService = new LatterService();
        PersonService personService = new PersonService();

        List<Person> people =  personService.findAll();
        int latterID = personService.findAll().size() + 2;

        for (var c: people){
            if(c.getPersonId() != sender.getPersonId()){
                Latter latter = new Latter();
                latter.setLattersID(latterID);
                latter.setSender(sender.getPersonId());
                latter.setAdressee(c.getPersonId());
                latter.setTopic(topic);
                latter.setText(text);
                latter.setSendDate(sendDate);

                latterService.add(latter);
                latterID++;
            }
        }

        Log.info("Направлено письмо заданного человека с заданной темой всем " +
                "адресатам");
    }

    public static  void Transaction()
    {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO LATTERS (Latters_ID,Sender,Adressee,Topic, Text, SendDate)" +
                "values(?,?,?,?,?,?)";
        LatterService latterService = new LatterService();

        try{

            connect = latterService.getConnect();
            connect.setAutoCommit(false);
            //Savepoint savepoint = connect.setSavepoint("Save point");
            preparedStatement =  connect.prepareStatement(query);

            preparedStatement.setLong(1, 10l);
            preparedStatement.setLong(2, 2l);
            preparedStatement.setLong(3, 3l);
            preparedStatement.setString(4,"Деньги");
            preparedStatement.setString(5,"ВАм переведены деньги...");
            preparedStatement.setDate(6, new Date(342376));
            preparedStatement.executeUpdate();

            connect.commit();
            List<Latter> latters =  latterService.findAll();
            for (var c:
                    latters) {

                System.out.println(c);
            }
            connect.rollback();
            Log.info("Транзакция выполнена успешно...");

        }catch (SQLException throwables){
            throwables.printStackTrace();
            Log.error("Ошибка при выполнении транзакции...");
        }
        finally {
            try {
                latterService.closeConnect(connect);
                if(preparedStatement !=null){
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Log.error("Ошибка при закрытии соединения...");
            }
        }
    }

}
