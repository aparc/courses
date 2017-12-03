package mysqlpackage;

import mysqlpackage.dao.MySQLProductCategoryDao;
import mysqlpackage.dao.MySQLProductDao;
import mysqlpackage.dao.ProductCategoryDao;
import mysqlpackage.dao.ProductDao;

public class Application {

    public static void main(String[] args) {

        ProductCategoryDao dao1 = new MySQLProductCategoryDao("onlineshop", "root", "root");
        dao1.getAll();
//        ProductDao dao = new MySQLProductDao();
////        dao.create(5, "BMW", 1500, 1);
//        dao.update(5, "BMW", 1600, 1);
//        dao.getAll();
    }
}
