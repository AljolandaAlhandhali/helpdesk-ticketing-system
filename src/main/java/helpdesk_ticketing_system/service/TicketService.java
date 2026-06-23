package helpdesk_ticketing_system.service;

import helpdesk_ticketing_system.dto.AssignTicketRequest;
import helpdesk_ticketing_system.dto.CreateCommentRequest;
import helpdesk_ticketing_system.dto.CreateTicketRequest;
import helpdesk_ticketing_system.dto.UpdateStatusRequest;
import helpdesk_ticketing_system.enums.Category;
import helpdesk_ticketing_system.enums.Priority;
import helpdesk_ticketing_system.enums.TicketStatus;
import helpdesk_ticketing_system.exception.BusinessRuleException;
import helpdesk_ticketing_system.exception.ResourceNotFoundException;
import helpdesk_ticketing_system.model.*;
import helpdesk_ticketing_system.repository.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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

    // Assign ticket to agent
    public Ticket assignTicket(Long ticketId, AssignTicketRequest request) {
        Ticket ticket = getTicketById(ticketId);

        if (ticket.getStatus() == TicketStatus.CLOSED) {
            throw new BusinessRuleException("Cannot assign a closed ticket");
        }

        Agent agent = agentRepository.findById(request.getAgentId())
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found"));

        ticket.setAgent(agent);
        return ticketRepository.save(ticket);
    }

    // Update status with rules
    public Ticket updateStatus(Long ticketId, UpdateStatusRequest request) {
        Ticket ticket = getTicketById(ticketId);

        TicketStatus current = ticket.getStatus();
        TicketStatus next = request.getStatus();

        if (!isValidTransition(current, next)) {
            throw new BusinessRuleException(
                    "Invalid status transition from " + current + " to " + next);
        }

        TicketStatusHistory history = new TicketStatusHistory();
        history.setOldStatus(current);
        history.setNewStatus(next);
        history.setTicket(ticket);
        ticketStatusHistoryRepository.save(history);

        ticket.setStatus(next);

        if (next == TicketStatus.RESOLVED) {
            ticket.setResolvedAt(LocalDateTime.now());
        }

        return ticketRepository.save(ticket);
    }

    // Allowed status transitions
    private boolean isValidTransition(TicketStatus current, TicketStatus next) {
        return (current == TicketStatus.OPEN && next == TicketStatus.IN_PROGRESS)
                || (current == TicketStatus.IN_PROGRESS && next == TicketStatus.RESOLVED)
                || (current == TicketStatus.RESOLVED && next == TicketStatus.CLOSED)
                || (current == TicketStatus.CLOSED && next == TicketStatus.REOPENED)
                || (current == TicketStatus.REOPENED && next == TicketStatus.RESOLVED);
    }

    // Add comment
    public Comment addComment(Long ticketId, CreateCommentRequest request) {
        Ticket ticket = getTicketById(ticketId);

        Comment comment = new Comment();
        comment.setMessage(request.getMessage());
        comment.setTicket(ticket);

        return commentRepository.save(comment);
    }

    // Get ticket by id
    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
    }

    // Search tickets
    public List<Ticket> searchTickets(TicketStatus status, Priority priority, Category category, Long assignedTo) {
        if (status != null) return ticketRepository.findByStatus(status);
        if (priority != null) return ticketRepository.findByPriority(priority);
        if (category != null) return ticketRepository.findByCategory(category);
        if (assignedTo != null) return ticketRepository.findByAgentAgentId(assignedTo);

        return ticketRepository.findAll();
    }

    // Average resolution time in hours
    public double averageResolutionTime() {
        return ticketRepository.findAll()
                .stream()
                .filter(ticket -> ticket.getResolvedAt() != null)
                .mapToLong(ticket -> Duration.between(ticket.getCreatedAt(), ticket.getResolvedAt()).toHours())
                .average()
                .orElse(0);
    }

    // Overdue tickets
    public List<Ticket> getOverdueTickets() {
        LocalDateTime now = LocalDateTime.now();

        return ticketRepository.findAll()
                .stream()
                .filter(ticket -> ticket.getStatus() != TicketStatus.CLOSED)
                .filter(ticket -> now.isAfter(ticket.getCreatedAt().plus(getSlaDuration(ticket.getPriority()))))
                .toList();
    }

    // SLA by priority
    private Duration getSlaDuration(Priority priority) {
        return switch (priority) {
            case CRITICAL -> Duration.ofHours(4);
            case HIGH -> Duration.ofDays(1);
            case MEDIUM -> Duration.ofDays(3);
            case LOW -> Duration.ofDays(7);
        };
    }

}