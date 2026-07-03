package vanq.demo.users.mapper;

import org.mapstruct.*;
import vanq.demo.users.dto.request.CreateUserRequest;
import vanq.demo.users.dto.response.UserResponse;
import vanq.demo.users.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(CreateUserRequest request);

    UserResponse toResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(
            vanq.demo.users.dto.request.UpdateUserRequest request,
            @MappingTarget User user
    );

}