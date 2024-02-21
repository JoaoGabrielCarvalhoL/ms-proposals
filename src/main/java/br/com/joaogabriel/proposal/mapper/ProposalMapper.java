package br.com.joaogabriel.proposal.mapper;

import br.com.joaogabriel.proposal.dto.request.ProposalPostRequest;
import br.com.joaogabriel.proposal.dto.response.ProposalGetResponse;
import br.com.joaogabriel.proposal.entity.Proposal;

public interface ProposalMapper {

    Proposal toProposal(ProposalPostRequest proposalPostRequest);

    ProposalGetResponse toProposalGetResponse(Proposal proposal);
}
