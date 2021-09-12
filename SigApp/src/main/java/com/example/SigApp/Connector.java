package com.example.SigApp;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private  static final String url = "jdbc:mysql://localhost/student?serverTimezone=Europe/Moscow&useSSL=false";
    private  static final String root = "root";
    private  static final String password = "123hateGnom546";//здесь твой пароль

    public static Connection getConnection() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        return DriverManager.getConnection(url,root,password);
    }
}
