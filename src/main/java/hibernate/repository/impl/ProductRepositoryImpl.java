package hibernate.repository.impl;

import hibernate.domain.Product;
import hibernate.repository.ProductRepository;
import hibernate.repository.TransactionOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    final SessionFactory factory;

    public ProductRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public Product createProduct(int id, String name, double weight) {
        final Product product = new Product();
        product.setProductId(id);
        product.setName(name);
        product.setWeight(weight);

        final Session session = factory.openSession();
        return (Product) doInTransaction(session, () -> {
            session.persist(product);
            return product;
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getAll() {
        final Session session = factory.openSession();
        return (List<Product>) doInTransaction(session, () -> {
            Query<Product> query = session.createQuery("from Product");
            return query.getResultList();
        });
    }

    public Product findById(int id) {
        final Session session = factory.openSession();
        return (Product) doInTransaction(session, () -> session.find(Product.class, id));
    }

    @Override
    public Product update(int id, String name, double weight) {
        final Product product = new Product();
        product.setProductId(id);
        product.setName(name);
        product.setWeight(weight);

        final Session session = factory.openSession();
        return (Product) doInTransaction(session, () -> {
            session.update(product);
            return product;
        });
//        return (Integer) doInTransaction(session, new TransactionOperation() {
//            @Override
//            public Object doInTransaction() {
//                return session.createQuery("update Product set name = :name" + ", weight = :weight" + " where productId = :productId").
//                        setParameter("name", name).
//                        setParameter("weight", weight).
//                        setParameter("prouctId", id).executeUpdate();
//            }
//        });
    }

    @Override
    public int remove(int id) {
        final Session session = factory.openSession();
        return (Integer) doInTransaction(session, () -> {
            Query query = session.
                    createQuery("delete from Product where productId = :productId").
                    setParameter("productId", id);
            return query.executeUpdate();
        });
    }

    private Object doInTransaction(Session session, TransactionOperation operation) {
        final Transaction transaction = session.beginTransaction();

        Object result = operation.doInTransaction();

        transaction.commit();
        session.close();
        return result;
    }
}
