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

}