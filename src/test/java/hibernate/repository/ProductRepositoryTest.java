package hibernate.repository;

import hibernate.domain.Product;
import hibernate.repository.impl.ProductRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProductRepositoryTest {

    @Mock
    private SessionFactory factory;
    @Mock
    private Session session;
    @Mock
    private Transaction transaction;
    @Mock
    private Query<Product> query;

    private ProductRepository repository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(factory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        this.repository = new ProductRepositoryImpl(factory);
    }

    @Test
    public void testCreate() {
        int id = 10;
        String name = "Marker";
        double weight = 0.050d;

        Product result = repository.createProduct(id, name, weight);
        assertEquals(id, result.getProductId());
        assertEquals(name, result.getName());
        assertEquals(weight, result.getWeight());

        verify(session).persist(Matchers.any(Product.class));
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void getAll() {

        when(session.createQuery(Matchers.anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(new ArrayList<Product>());
        List<Product> list = repository.getAll();

        assertNotNull(list);
        verify(session).createQuery(Matchers.anyString());
    }
}
