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

    public Long getHistoryId() {
        return historyId;
    }

    public TicketStatus getOldStatus() {
        return oldStatus;
    }

    public TicketStatus getNewStatus() {
        return newStatus;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setOldStatus(TicketStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public void setNewStatus(TicketStatus newStatus) {
        this.newStatus = newStatus;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

}