package co.istad.productapi.service.implement;

import co.istad.productapi.dto.product.request.ProductRequest;
import co.istad.productapi.dto.product.response.ProductResponse;
import co.istad.productapi.dto.product.request.UpdateProductRequest;
import co.istad.productapi.entity.Category;
import co.istad.productapi.entity.Product;
import co.istad.productapi.mapper.ProductMapper;
import co.istad.productapi.repository.CategoryRepositoryJPA;
import co.istad.productapi.repository.ProductRepositoryJPA;
import co.istad.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> findAllProducts() {
        // repository.findAll()
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToResponse)
                .toList();
    }

    @Override
    public Page<ProductResponse> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::mapToResponse);
    }


    @Override
    public ProductResponse createProduct(ProductRequest request) {
        // create entity product from the request
        var product = productMapper.mapToProduct(request);
        // set static userID
        product.setUserId(1);
        // insert the data to the table only need to
        // repository.save(entity) = insert
        return productMapper.mapToResponse(productRepository.save(product));

    }


    @Override
    public ProductResponse findProductById(Integer id) {
        var product =  productRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Product with ID = "+id+" not found"));

        return productMapper.mapToResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Integer id , UpdateProductRequest request) {
        // find existing product
        // repository.findById
        var existingProduct = productRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Product with ID = "+id+" not found"));

        if(request.name()!=null)
            existingProduct.setName(request.name());
        if(request.description()!=null)
            existingProduct.setDescription(request.description());
        if(request.price()!=null)
            existingProduct.setPrice(request.price());
        // update product
        productRepository.save(existingProduct);
        return productMapper.mapToResponse(existingProduct);
    }



    // TODO: make it like we delete in the category
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