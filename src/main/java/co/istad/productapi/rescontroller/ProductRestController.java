package co.istad.productapi.rescontroller;

import co.istad.productapi.dto.ProductRequest;
import co.istad.productapi.dto.ProductResponse;
import co.istad.productapi.dto.UpdateProductRequest;
import co.istad.productapi.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {
    //Service
    private final ProductService productService;
    //Method Handlers
    @GetMapping
    public List<ProductResponse> getAllProduct(){
        return productService.findAllProduct();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductByID(@PathVariable Integer id ){
        return productService.findProductById(id);
    }

    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest request){
        return productService.createProduct(request);
    }

    @PatchMapping("/{id}")
    public ProductResponse updateProduct (@PathVariable Integer id , @RequestBody UpdateProductRequest request){
        return productService.updateProduct(id, request);
    }
}
