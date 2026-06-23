package helpdesk_ticketing_system.service;

import helpdesk_ticketing_system.repository.*;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final AgentRepository agentRepository;
    private final CommentRepository commentRepository;
    private final TicketStatusHistoryRepository ticketStatusHistoryRepository;

    public TicketService(TicketRepository ticketRepository,
                         UserRepository userRepository,
                         AgentRepository agentRepository,
                         CommentRepository commentRepository,
                         TicketStatusHistoryRepository ticketStatusHistoryRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.agentRepository = agentRepository;
        this.commentRepository = commentRepository;
        this.ticketStatusHistoryRepository = ticketStatusHistoryRepository;
    }
}