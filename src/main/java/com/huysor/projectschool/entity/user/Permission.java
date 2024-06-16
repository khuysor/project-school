package com.huysor.projectschool.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public enum Permission {
    READ("permission:read"),
    WRITE("permission:write"),
    UPDATE("permission:update"),
    DELETE("permission:delete");
    private String permission;
}
