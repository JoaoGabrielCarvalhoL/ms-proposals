package br.com.joaogabriel.proposal.mapper.impl;

import br.com.joaogabriel.proposal.dto.request.ProposalPostRequest;
import br.com.joaogabriel.proposal.dto.response.ProposalGetResponse;
import br.com.joaogabriel.proposal.entity.Proposal;
import br.com.joaogabriel.proposal.entity.User;
import br.com.joaogabriel.proposal.mapper.ProposalMapper;
import org.springframework.stereotype.Component;


@Component
public class ProposalMapperImpl implements ProposalMapper {

    @Override
    public Proposal toProposal(ProposalPostRequest proposalPostRequest) {
        return new Proposal(proposalPostRequest.requestedAmount(), proposalPostRequest.deadlineInMonths(),
                false, true, "",
                new User(proposalPostRequest.firstName(), proposalPostRequest.lastName(),
                        proposalPostRequest.individualCertificate(), proposalPostRequest.cellphone(),
                        proposalPostRequest.income()));
    }

    @Override
    public ProposalGetResponse toProposalGetResponse(Proposal proposal) {
        return new ProposalGetResponse(proposal.getId(), proposal.getUser().getFirstName(),
                proposal.getUser().getLastName(), proposal.getUser().getCellphone(),
                proposal.getUser().getIndividualCertificate(), proposal.getUser().getIncome(), proposal.getRequestedAmount(),
                proposal.getDeadlineInMonths(), proposal.getApproved(), proposal.getObservations());
    }
}
