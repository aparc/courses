package hibernate.repository;

import hibernate.domain.Product;

import java.util.List;

public interface ProductRepository {

    Product createProduct(int id, String name, double weight);

    List<Product> getAll();

    Product findById(int id);

    Product update(int id, String name, double weight);

    int remove(int id);
}
