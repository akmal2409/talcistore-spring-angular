package tech.talci.talcistorespring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;

    @NotEmpty(message = "Username is required")
    @Size.List({
            @Size(min = 4, message = "The username must be at least {min} characters long"),
            @Size(max = 20, message = "The username must be less than {max} characters long")
    })
    private String username;

    @Size(min = 4, message = "The password must be at least {min} characters long")
    private String password;

    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    private boolean isEnabled;

    private boolean isProfileComplete;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "details_id")
    private CustomerDetails customerDetails;
}