package hibernate.repository;

import hibernate.config.TestHibernateConfiguration;
import hibernate.domain.Product;
import hibernate.repository.impl.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductRepositoryIT {

    private static ProductRepository repository;

    @BeforeAll
    public static void setupOnce() {
        repository = new ProductRepositoryImpl(TestHibernateConfiguration.getFactory());
    }

    @Test
    public void testCreate() {
        int id = 10;
        String name = "Marker";
        double weight = 0.05d;

        final Product result = repository.createProduct(id, name, weight);

        assertEquals(id, result.getProductId());
        assertEquals(name, result.getName());
        assertEquals(weight, result.getWeight());
    }

    @Test
    public void testGetAll() {
        List<Product> list = repository.getAll();

        assertTrue(list.size() > 0);
    }

    @Test
    public void testFindById_entityExists() {
        int id = 15;
        String name = "Laptop";
        double weight = 3.54d;

        repository.createProduct(id, name, weight);
        Product product = repository.findById(id);

        assertEquals(id, product.getProductId());
        assertEquals(name, product.getName());
        assertEquals(weight, product.getWeight());
    }

    @Test
    public void testFindById_entityNotExists() {}

    @Test
    public void testRemove() {
        int id = 20;
        repository.createProduct(id, "Phone", 0.123d);

        int result = repository.remove(id);
        assertEquals(1, result);
        Product product = repository.findById(id);
        assertNull(product);
    }

    @Test
    public void testRemove_noEntityWithId_returnZero() {
        int result = repository.remove(5);
        assertEquals(0, result);
    }
}
