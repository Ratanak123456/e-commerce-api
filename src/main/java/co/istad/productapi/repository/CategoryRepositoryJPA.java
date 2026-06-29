package co.istad.productapi.repository;

import co.istad.productapi.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepositoryJPA extends JpaRepository<Category, Integer> {
    Boolean existsByName(String name);

    Boolean existsByNameAndIsDeletedFalse(String name);

    Boolean existsByNameAndIsDeletedFalseAndIdNot(String name, Integer id);

    List<Category> findByIsDeletedFalse();

    Page<Category> findByIsDeletedFalse(Pageable pageable);

    Optional<Category> findByIdAndIsDeletedFalse(Integer id);

    List<Category> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name);
}
