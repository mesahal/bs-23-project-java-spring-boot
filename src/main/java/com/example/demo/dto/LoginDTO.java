package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String userName;
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}