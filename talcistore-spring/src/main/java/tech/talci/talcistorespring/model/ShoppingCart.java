package tech.talci.talcistorespring.model;

import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(fetch = LAZY)
    private List<CartItem> products;

    @OneToOne(fetch = LAZY)
    private User user;
}
