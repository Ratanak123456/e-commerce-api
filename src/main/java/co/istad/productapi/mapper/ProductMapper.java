package co.istad.productapi.mapper;

import co.istad.productapi.dto.product.request.ProductRequest;
import co.istad.productapi.dto.product.response.ProductResponse;
import co.istad.productapi.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {CategoryMapper.class})
public interface ProductMapper {
    // turn tags object into pure string
    // ["iphone","17 pro max" , "apple"]
    // [{"id":1,....
    ProductResponse mapToResponse(Product request);
    Product mapToProduct(ProductRequest request);
}