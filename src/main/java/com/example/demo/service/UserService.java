package com.example.demo.service;

import com.example.demo.config.JwtService;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.LoginMessage;
import com.example.demo.response.RegistrationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for handling user-related business logic.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    /**
     * The UserRepository for interacting with user data in the database.
     */
    private final UserRepository userRepository;

    /**
     * The PasswordEncoder for encoding and decoding user passwords.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * The JwtService for handling JWT token generation and validation.
     */
    private final JwtService jwtService;

    /**
     * Registers a new user in the system.
     *
     * @param userDTO The UserDTO containing user registration information.
     * @return A RegistrationMessage indicating the success or failure of the registration process.
     */
    public RegistrationMessage registerUser(UserDTO userDTO) {
        // Check if the username is already taken
        if (userRepository.existsByUserName(userDTO.getUserName())) {
            // Username is not unique, return a response indicating failure
            return new RegistrationMessage("Username already taken. Please choose a different username.", false);
        }

        User user = new User(userDTO.getUserId(),
                userDTO.getUserName(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
        return new RegistrationMessage("User Registration Successfully", true);
    }

    /**
     * Logs in a user and generates a JWT token upon successful authentication.
     *
     * @param loginDTO The LoginDTO containing user login credentials.
     * @return A LoginMessage containing the JWT token and indicating the success or failure of the login process.
     */
    public LoginMessage loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepository.findByUserName(loginDTO.getUserName());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepository.findOneByUserNameAndPassword(loginDTO.getUserName(), encodedPassword);
                if (user.isPresent()) {
                    String jwtToken = jwtService.generateToken(user.get());
                    return new LoginMessage(jwtToken, true);
                } else {
                    return new LoginMessage("Login failed", false);
                }
            } else {
                return new LoginMessage("Password not matched", false);
            }
        } else {
            return new LoginMessage("Username not exists", false);
        }
    }
}
