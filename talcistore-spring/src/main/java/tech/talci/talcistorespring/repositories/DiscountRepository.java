package tech.talci.talcistorespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcistorespring.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
