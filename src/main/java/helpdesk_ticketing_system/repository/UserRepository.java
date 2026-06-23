package helpdesk_ticketing_system.repository;

import helpdesk_ticketing_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Handles database operations for User
public interface UserRepository extends JpaRepository<User, Long> {
}