package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        String hostName = "localhost";
        String dbName = "seafoodDB";
        String userName = "root";
        String password = "mysql123456";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection connection = DriverManager.getConnection(connectionURL, userName,
                password);
        return connection;
    }
    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}
