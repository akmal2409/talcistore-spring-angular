package tech.talci.talcistorespring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotEmpty(message = "Product name is required")
    private String productName;

    @NotEmpty(message = "Product description is required")
    private String description;

    @DecimalMin(value = "1.0", inclusive = true, message = "Price per unit must be greater than {value}")
    private BigDecimal pricePerUnit;
    @NotEmpty(message = "Category id is required")
    private Long categoryId;
    @NotEmpty(message = "Country of origin is required")
    private String countryOfOrigin;
    @NotEmpty(message = "Producer is required")
    private String producer;

    @Min(value = 0, message = "Amount in stock must be greater than {value}")
    private Long leftInStock;
    private Double rating;
    private Long orderCount;
    private LocalDate addedOn;
    private LocalDate lastUpdated;
    private Long sellerId;
}
