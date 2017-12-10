package mysqlpackage.dao;

import mysqlpackage.ConnectionFactory;
import mysqlpackage.JdbcUtils;
import mysqlpackage.domain.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLProductDao implements ProductDao {

    private final ConnectionFactory connectionFactory;
//    private String databaseName;
//    private String login;
//    private String password;

    public MySQLProductDao(ConnectionFactory connectionFactory) {
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
    public Product create(int productId, String name, double weight, int categoryId_fk) {
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate
                    ("insert into product values(" + productId + ", \"" + name + "\"" + ", " + weight + ", " + categoryId_fk + ")");
            System.out.println("row(s) affected = " + rowUpdated);
            ResultSet result = statement.executeQuery("select * from product where productId = " + productId);
            result.next();
            return new Product(result.getInt(1), result.getString(2), result.getDouble(3), result.getInt(4));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        final List<Product> list = new ArrayList<>();
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from product");
            while (result.next()) {
                list.add(new Product(result.getInt(1), result.getString(2), result.getDouble(3), result.getInt(4)));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getById(int productId) {
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from product where productId = " + productId);
            result.next();
            return new Product(result.getInt(1), result.getString(2), result.getDouble(3), result.getInt(4));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product update(int productId, String name, double weight, int categoryId_fk) {
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate
                    ("update product set name = \"" + name + "\", weight = " + weight + ", categoryId_fk = " + categoryId_fk + " where productId = " + productId);
            System.out.println("row(s) affected = " + rowUpdated);
            ResultSet result = statement.executeQuery("select * from product where productId = " + productId);
            return new Product(result.getInt(1), result.getString(2), result.getDouble(3), result.getInt(4));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int productId) {
        try(Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("delete from product where productId = " + productId);
            System.out.println("row(s) affected = " + rowUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
