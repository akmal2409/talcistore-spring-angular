package tech.talci.talcistorespring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    @NotEmpty(message = "Password is required")
    private String password;
}
