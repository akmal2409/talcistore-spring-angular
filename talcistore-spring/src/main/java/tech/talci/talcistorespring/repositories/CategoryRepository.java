package tech.talci.talcistorespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcistorespring.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByCategoryName(String name);
}
