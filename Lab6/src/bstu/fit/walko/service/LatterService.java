package bstu.fit.walko.service;

import bstu.fit.walko.bl.IConnector;
import bstu.fit.walko.dao.LatterDAO;
import bstu.fit.walko.entity.Latter;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LatterService implements IConnector, LatterDAO {

    public static  final Logger Log = Logger.getLogger(LatterService.class);

    @Override
    public void add(Latter latter) {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO LATTERS (Latters_ID,Sender,Adressee,Topic, Text, SendDate)" +
                "values(?,?,?,?,?,?)";

        try{

            connect = this.getConnect();
            preparedStatement =  connect.prepareStatement(query);

            preparedStatement.setLong(1, latter.getLattersID());
            preparedStatement.setLong(2,latter.getSender());
            preparedStatement.setLong(3, latter.getAdressee());
            preparedStatement.setString(4,latter.getTopic());
            preparedStatement.setString(5,latter.getText());
            preparedStatement.setDate(6,latter.getSendDate());
            preparedStatement.executeUpdate();
            Log.info("Добавлено новое письмо..." );

        }catch (SQLException throwables){
            throwables.printStackTrace();
            Log.error("Ошибка при добавлении письма...");
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
    public List<Latter> findAll() {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM LATTERS";
        List<Latter> list = new ArrayList<>();

        try{

            connect = this.getConnect();
            preparedStatement =  connect.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                Latter latter = new Latter();
                latter.setLattersID(result.getLong(1));
                latter.setSender(result.getLong(2));
                latter.setAdressee(result.getLong(3));
                latter.setTopic(result.getString(4));
                latter.setText(result.getString(5));
                latter.setSendDate(result.getDate(6));

                list.add(latter);
                Log.info("Выполнено получение коллекции писем..." );
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
            Log.error("Ошибка при чтении писем...");
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
    public Latter getById(int id) {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM LATTERS WHERE Latters_ID=?";

        try{

            connect = this.getConnect();
            preparedStatement =  connect.prepareStatement(query);
            preparedStatement.setLong(1,id);

            ResultSet result = preparedStatement.executeQuery();
            result.next();
            Latter latter = new Latter();
            latter.setLattersID(result.getLong(1));
            latter.setSender(result.getLong(2));
            latter.setAdressee(result.getLong(3));
            latter.setTopic(result.getString(4));
            latter.setText(result.getString(5));
            latter.setSendDate(result.getDate(6));

            Log.info("Получено письмо по ID..." );
            return latter;


        }catch (SQLException throwables){
            throwables.printStackTrace();
            Log.error("Ошибка при чтении письма...");
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
    public void update(Latter latter) {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String query = "UPDATE LATTERS SET Latters_ID=?,Sender=1,Adressee=2,Topic='Новости', Text='Польша продала всю клубнику'," +
                " SendDate='03.03.2020' WHERE Latters_ID=?";

        try{

            connect = this.getConnect();
            preparedStatement =  connect.prepareStatement(query);

            preparedStatement.setLong(1, latter.getLattersID());
            preparedStatement.setLong(2, latter.getLattersID());
            preparedStatement.executeUpdate();
            Log.info("Письмо №" + latter.getLattersID() + " обновлено..." );

        }catch (SQLException throwables){
            throwables.printStackTrace();
            Log.error("Ошибка при обновлении письма...");
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
    public void remove(Latter latter) {

        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM LATTERS WHERE Latters_ID=?";

        try{

            connect = this.getConnect();
            preparedStatement =  connect.prepareStatement(query);

            preparedStatement.setLong(1, latter.getLattersID());
            preparedStatement.executeUpdate();
            Log.info("Письмо №" + latter.getLattersID() + " удалено..." );

        }catch (SQLException throwables){
            throwables.printStackTrace();
            Log.error("Ошибка при удалении письма...");
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
