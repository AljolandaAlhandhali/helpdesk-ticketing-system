package helpdesk_ticketing_system.controller;

import helpdesk_ticketing_system.dto.AssignTicketRequest;
import helpdesk_ticketing_system.dto.CreateTicketRequest;
import helpdesk_ticketing_system.model.Ticket;
import helpdesk_ticketing_system.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}