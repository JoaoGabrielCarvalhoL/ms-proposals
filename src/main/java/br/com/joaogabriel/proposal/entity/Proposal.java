package br.com.joaogabriel.proposal.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_proposal")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private BigDecimal requestedAmount;

    private Integer deadlineInMonths;

    private Boolean isApproved;

    private Boolean isIntegrated;

    private String observations;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private User user;

    public Proposal() {}

    public Proposal(BigDecimal requestedAmount, Integer deadlineInMonths, Boolean isApproved,
                    Boolean isIntegrated, String observations) {
        this.requestedAmount = requestedAmount;
        this.deadlineInMonths = deadlineInMonths;
        this.isApproved = isApproved;
        this.isIntegrated = isIntegrated;
        this.observations = observations;
    }

    public Proposal(BigDecimal requestedAmount, Integer deadlineInMonths, Boolean isApproved,
                    Boolean isIntegrated, String observations, User user) {
        this(requestedAmount, deadlineInMonths, isApproved, isIntegrated, observations);
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public Integer getDeadlineInMonths() {
        return deadlineInMonths;
    }

    public void setDeadlineInMonths(Integer deadlineInMonths) {
        this.deadlineInMonths = deadlineInMonths;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Boolean getIntegrated() {
        return isIntegrated;
    }

    public void setIntegrated(Boolean integrated) {
        isIntegrated = integrated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Proposal proposal = (Proposal) o;

        return Objects.equals(id, proposal.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

