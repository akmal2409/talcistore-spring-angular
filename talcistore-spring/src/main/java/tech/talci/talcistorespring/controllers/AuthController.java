package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.talci.talcistorespring.dto.RegisterRequest;
import tech.talci.talcistorespring.services.AuthService;

@RestController
@RequestMapping(AuthController.BASE_URL)
@RequiredArgsConstructor
public class AuthController {

    public static final String BASE_URL = "/api/auth";
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest) {
        authService.singUp(registerRequest);
        return new ResponseEntity<>("You were successfully registered", HttpStatus.CREATED);
    }
}
