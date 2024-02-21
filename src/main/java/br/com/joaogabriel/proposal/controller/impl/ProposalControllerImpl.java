package br.com.joaogabriel.proposal.controller.impl;

import br.com.joaogabriel.proposal.controller.ProposalController;
import br.com.joaogabriel.proposal.dto.request.ProposalPostRequest;
import br.com.joaogabriel.proposal.dto.response.ProposalGetResponse;
import br.com.joaogabriel.proposal.service.ProposalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/proposals")
public class ProposalControllerImpl implements ProposalController {

    private final ProposalService proposalService;

    public ProposalControllerImpl(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @Override
    public ResponseEntity<ProposalGetResponse> save(ProposalPostRequest proposalPostRequest) {
        ProposalGetResponse proposal = this.proposalService.create(proposalPostRequest);
        URI uri = buildUri(proposal.id());
        return ResponseEntity.created(uri).body(proposal);
    }

    @Override
    public ResponseEntity<ProposalGetResponse> findById(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.proposalService.findById(id));
    }

    @Override
    public ResponseEntity<List<ProposalGetResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.proposalService.findAll());
    }

    private URI buildUri(UUID id){
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
    }
}
