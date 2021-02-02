package tech.talci.talcistorespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcistorespring.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByToken(String token);
}
