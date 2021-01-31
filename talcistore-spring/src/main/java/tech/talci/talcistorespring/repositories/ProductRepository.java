package tech.talci.talcistorespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcistorespring.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
