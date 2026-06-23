package helpdesk_ticketing_system.service;

import helpdesk_ticketing_system.dto.CreateUserRequest;
import helpdesk_ticketing_system.model.User;
import helpdesk_ticketing_system.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest request) {
        User user = new User();

    }
}