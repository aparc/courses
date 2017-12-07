package mysqlpackage;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {

    Connection getConnection(String databaseName, String login, String password) throws SQLException;
}
