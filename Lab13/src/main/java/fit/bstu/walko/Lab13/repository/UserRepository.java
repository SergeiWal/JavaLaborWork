package fit.bstu.walko.Lab13.repository;


import fit.bstu.walko.Lab13.connection.UserConnection;
import fit.bstu.walko.Lab13.models.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User>, AutoCloseable{

    private Connection connection;

    public UserRepository()
            throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        connection =  UserConnection.GetConnection();
    }

    @Override
    public void add(User entity) {
        PreparedStatement preparedStatement = null;
        String query = "insert Users(user_name, login, user_password, user_role)" +
                "values(?,?,?,?)";

        try{
            preparedStatement =  connection.prepareStatement(query);

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLogin());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4, entity.getRole());
            preparedStatement.executeUpdate();

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            try {
                if(preparedStatement !=null){
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<User> findAll() {
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM USERS";
        List<User> list = new ArrayList<>();

        try{

            preparedStatement =  connection.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()){
                User user = new User();
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                user.setLogin(result.getString(3));
                user.setPassword(result.getString(4));
                user.setRole(result.getString(5));

                list.add(user);
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            try {
                if(preparedStatement !=null){
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public User getById(int id) {
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM USERS WHERE ID=?";

        try{
            preparedStatement =  connection.prepareStatement(query);
            preparedStatement.setLong(1,id);

            ResultSet result = preparedStatement.executeQuery();
            result.next();
            User user = new User();
            user.setId(result.getInt(1));
            user.setName(result.getString(2));
            user.setLogin(result.getString(3));
            user.setPassword(result.getString(4));
            user.setRole(result.getString(5));

            return user;

        }catch (SQLException  throwables){
            throwables.printStackTrace();
        }
        finally {
            try {
                if(preparedStatement !=null){
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void remove(User entity) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM USERS WHERE ID=?";

        try{
            preparedStatement =  connection.prepareStatement(query);

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException  throwables){
            throwables.printStackTrace();
        }
        finally {
            try {
                if(preparedStatement !=null){
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
