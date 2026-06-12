package co.istad.productapi.dto.product.request;

import jakarta.validation.constraints.Positive;

public record UpdateProductRequest(

        String name,
        String description,

        @Positive
        Float price

) {
}