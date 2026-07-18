package co.istad.productapi.service;

import co.istad.productapi.dto.auth.RegisterRequest;
import co.istad.productapi.dto.auth.RegisterResponse;
import co.istad.productapi.dto.auth.UserUpdateRequest;
import co.istad.productapi.dto.user.UserResponse;

public interface AuthService {

    // register the new user
    RegisterResponse register(RegisterRequest request );

    UserResponse updateUser(String keycloakId, UserUpdateRequest request);

    void forgotPassword(String email);
}
