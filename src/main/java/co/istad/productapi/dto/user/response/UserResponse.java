package co.istad.productapi.dto.user.response;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id ,
        String email,
        String profileUrl,
        String bio
) {
}