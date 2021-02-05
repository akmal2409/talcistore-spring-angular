package tech.talci.talcistorespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcistorespring.model.CartItem;
import tech.talci.talcistorespring.model.Product;
import tech.talci.talcistorespring.model.ShoppingCart;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartAndProduct(ShoppingCart cart, Product product);
}
