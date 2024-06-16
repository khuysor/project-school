package com.huysor.projectschool.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String role;
    private String token;
}
