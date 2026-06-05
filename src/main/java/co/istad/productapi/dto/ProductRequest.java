package co.istad.productapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank(message = "Please Insert Product Name!!")
        String name,
        @NotBlank(message = "Please Insert Description!!")
        String des,
        @NotNull(message = "Don't forget to put the price!!")
        @Positive (message = "Price must be positive!!")
        Float price
) {
}
