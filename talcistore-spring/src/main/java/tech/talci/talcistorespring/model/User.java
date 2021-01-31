package tech.talci.talcistorespring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotEmpty(message = "Username is required")
    @Size.List({
            @Size(min = 4, message = "The username must be at least {min} characters long"),
            @Size(max = 20, message = "The username must be less than {max} characters long")
    })
    private String username;

    @Column(name = "password")
    @Size(min = 4, message = "The password must be at least {min} characters long")
    private String password;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "is_profile_complete")
    private boolean isProfileComplete;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "detail_id", referencedColumnName = "id")
    private CustomerDetails customerDetails;

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        private String username;
        private String password;
        private String email;
        private boolean isEnabled;
        private boolean isProfileComplete;
        private CustomerDetails customerDetails;

        private UserBuilder() {}

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder isEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        public UserBuilder isProfileComplete(boolean isProfileComplete) {
            this.isProfileComplete = isProfileComplete;
            return this;
        }

        public UserBuilder customerDetails(CustomerDetails customerDetails) {
            this.customerDetails = customerDetails;
            return this;
        }

        public User build() {
            return new User(id, username, password, email,
                    isEnabled, isProfileComplete, customerDetails);
        }
    }

}
