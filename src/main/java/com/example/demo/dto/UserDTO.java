package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private String userName;
    @Email(message = "Invalid Email")
    private String email;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Invalid Password Provide\n" +
                    "At least one uppercase letter\n" +
                    "At least one digit\n" +
                    "At least one special character (one of @$!%*?&)\n" +
                    "Minimum length of 8 characters")
    private String password;

} //create getters and setters