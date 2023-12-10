package Java.enit.Catalog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="products")
public class Product {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
    private Long product_id;
    private String name; 
    private String description;
    private int price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
