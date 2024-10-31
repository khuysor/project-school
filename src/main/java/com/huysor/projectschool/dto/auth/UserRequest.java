package com.huysor.projectschool.dto.auth;

import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String role;
    private List<String> permission;
}
