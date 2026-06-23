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

    public Long getUserId() {
        return userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}