package co.istad.productapi.service.implement;

import co.istad.productapi.dto.user.request.CreateUserRequest;
import co.istad.productapi.dto.user.response.UserResponse;
import co.istad.productapi.entity.Profile;
import co.istad.productapi.mapper.ProductMapper;
import co.istad.productapi.mapper.UserMapper;
import co.istad.productapi.repository.ProfileRepository;
import co.istad.productapi.repository.UserRepository;
import co.istad.productapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileRepository profileRepository;

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        var user = userMapper.toUser(createUserRequest);
        var profile = new Profile();
        profile.setBio(createUserRequest.bio());
        profile.setProfileUrl(createUserRequest.profileUrl());
        profile.setUser(user);
        user.setProfile(profile);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream().map(userMapper::toUserResponse)
                .toList();
    }
}
