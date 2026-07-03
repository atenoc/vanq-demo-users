package vanq.demo.users.service.impl;

import lombok.RequiredArgsConstructor;

import vanq.demo.users.common.exception.DuplicateResourceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vanq.demo.users.common.exception.ResourceNotFoundException;
import vanq.demo.users.dto.request.CreateUserRequest;
import vanq.demo.users.dto.request.UpdateUserRequest;
import vanq.demo.users.dto.response.UserResponse;
import vanq.demo.users.entity.User;
import vanq.demo.users.mapper.UserMapper;
import vanq.demo.users.repository.UserRepository;
import vanq.demo.users.service.UserService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(CreateUserRequest request) {

        User user = mapper.toEntity(request);

        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setPassword(passwordEncoder.encode(request.password()));

        if (repository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email already exists");
        }

        User savedUser = repository.save(user);

        // futuro Kafka event (aquí irá)
        // kafkaProducer.send(new UserCreatedEvent(savedUser.getId(), savedUser.getEmail()));
        // User Created → Kafka Event → Notification Service → Email / Push

        return mapper.toResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(UUID id) {

        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    @Transactional
    public UserResponse update(UUID id, UpdateUserRequest request) {

        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (request.email() != null
                && !request.email().equals(user.getEmail())
                && repository.existsByEmail(request.email())) {

            throw new DuplicateResourceException("Email already exists");
        }

        if (request.username() != null
                && !request.username().equals(user.getUsername())
                && repository.existsByUsername(request.username())) {

            throw new DuplicateResourceException("Username already exists");
        }

        mapper.updateEntityFromDto(request, user);

        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        User updatedUser = repository.save(user);

        return mapper.toResponse(updatedUser);
    }

    @Override
    public void delete(UUID id) {

        repository.deleteById(id);

    }

}