package helpdesk_ticketing_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TICKET_STATUS_HISTORY")
public class TicketStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID")
    private Long historyId;

}