package br.com.joaogabriel.proposal.scheduler;

import br.com.joaogabriel.proposal.entity.Proposal;
import br.com.joaogabriel.proposal.repository.ProposalRepository;
import br.com.joaogabriel.proposal.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ProposalWithoutIntegration {

    private final ProposalRepository proposalRepository;
    private final NotificationService notificationService;
    private final String exchange;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ProposalWithoutIntegration(ProposalRepository proposalRepository, NotificationService notificationService,
                                      @Value("${rabbitmq.pending.proposal.exchange}") String exchange) {
        this.proposalRepository = proposalRepository;
        this.notificationService = notificationService;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 60, timeUnit = TimeUnit.SECONDS)
    public void findAllProposalsWithoutIntegration() {
        this.proposalRepository.findAllByIsIntegratedIsFalse().forEach(proposal -> {
            try {
                this.notificationService.notificate(proposal, exchange);
                updateProposal(proposal);
            } catch (Exception exception) {
                logger.error(exception.getMessage());
            }

        });
    }

    private void updateProposal(Proposal proposal) {
        proposal.setIntegrated(true);
        this.proposalRepository.save(proposal);
    }
}
