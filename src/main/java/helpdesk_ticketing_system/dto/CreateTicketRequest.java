package helpdesk_ticketing_system.dto;

import helpdesk_ticketing_system.enums.Priority;

public class CreateTicketRequest {
    private String title;
    private String description;
    private Priority priority;

}