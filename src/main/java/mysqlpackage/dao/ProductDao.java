package mysqlpackage.dao;

import mysqlpackage.domain.Product;

import java.util.List;

public interface ProductDao {
    Product create(int productId, String name, double weight, int categoryId_fk);

    List<Product> getAll();

    Product getById(int productId);

    Product update(int productId, String name, double weight, int categoryId_fk);

    void delete(int productId);
}
