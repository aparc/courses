package hibernate.domain;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory {

    @Id
    @Column(name = "CATEGORY_Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq_gen")
    @SequenceGenerator(name = "category_id_seq_gen", sequenceName = "category_id_seq")
    private int categoryId;
    @Column(name = "NAME", length = 100, unique = true, nullable = false)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {

        return categoryId;
    }

    public String getName() {
        return name;
    }
}
