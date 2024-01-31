package com.example.demo.response;

import lombok.Data;

@Data
public class RegistrationMessage {

    String message;
    Boolean status;

    public RegistrationMessage(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

}
