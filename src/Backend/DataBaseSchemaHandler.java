package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseSchemaHandler extends configuration {
    Connection connection;

    public Connection getConnection() throws SQLException, ClassNotFoundException {

        String connectionLine = "jdbc:mysql://" + host + ":" + port + "/" + schemaName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(connectionLine, username, password);
        return connection;
    }
}
