package com.r2s.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
