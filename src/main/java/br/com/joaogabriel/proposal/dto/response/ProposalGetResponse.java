package br.com.joaogabriel.proposal.dto.response;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record ProposalGetResponse(
        UUID id,
        String firstName,
        String lastName,
        String cellphone,
        String individualCertificate,
        BigDecimal income,
        BigDecimal requestedAmount,
        Integer deadlineInMonths,
        Boolean isApproved,
        String observations
) implements Serializable {
}
