package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for handling user information during registration.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    /**
     * The unique identifier for the user.
     */
    private int userId;

    /**
     * The username chosen by the user.
     */
    private String userName;

    /**
     * The email address of the user.
     */
    @Email(message = "Invalid Email")
    private String email;

    /**
     * The password chosen by the user, adhering to specific pattern constraints.
     * - At least one uppercase letter
     * - At least one digit
     * - At least one special character (one of @$!%*?&)
     * - Minimum length of 8 characters
     */
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Invalid Password Provided.\n" +
                    "Please ensure it meets the following criteria:\n" +
                    "- At least one uppercase letter\n" +
                    "- At least one digit\n" +
                    "- At least one special character (one of @$!%*?&)\n" +
                    "- Minimum length of 8 characters")
    private String password;
}
