package co.istad.productapi.service;

import co.istad.productapi.dto.product.request.ProductRequest;
import co.istad.productapi.dto.product.response.ProductResponse;
import co.istad.productapi.dto.product.request.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> findAllProduct();
    ProductResponse findProductById(Integer id);
    ProductResponse updateProduct (Integer id , UpdateProductRequest updateProductRequest);
    boolean deleteProduct(Integer id);

}
