package com.huysor.projectschool.dto.auth;

import lombok.Data;

import java.util.List;
@Data
public class UpdatePermissionsRequestDTO {
    private String username;
    private String role;
    private List<String> permissions;
}
