package com.example.SigApp;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbWorker {
    private Connection connection;

    public DbWorker(){
        try {
            connection = Connector.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void add(Person person){

        String query = " insert  into persons(FIO, avgNumb)\n" +
                "values(?, ?);";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,person.getFIO());
            statement.setDouble(2,person.getAvgNumber());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<Person> FindAll() {
        PreparedStatement preparedStatement = null;
        String query = "select * from persons";
        List<Person> personList  = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Person person = new Person();

                person.setFIO(resultSet.getString(1));
                person.setAvgNumber(resultSet.getDouble(2));
                personList.add(person);
            }

            return personList;

        } catch (SQLException throwables) {

        }
        return null;
    }
}
