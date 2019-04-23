package hu.bme.sch.bss.webcentral.controller.core;

import hu.bme.sch.bss.webcentral.core.domain.UserListResponse;
import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.domain.UserResponse;
import hu.bme.sch.bss.webcentral.core.model.User;
import hu.bme.sch.bss.webcentral.core.service.UserService;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
public class UserController {

	private static final String REQUEST_USER_CREATE = "Request for user creation received. {}";
	private static final String REQUEST_USER_SEARCH = "Request to find user received for id {}";
	private static final String REQUEST_USERS_LIST = "Request to find all users received.";
	private final UserService userService;
	private final Logger logger;

	public UserController(final UserService userService, final Logger logger) {
		this.userService = userService;
		this.logger = logger;
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public final UserResponse createUser(@Valid @RequestBody final UserRequest request) {
		logger.info(REQUEST_USER_CREATE, request);
		User result = userService.create(request);
		return new UserResponse(result);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public final UserResponse getUser(@PathVariable("id") final Long id) {
		logger.info(REQUEST_USER_SEARCH, id);
		User result = userService.findById(id);
		return new UserResponse(result);
	}

	@GetMapping("/all")
	@ResponseStatus(HttpStatus.FOUND)
	public final UserListResponse listAllUsers() {
		logger.info(REQUEST_USERS_LIST);
		ArrayList<User> users = new ArrayList<>(userService.findAll());
		return UserListResponse.builder()
				.withUsers(users)
				.build();

	}

}