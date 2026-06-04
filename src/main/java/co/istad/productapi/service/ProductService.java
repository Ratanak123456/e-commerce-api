package co.istad.productapi.service;

import co.istad.productapi.dto.ProductRequest;
import co.istad.productapi.dto.ProductResponse;
import co.istad.productapi.dto.UpdateProductRequest;
import co.istad.productapi.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> findAllProduct();
    ProductResponse findProductById(Integer id);
    ProductResponse updateProduct (Integer id , UpdateProductRequest updateProductRequest);
    boolean deleteProduct(int id);

}
