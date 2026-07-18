package co.istad.productapi.restcontrollers;


import co.istad.productapi.dto.auth.RegisterRequest;
import co.istad.productapi.dto.auth.RegisterResponse;
import co.istad.productapi.dto.auth.UserUpdateRequest;
import co.istad.productapi.dto.user.UserResponse;
import co.istad.productapi.service.AuthService;
import co.istad.productapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthRestRestController{
    private final AuthService authService;
    private final UserService userService;


    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/forgot-password/{email}")
    public String forgotPassword(@PathVariable String email) {
        authService.forgotPassword(email);
        return "If the email address is registered, a password-reset link has been sent.";
    }

    @GetMapping("/profile")
    public UserResponse getProfile(@AuthenticationPrincipal Jwt jwt) {
        return userService.getUserByKeycloakId(jwt.getSubject());
    }

    @PutMapping("/profile")
    public UserResponse updateProfile(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        return authService.updateUser(jwt.getSubject(), request);
    }
}
