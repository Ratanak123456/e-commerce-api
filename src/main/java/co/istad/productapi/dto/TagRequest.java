package co.istad.productapi.dto;

import lombok.Builder;

@Builder
public record TagRequest(
        String name
) {
}
