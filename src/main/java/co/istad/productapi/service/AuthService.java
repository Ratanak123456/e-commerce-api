package co.istad.productapi.service;

import co.istad.productapi.dto.auth.RegisterRequest;
import co.istad.productapi.dto.auth.RegisterResponse;

public interface AuthService {

    // register the new user
    RegisterResponse register(RegisterRequest request );
}
