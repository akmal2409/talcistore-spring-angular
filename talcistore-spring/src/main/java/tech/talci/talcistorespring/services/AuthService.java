package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.talcistorespring.controllers.AuthController;
import tech.talci.talcistorespring.dto.RegisterRequest;
import tech.talci.talcistorespring.exceptions.AuthenticationException;
import tech.talci.talcistorespring.model.NotificationEmail;
import tech.talci.talcistorespring.model.User;
import tech.talci.talcistorespring.model.VerificationToken;
import tech.talci.talcistorespring.repositories.UserRepository;
import tech.talci.talcistorespring.repositories.VerificationTokenRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${talci.website.url}")
    private String websiteUrl;
    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Transactional
    public void singUp(RegisterRequest registerRequest) {
        if (!isEmailAvailable(registerRequest.getEmail())
                || !isUsernameAvailable(registerRequest.getUsername())) {
            throw new AuthenticationException("Username or email is already taken");
        }

        User createdUser = User.builder()
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .isEnabled(false)
                .isProfileComplete(false)
                .build();

        User savedUser = userRepository.save(createdUser);

        String token = generateVerificationToken(savedUser);

        sendActivationEmail(savedUser, token);
    }

    public void verifyAccount(String token) {
        VerificationToken verificationToken =
                tokenRepository.findVerificationTokenByToken(token)
                .orElseThrow(() -> new AuthenticationException("Activation token is invalid"));

        fetchAndEnableUser(verificationToken);
    }

    @Transactional
    public void fetchAndEnableUser(VerificationToken token) {
        User fetchedUser =
                userRepository.findUserByUsername(token.getUser().getUsername())
                        .orElseThrow(() -> new AuthenticationException("User was not found. Invalid token"));
        fetchedUser.setEnabled(true);
        userRepository.save(fetchedUser);
    }

    private void sendActivationEmail(User user, String token) {
        NotificationEmail email = NotificationEmail.builder()
                .subject("Thank you for registering at Talci Store! The biggest online retailer")
                .recipient(user.getEmail())
                .body("Welcome to Talci Store. We are the biggest online retailer in the entire Europe!" +
                        " Thank you for choosing us, we will not let you down." +
                        " To start your journey, please activate your account by clicking the link below: \n" +
                        websiteUrl + AuthController.BASE_URL + "/verify-account" + token)
                .build();

        mailService.sendMail(email);
    }

    private String generateVerificationToken(User user) {
        Optional<VerificationToken> tokenOptional = tokenRepository
                .findVerificationTokenByUser(user);

        VerificationToken token = tokenOptional.orElseGet(VerificationToken::new);

        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        tokenRepository.save(token);

        return token.getToken();
    }

    @Transactional(readOnly = true)
    public boolean isEmailAvailable(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);

        return optionalUser.isEmpty();
    }

    @Transactional(readOnly = true)
    public boolean isUsernameAvailable(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        return optionalUser.isEmpty();
    }
}
