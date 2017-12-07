package mysqlpackage.domain;

public class Product {

    private int productId;
    private String name;
    private double weight;
    private int categoryId_fk;

    public Product(int productId, String name, double weight, int categoryId_fk) {
        this.productId = productId;
        this.name = name;
        this.weight = weight;
        this.categoryId_fk = categoryId_fk;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getCategoryId_fk() {
        return categoryId_fk;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", categoryId_fk=" + categoryId_fk +
                '}';
    }
}

