package bstu.fit.walko.service;

import bstu.fit.walko.bl.IConnector;
import bstu.fit.walko.dao.PersonDAO;
import bstu.fit.walko.entity.Person;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonService implements IConnector, PersonDAO {

    public static  final Logger Log = Logger.getLogger(PersonService.class);

    @Override
    public void add(Person person) {

        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO PERSON (Person_ID,LastName,name,FhaterName,BirthDate)" +
                "values(?,?,?,?,?)";

        try{

            connect = this.getConnect();
            preparedStatement =  connect.prepareStatement(query);

            preparedStatement.setLong(1, person.getPersonId());
            preparedStatement.setString(2,person.getLastName());
            preparedStatement.setString(3,person.getFirstName());
            preparedStatement.setString(4,person.getFatherName());
            preparedStatement.setDate(5,person.getBirthDate());
            preparedStatement.executeUpdate();
            Log.error("Добавлен пользователь" +  person.getLastName() + "...");

        }catch (SQLException throwables){
            throwables.printStackTrace();
            Log.error("Ошибка при добавлении пользователя...");
        }
        finally {
            try {
                this.closeConnect(connect);
                if(preparedStatement !=null){
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Log.error("Ошибка при закрытии соеденения...");
            }
        }

    }

    @Override
    public List<Person> findAll() {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM PERSON";
        List<Person> list = new ArrayList<>();

        try{

            connect = this.getConnect();
            preparedStatement =  connect.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                Person person = new Person();
                person.setPersonId(result.getLong(1));
                person.setLastName(result.getString(2));
                person.setFirstName(result.getString(3));
                person.setFatherName(result.getString(4));
                person.setBirthDate(result.getDate(5));

                list.add(person);

            }
            Log.error("Получена инфо о пользователях...");

        }catch (SQLException throwables){
            throwables.printStackTrace();
            Log.error("Ошибка при получении информации о пользователях...");
        }
        finally {
            try {
                this.closeConnect(connect);
                if(preparedStatement !=null){
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Log.error("Ошибка при закрытии соеденения...");
            }
        }
        return list;
    }

    @Override
    public Person getById(int id) {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM PERSON WHERE Person_ID=?";


        try{

            connect = this.getConnect();
            preparedStatement =  connect.prepareStatement(query);
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            result.next();
            Person person = new Person();
            person.setPersonId(result.getLong(1));
            person.setLastName(result.getString(2));
            person.setFirstName(result.getString(3));
            person.setFatherName(result.getString(4));
            person.setBirthDate(result.getDate(5));

            Log.error("Получено инфо о пользователе " + person.getLastName());
            return person;

        }catch (SQLException throwables){
            throwables.printStackTrace();
            Log.error("Ошибка при получении информации о пользователе...");
        }
        finally {
            try {
                this.closeConnect(connect);
                if(preparedStatement !=null){
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Log.error("Ошибка при закрытии соеденения...");
            }
        }
        return null;
    }

    @Override
    public void update(Person person) {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String query = "UPDATE  PERSON SET Person_ID=?,LastName='Капорцев',name='Олег',FhaterName='Олегович'," +
                "BirthDate='05.05.2002' WHERE Person_ID=?";

        try{

            connect = this.getConnect();
            preparedStatement =  connect.prepareStatement(query);

            preparedStatement.setLong(1, person.getPersonId());
            preparedStatement.setLong(2, person.getPersonId());
            preparedStatement.executeUpdate();
            Log.error("Обновлена инфо о пользователе " +  person.getLastName() + "...");

        }catch (SQLException throwables){
            throwables.printStackTrace();
            Log.error("Ошибка при обновлении пользователя...");
        }
        finally {
            try {
                this.closeConnect(connect);
                if(preparedStatement !=null){
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Log.error("Ошибка при закрытии соеденения...");
            }
        }
    }

    @Override
    public void remove(Person person) {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM PERSON WHERE Person_ID=?";

        try{

            connect = this.getConnect();
            preparedStatement =  connect.prepareStatement(query);

            preparedStatement.setLong(1, person.getPersonId());
            preparedStatement.executeUpdate();
            Log.error("Удалён пользователь " +  person.getLastName() + "...");

        }catch (SQLException throwables){
            throwables.printStackTrace();
            Log.error("Ошибка при удалении..");
        }
        finally {
            try {
                this.closeConnect(connect);
                if(preparedStatement !=null){
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Log.error("Ошибка при закрытии соеденения...");
            }
        }
    }
}
