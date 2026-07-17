package co.istad.productapi.restcontrollers;


import co.istad.productapi.dto.user.CreateUserRequest;
import co.istad.productapi.dto.user.UserResponse;
import co.istad.productapi.service.UserService;
import lombok.Getter;
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
