package vanq.demo.users.dto.request;

public record UpdateUserRequest(

	    String username,
	    String email,
	    String password,
	    String firstName,
	    String lastName,
	    Boolean enabled

	) {}