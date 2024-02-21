package br.com.joaogabriel.proposal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProposalPostRequest(
        @NotBlank(message = "The field firstName cannot be empty!")
        String firstName,

        @NotBlank(message = "The field lastName cannot be empty!")
        String lastName,

        @NotBlank(message = "The field lastName cannot be empty!")
        String cellphone,

        @CPF
        @JsonProperty("CPF")
        String individualCertificate,

        @NotNull
        BigDecimal income,

        @NotNull
        Integer deadlineInMonths,

        @NotNull
        BigDecimal requestedAmount
) implements Serializable {
}
