package co.istad.productapi.rescontroller;

import co.istad.productapi.dto.user.request.CreateUserRequest;
import co.istad.productapi.dto.user.response.UserResponse;
import co.istad.productapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }
    @PostMapping
    public UserResponse createNew(@RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }
}