package com.huysor.projectschool.dto.auth;

import com.huysor.projectschool.entity.user.Permission;
import lombok.Data;

import java.util.Set;

@Data
public class UserReq {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String role;
    private Set<Permission> permissions;



}