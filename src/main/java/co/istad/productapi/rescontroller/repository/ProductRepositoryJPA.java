package co.istad.productapi.rescontroller.repository;

import co.istad.productapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryJPA extends JpaRepository<Product, Integer> {
}
