package fit.bstu.walko.Lab13.connection;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class UserConnection {
    private static final String PROPERTY_PATH = "db";
    private static final int INITIAL_CAPACITY = 10;
    private  static final String url = "jdbc:mysql://localhost/lab9?serverTimezone=Europe/Moscow&useSSL=false";
    private  static final String root = "root";
    private  static final String password = "123hateGnom546";


    public static Connection GetConnection()
            throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        return DriverManager.getConnection(url, root, password);
    }
}
