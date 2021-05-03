package fit.bstu.Lab9.DB;

import java.sql.Connection;
import java.util.List;

public interface UniwerDAO {
    //create
    void add(Connection connection, Uniwer uniwer);
    //find
    List<Uniwer> findAll(Connection connection);
    //delete
    void remove(Connection connection, Uniwer uniwer);
}
