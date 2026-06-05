package co.istad.productapi.service.implement;

import co.istad.productapi.dto.ProductRequest;
import co.istad.productapi.dto.ProductResponse;
import co.istad.productapi.dto.UpdateProductRequest;
import co.istad.productapi.entity.Product;
import co.istad.productapi.rescontroller.repository.ProductRepository;
import co.istad.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultLifecycleProcessor;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImplement implements ProductService {

    private final ProductRepository productRepository;
    private final DefaultLifecycleProcessor defaultLifecycleProcessor;
    private Integer nextId = 1004;

    private ProductResponse mapToResponse (Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDec(),
                product.getPrice()
        );
    }

    private Product mapToEntity (ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.name());
        product.setDec(productRequest.des());
        product.setPrice(productRequest.price());
        return product;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

        var newProduct = mapToEntity(productRequest);

        newProduct.setUserId(1);
        newProduct.setId(nextId++);
        return mapToResponse(productRepository.createProduct(newProduct));
    }

    @Override
    public List<ProductResponse> findAllProduct() {

        return productRepository.getProductList().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        var product = productRepository.findProductById(id);
        if (product == null){
            log.info("Product with id: {} not found", id);
            return null;
        }

        return mapToResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Integer id , UpdateProductRequest updateProductRequest) {

        var existingProduct = productRepository.findProductById(id);
        if (existingProduct == null){
            log.info("Product with id: {} does not exit!!!", id);
            return null;
        }

        if(updateProductRequest.name()!=null){
            existingProduct.setName(updateProductRequest.name());
        }

        if(updateProductRequest.dec()!=null){
            existingProduct.setDec(updateProductRequest.dec());
        }

        if(updateProductRequest.price()!=null){
            existingProduct.setPrice(updateProductRequest.price());
        }

        productRepository.updateProduct(existingProduct);
        return mapToResponse(existingProduct);
    }

    @Override
    public boolean deleteProduct(Integer id) {
        return false;
    }
}
