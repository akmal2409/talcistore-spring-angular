package tech.talci.talcistorespring.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.talci.talcistorespring.model.Category;
import tech.talci.talcistorespring.model.Product;
import tech.talci.talcistorespring.model.User;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAll(Pageable pageable);

    Page<Product> findByCategory(Category category, Pageable pageable);

    @Query("Select p from Product p where lower(p.productName) " +
            "like lower(concat('%', :query, '%')) " +
            "or lower(p.description) like lower(concat('%', :query, '%'))")
    Page<Product> findByProductNameOrDescriptionContaining(String query, Pageable pageable);

    Page<Product> findBySeller(User seller, Pageable pageable);

    Long countByCategory(Category category);
}
