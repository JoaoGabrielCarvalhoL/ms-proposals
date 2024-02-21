package br.com.joaogabriel.proposal.service.impl;

import br.com.joaogabriel.proposal.dto.request.ProposalPostRequest;
import br.com.joaogabriel.proposal.dto.response.ProposalGetResponse;
import br.com.joaogabriel.proposal.entity.Proposal;
import br.com.joaogabriel.proposal.exception.ResourceNotFoundException;
import br.com.joaogabriel.proposal.mapper.ProposalMapper;
import br.com.joaogabriel.proposal.repository.ProposalRepository;
import br.com.joaogabriel.proposal.service.NotificationService;
import br.com.joaogabriel.proposal.service.ProposalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProposalServiceImpl implements ProposalService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ProposalMapper proposalMapper;
    private final ProposalRepository proposalRepository;
    private final NotificationService notificationService;

    private final String exchange;

    public ProposalServiceImpl(ProposalMapper proposalMapper, ProposalRepository proposalRepository,
                               NotificationService notificationService,
                               @Value("${rabbitmq.pending.proposal.exchange}") String exchange) {
        this.proposalMapper = proposalMapper;
        this.proposalRepository = proposalRepository;
        this.notificationService = notificationService;
        this.exchange = exchange;
    }

    @Override
    public ProposalGetResponse create(ProposalPostRequest proposalPostRequest) {
        logger.info("Saving proposal into database. {}", proposalPostRequest);
        Proposal proposal = proposalMapper.toProposal(proposalPostRequest);
        Proposal saved = this.proposalRepository.save(proposal);
        notifyRabbitMQ(saved);
        return this.proposalMapper.toProposalGetResponse(saved);
    }

    @Override
    public List<ProposalGetResponse> findAll() {
        logger.info("Find all proposals");
        return this.proposalRepository.findAll().stream()
                .map(proposalMapper::toProposalGetResponse).toList();
    }

    @Override
    public ProposalGetResponse findById(UUID id) {
        logger.info("Getting proposal by id: {}", id);
        return this.proposalRepository.findById(id).map(proposalMapper::toProposalGetResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Proposal not found into database. Id: " + id));
    }

    private void notifyRabbitMQ(Proposal proposal) {
        try {
            this.notificationService.notificate(proposal, exchange);
        } catch (RuntimeException exception) {
            proposal.setIntegrated(false);
            this.proposalRepository.save(proposal);
        }

    }


}
