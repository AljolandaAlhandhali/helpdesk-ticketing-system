package helpdesk_ticketing_system.repository;

import helpdesk_ticketing_system.enums.Category;
import helpdesk_ticketing_system.enums.Priority;
import helpdesk_ticketing_system.enums.TicketStatus;
import helpdesk_ticketing_system.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Handles database operations for Ticket
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStatus(TicketStatus status);

    List<Ticket> findByPriority(Priority priority);

    List<Ticket> findByCategory(Category category);

    List<Ticket> findByAgentAgentId(Long agentId);
}