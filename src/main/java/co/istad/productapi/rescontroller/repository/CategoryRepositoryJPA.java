package co.istad.productapi.rescontroller.repository;

import co.istad.productapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoryJPA extends JpaRepository<Category, Integer> {
    Boolean existsByName(String name);
}
