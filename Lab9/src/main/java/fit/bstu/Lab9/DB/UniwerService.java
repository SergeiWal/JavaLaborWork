package fit.bstu.Lab9.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniwerService implements UniwerDAO{

    @Override
    public void add(Connection connection, Uniwer uniwer) {
        PreparedStatement preparedStatement = null;
        String query = "insert Uniwers(uni_name, city, country)" +
                "values(?,?,?)";

        try{
            preparedStatement =  connection.prepareStatement(query);

            preparedStatement.setString(1, uniwer.getUni_name());
            preparedStatement.setString(2, uniwer.getCity());
            preparedStatement.setString(3, uniwer.getCountry());
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
    public List<Uniwer> findAll(Connection connection) {
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
            }
        }
        return list;
    }

    @Override
    public void remove(Connection connection, Uniwer uniwer) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM UNIWERS WHERE ID=?";

        try{
            preparedStatement =  connection.prepareStatement(query);

            preparedStatement.setLong(1, uniwer.getId());
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
}
