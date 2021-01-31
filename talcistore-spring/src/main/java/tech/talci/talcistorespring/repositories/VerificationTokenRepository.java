package tech.talci.talcistorespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcistorespring.model.User;
import tech.talci.talcistorespring.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findVerificationTokenByToken(String token);

    Optional<VerificationToken> findVerificationTokenByUser(User user);
}
