package tech.talci.talcistorespring.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.KeyStore;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private KeyStore keyStore;

    @PostConstruct
    void init() {
        try {
            keyStore
        }
    }
}
