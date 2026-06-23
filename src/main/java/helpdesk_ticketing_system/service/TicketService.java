package helpdesk_ticketing_system.service;

import helpdesk_ticketing_system.dto.CreateTicketRequest;
import helpdesk_ticketing_system.enums.TicketStatus;
import helpdesk_ticketing_system.exception.ResourceNotFoundException;
import helpdesk_ticketing_system.model.Ticket;
import helpdesk_ticketing_system.model.User;
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

    // Create ticket
    public Ticket createTicket(CreateTicketRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        ticket.setCategory(request.getCategory());
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setUser(user);

        return ticketRepository.save(ticket);
    }
}