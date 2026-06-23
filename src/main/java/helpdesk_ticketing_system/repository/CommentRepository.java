package helpdesk_ticketing_system.repository;

import helpdesk_ticketing_system.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

// Handles database operations for Comment
public interface CommentRepository extends JpaRepository<Comment, Long> {
}