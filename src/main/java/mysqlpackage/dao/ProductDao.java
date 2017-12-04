package mysqlpackage.dao;

public interface ProductDao {
    void create(int productId, String name, double weight, int categoryId_fk);

    void getAll();

    void getById(int productId);

    void update(int productId, String name, double weight, int categoryId_fk);

    void delete(int productId);
}
