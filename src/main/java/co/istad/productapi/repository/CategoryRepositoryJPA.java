package co.istad.productapi.repository;

import co.istad.productapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryJPA extends JpaRepository<Category, Integer> {
    Boolean existsByName(String name);
}
