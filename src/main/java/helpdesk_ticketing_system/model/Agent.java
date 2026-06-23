package helpdesk_ticketing_system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agentId;

    // Agent first name
    private String firstName;
    // Agent last name
    private String lastName;

    // Agent email
    @Column(unique = true, nullable = false)
    private String email;

    // Date and time when agent was created
    private LocalDateTime createdAt = LocalDateTime.now();

    public Agent() {
    }

    public Long getAgentId() {
        return agentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
