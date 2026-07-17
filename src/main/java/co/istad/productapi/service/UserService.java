package co.istad.productapi.service;

// Specification

import co.istad.productapi.dto.user.CreateUserRequest;
import co.istad.productapi.dto.user.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request );
    List<UserResponse> getAllUsers();
    UserResponse getUserByKeycloakId(String keycloakId);
}
