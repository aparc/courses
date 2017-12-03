package mysqlpackage.dao;

import mysqlpackage.JdbcUtils;

import java.sql.*;

public class MySQLProductCategoryDao implements ProductCategoryDao {

    private String databaseName;
    private String login;
    private String password;

    public MySQLProductCategoryDao(String databaseName, String login, String password) {
        this.databaseName = databaseName;
        this.login = login;
        this.password = password;
    }

    @Override
    public void create(String name) {
        try (Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            int rowUpdated =  statement.executeUpdate("insert into productCategory(name) values(\"" + name + "\")");
            System.out.println("row(s) affected = " + rowUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAll() {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from productCategory order by categoryId asc");
            while(result.next()) {
                System.out.println("ID = " + result.getInt(1) +
                        ", name = " + result.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getById(int categoryId) {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery
                    ("select * from productCategory where categoryId = " + categoryId);
            while(result.next()) {
                System.out.println("ID = " + result.getInt(1) + ", name = " + result.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int categoryId, String name) {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate
                    ("update productCategory set name = \"" + name + "\" where categoryId = " + categoryId);
            System.out.println("row(s) affected = " + rowUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int categoryId) {
        try(Connection connection = JdbcUtils.getConnection(databaseName, login, password)) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("delete from productCategory where categoryId = " + categoryId);
            System.out.println("row(s) affected = " + rowUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
