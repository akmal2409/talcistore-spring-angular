package tech.talci.talcistorespring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String description;
    private BigDecimal pricePerUnit;
    private Long categoryId;
    private String countryOfOrigin;
    private String producer;
    private Long leftInStock;
    private Double rating;
    private Long orderCount;
    private LocalDate addedOn;
    private LocalDate lastUpdated;
    private Long sellerId;
}
