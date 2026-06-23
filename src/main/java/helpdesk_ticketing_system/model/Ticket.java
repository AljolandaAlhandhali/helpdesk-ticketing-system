package helpdesk_ticketing_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

}