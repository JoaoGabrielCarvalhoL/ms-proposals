package br.com.joaogabriel.proposal.repository;

import br.com.joaogabriel.proposal.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProposalRepository extends JpaRepository<Proposal, UUID> {

    List<Proposal> findAllByIsIntegratedIsFalse();
}
