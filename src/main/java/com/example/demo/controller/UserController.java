package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.response.LoginMessage;
import com.example.demo.response.RegistrationMessage;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling user-related operations.
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    /**
     * Handles the registration of a new user.
     *
     * @param userDTO The data transfer object containing user information.
     * @return ResponseEntity with the registration message.
     */
    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userDTO) {
        RegistrationMessage registrationMessage = userService.registerUser(userDTO);
        return ResponseEntity.ok(registrationMessage);
    }

    /**
     * Handles user login.
     *
     * @param loginDTO The data transfer object containing login credentials.
     * @return ResponseEntity with the login message.
     */
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginMessage loginMessage = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }
}
