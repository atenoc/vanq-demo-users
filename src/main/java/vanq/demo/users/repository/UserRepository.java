package vanq.demo.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vanq.demo.users.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}