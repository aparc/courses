package mysqlpackage.dao;

import mysqlpackage.domain.Producer;

import java.util.List;

public interface ProducerDao {

    Producer create(String name, String address);

    List<Producer> getAll();

    Producer getById(int producerId);

    Producer update(int producerId, String name, String address);

    void delete(int producerId);
}
