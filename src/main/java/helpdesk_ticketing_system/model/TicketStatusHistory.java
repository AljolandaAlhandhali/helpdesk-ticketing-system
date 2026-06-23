package helpdesk_ticketing_system.model;

import helpdesk_ticketing_system.enums.TicketStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TICKET_STATUS_HISTORY")
public class TicketStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID")
    private Long historyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "OLD_STATUS")
    private TicketStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "NEW_STATUS")
    private TicketStatus newStatus;

    @Column(name = "CHANGED_AT")
    private LocalDateTime changedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "TICKET_ID")
    private Ticket ticket;

    public TicketStatusHistory() {
    }

}