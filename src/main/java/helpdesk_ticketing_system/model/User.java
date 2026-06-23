package helpdesk_ticketing_system.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private LocalDateTime createdAt = LocalDateTime.now();

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

}