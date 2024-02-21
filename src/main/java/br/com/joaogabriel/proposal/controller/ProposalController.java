package br.com.joaogabriel.proposal.controller;

import br.com.joaogabriel.proposal.dto.request.ProposalPostRequest;
import br.com.joaogabriel.proposal.dto.response.ProposalGetResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ProposalController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<ProposalGetResponse> save(@RequestBody @Valid ProposalPostRequest proposalPostRequest);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProposalGetResponse> findById(@PathVariable("id") final UUID id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<ProposalGetResponse>> findAll();
}
