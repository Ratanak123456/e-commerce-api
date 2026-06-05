package co.istad.productapi.dto.category.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Please Input the name")
        String name,
        @NotBlank(message = "Don't for get the Description!!!")
        String des,
        @NotBlank(message = "Please Insert Ur Icon")
        String icon
) {
}
