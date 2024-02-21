package br.com.joaogabriel.proposal.service;

import br.com.joaogabriel.proposal.dto.request.ProposalPostRequest;
import br.com.joaogabriel.proposal.dto.response.ProposalGetResponse;

import java.util.List;
import java.util.UUID;

public interface ProposalService {

    ProposalGetResponse create(final ProposalPostRequest proposalPostRequest);

    List<ProposalGetResponse> findAll();

    ProposalGetResponse findById(final UUID id);

}
