package co.istad.productapi.dto.tag.request;

import lombok.Builder;

@Builder
public record TagRequest(
        String name
) {
}