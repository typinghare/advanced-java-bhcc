package edu.bhcc.superbudget.repository;

import edu.bhcc.superbudget.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Category repository.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findFirstByName(String categoryName);
}
