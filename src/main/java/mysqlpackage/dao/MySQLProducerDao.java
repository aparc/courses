package mysqlpackage.dao;

import mysqlpackage.ConnectionFactory;
import mysqlpackage.JdbcUtils;
import mysqlpackage.domain.Producer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLProducerDao implements ProducerDao {

    private final ConnectionFactory connectionFactory;
//    private String databaseName;
//    private String login;
//    private String password;

//    public MySQLProducerDao(String databaseName, String login, String password) {
//        this.databaseName = databaseName;
//        this.login = login;
//        this.password = password;
//    }

    public MySQLProducerDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
//        authorize();
    }

//    private void authorize(){
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            this.databaseName = reader.readLine();
//            this.login = reader.readLine();
//            this.password = reader.readLine();
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public Producer create(String name, String address) {
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate
                    ("insert into producer(name, address) values(\"" + name + "\", " + address + "\")");
            System.out.println("row(s) affected = " + rowUpdated);
            ResultSet result = statement.executeQuery("select * from producer where name = \"" + name + "\"");
            result.next();
            return new Producer(result.getInt(1), result.getString(2), result.getString(3));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Producer> getAll() {
        final List<Producer> list = new ArrayList<>();
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from producer order by producerId asc");
            while(result.next()) {
                list.add(new Producer(result.getInt(1), result.getString(2), result.getString(3)));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Producer getById(int producerId) {
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery
                    ("select * from producer where producerId = " + producerId);
            result.next();
            return new Producer(result.getInt(1), result.getString(2), result.getString(3));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Producer update(int producerId, String name, String address) {
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate
                    ("update producer set name = \"" + name + "\", address = \"" + address + "\" where producerId = " + producerId);
            System.out.println("row(s) affected = " + rowUpdated);
            ResultSet result = statement.executeQuery("select * from producer where producerId = " + producerId);
            return new Producer(result.getInt(1), result.getString(2), result.getString(3));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int producerId) {
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("delete from producer where producerId = " + producerId);
            System.out.println("row(s) affected = " + rowUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
