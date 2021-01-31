package tech.talci.talcistorespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcistorespring.model.ShoppingCart;
import tech.talci.talcistorespring.model.User;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findShoppingCartByUser(User user);
}
