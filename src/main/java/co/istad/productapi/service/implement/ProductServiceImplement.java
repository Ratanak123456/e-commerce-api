package co.istad.productapi.service.implement;

import co.istad.productapi.dto.product.request.ProductRequest;
import co.istad.productapi.dto.product.response.ProductResponse;
import co.istad.productapi.dto.product.request.UpdateProductRequest;
import co.istad.productapi.entity.Product;
import co.istad.productapi.rescontroller.repository.ProductRepositoryJPA;
import co.istad.productapi.rescontroller.repository.ProductRepositoryOld;
import co.istad.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImplement implements ProductService {
    // inject the repository here
    //private final ProductRepositoryOld productRepositoryOld;
    private final ProductRepositoryJPA productRepository;


    private Product mapToEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDec(request.des());
        product.setPrice(request.price());

        return product;
    }
    // mapToResponse -> convert Entity to Response
    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDec(),
                product.getPrice()
        );
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        // create entity product from the request
        var product = mapToEntity(request);
        // set static userID
        product.setUserId(1);
        // insert the data to the table only need to
        // repository.save(entity) = insert
        return mapToResponse(productRepository.save(product));

    }

    @Override
    public List<ProductResponse> findAllProducts() {
        // repository.findAll()
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        var product =  productRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Product with ID = "+id+" not found"));

        return mapToResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Integer id , UpdateProductRequest request) {
        // find existing product
        // repository.findById
        var existingProduct = productRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Product with ID = "+id+" not found"));

        if(request.name()!=null)
            existingProduct.setName(request.name());
        if(request.dec()!=null)
            existingProduct.setDec(request.dec());
        if(request.price()!=null)
            existingProduct.setPrice(request.price());
        // update product
        productRepository.save(existingProduct);
        return mapToResponse(existingProduct);
    }


    @Override
    public boolean deleteProduct(Integer id) {
        // find if the product exist
        // if it's we delete it and return true
        // else return false

        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
