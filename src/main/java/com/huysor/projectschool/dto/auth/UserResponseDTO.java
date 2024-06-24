package com.huysor.projectschool.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String token;
    private String role;
}
