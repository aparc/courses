package mysqlpackage.dao;

public interface ProductCategoryDao {

    void create(String name);

    void getAll();

    void getById(int categoryId);

    void update(int categoryId, String name);

    void delete(int categoryId);
}
