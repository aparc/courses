package mysqlpackage.dao;

import mysqlpackage.JdbcUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLProductDao implements ProductDao {

    private String databaseName;
    private String login;
    private String password;

    public MySQLProductDao() {
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
    public void create(int productId, String name, double weight, int categoryId_fk) {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate
                    ("insert into product values(" + productId + ", \"" + name + "\"" + ", " + weight + ", " + categoryId_fk + ")");
            System.out.println("row(s) affected = " + rowUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAll() {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from product");
            while (result.next()) {
                System.out.println("ID = " + result.getInt(1) +
                        ", name = " + result.getString(2) + ", weight = " + result.getDouble(3) +
                        ", categoryId = " + result.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getById(int productId) {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from product where productId = " + productId);
            while (result.next()) {
                System.out.println("ID = " + result.getInt(1) +
                        ", name = " + result.getString(2) + ", weight = " + result.getDouble(3) +
                        ", categoryId = " + result.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int productId, String name, double weight, int categoryId_fk) {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate
                    ("update product set name = \"" + name + "\", weight = " + weight + ", categoryId_fk = " + categoryId_fk + " where productId = " + productId);
            System.out.println("row(s) affected = " + rowUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int productId) {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("delete from product where productId = " + productId);
            System.out.println("row(s) affected = " + rowUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
