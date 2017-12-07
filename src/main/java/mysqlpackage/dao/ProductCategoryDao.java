package mysqlpackage.dao;

import mysqlpackage.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    ProductCategory create(String name);

    List<ProductCategory> getAll();

    ProductCategory getById(int categoryId);

    ProductCategory update(int categoryId, String name);

    void delete(int categoryId);
}
