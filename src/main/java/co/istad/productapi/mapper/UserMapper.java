package co.istad.productapi.mapper;

import co.istad.productapi.dto.user.request.CreateUserRequest;
import co.istad.productapi.dto.user.response.UserResponse;
import co.istad.productapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "profileUrl", source = "profile.profileUrl")
    @Mapping(target="bio", source = "profile.bio")
    UserResponse toUserResponse(User user);
    User toUser(CreateUserRequest request);
}