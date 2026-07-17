package co.istad.productapi.dto.product;

import java.math.BigDecimal;

public record UpdateProductRequest(
        String name,
        String description ,
        BigDecimal price
) {
}
