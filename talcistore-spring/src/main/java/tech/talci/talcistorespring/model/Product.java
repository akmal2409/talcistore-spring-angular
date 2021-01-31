package tech.talci.talcistorespring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long productId;

    @NotEmpty(message = "Product name is required")
    private String name;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than {value}")
    private BigDecimal price;

    @ManyToMany(fetch = LAZY, mappedBy = "products")
    private List<Category> categories;

    @NotEmpty(message = "Country of origin is required")
    private String countryOfOrigin;

    @NotEmpty(message = "Producer is required")
    private String producer;

    @Min(value = 0, message = "Amount in stock must be greater than {value}")
    private Integer leftInStock;

    @DecimalMax(value = "5.0", inclusive = true, message = "Rating cannot be greater than 5.0")
    @DecimalMin(value = "1.0", inclusive = true, message = "Rating cannot be smaller than 1.0")
    private Double rating;

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private Long id;
        private String name;
        private BigDecimal price;
        private List<Category> categories;
        private String countryOfOrigin;
        private String producer;
        private Integer leftInStock;
        private Double rating;

        private ProductBuilder() {}

        public ProductBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductBuilder category(List<Category> categories) {
            this.categories = categories;
            return this;
        }

        public ProductBuilder countryOfOrigin(String countryOfOrigin) {
            this.countryOfOrigin = countryOfOrigin;
            return this;
        }

        public ProductBuilder producer(String producer) {
            this.producer = producer;
            return this;
        }

        public ProductBuilder leftInStock(Integer leftInStock) {
            this.leftInStock = leftInStock;
            return this;
        }

        public ProductBuilder rating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Product build() {
            return new Product(id, name, price, categories,
                    countryOfOrigin, producer, leftInStock, rating);
        }
    }
}
