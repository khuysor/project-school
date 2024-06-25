package com.huysor.projectschool.entity.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role_Enum {
    OWNER(List.of(Permission.OWNER_DELETE, Permission.OWNER_EDIT, Permission.OWNER_READ,Permission.OWNER_WRITE)),
    ADMIN(List.of(Permission.ADMIN_DELETE, Permission.ADMIN_EDIT, Permission.ADMIN_READ,Permission.ADMIN_WRITE)) ,
    USER(Collections.emptyList()) ;

    private final List<Permission>permission;

    public List<SimpleGrantedAuthority> getAuthority(List<Permission> permissions) {
        var auth = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
                .collect(Collectors.toList());
        auth.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return auth;
    }

}
