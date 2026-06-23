package helpdesk_ticketing_system.model;

import helpdesk_ticketing_system.enums.Priority;
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

}