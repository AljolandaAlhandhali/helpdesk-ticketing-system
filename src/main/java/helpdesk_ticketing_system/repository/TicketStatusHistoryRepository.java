package helpdesk_ticketing_system.repository;

import helpdesk_ticketing_system.model.TicketStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketStatusHistoryRepository extends JpaRepository<TicketStatusHistory, Long> {

    List<TicketStatusHistory> findByTicketTicketId(Long ticketId);
}