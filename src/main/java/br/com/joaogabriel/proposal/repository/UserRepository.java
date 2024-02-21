package br.com.joaogabriel.proposal.repository;

import br.com.joaogabriel.proposal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
