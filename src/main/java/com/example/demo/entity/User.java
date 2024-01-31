package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entity class representing a User in the system.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User implements UserDetails {

    /**
     * The unique identifier for the user.
     */
    @Id
    @Column(name = "user_id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    /**
     * The username chosen by the user.
     */
    @Column(name = "user_name", length = 255)
    private String userName;

    /**
     * The email address of the user.
     */
    @Column(name = "email", length = 255)
    private String email;

    /**
     * The password associated with the user's account.
     */
    @Column(name = "password", length = 255)
    private String password;

    /**
     * The role assigned to the user.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Constructor for creating a User with essential information.
     */
    public User(int userId, String userName, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the authorities granted to the user (in this case, their role).
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Returns the password associated with the user's account.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username chosen by the user.
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * Indicates whether the user's account has not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is not locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) are not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is currently enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
