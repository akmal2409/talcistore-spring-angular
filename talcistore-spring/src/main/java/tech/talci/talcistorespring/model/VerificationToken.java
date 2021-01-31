package tech.talci.talcistorespring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    private User user;
    private String token;
    private Instant expiryDate;

    public static VerificationTokenBuilder builder() {
        return new VerificationTokenBuilder();
    }

    public static class VerificationTokenBuilder {
        private Long id;
        private User user;
        private String token;
        private Instant expiryDate;

        private VerificationTokenBuilder() {}

        public VerificationTokenBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public VerificationTokenBuilder user(User user) {
            this.user = user;
            return this;
        }

        public VerificationTokenBuilder token(String token) {
            this.token = token;
            return this;
        }

        public VerificationTokenBuilder expiryDate(Instant expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public VerificationToken build() {
            return new VerificationToken(id, user, token, expiryDate);
        }
    }
}
