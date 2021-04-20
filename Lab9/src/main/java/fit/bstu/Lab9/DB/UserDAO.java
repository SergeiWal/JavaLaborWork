package fit.bstu.Lab9.DB;

import java.sql.Connection;
import java.util.List;

public interface UserDAO {
    //create
    void add(Connection connection, User latter);
    //find
    List<User> findAll(Connection connection);
    User getById(Connection connection, int id);
    //update
    void update(Connection connection, User latter);
    //delete
    void remove(Connection connection, User latter);
}
