package com.huysor.projectschool.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_EDIT("admin:edit"),
    OWNER_READ("owner:read"),
    OWNER_WRITE("owner:write"),
    OWNER_DELETE("owner:delete"),
    OWNER_EDIT("owner:edit"),
    MANGER_READ("manager:read"),
    MANGER_WRITE("manager:write"),
    MANGER_DELETE("manager:delete"),
    MANGER_EDIT("manager:edit");

    private final String permissionName;
}
