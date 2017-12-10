package mysqlpackage;

import mysqlpackage.dao.MySQLProductCategoryDao;
import mysqlpackage.dao.ProductCategoryDao;
import mysqlpackage.domain.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class MySQLProductCategoryDaoTest {

    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    private Connection connection;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    private ProductCategoryDao dao;


    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(connectionFactory.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        dao = new MySQLProductCategoryDao(connectionFactory);
    }

    @Test
    public void testCreate() throws SQLException {
        int categoryId = 5;
        String name = "games";
        int rowUpdated = 1;

        when(statement.executeUpdate(Matchers.anyString())).thenReturn(rowUpdated);
        when(statement.executeQuery(Matchers.anyString())).thenReturn(resultSet);
        when(resultSet.getInt(1)).thenReturn(categoryId);
        when(resultSet.getString(2)).thenReturn(name);
        ProductCategory category = dao.create(name);
        Assertions.assertEquals(categoryId, category.getCategoryId());
        Assertions.assertEquals(name, category.getName());
    }

    @Test
    public void testCreate_noUpdates() throws SQLException {

        when(statement.executeUpdate(Matchers.anyString())).thenReturn(0);

        ProductCategory category = dao.create("");
        Assertions.assertNull(category);
        verify(statement, times(0)).executeQuery(Matchers.anyString());
    }

    @Test
    public void testGetAll() throws SQLException {

        when(statement.executeQuery(Matchers.anyString())).thenReturn(resultSet);

        List<ProductCategory> list = null;
        list = dao.getAll();
        Assertions.assertNotNull(list);
        verify(resultSet).next();
    }

    @Test
    public void testGetById() throws SQLException {
        int categoryId = 9;
        String name = "Glasses";

        when(statement.executeQuery(Matchers.anyString())).thenReturn(resultSet);
        when(resultSet.getInt(1)).thenReturn(categoryId);
        when(resultSet.getString(2)).thenReturn(name);
        ProductCategory category = dao.getById(categoryId);
        Assertions.assertEquals(categoryId, category.getCategoryId());
        Assertions.assertEquals(name, category.getName());
    }

    @Test
    public void testUpdate() throws SQLException {
        int categoryId = 9;
        String name = "Glasses";
        int rowUpdated = 1;

        when(statement.executeUpdate(Matchers.anyString())).thenReturn(rowUpdated);
        when(statement.executeQuery(Matchers.anyString())).thenReturn(resultSet);
        when(resultSet.getInt(1)).thenReturn(categoryId);
        when(resultSet.getString(2)).thenReturn(name);
        ProductCategory category = dao.update(categoryId, name);
        Assertions.assertEquals(categoryId, category.getCategoryId());
        Assertions.assertEquals(name, category.getName());
    }

    @Test
    public void testUpdate_noUpdates() throws SQLException {
        when(statement.executeUpdate(Matchers.anyString())).thenReturn(0);

        ProductCategory category = dao.update(2, "");
        Assertions.assertNull(category);
        verify(statement, times(0)).executeQuery(Matchers.anyString());
    }


}
