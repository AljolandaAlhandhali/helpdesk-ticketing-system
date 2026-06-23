package helpdesk_ticketing_system.controller;

import helpdesk_ticketing_system.dto.AssignTicketRequest;
import helpdesk_ticketing_system.dto.CreateCommentRequest;
import helpdesk_ticketing_system.dto.CreateTicketRequest;
import helpdesk_ticketing_system.dto.UpdateStatusRequest;
import helpdesk_ticketing_system.enums.Category;
import helpdesk_ticketing_system.enums.Priority;
import helpdesk_ticketing_system.enums.TicketStatus;
import helpdesk_ticketing_system.model.Comment;
import helpdesk_ticketing_system.model.Ticket;
import helpdesk_ticketing_system.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Create ticket
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket createTicket(@RequestBody CreateTicketRequest request) {
        return ticketService.createTicket(request);
    }

    // Assign ticket to agent
    @PostMapping("/{ticketId}/assign")
    public Ticket assignTicket(@PathVariable Long ticketId,
                               @RequestBody AssignTicketRequest request) {
        return ticketService.assignTicket(ticketId, request);
    }

    // Update ticket status
    @PostMapping("/{ticketId}/status")
    public Ticket updateStatus(@PathVariable Long ticketId,
                               @RequestBody UpdateStatusRequest request) {
        return ticketService.updateStatus(ticketId, request);
    }

    // Add comment
    @PostMapping("/{ticketId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment addComment(@PathVariable Long ticketId,
                              @RequestBody CreateCommentRequest request) {
        return ticketService.addComment(ticketId, request);
    }

    // Get ticket details
    @GetMapping("/{ticketId}")
    public Ticket getTicketById(@PathVariable Long ticketId) {
        return ticketService.getTicketById(ticketId);
    }

    // Search tickets
    @GetMapping
    public List<Ticket> searchTickets(
            @RequestParam(required = false) TicketStatus status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Long assignedTo) {
        return ticketService.searchTickets(status, priority, category, assignedTo);
    }

    // Average resolution time
    @GetMapping("/metrics/avg-resolution-time")
    public double averageResolutionTime() {
        return ticketService.averageResolutionTime();
    }
}