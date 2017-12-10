package mysqlpackage.dao;

import mysqlpackage.ConnectionFactory;
import mysqlpackage.JdbcUtils;
import mysqlpackage.domain.ProductCategory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLProductCategoryDao implements ProductCategoryDao {

    private final ConnectionFactory connectionFactory;
//    private String databaseName;
//    private String login;
//    private String password;

    public MySQLProductCategoryDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
//        this.databaseName = "onlineshop";
//        this.login = "root";
//        this.password = "root";
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
    @SuppressWarnings("Duplicates")
    @Override
    public ProductCategory create(String name) {
        try (Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            int rowUpdated =  statement.executeUpdate("insert into productCategory(name) values(\"" + name + "\")");
            if(rowUpdated == 0) {
                return null;
            }
            ResultSet result = statement.executeQuery("select * from productCategory where name = \"" + name + "\"");
            result.next();
            return new ProductCategory(result.getInt(1), result.getString(2));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        final List<ProductCategory> list = new ArrayList<>();
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from productCategory order by categoryId asc");
            while(result.next()) {
                list.add(new ProductCategory(result.getInt(1), result.getString(2)));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductCategory getById(int categoryId) {
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery
                    ("select * from productCategory where categoryId = " + categoryId);
            result.next();
            return new ProductCategory(result.getInt(1), result.getString(2));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductCategory update(int categoryId, String name) {
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate
                    ("update productCategory set name = \"" + name + "\" where categoryId = " + categoryId);
            System.out.println("row(s) affected = " + rowUpdated);
            if(rowUpdated == 0) {
                return null;
            }
            ResultSet result = statement.executeQuery("select * from productCategory where categoryId = " + categoryId);
            return new ProductCategory(result.getInt(1), result.getString(2));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int categoryId) {
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("delete from productCategory where categoryId = " + categoryId);
            System.out.println("row(s) affected = " + rowUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
