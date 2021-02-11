package tech.talci.talcistorespring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @DecimalMin(value = "1.0", inclusive = true, message = "Rating cannot be less than 1.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "Rating cannot be greater than 5.0")
    private Double rating;

    @NotEmpty(message = "Comment is required")
    private String comment;

    private LocalDate date;

    @PrePersist
    public void setupDate() {
        this.date = LocalDate.now();
    }
}
