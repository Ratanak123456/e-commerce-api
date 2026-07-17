package co.istad.productapi.service;

import co.istad.productapi.dto.ProductFilter;
import co.istad.productapi.dto.product.ProductRequest;
import co.istad.productapi.dto.product.ProductResponse;
import co.istad.productapi.dto.product.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// For the loosely coupling design
// This interface will be implemented by other class

public interface ProductService {
    ProductResponse createProduct(ProductRequest product);
    List<ProductResponse> findAllProducts();

    // for the pagination support when get all products
    Page<ProductResponse> findAllProducts(Pageable pageable, ProductFilter filter);
    // Page<ProductResponse> name(String keywords, Pageable page);

    ProductResponse findProductById(Integer id);
    ProductResponse updateProduct(Integer id, UpdateProductRequest request);
    boolean deleteProduct(Integer id);
}
