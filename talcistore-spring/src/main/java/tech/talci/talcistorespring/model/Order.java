package tech.talci.talcistorespring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static tech.talci.talcistorespring.model.OrderStatus.PROCESSED;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = LAZY)
    private User user;

    @OneToMany(fetch = LAZY)
    private List<Product> products;

    private LocalDate placedOn;

    private String trackingNumber;

    @Enumerated(value = STRING)
    private OrderStatus status;

    private BigDecimal totalPrice;

    @PrePersist
    public void setupOrder() {
        this.placedOn = LocalDate.now();
        this.status = PROCESSED;
    }
}
