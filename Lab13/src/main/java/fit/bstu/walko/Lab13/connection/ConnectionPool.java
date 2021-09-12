package fit.bstu.walko.Lab13.connection;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static final String PROPERTY_PATH = "db";

    private static final int INITIAL_CAPACITY = 10;

    private ArrayBlockingQueue<Connection> freeConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);
    private ArrayBlockingQueue<Connection> releaseConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);
    private static ReentrantLock Lock = new ReentrantLock();

    private volatile static ConnectionPool connectionPool;

    public static ConnectionPool getInstance() {
        try {
            Lock.lock();
            if (connectionPool == null) {
                connectionPool = new ConnectionPool();
            }
        } catch (Exception e) {
            LOGGER.error("Can not get Instance", e);
            throw new RuntimeException("Can not get Instance", e);
        } finally {
            Lock.unlock();
        }
        return connectionPool;
    }

    private ConnectionPool() throws SQLException {
        try {
            Lock.lock();
            if (connectionPool != null) {
                throw new UnsupportedOperationException();
            } else {
                DriverManager.registerDriver(new Driver() {
                    @Override
                    public Connection connect(String url, Properties info) throws SQLException {
                        return null;
                    }

                    @Override
                    public boolean acceptsURL(String url) throws SQLException {
                        return false;
                    }

                    @Override
                    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
                        return new DriverPropertyInfo[0];
                    }

                    @Override
                    public int getMajorVersion() {
                        return 0;
                    }

                    @Override
                    public int getMinorVersion() {
                        return 0;
                    }

                    @Override
                    public boolean jdbcCompliant() {
                        return false;
                    }

                    @Override
                    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
                        return null;
                    }
                });
                init();
            }
        } finally {
            Lock.unlock();
        }
    }

    private void init() {

        String connectionURL = "jdbc:mysql://localhost:3306/lab9?useSSL=false";
        String initialcapacitystring = "5";
        String user = "root";
        String pass = "123hateGnom546";

        Integer initialCapacity = Integer.valueOf(initialcapacitystring);

        for (int i = 0; i < initialCapacity; i++) {
            try {
                Connection connection = DriverManager.getConnection(connectionURL, user, pass);
                freeConnections.add(connection);
            } catch (SQLException e) {
                LOGGER.error("Pool can not initialize", e);
                throw new RuntimeException("Pool can not initialize", e);
            }
        }
    }

    public Connection getConnection() {
        try {
            Connection connection = freeConnections.take();
            releaseConnections.offer(connection);
            LOGGER.info("Connection was taken, the are free connection " + freeConnections.size());
            return connection;
        } catch (InterruptedException e) {
            throw new RuntimeException("can not get database", e);
        }
    }

    public void releaseConnection(Connection connection) {
        releaseConnections.remove(connection);
        freeConnections.offer(connection);
        LOGGER.info("Connection was released, the are free connection " + freeConnections.size());
    }

    public void destroy() {
        for (int i = 0; i < freeConnections.size(); i++) {
            try {
                Connection connection = (Connection) freeConnections.take();
                connection.close();
            } catch (InterruptedException e) {
                LOGGER.error("Connection close exception", e);
            } catch (SQLException e) {
                LOGGER.error("database is not closed", e);
                throw new RuntimeException("database is not closed", e);
            }

        }

        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                java.sql.Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            LOGGER.error("Drivers were not deregistrated", e);
        }
    }
}