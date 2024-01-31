package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.LoginMessage;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private String generateJwt(LoginDTO loginDTO) {
        // Use the jjwt library to build the JWT
        JwtBuilder jwtBuilder = Jwts.builder();

        // Set the subject (typically the username) and the authorities (roles)
        jwtBuilder.setSubject(loginDTO.getUserName());
//        jwtBuilder.claim("authorities", loginDTO.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList()));

        // Set the expiration time (e.g., 1 hour)
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 3600000));

        // Sign the JWT with a secret key
        jwtBuilder.signWith(SignatureAlgorithm.HS512, "jwtSecret");

        // Build the JWT as a string
        return jwtBuilder.compact();
    }

    public String addUser(UserDTO userDTO) {
        User user = new User(userDTO.getUserId(),
                userDTO.getUserName(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return user.getUserName();
    }

    public LoginMessage loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = (User) userRepository.findByUserName(loginDTO.getUserName());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepository.findOneByUserNameAndPassword(loginDTO.getUserName(), encodedPassword);
                if (user.isPresent()) {

                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        } else {
            return new LoginMessage("Email not exits", false);
        }
    }
}