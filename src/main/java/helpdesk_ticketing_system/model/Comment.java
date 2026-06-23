package helpdesk_ticketing_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // Comment message
    private String message;

}