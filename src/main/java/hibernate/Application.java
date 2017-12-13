package hibernate;

import hibernate.domain.Product;
import hibernate.domain.ProductCategory;
import hibernate.repository.impl.ProductRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class Application {

    public static void main(String[] args) {


        Configuration configuration = new Configuration();
        SessionFactory factory = configuration.configure().buildSessionFactory();
        ProductRepositoryImpl repository = new ProductRepositoryImpl(factory);


        Product pro1 = repository.createProduct(1, "dsgsd", 0.123d);
        Product pro2 = repository.createProduct(2,"sagfhh", 0.1457d);


//        List<Product> list = repository.getAll();
//        for(Product pro: list) {
//            System.out.println(pro.toString());
//        }

        int result = repository.remove(2);
        System.out.println(result);
        Product pro3 = repository.findById(2);
        System.out.println(pro3.toString());

//        Session session = factory.openSession();
//        final Transaction transaction = session.beginTransaction();
//        ProductCategory category = new ProductCategory();
//        category.setName("AVIA");
//        session.persist(category);
//        System.out.println(category.getCategoryId() + " " + category.getName());
//        transaction.commit();
//
//        final Transaction tr = session.beginTransaction();
//        ProductCategory category1 = session.find(ProductCategory.class, category.getCategoryId());
//        System.out.println(category1.getCategoryId() + " " + category1.getName());
//        tr.commit();
//
//        session.close();
//        factory.close();
    }
}
