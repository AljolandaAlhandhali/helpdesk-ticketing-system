package helpdesk_ticketing_system.controller;

import helpdesk_ticketing_system.dto.CreateAgentRequest;
import helpdesk_ticketing_system.model.Agent;
import helpdesk_ticketing_system.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agents")
public class AgentController {
    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    // Create agent
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agent createAgent(@RequestBody CreateAgentRequest request) {
        return agentService.createAgent(request);
    }

}