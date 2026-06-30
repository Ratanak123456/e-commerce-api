package co.istad.productapi.mapper;

import co.istad.productapi.dto.product.request.ProductRequest;
import co.istad.productapi.dto.product.response.ProductResponse;
import co.istad.productapi.entity.Product;
import co.istad.productapi.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" , uses = {CategoryMapper.class})
public interface ProductMapper {
    // turn tags object into pure string
    // ["iphone","17 pro max" , "apple"]
    // [{"id":1,....
    //@Mapping(target = "tags", ignore = true)
    @Mapping(target = "tags", source = "tags")
    ProductResponse mapToResponse(Product request);
    Product mapToProduct(ProductRequest request);


    // method for converting the Set<Tag> to Set<String>
    default Set<String> mapToString(Set<Tag> tags ){
        return tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
    }

}