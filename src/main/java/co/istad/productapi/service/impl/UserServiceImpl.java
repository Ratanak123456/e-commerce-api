package co.istad.productapi.service.impl;

import co.istad.productapi.dto.user.CreateUserRequest;
import co.istad.productapi.dto.user.UserResponse;
import co.istad.productapi.entity.Profile;
import co.istad.productapi.mapper.UserMapper;
import co.istad.productapi.repository.ProfileRepository;
import co.istad.productapi.repository.UserRepository;
import co.istad.productapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Override
    public UserResponse createUser(CreateUserRequest request) {

        var user = userMapper.toUser(request);
        var profile = new Profile();

        profile.setBio(request.bio());
        profile.setProfileUrl(request.profileUrl());
         // linked profile to user
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

    @Override
    public UserResponse getUserByKeycloakId(String keycloakId) {
        return userMapper.toUserResponse(userRepository.findByKeycloakId(keycloakId).orElseThrow(
                ()-> new NoSuchElementException("user not found with id: " + keycloakId)
        ));
    }
}
