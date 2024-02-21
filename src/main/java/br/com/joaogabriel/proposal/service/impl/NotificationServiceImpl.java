package br.com.joaogabriel.proposal.service.impl;

import br.com.joaogabriel.proposal.dto.response.ProposalGetResponse;
import br.com.joaogabriel.proposal.entity.Proposal;
import br.com.joaogabriel.proposal.service.NotificationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final RabbitTemplate rabbitTemplate;

    public NotificationServiceImpl(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notificate(Proposal proposal, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposal);
    }
}
