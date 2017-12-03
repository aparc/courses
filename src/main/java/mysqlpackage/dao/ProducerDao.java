package mysqlpackage.dao;

public interface ProducerDao {

    void create(String name, String address);

    void getAll();

    void getById(int producerId);

    void update(int producerId, String name, String address);

    void delete(int producerId);
}
