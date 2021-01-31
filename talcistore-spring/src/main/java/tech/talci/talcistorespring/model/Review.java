package tech.talci.talcistorespring.model;

import lombok.AllArgsConstructor;
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
public class Review {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private User user;

    @ManyToOne(fetch = LAZY)
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

    public static ReviewBuilder builder() {
        return  new ReviewBuilder();
    }

    public static class ReviewBuilder {
        private Long id;
        private User user;
        private Product product;
        private Double rating;
        private String comment;
        private LocalDate date;

        private ReviewBuilder() {}

        public ReviewBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ReviewBuilder user(User user) {
            this.user = user;
            return this;
        }

        public ReviewBuilder product(Product product) {
            this.product = product;
            return this;
        }

        public ReviewBuilder rating(Double rating) {
            this.rating = rating;
            return this;
        }

        public ReviewBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public ReviewBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Review build() {
            return new Review(id, user, product, rating, comment, date);
        }
    }

}
