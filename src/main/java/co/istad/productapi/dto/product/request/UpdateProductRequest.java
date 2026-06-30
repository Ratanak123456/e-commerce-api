package co.istad.productapi.dto.product.request;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateProductRequest(

        String name,
        String description,

        @Positive
        BigDecimal price

) {
}