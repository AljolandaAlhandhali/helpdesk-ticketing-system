package helpdesk_ticketing_system.dto;

import helpdesk_ticketing_system.enums.TicketStatus;

public class UpdateStatusRequest {
    private TicketStatus status;

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

}