package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcistorespring.dto.AuthenticationResponse;
import tech.talci.talcistorespring.dto.LoginRequest;
import tech.talci.talcistorespring.dto.RefreshTokenRequest;
import tech.talci.talcistorespring.dto.RegisterRequest;
import tech.talci.talcistorespring.model.Role;
import tech.talci.talcistorespring.services.AuthService;
import tech.talci.talcistorespring.services.RefreshTokenService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singletonMap;

@RestController
@RequestMapping(AuthController.BASE_URL)
@RequiredArgsConstructor
public class AuthController {

    public static final String BASE_URL = "/api/auth";
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid RegisterRequest registerRequest) {
        authService.singUp(registerRequest);
        return new ResponseEntity<>("You were successfully registered", HttpStatus.CREATED);
    }

    @PostMapping("/seller-sign-up")
    public ResponseEntity<String> createSeller(@RequestBody @Valid RegisterRequest registerRequest) {
        authService.createSeller(registerRequest);

        return new ResponseEntity<>("You were successfully registered as seller", HttpStatus.CREATED);
    }

    @GetMapping("/verify-account/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account was successfully activated", HttpStatus.OK);
    }

    @GetMapping("/request-verification-code/{email}")
    public ResponseEntity<String> resendVerificationCode(@PathVariable String email) {
        authService.resendVerificationToken(email);
        return new ResponseEntity<>("Activation email with code was sent", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse authenticate(@RequestBody LoginRequest loginRequest) {
        return authService.authenticate(loginRequest);
    }

    @GetMapping("/check-username/")
    public Map<String, Boolean> checkUsernameAvailability(@RequestParam String username) {
        return singletonMap("available", authService.isUsernameAvailable(username));
    }

    @GetMapping("/check-email/")
    public Map<String, Boolean> checkEmailAvailability(@RequestParam String email) {
        return singletonMap("available", authService.isEmailAvailable(email));
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @GetMapping("/roles")
    public Set<Role> getRoles() {
        return authService.getAllRoles();
    }

    @PostMapping("/logout")
    public void logout(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService
                .deleteByToken(refreshTokenRequest.getRefreshToken());
    }
}
