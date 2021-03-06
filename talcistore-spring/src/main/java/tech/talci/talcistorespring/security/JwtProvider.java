package tech.talci.talcistorespring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tech.talci.talcistorespring.exceptions.TalciStoreException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProvider {

    private KeyStore keyStore;

    @Value("${jwt.expiration.time}")
    private Long jwtExpirationTime;

    @PostConstruct
    void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/talcistore.jsk");
            keyStore.load(resourceAsStream, "01041955".toCharArray());
        } catch (IOException | NoSuchAlgorithmException | KeyStoreException | CertificateException e) {
            throw new TalciStoreException("There was a problem loading the keystore");
        }
    }

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .signWith(getPrivateKey())
                .setSubject(principal.getUsername())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationTime)))
                .compact();
    }

    public String generateTokenWithUsername(String username) {
        return Jwts.builder()
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationTime)))
                .setSubject(username)
                .compact();
    }

    public boolean validateToken(String jwt) {
        parser().setSigningKey(getPublicKey())
                .parseClaimsJws(jwt);
        return true;
    }

    public String getUsernameFromJwt(String jwt) {
        Claims claim = parser().setSigningKey(getPublicKey())
                .parseClaimsJws(jwt)
                .getBody();
        return claim.getSubject();
    }

    public Long getJwtExpirationTime() {
        return this.jwtExpirationTime;
    }

    private Key getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("talcistore", "01041955".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new TalciStoreException("There was a problem loading the private key");
        }
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("talcistore").getPublicKey();
        } catch (KeyStoreException e) {
            throw new TalciStoreException("There was an error loading public key");
        }
    }


}
