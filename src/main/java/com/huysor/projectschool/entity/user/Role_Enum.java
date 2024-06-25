package com.huysor.projectschool.entity.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role_Enum {
    OWNER(Set.of(Permission.OWNER_DELETE, Permission.OWNER_EDIT, Permission.OWNER_READ,Permission.OWNER_WRITE,
            Permission.ADMIN_DELETE,Permission.ADMIN_READ,Permission.ADMIN_WRITE,Permission.ADMIN_EDIT,Permission.MANGER_DELETE,Permission.MANGER_WRITE,Permission.MANGER_READ)),
    MANAGER(Set.of(  Permission.ADMIN_DELETE,Permission.ADMIN_READ,Permission.ADMIN_WRITE,Permission.ADMIN_EDIT,Permission.MANGER_DELETE,Permission.MANGER_WRITE,Permission.MANGER_READ)),
    ADMIN(Set.of(Permission.ADMIN_DELETE, Permission.ADMIN_EDIT, Permission.ADMIN_READ,Permission.ADMIN_WRITE)) ,
    USER(Collections.emptySet()) ;

    private final Set<Permission> permission;

    public Set<SimpleGrantedAuthority> getAuthority(Set<Permission> permissions) {
        var auth = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
                .collect(Collectors.toSet());
        auth.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return auth;
    }

}
