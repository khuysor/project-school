package com.huysor.projectschool.Auth;

import lombok.Data;

@Data
public class UserRegister {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String role;
}
