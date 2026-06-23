package helpdesk_ticketing_system.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // Comment message
    private String message;

    // Date and time when comment was created
    private LocalDateTime createdAt = LocalDateTime.now();

    // Many comments belong to one ticket
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Comment() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

}