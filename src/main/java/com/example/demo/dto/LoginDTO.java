package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for handling user login information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    /**
     * The username of the user.
     */
    private String userName;

    /**
     * The password associated with the user's account.
     */
    private String password;
}
