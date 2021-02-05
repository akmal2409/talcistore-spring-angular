package tech.talci.talcistorespring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailsDto {

    private Long id;
    private Long userId;
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Birth date is required")
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate birthDate;
    @NotEmpty(message = "Phone is required")
    private String phoneNumber;
    @NotEmpty(message = "Country is required")
    private String country;
    @NotEmpty(message = "City is required")
    private String city;
    @NotEmpty(message = "Post code is required")
    private String postCode;
    @NotEmpty(message = "Address is required")
    private String address;
}
