package co.istad.productapi.service;

import co.istad.productapi.dto.user.request.CreateUserRequest;
import co.istad.productapi.dto.user.response.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserResponse createUser (CreateUserRequest createUserRequest);
    List<UserResponse> getAllUsers ();
}
