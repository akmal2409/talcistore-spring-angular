package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.talcistorespring.controllers.AuthController;
import tech.talci.talcistorespring.dto.AuthenticationResponse;
import tech.talci.talcistorespring.dto.LoginRequest;
import tech.talci.talcistorespring.dto.RegisterRequest;
import tech.talci.talcistorespring.exceptions.AccountVerificationFailedException;
import tech.talci.talcistorespring.exceptions.AuthenticationFailedException;
import tech.talci.talcistorespring.exceptions.ResourceNotFoundException;
import tech.talci.talcistorespring.exceptions.TalciStoreException;
import tech.talci.talcistorespring.model.*;
import tech.talci.talcistorespring.repositories.*;
import tech.talci.talcistorespring.security.JwtProvider;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${talci.website.url}")
    private String websiteUrl;
    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final CustomerDetailsRepository customerDetailsRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final JwtProvider jwtProvider;

    @Transactional
    public void singUp(RegisterRequest registerRequest) {
        if (!isEmailAvailable(registerRequest.getEmail())
                || !isUsernameAvailable(registerRequest.getUsername())) {
            throw new AuthenticationFailedException("Username or email is already taken");
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
                .orElseThrow(() -> new AuthenticationFailedException("Activation token is invalid"));

        fetchAndEnableUser(verificationToken);
    }

    @Transactional
    public void fetchAndEnableUser(VerificationToken token) {
        User fetchedUser =
                userRepository.findUserByUsername(token.getUser().getUsername())
                        .orElseThrow(() -> new AuthenticationFailedException("User was not found. Invalid token"));

        if (fetchedUser.isEnabled()) {
            throw new AccountVerificationFailedException("Account has been already verified");
        }

        Role userRole = roleRepository.findRoleByName("USER")
                .orElseThrow(() -> new TalciStoreException("Roles must be defined by the administrator"));


        fetchedUser.setEnabled(true);
        fetchedUser.getRoles().add(userRole);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setUser(fetchedUser);
        fetchedUser.setCustomerDetails(customerDetails);

        ShoppingCart cart = new ShoppingCart();
        cart.setUser(fetchedUser);

        shoppingCartRepository.save(cart);
        customerDetailsRepository.save(customerDetails);
        userRepository.save(fetchedUser);
    }

    private void sendActivationEmail(User user, String token) {
        NotificationEmail email = NotificationEmail.builder()
                .subject("Thank you for registering at Talci Store! The biggest online retailer")
                .recipient(user.getEmail())
                .body("Welcome to Talci Store. We are the biggest online retailer in the entire Europe!" +
                        " Thank you for choosing us, we will not let you down." +
                        " To start your journey, please activate your account by clicking the link below: \n" +
                        websiteUrl + AuthController.BASE_URL + "/verify-account/" + token)
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

    @Transactional
    public void resendVerificationToken(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " was not found"));

        if (user.isEnabled()) {
           throw new AccountVerificationFailedException("Account has been already verified");
        }

        String token = generateVerificationToken(user);

        sendActivationEmail(user, token);
    }

    public AuthenticationResponse authenticate(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        return new AuthenticationResponse(jwt, authentication.getName());
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User was not found"));
    }
}
