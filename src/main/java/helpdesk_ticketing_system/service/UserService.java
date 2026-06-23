package helpdesk_ticketing_system.service;

import helpdesk_ticketing_system.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
}