package br.com.joaogabriel.proposal.service;

import br.com.joaogabriel.proposal.entity.Proposal;

public interface NotificationService {

    void notificate(Proposal proposal, String exchange);
}
