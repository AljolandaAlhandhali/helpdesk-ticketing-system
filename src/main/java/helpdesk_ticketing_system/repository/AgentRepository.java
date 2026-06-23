package helpdesk_ticketing_system.repository;

import helpdesk_ticketing_system.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

// Handles database operations for Agent
public interface AgentRepository extends JpaRepository<Agent, Long> {
}