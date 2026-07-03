package vanq.demo.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(

		@NotBlank(message = "El nombre de usuario es obligatorio")
        String username,

        @Email
        String email,

        @NotBlank
        String password,

        String firstName,

        String lastName

) {
}