package com.banking.customer.service.DTO;

import com.banking.customer.service.Validation.PasswordMatches;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class CustomerRequestDTO {

    @NotBlank(message = "first_name is required")
    private String firstName;

    @NotBlank(message = "last_name is required")
    private String lastName;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be a positive number")
    private Integer age;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one number"
    )
    @NotBlank(message = "Password is required")
    private String password;

    private String confirmPassword;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String contactNo;

    @NotBlank(message = "address is required")
    private String address;

}
