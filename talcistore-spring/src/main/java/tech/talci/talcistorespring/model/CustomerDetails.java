package tech.talci.talcistorespring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    private User user;

    @Column(name = "first_name")
    @NotEmpty(message = "First name is required")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Column(name = "birth_date")
    @NotNull(message = "Birth date is required")
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate birthDate;

    @Column(name = "phone_number")
    @NotEmpty(message = "Phone number is required")
    private String phoneNumber;

    @Column(name = "country")
    @NotEmpty(message = "Country is required")
    private String country;

    @Column(name = "city")
    @NotEmpty(message = "City is required")
    private String city;

    @Column(name = "postcode")
    @NotEmpty(message = "Post code is required")
    private String postCode;

    @Column(name = "address")
    @NotEmpty(message = "Address is required")
    private String address;

    public CustomerDetailsBuilder builder() {
        return new CustomerDetailsBuilder();
    }

    public static class CustomerDetailsBuilder {
        private Long id;
        private User user;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private String phoneNumber;
        private String country;
        private String city;
        private String postCode;
        private String address;

        private CustomerDetailsBuilder() {}

        public CustomerDetailsBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CustomerDetailsBuilder user(User user) {
            this.user = user;
            return this;
        }

        public CustomerDetailsBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerDetailsBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerDetailsBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public CustomerDetailsBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CustomerDetailsBuilder country(String country) {
            this.country = country;
            return this;
        }

        public CustomerDetailsBuilder city(String city) {
            this.city = city;
            return this;
        }

        public CustomerDetailsBuilder postCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public CustomerDetailsBuilder address(String address) {
            this.address = address;
            return this;
        }

        public CustomerDetails build() {
            return new CustomerDetails(
                    id, user, firstName, lastName, birthDate,
                    phoneNumber, country, city, postCode, address
            );
        }
    }
}
