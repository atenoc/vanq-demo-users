package vanq.demo.users.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(

		String username,
	    String email,
	    String password,
	    String firstName,
	    String lastName,
	    Boolean enabled

) {
}