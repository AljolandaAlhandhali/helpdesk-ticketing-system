package helpdesk_ticketing_system.model;

import helpdesk_ticketing_system.enums.TicketStatus;
import jakarta.persistence.*;

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

}