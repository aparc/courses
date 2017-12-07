package mysqlpackage.domain;

public class ProductCategory {

    private int categoryId;
    private String name;

    public ProductCategory(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
