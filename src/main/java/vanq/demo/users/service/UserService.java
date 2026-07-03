package vanq.demo.users.service;

import vanq.demo.users.dto.request.CreateUserRequest;
import vanq.demo.users.dto.request.UpdateUserRequest;
import vanq.demo.users.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    List<UserResponse> findAll();

    UserResponse findById(UUID id);

    UserResponse update(UUID id, UpdateUserRequest request);

    void delete(UUID id);

}