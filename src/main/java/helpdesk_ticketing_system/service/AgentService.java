package helpdesk_ticketing_system.service;

import helpdesk_ticketing_system.dto.CreateAgentRequest;
import helpdesk_ticketing_system.model.Agent;
import helpdesk_ticketing_system.repository.AgentRepository;
import org.springframework.stereotype.Service;

@Service
public class AgentService {
    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    // Create new agent
    public Agent createAgent(CreateAgentRequest request) {
        Agent agent = new Agent();
        agent.setFirstName(request.getFirstName());
        agent.setLastName(request.getLastName());

    }

}