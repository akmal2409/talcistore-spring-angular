package tech.talci.talcistorespring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Discount percent cannot be smaller than {value}")
    @Max(value = 99, message = "Discount percent cannot be greater than {value}")
    private Integer percent;

    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate from;

    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate till;

    @OneToOne(fetch = LAZY, mappedBy = "discount")
    private Product product;
}
