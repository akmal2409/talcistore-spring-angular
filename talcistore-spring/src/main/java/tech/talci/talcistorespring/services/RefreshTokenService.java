package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.talci.talcistorespring.exceptions.AuthenticationFailedException;
import tech.talci.talcistorespring.model.RefreshToken;
import tech.talci.talcistorespring.repositories.RefreshTokenRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public String generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        refreshTokenRepository.save(refreshToken);

        return refreshToken.getToken();
    }

    public void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new AuthenticationFailedException("Invalid refresh token"));
    }

    public void deleteByToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
