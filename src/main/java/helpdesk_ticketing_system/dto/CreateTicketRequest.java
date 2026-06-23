package helpdesk_ticketing_system.dto;

import helpdesk_ticketing_system.enums.Category;
import helpdesk_ticketing_system.enums.Priority;

public class CreateTicketRequest {
    private String title;
    private String description;
    private Priority priority;
    private Category category;
    private Long userId;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Category getCategory() {
        return category;
    }

}