package tech.talci.talcistorespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcistorespring.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
