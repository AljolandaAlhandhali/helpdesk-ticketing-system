package helpdesk_ticketing_system.model;

import helpdesk_ticketing_system.enums.Category;
import helpdesk_ticketing_system.enums.Priority;
import helpdesk_ticketing_system.enums.TicketStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private TicketStatus status = TicketStatus.OPEN;

    @Enumerated(EnumType.STRING)
    private Category category;

}