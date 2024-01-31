package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int userId;
    private String userName;
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(int userid, String username, String email, String password) {
        this.userId = userid;
        this.userName = username;
        this.email = email;
        this.password = password;
    }
} //create getters and setters