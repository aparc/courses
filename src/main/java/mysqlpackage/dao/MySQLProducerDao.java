package mysqlpackage.dao;

import mysqlpackage.JdbcUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLProducerDao implements ProducerDao {

    private String databaseName;
    private String login;
    private String password;

//    public MySQLProducerDao(String databaseName, String login, String password) {
//        this.databaseName = databaseName;
//        this.login = login;
//        this.password = password;
//    }

    public MySQLProducerDao() {
        authorize();
    }

    private void authorize(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            this.databaseName = reader.readLine();
            this.login = reader.readLine();
            this.password = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(String name, String address) {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate
                    ("insert into producer(name, address) values(\"" + name + "\", " + address + "\")");
            System.out.println("row(s) affected = " + rowUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAll() {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from producer order by producerId asc");
            while(result.next()) {
                System.out.println("producerId = " + result.getInt(1) +
                        ", name = " + result.getString(2) + ", adress = " + result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getById(int producerId) {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery
                    ("select * from producer where producerId = " + producerId);
            while(result.next()) {
                System.out.println("producerId = " + result.getInt(1) + ", name = " + result.getString(2) +
                ", address = " + result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int producerId, String name, String address) {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate
                    ("update producer set name = \"" + name + "\", address = \"" + address + "\" where producerId = " + producerId);
            System.out.println("row(s) affected = " + rowUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int producerId) {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("delete from producer where producerId = " + producerId);
            System.out.println("row(s) affected = " + rowUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
