package hibernate.config;

import hibernate.domain.Product;
import hibernate.domain.ProductCategory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class TestHibernateConiguration {

    private static SessionFactory factory;

    static {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.connection.driver_class", "org.h2.Driver");
        properties.put("hibernate.connection.url", "jdbc:h2:mem:onlineshop;INIT=create schema if not exists onlineshop");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(properties).build();

        factory = new Configuration()
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(ProductCategory.class)
                .buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}

