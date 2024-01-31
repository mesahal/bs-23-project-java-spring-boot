package com.example.demo.service;

import com.example.demo.config.JwtService;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.LoginMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public String registerUser(UserDTO userDTO) {
        // Check if the username is already taken
        if (userRepository.existsByUserName(userDTO.getUserName())) {
            // Username is not unique, return a response indicating failure
            return "Username already taken. Please choose a different username.";
        }
        User user = new User(userDTO.getUserId(),
                userDTO.getUserName(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return user.getUsername();
    }

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
            return new LoginMessage("Email not exits", false);
        }
    }
}