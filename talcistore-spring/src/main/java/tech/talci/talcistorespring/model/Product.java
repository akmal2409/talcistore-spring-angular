package tech.talci.talcistorespring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty(message = "Product name is required")
    private String productName;

    @NotEmpty(message = "Description is required")
    private String description;

    @ManyToOne(fetch = LAZY)
    private User seller;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than {value}")
    private BigDecimal pricePerUnit;

    @ManyToOne(fetch = LAZY)
    private Category category;

    @NotEmpty(message = "Country of origin is required")
    private String countryOfOrigin;

    @NotEmpty(message = "Producer is required")
    private String producer;

    @Min(value = 0, message = "Amount in stock must be greater than {value}")
    private Long leftInStock;

    @DecimalMax(value = "5.0", inclusive = true, message = "Rating cannot be greater than 5.0")
    @DecimalMin(value = "1.0", inclusive = true, message = "Rating cannot be smaller than 1.0")
    private Double rating;

    @Min(value = 0)
    private Long orderCount;

    private BigDecimal shippingCost;

    private LocalDate addedOn;

    @UpdateTimestamp
    private LocalDate lastUpdated;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL)
    private List<ProductImage> images;


    @PrePersist
    private void setupProduct() {
        this.orderCount = 0L;
        this.rating = 1.0;
        this.addedOn = LocalDate.now();
    }
}
