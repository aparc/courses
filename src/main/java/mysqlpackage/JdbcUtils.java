package mysqlpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {

    private JdbcUtils() {}

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String onlineshop, String login, String password) throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/" + onlineshop + "?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false",
                login, password);
    }
}
