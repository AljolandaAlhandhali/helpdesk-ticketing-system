package helpdesk_ticketing_system.controller;

import helpdesk_ticketing_system.dto.CreateUserRequest;
import helpdesk_ticketing_system.model.User;
import helpdesk_ticketing_system.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }
}