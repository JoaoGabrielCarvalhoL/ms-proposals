package br.com.joaogabriel.proposal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 11, unique = true)
    private String individualCertificate;

    @Column(nullable = false, length = 14)
    private String cellphone;

    @Column(nullable = false, scale = 2, precision = 10)
    private BigDecimal income;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Proposal> proposals;

    public User() {}

    public User(String firstName, String lastname, String individualCertificate, String cellphone, BigDecimal income) {
        this.firstName = firstName;
        this.lastName = lastname;
        this.individualCertificate = individualCertificate;
        this.cellphone = cellphone;
        this.income = income;
    }

    public User(String firstName, String lastname, String individualCertificate, String cellphone, BigDecimal income,
                List<Proposal> proposals) {
        this(firstName, lastname, individualCertificate, cellphone, income);
        this.proposals = proposals;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIndividualCertificate() {
        return individualCertificate;
    }

    public void setIndividualCertificate(String individualCertificate) {
        this.individualCertificate = individualCertificate;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
