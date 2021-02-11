package tech.talci.talcistorespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcistorespring.model.Product;
import tech.talci.talcistorespring.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    ProductImage findByProduct(Product product);
}
