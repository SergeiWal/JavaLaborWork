package fit.bstu.walko.Lab13.repository;

import fit.bstu.walko.Lab13.connection.UserConnection;
import fit.bstu.walko.Lab13.exception.RepositoryError;
import fit.bstu.walko.Lab13.models.Uniwer;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniwerRepository implements Repository<Uniwer>, AutoCloseable{


    protected Connection connection;

    public UniwerRepository()
            throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        connection = UserConnection.GetConnection();
    }

    @Override
    public void add(Uniwer entity) throws RepositoryError {
        PreparedStatement preparedStatement = null;
        String query = "insert Uniwers(uni_name, city, country)" +
                "values(?,?,?)";

        try{
            preparedStatement =  connection.prepareStatement(query);

            preparedStatement.setString(1, entity.getUni_name());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getCountry());
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
                throw new RepositoryError("RepositoryError");
            }
        }
    }

    @Override
    public List<Uniwer> findAll() throws RepositoryError {
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM UNIWERS";
        List<Uniwer> list = new ArrayList<>();

        try{

            preparedStatement =  connection.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()){
                Uniwer uniwer = new Uniwer();
                uniwer.setId(result.getInt(1));
                uniwer.setUni_name(result.getString(2));
                uniwer.setCity(result.getString(3));
                uniwer.setCountry(result.getString(4));
                list.add(uniwer);
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
                throw new RepositoryError("RepositoryError");
            }
        }
        return list;
    }

    @Override
    public Uniwer getById(int id) {
        return null;
    }

    @Override
    public void update(Uniwer entity) {

    }

    @Override
    public void remove(Uniwer entity) throws RepositoryError {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM UNIWERS WHERE ID=?";

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
                throw new RepositoryError("RepositoryError");
            }
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
