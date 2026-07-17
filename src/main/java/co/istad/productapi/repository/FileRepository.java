package co.istad.productapi.repository;

import co.istad.productapi.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileUpload, Long> {
    // find the file by name inside the table
    Optional<FileUpload> findByName(String name);
}
