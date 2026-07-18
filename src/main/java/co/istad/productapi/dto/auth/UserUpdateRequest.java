package co.istad.productapi.dto.auth;

import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * The optional fields a signed-in user may change on their own profile.
 */
@Builder
public record UserUpdateRequest(
        @Size(max = 255) String firstName,
        @Size(max = 255) String lastName,
        @Size(max = 100) String gender,
        @Size(max = 2_000) String biography,
        @Size(max = 2_048) String profileUrl
) {
}
