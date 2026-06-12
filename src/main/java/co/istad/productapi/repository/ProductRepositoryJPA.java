package co.istad.productapi.repository;

import co.istad.productapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryJPA extends JpaRepository<Product, Integer> {
}