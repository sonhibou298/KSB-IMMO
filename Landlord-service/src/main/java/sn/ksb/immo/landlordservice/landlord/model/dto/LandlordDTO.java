package sn.ksb.immo.landlordservice.landlord.model.dto;

import lombok.Data;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
public class LandlordDTO {
    @NotBlank(message = "First name cannot be blank")
    @NotEmpty(message = "First name cannot be empty")
    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @NotEmpty(message = "Last name cannot be empty")
    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @Email(message ="Email is not valid")
    @NotBlank(message = "Email cannot be blank")
    @NotEmpty(message = "Email cannot be empty")
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @NotEmpty(message = "Phone number cannot be empty")
    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;

    @NotBlank(message = "Address cannot be blank")
    @NotEmpty(message = "Address cannot be empty")
    @NotNull(message = "Address cannot be null")
    private String address;

    @NotBlank(message = "Birthdate cannot be blank")
    @NotEmpty(message = "Birthdate cannot be empty")
    @NotNull(message = "Birthdate cannot be null")
    private String birthDate;

    @NotBlank(message = "Passport number cannot be blank")
    @NotEmpty(message = "Passport number cannot be empty")
    @NotNull(message = "Passport number cannot be null")
    private String passportNumber;

    @NotNull(message = "Identity card number cannot be null")
    @NotEmpty(message = "Identity card number cannot be empty")
    @NotBlank(message = "Identity card number cannot be blank")
    private String idCardNumber;

    @NotBlank(message = "Account number cannot be blank")
    @NotEmpty(message = "Account number cannot be empty")
    @NotNull(message = "Account number cannot be null")
    private String accountNumber;

    @NotEmpty(message = "Properties cannot be empty")
    @NotNull(message = "Properties cannot be null")
    private List<PropertyDTO> properties;
}
