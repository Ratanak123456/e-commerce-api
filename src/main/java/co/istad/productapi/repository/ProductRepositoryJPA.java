package co.istad.productapi.repository;

import co.istad.productapi.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepositoryJPA extends JpaRepository<Product, Integer> {
    List<Product> findByIsDeletedFalse();

    Page<Product> findByIsDeletedFalse(Pageable pageable);

    Optional<Product> findByIdAndIsDeletedFalse(Integer id);
}
